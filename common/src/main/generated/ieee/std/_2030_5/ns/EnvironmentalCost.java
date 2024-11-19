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
 * Provides alternative or secondary price information for the relevant RateComponent. Supports jurisdictions that seek to convey the environmental price per unit of the specified commodity not expressed in currency.
 * 
 * Implementers and consumers can use this attribute to prioritize operations of their HAN devices (e.g., PEV charging during times of high availability of renewable electricity resources).
 * 
 * <p>Java class for EnvironmentalCost complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvironmentalCost"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{urn:ieee:std:2030.5:ns}UInt32"/&gt;
 *         &lt;element name="costKind" type="{urn:ieee:std:2030.5:ns}CostKindType"/&gt;
 *         &lt;element name="costLevel" type="{urn:ieee:std:2030.5:ns}UInt8"/&gt;
 *         &lt;element name="numCostLevels" type="{urn:ieee:std:2030.5:ns}UInt8"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvironmentalCost", propOrder = {
    "amount",
    "costKind",
    "costLevel",
    "numCostLevels"
})
public class EnvironmentalCost {

    @XmlSchemaType(name = "unsignedInt")
    protected long amount;
    @XmlElement(required = true)
    protected CostKindType costKind;
    @XmlSchemaType(name = "unsignedByte")
    protected short costLevel;
    @XmlSchemaType(name = "unsignedByte")
    protected short numCostLevels;

    /**
     * Gets the value of the amount property.
     * 
     */
    public long getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(long value) {
        this.amount = value;
    }

    /**
     * Gets the value of the costKind property.
     * 
     * @return
     *     possible object is
     *     {@link CostKindType }
     *     
     */
    public CostKindType getCostKind() {
        return costKind;
    }

    /**
     * Sets the value of the costKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link CostKindType }
     *     
     */
    public void setCostKind(CostKindType value) {
        this.costKind = value;
    }

    /**
     * Gets the value of the costLevel property.
     * 
     */
    public short getCostLevel() {
        return costLevel;
    }

    /**
     * Sets the value of the costLevel property.
     * 
     */
    public void setCostLevel(short value) {
        this.costLevel = value;
    }

    /**
     * Gets the value of the numCostLevels property.
     * 
     */
    public short getNumCostLevels() {
        return numCostLevels;
    }

    /**
     * Sets the value of the numCostLevels property.
     * 
     */
    public void setNumCostLevels(short value) {
        this.numCostLevels = value;
    }

}
