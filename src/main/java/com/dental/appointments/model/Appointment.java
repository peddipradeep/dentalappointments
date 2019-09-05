package com.dental.appointments.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    public Appointment() {

    }

    public Appointment(Integer id, Integer dentistId, Integer patientId, String startDate, String endDate) {
        super();
        this.id = id;
        this.patientId = patientId;
        this.dentistId = dentistId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Id
    @GeneratedValue
    private Integer id;
    private Integer patientId;
    private Integer dentistId;
    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String startDate;
    // @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getDentistId() {
        return dentistId;
    }

    public void setDentistId(Integer dentistId) {
        this.dentistId = dentistId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Appointment [id=" +id +", patientId=" + patientId + ", dentistId=" + dentistId + ", startDate=" + startDate + ", endDate=" + endDate + "]";
    }
}
