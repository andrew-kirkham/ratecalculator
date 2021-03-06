package com.ratecalculator.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "range")
@XmlAccessorType(XmlAccessType.FIELD)
public class RangeRequest {

    @XmlElement(name = "from")
    @JsonProperty(value = "from")
    private String fromTime = "";

    @XmlElement(name = "to")
    @JsonProperty(value = "to")
    private String toTime = "";
}
