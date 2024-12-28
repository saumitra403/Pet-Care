package com.spapp.universalpetcare.service.appointment;

import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.request.AppointmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AppointmentService implements IAppointmentService {

    @Override
    public Appointment createAppointment(Appointment appointment, Long sender, Long recipient) {
        return null;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return List.of();
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentRequest request) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {

    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return null;
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return null;
    }
}
