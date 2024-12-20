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
 * The ApplianceLoadReduction object is used by a Demand Response service provider to provide signals for ENERGY STAR compliant appliances. See the definition of ApplianceLoadReductionType for more information.
 * 
 * <p>Java class for ApplianceLoadReduction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApplianceLoadReduction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="type" type="{urn:ieee:std:2030.5:ns}ApplianceLoadReductionType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplianceLoadReduction", propOrder = {
    "type"
})
public class ApplianceLoadReduction {

    @XmlElement(required = true)
    protected ApplianceLoadReductionType type;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link ApplianceLoadReductionType }
     *     
     */
    public ApplianceLoadReductionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApplianceLoadReductionType }
     *     
     */
    public void setType(ApplianceLoadReductionType value) {
        this.type = value;
    }

}
