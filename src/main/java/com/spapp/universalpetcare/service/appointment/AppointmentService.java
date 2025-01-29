package com.spapp.universalpetcare.service.appointment;

import com.spapp.universalpetcare.enums.AppointmentStatus;
import com.spapp.universalpetcare.exception.ResourceNotFoundException;
import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.AppointmentRepository;
import com.spapp.universalpetcare.repository.UserRepository;
import com.spapp.universalpetcare.request.AppointmentUpdateRequest;
import com.spapp.universalpetcare.utils.FeedBackMessage;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    private final ApplicationContext applicationContext;

    private final RedissonClient redissonClient;
    @Override
    @Transactional
    public Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> recipient = userRepository.findById(recipientId);
        if (sender.isPresent() && recipient.isPresent()) {
            Optional<Appointment> existingAppointment = appointmentRepository.findByAppointmentDateAndAppointmentTime(date, time);
            if (existingAppointment.isPresent()) {
                throw new IllegalArgumentException("An appointment already exists for the selected date and time");
            }
            appointment.addPatient(sender.get());
            appointment.addVeterinarian(recipient.get());
            appointment.setAppointmentNo();
            appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException(FeedBackMessage.SENDER_RECIPIENT_NOT_FOUND);
    }

    @Override
    public CompletableFuture<Appointment> bookAppointmentAsync(Appointment appointment, Long senderId, Long recipientId) {
        LocalDate date = appointment.getAppointmentDate();
        LocalTime time = appointment.getAppointmentTime();
        return CompletableFuture.supplyAsync(() -> {
            RLock lock = redissonClient.getLock("appointment-slot:" + date + ":" + time);
            try {
                // Attempt to acquire the lock with a wait time and lease time
                if (lock.tryLock(10, 5, TimeUnit.SECONDS)) {
                    try {
                        AppointmentService self = applicationContext.getBean(AppointmentService.class);
                        // Call createAppointment and return the result
                        return self.createAppointment(appointment, senderId, recipientId, date, time);
                    } finally {
                        lock.unlock();
                    }
                } else {
                    throw new IllegalStateException("Could not acquire lock for booking appointment");
                }
            } catch (InterruptedException e) {
                throw new IllegalStateException("Interrupted while trying to acquire lock for booking appointment", e);
            }
        });
    }


    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentUpdateRequest request) {
        Appointment existingAppointment = getAppointmentById(id);
        if(!Objects.equals(existingAppointment.getStatus(), AppointmentStatus.WAITING_FOR_APPROVAL)) {
            throw new IllegalStateException(FeedBackMessage.ALREADY_APPROVED);
        }
        existingAppointment.setAppointmentDate(LocalDate.parse(request.getAppointmentDate()));
        existingAppointment.setAppointmentTime(LocalTime.parse(request.getAppointmentTime()));
        existingAppointment.setReason(request.getReason());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id).ifPresentOrElse(appointmentRepository::delete, () -> {
            throw new ResourceNotFoundException(FeedBackMessage.NOT_FOUND);
        });
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(FeedBackMessage.NOT_FOUND));
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo);
    }
}
