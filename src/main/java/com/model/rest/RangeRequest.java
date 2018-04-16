package com.model.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "range")
@Data
public class RangeRequest {

    @JacksonXmlProperty(localName= "from")
    @JsonProperty(value = "from")
    private String fromTime;

    @JacksonXmlProperty(localName= "to")
    @JsonProperty(value = "to")
    private String toTime;
}
