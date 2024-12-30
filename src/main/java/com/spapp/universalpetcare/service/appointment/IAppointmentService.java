package com.spapp.universalpetcare.service.appointment;

import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.request.AppointmentRequest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IAppointmentService {
    Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(Long id, AppointmentRequest request);
    void deleteAppointment(Long id);
    Appointment getAppointmentById(Long id);
    Appointment getAppointmentByNo(String appointmentNo);
    void bookAppointmentAsync(Appointment appointment, Long senderId, Long recipientId, LocalDate date, LocalTime time);
}
