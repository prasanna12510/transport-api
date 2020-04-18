package com.cloudsre.services.transport_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stop")
public class Stop {
    @Id
    @GeneratedValue()
    private Long id;
    private Long stopId;
    private int x;
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