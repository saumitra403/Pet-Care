package com.spapp.universalpetcare.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentUpdateRequest {
    private String appointmentDate;
    private String appointmentTime;
    private String reason;

}
