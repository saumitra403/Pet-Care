package com.spapp.universalpetcare.controller;

import com.spapp.universalpetcare.exception.ResourceNotFoundException;
import com.spapp.universalpetcare.model.Appointment;
import com.spapp.universalpetcare.request.AppointmentUpdateRequest;
import com.spapp.universalpetcare.response.ApiResponse;
import com.spapp.universalpetcare.service.appointment.AppointmentService;
import com.spapp.universalpetcare.utils.FeedBackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
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

    @PostMapping("/book-appointment")
    public CompletableFuture<ResponseEntity<ApiResponse>> bookAppointment(@RequestBody Appointment appointment, @RequestParam Long senderId, @RequestParam Long recipientId) {
        return appointmentService.bookAppointmentAsync(appointment, senderId, recipientId).handle((savedAppointment, ex) -> {
            if (ex == null) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ApiResponse("Appointment scheduled successfully.", savedAppointment));
            } else {
                if (ex.getCause() instanceof ResourceNotFoundException) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiResponse(ex.getMessage(), null));
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ApiResponse(ex.getMessage(), null));
                }
            }
        });
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.FOUND, appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/appointment-number/{appointmentNo}")
    public ResponseEntity<ApiResponse> getAppointmentByNo(@PathVariable String appointmentNo) {
        try {
            Appointment appointment = appointmentService.getAppointmentByNo(appointmentNo);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.FOUND, appointment));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<ApiResponse> deleteAppointmentById(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/appointment/{id}")
    public ResponseEntity<ApiResponse> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateRequest request) {
        try {
            Appointment appointment = appointmentService.updateAppointment(id, request);
            return ResponseEntity.ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, appointment));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
