package com.cloudsre.services.transport_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "time")
@ApiModel(description = "details about line arrival time ")
public class Time {
    @Id
    @GeneratedValue()
    @ApiModelProperty(notes = "The database generated Time ID")
    private Long id;
    @ApiModelProperty(notes = "line Id ")
    private Long lineId;
    @ApiModelProperty(notes = "stop Id")
    private Long stopId;
    @ApiModelProperty(notes = "regular arrival time for Line")
    private Timestamp time;

    public Time(Long lineId, Long stopId, Timestamp time) {
        this.lineId = lineId;
        this.stopId = stopId;
        this.time = time;

    }
    public  Time(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStopId() {
        return stopId;
    }

    public Date getTime() {
        return time;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

   
}