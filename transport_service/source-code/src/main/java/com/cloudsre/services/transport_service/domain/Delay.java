package com.cloudsre.services.transport_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delay")
public class Delay {
    @Id
    @GeneratedValue()
    private Long id;
    private Long delay;
    private String lineName;

    public Delay(String lineName, Long delay) {
        this.delay = delay;
        this.lineName = lineName;

    }

    public Delay() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String linenName) {
        this.lineName = linenName;
    }

}