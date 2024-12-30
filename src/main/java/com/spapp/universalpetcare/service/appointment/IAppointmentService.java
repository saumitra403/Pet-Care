package com.spapp.universalpetcare.service.appointment;

import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.request.AppointmentRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IAppointmentService {
    Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, AppointmentRequest request);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByNo(String appointmentNo);
    CompletableFuture<Appointment> bookAppointmentAsync(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time);
}
