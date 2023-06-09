package com.example.rafdnevnjak.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Duty implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
    private String description;
    private DutyPriority priority;
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duty duty = (Duty) o;
        return Objects.equals(id, duty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setPriority(DutyPriority priority) {
        this.priority = priority;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Duty(LocalTime startTime, LocalTime endTime, String title, String description, DutyPriority priority) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public Duty() {

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DutyPriority getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        if (priority==1)this.priority = DutyPriority.LOW;
        else if(priority==2) this.priority =DutyPriority.MID;
        else this.priority = DutyPriority.HIGH;
    }
}
