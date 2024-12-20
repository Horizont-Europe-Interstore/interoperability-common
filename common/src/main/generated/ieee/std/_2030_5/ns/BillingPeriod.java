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
import jakarta.xml.bind.annotation.XmlType;


/**
 * A Billing Period relates to the period of time on which a customer is billed.  As an example the billing period interval for a particular customer might be 31 days starting on July 1, 2011. The start date and interval can change on each billing period. There may also be multiple billing periods related to a customer agreement to support different tariff structures.
 * 
 * <p>Java class for BillingPeriod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingPeriod"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}Resource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="billLastPeriod" type="{urn:ieee:std:2030.5:ns}Int48" minOccurs="0"/&gt;
 *         &lt;element name="billToDate" type="{urn:ieee:std:2030.5:ns}Int48" minOccurs="0"/&gt;
 *         &lt;element name="interval" type="{urn:ieee:std:2030.5:ns}DateTimeInterval"/&gt;
 *         &lt;element name="statusTimeStamp" type="{urn:ieee:std:2030.5:ns}TimeType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingPeriod", propOrder = {
    "billLastPeriod",
    "billToDate",
    "interval",
    "statusTimeStamp"
})
public class BillingPeriod
    extends Resource
{

    protected Long billLastPeriod;
    protected Long billToDate;
    @XmlElement(required = true)
    protected DateTimeInterval interval;
    protected TimeType statusTimeStamp;

    /**
     * Gets the value of the billLastPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBillLastPeriod() {
        return billLastPeriod;
    }

    /**
     * Sets the value of the billLastPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBillLastPeriod(Long value) {
        this.billLastPeriod = value;
    }

    /**
     * Gets the value of the billToDate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBillToDate() {
        return billToDate;
    }

    /**
     * Sets the value of the billToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBillToDate(Long value) {
        this.billToDate = value;
    }

    /**
     * Gets the value of the interval property.
     * 
     * @return
     *     possible object is
     *     {@link DateTimeInterval }
     *     
     */
    public DateTimeInterval getInterval() {
        return interval;
    }

    /**
     * Sets the value of the interval property.
     * 
     * @param value
     *     allowed object is
     *     {@link DateTimeInterval }
     *     
     */
    public void setInterval(DateTimeInterval value) {
        this.interval = value;
    }

    /**
     * Gets the value of the statusTimeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link TimeType }
     *     
     */
    public TimeType getStatusTimeStamp() {
        return statusTimeStamp;
    }

    /**
     * Sets the value of the statusTimeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimeType }
     *     
     */
    public void setStatusTimeStamp(TimeType value) {
        this.statusTimeStamp = value;
    }

}
