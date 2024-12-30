package com.spapp.universalpetcare.repository;

import com.spapp.universalpetcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Appointment findByAppointmentNo(String appointmentNo);

    Optional<Appointment> findByDateAndTime(LocalDate date, LocalTime time);
}
