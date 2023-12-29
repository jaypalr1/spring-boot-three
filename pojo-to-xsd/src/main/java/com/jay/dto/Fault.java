package com.jay.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import lombok.Getter;
import lombok.Setter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Fault", propOrder = {
    "faultcode",
    "faultstring",
    "faultactor",
    "detail"
})
public class Fault {

  @Getter
  @Setter
  @XmlElement(required = true)
  protected QName faultcode;

  @Getter
  @Setter
  @XmlElement(required = true)
  protected String faultstring;

  @Getter
  @Setter
  @XmlSchemaType(name = "anyURI")
  protected String faultactor;

  @Getter
  @Setter
  protected Detail detail;
}
