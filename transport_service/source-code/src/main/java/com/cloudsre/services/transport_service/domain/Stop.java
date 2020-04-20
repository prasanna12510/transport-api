package com.cloudsre.services.transport_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "stop")
@ApiModel(description = "stop details ")
public class Stop {
    @Id
    @GeneratedValue()
    @ApiModelProperty(notes = "The database generated stop ID")
    private Long id;
    @ApiModelProperty(notes = "Id for given stop")
    private Long stopId;
    @ApiModelProperty(notes = "X coordinfate for given stop")
    private int x;
    @ApiModelProperty(notes = "Y coordinate for given stop")
    private int y;

    public Stop(Long stopId, int x, int y) {
        this.x = x;
        this.stopId = stopId;
        this.y = y;

    }
    public Stop(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}