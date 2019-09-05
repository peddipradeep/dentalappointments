package com.dental.appointments.controller;

import java.net.URI;
import java.util.List;

import com.dental.appointments.model.Appointment;
import com.dental.appointments.service.DentalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(path = "/dentalAppointments")
public class DentalAppointmentController
{
    @Autowired
    private DentalAppointmentService dentalAppointmentService;

    @GetMapping(path="/", produces = "application/json")
    public List<Appointment> getAllAppointments()
    {
        return dentalAppointmentService.getAppointments();

    }

    @GetMapping(path="/list", produces = "application/json")
    public List<Appointment> getAppointments()
    {
        return dentalAppointmentService.getAppointments();

    }

    @GetMapping(path="/{id}", produces = "application/json")
    public Appointment getAppointmentById(@PathVariable String id)
    {

        return dentalAppointmentService.getAppointmentById(id);
    }

    @GetMapping(path="/dentist/{id}", produces = "application/json")
    public List<Appointment> getAppointmentByDentistId(@PathVariable String id)
    {
        return dentalAppointmentService.getAppointmentByDentistId(id);
        // return dentistAppointmentDao.getAppointmentById(id);
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addAppointment(@RequestBody Appointment appointment) throws Exception
    {       

        dentalAppointmentService.addAppointment(appointment);

        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(appointment.getPatientId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
    }
}
