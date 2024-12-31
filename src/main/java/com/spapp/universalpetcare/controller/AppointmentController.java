package com.spapp.universalpetcare.controller;

import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.response.ApiResponse;
import com.spapp.universalpetcare.service.appointment.AppointmentService;
import com.spapp.universalpetcare.utils.FeedBackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();

            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.FOUND, appointments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    public ResponseEntity<ApiResponse> bookAppointment(@RequestBody Appointment appointment, @RequestParam Long senderId, @RequestParam Long recipientId) {
        try {

        } catch (Exception e) {

        }
    }
}
