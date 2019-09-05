package com.dental.appointments.dao;

import com.dental.appointments.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {

    List<Appointment> getByDentistId(Integer dentistId);
}
