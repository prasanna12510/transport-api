package com.cloudsre.services.transport_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "line")
@ApiModel(description = "Details about Lines ")
public class Line {
    @Id
    @GeneratedValue()
    @ApiModelProperty(notes = "The database generated Line ID")
    private Long id;
    @ApiModelProperty(notes = "LineId")
    private Long lineId;
    @ApiModelProperty(notes = "Linename")
    private String lineName;

    public Line(Long lineId, String linenName) {
        this.lineId = lineId;
        this.lineName = linenName;

    }
    public Line(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }
    

}