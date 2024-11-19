//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Prepayment (inherited from CIM SDPAccountingFunction)
 * 
 * <p>Java class for Prepayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Prepayment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}IdentifiedObject"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AccountBalanceLink" type="{urn:ieee:std:2030.5:ns}AccountBalanceLink"/&gt;
 *         &lt;element name="ActiveCreditRegisterListLink" type="{urn:ieee:std:2030.5:ns}ActiveCreditRegisterListLink" minOccurs="0"/&gt;
 *         &lt;element name="ActiveSupplyInterruptionOverrideListLink" type="{urn:ieee:std:2030.5:ns}ActiveSupplyInterruptionOverrideListLink" minOccurs="0"/&gt;
 *         &lt;element name="creditExpiryLevel" type="{urn:ieee:std:2030.5:ns}AccountingUnit" minOccurs="0"/&gt;
 *         &lt;element name="CreditRegisterListLink" type="{urn:ieee:std:2030.5:ns}CreditRegisterListLink"/&gt;
 *         &lt;element name="lowCreditWarningLevel" type="{urn:ieee:std:2030.5:ns}AccountingUnit" minOccurs="0"/&gt;
 *         &lt;element name="lowEmergencyCreditWarningLevel" type="{urn:ieee:std:2030.5:ns}AccountingUnit" minOccurs="0"/&gt;
 *         &lt;element name="prepayMode" type="{urn:ieee:std:2030.5:ns}PrepayModeType"/&gt;
 *         &lt;element name="PrepayOperationStatusLink" type="{urn:ieee:std:2030.5:ns}PrepayOperationStatusLink"/&gt;
 *         &lt;element name="SupplyInterruptionOverrideListLink" type="{urn:ieee:std:2030.5:ns}SupplyInterruptionOverrideListLink"/&gt;
 *         &lt;element name="UsagePoint" type="{urn:ieee:std:2030.5:ns}UsagePoint" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="UsagePointLink" type="{urn:ieee:std:2030.5:ns}UsagePointLink" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Prepayment", propOrder = {
    "accountBalanceLink",
    "activeCreditRegisterListLink",
    "activeSupplyInterruptionOverrideListLink",
    "creditExpiryLevel",
    "creditRegisterListLink",
    "lowCreditWarningLevel",
    "lowEmergencyCreditWarningLevel",
    "prepayMode",
    "prepayOperationStatusLink",
    "supplyInterruptionOverrideListLink",
    "usagePoint",
    "usagePointLink"
})
public class Prepayment
    extends IdentifiedObject
{

    @XmlElement(name = "AccountBalanceLink", required = true)
    protected AccountBalanceLink accountBalanceLink;
    @XmlElement(name = "ActiveCreditRegisterListLink")
    protected ActiveCreditRegisterListLink activeCreditRegisterListLink;
    @XmlElement(name = "ActiveSupplyInterruptionOverrideListLink")
    protected ActiveSupplyInterruptionOverrideListLink activeSupplyInterruptionOverrideListLink;
    protected AccountingUnit creditExpiryLevel;
    @XmlElement(name = "CreditRegisterListLink", required = true)
    protected CreditRegisterListLink creditRegisterListLink;
    protected AccountingUnit lowCreditWarningLevel;
    protected AccountingUnit lowEmergencyCreditWarningLevel;
    @XmlElement(required = true)
    protected PrepayModeType prepayMode;
    @XmlElement(name = "PrepayOperationStatusLink", required = true)
    protected PrepayOperationStatusLink prepayOperationStatusLink;
    @XmlElement(name = "SupplyInterruptionOverrideListLink", required = true)
    protected SupplyInterruptionOverrideListLink supplyInterruptionOverrideListLink;
    @XmlElement(name = "UsagePoint")
    protected List<UsagePoint> usagePoint;
    @XmlElement(name = "UsagePointLink")
    protected UsagePointLink usagePointLink;

    /**
     * Gets the value of the accountBalanceLink property.
     * 
     * @return
     *     possible object is
     *     {@link AccountBalanceLink }
     *     
     */
    public AccountBalanceLink getAccountBalanceLink() {
        return accountBalanceLink;
    }

    /**
     * Sets the value of the accountBalanceLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountBalanceLink }
     *     
     */
    public void setAccountBalanceLink(AccountBalanceLink value) {
        this.accountBalanceLink = value;
    }

    /**
     * Gets the value of the activeCreditRegisterListLink property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveCreditRegisterListLink }
     *     
     */
    public ActiveCreditRegisterListLink getActiveCreditRegisterListLink() {
        return activeCreditRegisterListLink;
    }

    /**
     * Sets the value of the activeCreditRegisterListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveCreditRegisterListLink }
     *     
     */
    public void setActiveCreditRegisterListLink(ActiveCreditRegisterListLink value) {
        this.activeCreditRegisterListLink = value;
    }

    /**
     * Gets the value of the activeSupplyInterruptionOverrideListLink property.
     * 
     * @return
     *     possible object is
     *     {@link ActiveSupplyInterruptionOverrideListLink }
     *     
     */
    public ActiveSupplyInterruptionOverrideListLink getActiveSupplyInterruptionOverrideListLink() {
        return activeSupplyInterruptionOverrideListLink;
    }

    /**
     * Sets the value of the activeSupplyInterruptionOverrideListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActiveSupplyInterruptionOverrideListLink }
     *     
     */
    public void setActiveSupplyInterruptionOverrideListLink(ActiveSupplyInterruptionOverrideListLink value) {
        this.activeSupplyInterruptionOverrideListLink = value;
    }

    /**
     * Gets the value of the creditExpiryLevel property.
     * 
     * @return
     *     possible object is
     *     {@link AccountingUnit }
     *     
     */
    public AccountingUnit getCreditExpiryLevel() {
        return creditExpiryLevel;
    }

    /**
     * Sets the value of the creditExpiryLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountingUnit }
     *     
     */
    public void setCreditExpiryLevel(AccountingUnit value) {
        this.creditExpiryLevel = value;
    }

    /**
     * Gets the value of the creditRegisterListLink property.
     * 
     * @return
     *     possible object is
     *     {@link CreditRegisterListLink }
     *     
     */
    public CreditRegisterListLink getCreditRegisterListLink() {
        return creditRegisterListLink;
    }

    /**
     * Sets the value of the creditRegisterListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditRegisterListLink }
     *     
     */
    public void setCreditRegisterListLink(CreditRegisterListLink value) {
        this.creditRegisterListLink = value;
    }

    /**
     * Gets the value of the lowCreditWarningLevel property.
     * 
     * @return
     *     possible object is
     *     {@link AccountingUnit }
     *     
     */
    public AccountingUnit getLowCreditWarningLevel() {
        return lowCreditWarningLevel;
    }

    /**
     * Sets the value of the lowCreditWarningLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountingUnit }
     *     
     */
    public void setLowCreditWarningLevel(AccountingUnit value) {
        this.lowCreditWarningLevel = value;
    }

    /**
     * Gets the value of the lowEmergencyCreditWarningLevel property.
     * 
     * @return
     *     possible object is
     *     {@link AccountingUnit }
     *     
     */
    public AccountingUnit getLowEmergencyCreditWarningLevel() {
        return lowEmergencyCreditWarningLevel;
    }

    /**
     * Sets the value of the lowEmergencyCreditWarningLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountingUnit }
     *     
     */
    public void setLowEmergencyCreditWarningLevel(AccountingUnit value) {
        this.lowEmergencyCreditWarningLevel = value;
    }

    /**
     * Gets the value of the prepayMode property.
     * 
     * @return
     *     possible object is
     *     {@link PrepayModeType }
     *     
     */
    public PrepayModeType getPrepayMode() {
        return prepayMode;
    }

    /**
     * Sets the value of the prepayMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrepayModeType }
     *     
     */
    public void setPrepayMode(PrepayModeType value) {
        this.prepayMode = value;
    }

    /**
     * Gets the value of the prepayOperationStatusLink property.
     * 
     * @return
     *     possible object is
     *     {@link PrepayOperationStatusLink }
     *     
     */
    public PrepayOperationStatusLink getPrepayOperationStatusLink() {
        return prepayOperationStatusLink;
    }

    /**
     * Sets the value of the prepayOperationStatusLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrepayOperationStatusLink }
     *     
     */
    public void setPrepayOperationStatusLink(PrepayOperationStatusLink value) {
        this.prepayOperationStatusLink = value;
    }

    /**
     * Gets the value of the supplyInterruptionOverrideListLink property.
     * 
     * @return
     *     possible object is
     *     {@link SupplyInterruptionOverrideListLink }
     *     
     */
    public SupplyInterruptionOverrideListLink getSupplyInterruptionOverrideListLink() {
        return supplyInterruptionOverrideListLink;
    }

    /**
     * Sets the value of the supplyInterruptionOverrideListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplyInterruptionOverrideListLink }
     *     
     */
    public void setSupplyInterruptionOverrideListLink(SupplyInterruptionOverrideListLink value) {
        this.supplyInterruptionOverrideListLink = value;
    }

    /**
     * Gets the value of the usagePoint property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the usagePoint property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsagePoint().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UsagePoint }
     * 
     * 
     */
    public List<UsagePoint> getUsagePoint() {
        if (usagePoint == null) {
            usagePoint = new ArrayList<UsagePoint>();
        }
        return this.usagePoint;
    }

    /**
     * Gets the value of the usagePointLink property.
     * 
     * @return
     *     possible object is
     *     {@link UsagePointLink }
     *     
     */
    public UsagePointLink getUsagePointLink() {
        return usagePointLink;
    }

    /**
     * Sets the value of the usagePointLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link UsagePointLink }
     *     
     */
    public void setUsagePointLink(UsagePointLink value) {
        this.usagePointLink = value;
    }

}