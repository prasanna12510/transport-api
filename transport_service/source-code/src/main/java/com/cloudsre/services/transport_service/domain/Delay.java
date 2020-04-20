package com.cloudsre.services.transport_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "delay")
@ApiModel(description = "Delay associated with lines. ")
public class Delay {
    @Id
    @GeneratedValue()
    @ApiModelProperty(notes = "The database generated Delay ID")
    private Long id;
    @ApiModelProperty(notes = "Line delay status in minutes")
    private Long delay;
    @ApiModelProperty(notes = "name of Line Delayed")
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