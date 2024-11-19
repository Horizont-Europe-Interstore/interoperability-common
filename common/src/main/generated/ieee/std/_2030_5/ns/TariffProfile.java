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
 * A schedule of charges; structure that allows the definition of tariff structures such as step (block) and time of use (tier) when used in conjunction with TimeTariffInterval and ConsumptionTariffInterval.
 * 
 * <p>Java class for TariffProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TariffProfile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}IdentifiedObject"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currency" type="{urn:ieee:std:2030.5:ns}CurrencyCode" minOccurs="0"/&gt;
 *         &lt;element name="pricePowerOfTenMultiplier" type="{urn:ieee:std:2030.5:ns}PowerOfTenMultiplierType" minOccurs="0"/&gt;
 *         &lt;element name="primacy" type="{urn:ieee:std:2030.5:ns}PrimacyType"/&gt;
 *         &lt;element name="rateCode" type="{urn:ieee:std:2030.5:ns}String20" minOccurs="0"/&gt;
 *         &lt;element name="RateComponentListLink" type="{urn:ieee:std:2030.5:ns}RateComponentListLink" minOccurs="0"/&gt;
 *         &lt;element name="serviceCategoryKind" type="{urn:ieee:std:2030.5:ns}ServiceKind"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TariffProfile", propOrder = {
    "currency",
    "pricePowerOfTenMultiplier",
    "primacy",
    "rateCode",
    "rateComponentListLink",
    "serviceCategoryKind"
})
public class TariffProfile
    extends IdentifiedObject
{

    protected CurrencyCode currency;
    protected PowerOfTenMultiplierType pricePowerOfTenMultiplier;
    @XmlElement(required = true)
    protected PrimacyType primacy;
    protected String rateCode;
    @XmlElement(name = "RateComponentListLink")
    protected RateComponentListLink rateComponentListLink;
    @XmlElement(required = true)
    protected ServiceKind serviceCategoryKind;

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link CurrencyCode }
     *     
     */
    public CurrencyCode getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrencyCode }
     *     
     */
    public void setCurrency(CurrencyCode value) {
        this.currency = value;
    }

    /**
     * Gets the value of the pricePowerOfTenMultiplier property.
     * 
     * @return
     *     possible object is
     *     {@link PowerOfTenMultiplierType }
     *     
     */
    public PowerOfTenMultiplierType getPricePowerOfTenMultiplier() {
        return pricePowerOfTenMultiplier;
    }

    /**
     * Sets the value of the pricePowerOfTenMultiplier property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerOfTenMultiplierType }
     *     
     */
    public void setPricePowerOfTenMultiplier(PowerOfTenMultiplierType value) {
        this.pricePowerOfTenMultiplier = value;
    }

    /**
     * Gets the value of the primacy property.
     * 
     * @return
     *     possible object is
     *     {@link PrimacyType }
     *     
     */
    public PrimacyType getPrimacy() {
        return primacy;
    }

    /**
     * Sets the value of the primacy property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrimacyType }
     *     
     */
    public void setPrimacy(PrimacyType value) {
        this.primacy = value;
    }

    /**
     * Gets the value of the rateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCode() {
        return rateCode;
    }

    /**
     * Sets the value of the rateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCode(String value) {
        this.rateCode = value;
    }

    /**
     * Gets the value of the rateComponentListLink property.
     * 
     * @return
     *     possible object is
     *     {@link RateComponentListLink }
     *     
     */
    public RateComponentListLink getRateComponentListLink() {
        return rateComponentListLink;
    }

    /**
     * Sets the value of the rateComponentListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link RateComponentListLink }
     *     
     */
    public void setRateComponentListLink(RateComponentListLink value) {
        this.rateComponentListLink = value;
    }

    /**
     * Gets the value of the serviceCategoryKind property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceKind }
     *     
     */
    public ServiceKind getServiceCategoryKind() {
        return serviceCategoryKind;
    }

    /**
     * Sets the value of the serviceCategoryKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceKind }
     *     
     */
    public void setServiceCategoryKind(ServiceKind value) {
        this.serviceCategoryKind = value;
    }

}