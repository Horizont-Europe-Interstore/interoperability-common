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
 * Describes the time-differentiated portion of the RateComponent, if applicable, and provides the ability to specify multiple time intervals, each with its own consumption-based components and other attributes.
 * 
 * <p>Java class for TimeTariffInterval complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTariffInterval"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}RandomizableEvent"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ConsumptionTariffIntervalListLink" type="{urn:ieee:std:2030.5:ns}ConsumptionTariffIntervalListLink" minOccurs="0"/&gt;
 *         &lt;element name="touTier" type="{urn:ieee:std:2030.5:ns}TOUType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeTariffInterval", propOrder = {
    "consumptionTariffIntervalListLink",
    "touTier"
})
public class TimeTariffInterval
    extends RandomizableEvent
{

    @XmlElement(name = "ConsumptionTariffIntervalListLink")
    protected ConsumptionTariffIntervalListLink consumptionTariffIntervalListLink;
    @XmlElement(required = true)
    protected TOUType touTier;

    /**
     * Gets the value of the consumptionTariffIntervalListLink property.
     * 
     * @return
     *     possible object is
     *     {@link ConsumptionTariffIntervalListLink }
     *     
     */
    public ConsumptionTariffIntervalListLink getConsumptionTariffIntervalListLink() {
        return consumptionTariffIntervalListLink;
    }

    /**
     * Sets the value of the consumptionTariffIntervalListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsumptionTariffIntervalListLink }
     *     
     */
    public void setConsumptionTariffIntervalListLink(ConsumptionTariffIntervalListLink value) {
        this.consumptionTariffIntervalListLink = value;
    }

    /**
     * Gets the value of the touTier property.
     * 
     * @return
     *     possible object is
     *     {@link TOUType }
     *     
     */
    public TOUType getTouTier() {
        return touTier;
    }

    /**
     * Sets the value of the touTier property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOUType }
     *     
     */
    public void setTouTier(TOUType value) {
        this.touTier = value;
    }

}
