package com.spapp.universalpetcare.utils;

public class UrlMapping {
    public static final String API = "/api/v1";
    public static final String USERS = API+"/users";
    public static final String UPDATE_USER = "/update/{userId}";
    public static final String REGISTER_USER = "/register";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String DELETE_USER_BY_ID = "/delete/{userId}";
    public static final String GET_ALL_USERS = "/all-users";

    /*======================= Start Appointment API =========================*/

    public static final String APPOINTMENTS = API+"/appointments";
    public static final String ALL_APPOINTMENTS = "/all";
    public static final String BOOK_APPOINTMENTS = "/book-appointment";
    public static final String GET_APPOINTMENT_BY_ID = "/appointment/{id}";
    public static final String GET_APPOINTMENT_BY_NO = "/appointment-number/{appointmentNo}";
    public static final String DELETE_APPOINTMENT = "/appointment/{id}";
    public static final String UPDATE_APPOINTMENT = "/appointment/{id}";

    /*======================= End Appointment API =========================*/
}
