package io.pivotal.pal.tracker;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class TimeEntry {

    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(){
    }

    public TimeEntry(
            long id,
            long projectId,
            long userId,
            LocalDate date,
            int hours
    ){
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry(
            long projectId,
            long userId,
            LocalDate date,
            int hours
    ){
        this.id = 0;//UUID.randomUUID()
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String toJsonString() {
        try {
            ObjectMapper om = new ObjectMapper();
            ObjectNode node = om.createObjectNode();
            node.put("id", this.id);
            node.put("projectId", this.projectId);
            node.put("userId", this.userId);
            //node.put("date", this.date.format(DateTimeFormatter.ofPattern("dd/mm/yyyy")));
            node.put("hours", this.hours);
            return om.writeValueAsString(node);
        } catch (Exception exp){
            exp.printStackTrace();
            return "";
        }
    }

    @Override
    public boolean equals(Object entry){
        if (!(entry instanceof TimeEntry)){
            return false;
        }
        if ((this.projectId == ((TimeEntry)entry).projectId) && (this.userId == ((TimeEntry)entry).userId)
                && (this.hours == ((TimeEntry)entry).hours) && (this.date.equals(((TimeEntry)entry).date)))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, projectId, userId, date, hours);
    }
}
