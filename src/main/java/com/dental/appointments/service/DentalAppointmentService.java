package com.dental.appointments.service;

import com.dental.appointments.dao.AppointmentRepository;
import com.dental.appointments.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class DentalAppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAppointments()
    {

        List appointments = new ArrayList<Appointment>();
        appointmentRepository.findAll().forEach(it -> appointments.add(it));

        return appointments;
    }

    public Appointment getAppointmentById(String id)
    {
        // Production code should never have print statements.
        System.out.println(">>> DentalAppointmentService: getAppointmentById(id) ");
        return appointmentRepository.findById(Integer.parseInt(id)).get();
    }

    public List<Appointment> getAppointmentByDentistId(String id)
    {
        return appointmentRepository.getByDentistId(Integer.parseInt(id));
    }

    public void addAppointment(Appointment appointment) throws Exception{

        List<Appointment> appointmentsByDentist = appointmentRepository.getByDentistId(appointment.getDentistId());

        boolean error = false;
        String errorMsg = "";
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        try { // Parse the String date to LocalDateTime. If the date in invalid then error will be thrown with actual error message.
            startDate = LocalDateTime.parse(appointment.getStartDate());
            endDate = LocalDateTime.parse(appointment.getEndDate());
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Start and End dates have to be valid");
        }
        long appointmentDuration = startDate.until(endDate, ChronoUnit.MINUTES);

        if(startDate.isBefore(LocalDateTime.now()) || endDate.isBefore(LocalDateTime.now())){
            error = true;
            errorMsg = "Appointment start and end dates must be in future";
        }
        else if(endDate.isBefore(startDate)){
            error = true;
            errorMsg = "Appointment start should be before end date";
        }
        else if(appointmentDuration<30 ) {
            error = true;
            errorMsg = "Appointment duration has to be atleast 30 minutes";
        }
        else if(appointmentsByDentist.size()!=0 && appointmentsByDentist.stream().anyMatch(
                it -> LocalDateTime.parse(it.getStartDate()).equals(LocalDateTime.parse(appointment.getStartDate())))
        ) {
            error = true;
            errorMsg = "Dentist already has an appointment at "+appointment.getStartDate()+". Please select another start date";
        }
        else {
            System.out.println("======== Saving appointment ==========");
            appointmentRepository.save(appointment);
        }

        if(error)
            throw new Exception ("Please check appointment details. "+errorMsg);
    }
}
