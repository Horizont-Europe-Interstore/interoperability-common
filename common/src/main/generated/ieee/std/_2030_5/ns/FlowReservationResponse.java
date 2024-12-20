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
 * The server may modify the charging or discharging parameters and interval to provide a lower aggregated demand at the premises, or within a larger part of the distribution system.
 * 
 * <p>Java class for FlowReservationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FlowReservationResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}Event"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="energyAvailable" type="{urn:ieee:std:2030.5:ns}SignedRealEnergy"/&gt;
 *         &lt;element name="powerAvailable" type="{urn:ieee:std:2030.5:ns}ActivePower"/&gt;
 *         &lt;element name="subject" type="{urn:ieee:std:2030.5:ns}mRIDType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlowReservationResponse", propOrder = {
    "energyAvailable",
    "powerAvailable",
    "subject"
})
public class FlowReservationResponse
    extends Event
{

    @XmlElement(required = true)
    protected SignedRealEnergy energyAvailable;
    @XmlElement(required = true)
    protected ActivePower powerAvailable;
    @XmlElement(required = true)
    protected MRIDType subject;

    /**
     * Gets the value of the energyAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link SignedRealEnergy }
     *     
     */
    public SignedRealEnergy getEnergyAvailable() {
        return energyAvailable;
    }

    /**
     * Sets the value of the energyAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignedRealEnergy }
     *     
     */
    public void setEnergyAvailable(SignedRealEnergy value) {
        this.energyAvailable = value;
    }

    /**
     * Gets the value of the powerAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link ActivePower }
     *     
     */
    public ActivePower getPowerAvailable() {
        return powerAvailable;
    }

    /**
     * Sets the value of the powerAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivePower }
     *     
     */
    public void setPowerAvailable(ActivePower value) {
        this.powerAvailable = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link MRIDType }
     *     
     */
    public MRIDType getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link MRIDType }
     *     
     */
    public void setSubject(MRIDType value) {
        this.subject = value;
    }

}
