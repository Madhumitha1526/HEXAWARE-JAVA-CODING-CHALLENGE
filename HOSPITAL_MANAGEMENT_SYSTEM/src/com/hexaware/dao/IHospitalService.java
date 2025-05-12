package com.hexaware.dao;

import java.util.List;
import com.hexaware.model.Appointment;

public interface IHospitalService {
    Appointment getAppointmentById(int appointmentId);
    List<Appointment> getAppointmentsForPatient(int patientId);
    List<Appointment> getAppointmentsForDoctor(int doctorId);
    List<Appointment> getAppointmentsForDoctorOnDate(int doctorId, java.sql.Date date); // New method
    boolean scheduleAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean cancelAppointment(int appointmentId);
}
