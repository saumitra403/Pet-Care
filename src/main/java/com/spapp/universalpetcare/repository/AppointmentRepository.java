package com.spapp.universalpetcare.repository;

import com.spapp.universalpetcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
