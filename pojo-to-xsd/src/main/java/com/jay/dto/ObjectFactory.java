package com.jay.dto;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import lombok.NoArgsConstructor;

@XmlRegistry
@NoArgsConstructor
public class ObjectFactory {

  public static final String URL = "http://schemas.xmlsoap.org/soap/envelope/";

  private static final QName _Envelope_QNAME = new QName(URL, "Envelope");
  private static final QName _Header_QNAME = new QName(URL, "Header");
  private static final QName _Body_QNAME = new QName(URL, "Body");
  private static final QName _Fault_QNAME = new QName(URL, "Fault");

  public Envelope createEnvelope() {
    return new Envelope();
  }

  public Header createHeader() {
    return new Header();
  }

  public Body createBody() {
    return new Body();
  }

  public Fault createFault() {
    return new Fault();
  }

  public Detail createDetail() {
    return new Detail();
  }

  @XmlElementDecl(namespace = URL, name = "Envelope")
  public JAXBElement<Envelope> createEnvelope(Envelope value) {
    return new JAXBElement<>(_Envelope_QNAME, Envelope.class, null, value);
  }

  @XmlElementDecl(namespace = URL, name = "Header")
  public JAXBElement<Header> createHeader(Header value) {
    return new JAXBElement<>(_Header_QNAME, Header.class, null, value);
  }

  @XmlElementDecl(namespace = URL, name = "Body")
  public JAXBElement<Body> createBody(Body value) {
    return new JAXBElement<>(_Body_QNAME, Body.class, null, value);
  }

  @XmlElementDecl(namespace = URL, name = "Fault")
  public JAXBElement<Fault> createFault(Fault value) {
    return new JAXBElement<>(_Fault_QNAME, Fault.class, null, value);
  }
}
