package com.spapp.universalpetcare.service.appointment;

import com.spapp.universalpetcare.enums.AppointmentStatus;
import com.spapp.universalpetcare.exception.ResourceNotFoundException;
import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.model.User;
import com.spapp.universalpetcare.repository.AppointmentRepository;
import com.spapp.universalpetcare.repository.UserRepository;
import com.spapp.universalpetcare.request.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final ApplicationContext applicationContext;

    @Override
    @Transactional
    public Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time) {
        Optional<User> sender = userRepository.findById(senderId);
        Optional<User> recipient = userRepository.findById(recipientId);
        if (sender.isPresent() && recipient.isPresent()) {
            Optional<Appointment> existingAppointment = appointmentRepository.findByDateAndTime(date, time);
            if (existingAppointment.isPresent()) {
                throw new IllegalArgumentException("An appointment already exists for the selected date and time");
            }
            appointment.setPatient(sender.get());
            appointment.setVeterinarian(recipient.get());
            appointment.setAppointmentNo();
            appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException("Sender or recipient not found");
    }

    @Override
    public void bookAppointmentAsync(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time) {
        executorService.submit(() -> {
            try {
                AppointmentService self = applicationContext.getBean(AppointmentService.class);
                self.createAppointment(appointment, senderId, recipientId, date, time);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }


    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.findById(id).ifPresent(appointmentRepository::delete);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo);
    }
}
