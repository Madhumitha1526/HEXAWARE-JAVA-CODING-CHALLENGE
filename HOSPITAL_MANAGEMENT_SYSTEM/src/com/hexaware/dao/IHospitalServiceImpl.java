package com.hexaware.dao;

import com.hexaware.model.Appointment;
import java.util.ArrayList;
import java.util.List;

public class IHospitalServiceImpl implements IHospitalService {
    private List<Appointment> appointments = new ArrayList<>();

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointments.stream()
                .filter(appointment -> appointment.getAppointmentId() == appointmentId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Appointment> getAppointmentsForPatient(int patientId) {
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId() == patientId) {
                patientAppointments.add(appointment);
            }
        }
        return patientAppointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(int doctorId) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId() == doctorId) {
                doctorAppointments.add(appointment);
            }
        }
        return doctorAppointments;
    }

    @Override
    public List<Appointment> getAppointmentsForDoctorOnDate(int doctorId, java.sql.Date date) {
        List<Appointment> doctorAppointmentsOnDate = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId() == doctorId && appointment.getAppointmentDate().equals(date)) {
                doctorAppointmentsOnDate.add(appointment);
            }
        }
        return doctorAppointmentsOnDate;
    }

    @Override
    public boolean scheduleAppointment(Appointment appointment) {
        return appointments.add(appointment);
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId() == appointment.getAppointmentId()) {
                appointments.set(i, appointment);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean cancelAppointment(int appointmentId) {
        return appointments.removeIf(appointment -> appointment.getAppointmentId() == appointmentId);
    }
}
