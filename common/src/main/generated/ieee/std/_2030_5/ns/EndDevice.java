//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Asset container that performs one or more end device functions. Contains information about individual devices in the network.
 * 
 * <p>Java class for EndDevice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EndDevice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}AbstractDevice"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="changedTime" type="{urn:ieee:std:2030.5:ns}TimeType"/&gt;
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="FlowReservationRequestListLink" type="{urn:ieee:std:2030.5:ns}FlowReservationRequestListLink" minOccurs="0"/&gt;
 *         &lt;element name="FlowReservationResponseListLink" type="{urn:ieee:std:2030.5:ns}FlowReservationResponseListLink" minOccurs="0"/&gt;
 *         &lt;element name="FunctionSetAssignmentsListLink" type="{urn:ieee:std:2030.5:ns}FunctionSetAssignmentsListLink" minOccurs="0"/&gt;
 *         &lt;element name="postRate" type="{urn:ieee:std:2030.5:ns}UInt32" minOccurs="0"/&gt;
 *         &lt;element name="RegistrationLink" type="{urn:ieee:std:2030.5:ns}RegistrationLink" minOccurs="0"/&gt;
 *         &lt;element name="SubscriptionListLink" type="{urn:ieee:std:2030.5:ns}SubscriptionListLink" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EndDevice", propOrder = {
    "changedTime",
    "enabled",
    "flowReservationRequestListLink",
    "flowReservationResponseListLink",
    "functionSetAssignmentsListLink",
    "postRate",
    "registrationLink",
    "subscriptionListLink"
})
public class EndDevice
    extends AbstractDevice
{

    @XmlElement(required = true)
    protected TimeType changedTime;
    protected Boolean enabled;
    @XmlElement(name = "FlowReservationRequestListLink")
    protected FlowReservationRequestListLink flowReservationRequestListLink;
    @XmlElement(name = "FlowReservationResponseListLink")
    protected FlowReservationResponseListLink flowReservationResponseListLink;
    @XmlElement(name = "FunctionSetAssignmentsListLink")
    protected FunctionSetAssignmentsListLink functionSetAssignmentsListLink;
    @XmlSchemaType(name = "unsignedInt")
    protected Long postRate;
    @XmlElement(name = "RegistrationLink")
    protected RegistrationLink registrationLink;
    @XmlElement(name = "SubscriptionListLink")
    protected SubscriptionListLink subscriptionListLink;

    /**
     * Gets the value of the changedTime property.
     * 
     * @return
     *     possible object is
     *     {@link TimeType }
     *     
     */
    public TimeType getChangedTime() {
        return changedTime;
    }

    /**
     * Sets the value of the changedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeType }
     *     
     */
    public void setChangedTime(TimeType value) {
        this.changedTime = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnabled(Boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the flowReservationRequestListLink property.
     * 
     * @return
     *     possible object is
     *     {@link FlowReservationRequestListLink }
     *     
     */
    public FlowReservationRequestListLink getFlowReservationRequestListLink() {
        return flowReservationRequestListLink;
    }

    /**
     * Sets the value of the flowReservationRequestListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowReservationRequestListLink }
     *     
     */
    public void setFlowReservationRequestListLink(FlowReservationRequestListLink value) {
        this.flowReservationRequestListLink = value;
    }

    /**
     * Gets the value of the flowReservationResponseListLink property.
     * 
     * @return
     *     possible object is
     *     {@link FlowReservationResponseListLink }
     *     
     */
    public FlowReservationResponseListLink getFlowReservationResponseListLink() {
        return flowReservationResponseListLink;
    }

    /**
     * Sets the value of the flowReservationResponseListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link FlowReservationResponseListLink }
     *     
     */
    public void setFlowReservationResponseListLink(FlowReservationResponseListLink value) {
        this.flowReservationResponseListLink = value;
    }

    /**
     * Gets the value of the functionSetAssignmentsListLink property.
     * 
     * @return
     *     possible object is
     *     {@link FunctionSetAssignmentsListLink }
     *     
     */
    public FunctionSetAssignmentsListLink getFunctionSetAssignmentsListLink() {
        return functionSetAssignmentsListLink;
    }

    /**
     * Sets the value of the functionSetAssignmentsListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link FunctionSetAssignmentsListLink }
     *     
     */
    public void setFunctionSetAssignmentsListLink(FunctionSetAssignmentsListLink value) {
        this.functionSetAssignmentsListLink = value;
    }

    /**
     * Gets the value of the postRate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPostRate() {
        return postRate;
    }

    /**
     * Sets the value of the postRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPostRate(Long value) {
        this.postRate = value;
    }

    /**
     * Gets the value of the registrationLink property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationLink }
     *     
     */
    public RegistrationLink getRegistrationLink() {
        return registrationLink;
    }

    /**
     * Sets the value of the registrationLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationLink }
     *     
     */
    public void setRegistrationLink(RegistrationLink value) {
        this.registrationLink = value;
    }

    /**
     * Gets the value of the subscriptionListLink property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriptionListLink }
     *     
     */
    public SubscriptionListLink getSubscriptionListLink() {
        return subscriptionListLink;
    }

    /**
     * Sets the value of the subscriptionListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriptionListLink }
     *     
     */
    public void setSubscriptionListLink(SubscriptionListLink value) {
        this.subscriptionListLink = value;
    }

}