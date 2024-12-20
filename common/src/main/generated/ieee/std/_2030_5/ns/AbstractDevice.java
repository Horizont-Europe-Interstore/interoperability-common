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
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The EndDevice providing the resources available within the DeviceCapabilities.
 * 
 * <p>Java class for AbstractDevice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AbstractDevice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}SubscribableResource"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ConfigurationLink" type="{urn:ieee:std:2030.5:ns}ConfigurationLink" minOccurs="0"/&gt;
 *         &lt;element name="DERListLink" type="{urn:ieee:std:2030.5:ns}DERListLink" minOccurs="0"/&gt;
 *         &lt;element name="deviceCategory" type="{urn:ieee:std:2030.5:ns}DeviceCategoryType" minOccurs="0"/&gt;
 *         &lt;element name="DeviceInformationLink" type="{urn:ieee:std:2030.5:ns}DeviceInformationLink" minOccurs="0"/&gt;
 *         &lt;element name="DeviceStatusLink" type="{urn:ieee:std:2030.5:ns}DeviceStatusLink" minOccurs="0"/&gt;
 *         &lt;element name="FileStatusLink" type="{urn:ieee:std:2030.5:ns}FileStatusLink" minOccurs="0"/&gt;
 *         &lt;element name="IPInterfaceListLink" type="{urn:ieee:std:2030.5:ns}IPInterfaceListLink" minOccurs="0"/&gt;
 *         &lt;element name="lFDI" type="{urn:ieee:std:2030.5:ns}HexBinary160" minOccurs="0"/&gt;
 *         &lt;element name="LoadShedAvailabilityListLink" type="{urn:ieee:std:2030.5:ns}LoadShedAvailabilityListLink" minOccurs="0"/&gt;
 *         &lt;element name="LogEventListLink" type="{urn:ieee:std:2030.5:ns}LogEventListLink" minOccurs="0"/&gt;
 *         &lt;element name="PowerStatusLink" type="{urn:ieee:std:2030.5:ns}PowerStatusLink" minOccurs="0"/&gt;
 *         &lt;element name="sFDI" type="{urn:ieee:std:2030.5:ns}SFDIType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractDevice", propOrder = {
    "configurationLink",
    "derListLink",
    "deviceCategory",
    "deviceInformationLink",
    "deviceStatusLink",
    "fileStatusLink",
    "ipInterfaceListLink",
    "lfdi",
    "loadShedAvailabilityListLink",
    "logEventListLink",
    "powerStatusLink",
    "sfdi"
})
@XmlSeeAlso({
    EndDevice.class,
    SelfDevice.class
})
public class AbstractDevice
    extends SubscribableResource
{

    @XmlElement(name = "ConfigurationLink")
    protected ConfigurationLink configurationLink;
    @XmlElement(name = "DERListLink")
    protected DERListLink derListLink;
    protected DeviceCategoryType deviceCategory;
    @XmlElement(name = "DeviceInformationLink")
    protected DeviceInformationLink deviceInformationLink;
    @XmlElement(name = "DeviceStatusLink")
    protected DeviceStatusLink deviceStatusLink;
    @XmlElement(name = "FileStatusLink")
    protected FileStatusLink fileStatusLink;
    @XmlElement(name = "IPInterfaceListLink")
    protected IPInterfaceListLink ipInterfaceListLink;
    @XmlElement(name = "lFDI", type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] lfdi;
    @XmlElement(name = "LoadShedAvailabilityListLink")
    protected LoadShedAvailabilityListLink loadShedAvailabilityListLink;
    @XmlElement(name = "LogEventListLink")
    protected LogEventListLink logEventListLink;
    @XmlElement(name = "PowerStatusLink")
    protected PowerStatusLink powerStatusLink;
    @XmlElement(name = "sFDI", required = true)
    protected SFDIType sfdi;

    /**
     * Gets the value of the configurationLink property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigurationLink }
     *     
     */
    public ConfigurationLink getConfigurationLink() {
        return configurationLink;
    }

    /**
     * Sets the value of the configurationLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigurationLink }
     *     
     */
    public void setConfigurationLink(ConfigurationLink value) {
        this.configurationLink = value;
    }

    /**
     * Gets the value of the derListLink property.
     * 
     * @return
     *     possible object is
     *     {@link DERListLink }
     *     
     */
    public DERListLink getDERListLink() {
        return derListLink;
    }

    /**
     * Sets the value of the derListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DERListLink }
     *     
     */
    public void setDERListLink(DERListLink value) {
        this.derListLink = value;
    }

    /**
     * Gets the value of the deviceCategory property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceCategoryType }
     *     
     */
    public DeviceCategoryType getDeviceCategory() {
        return deviceCategory;
    }

    /**
     * Sets the value of the deviceCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceCategoryType }
     *     
     */
    public void setDeviceCategory(DeviceCategoryType value) {
        this.deviceCategory = value;
    }

    /**
     * Gets the value of the deviceInformationLink property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceInformationLink }
     *     
     */
    public DeviceInformationLink getDeviceInformationLink() {
        return deviceInformationLink;
    }

    /**
     * Sets the value of the deviceInformationLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceInformationLink }
     *     
     */
    public void setDeviceInformationLink(DeviceInformationLink value) {
        this.deviceInformationLink = value;
    }

    /**
     * Gets the value of the deviceStatusLink property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceStatusLink }
     *     
     */
    public DeviceStatusLink getDeviceStatusLink() {
        return deviceStatusLink;
    }

    /**
     * Sets the value of the deviceStatusLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceStatusLink }
     *     
     */
    public void setDeviceStatusLink(DeviceStatusLink value) {
        this.deviceStatusLink = value;
    }

    /**
     * Gets the value of the fileStatusLink property.
     * 
     * @return
     *     possible object is
     *     {@link FileStatusLink }
     *     
     */
    public FileStatusLink getFileStatusLink() {
        return fileStatusLink;
    }

    /**
     * Sets the value of the fileStatusLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileStatusLink }
     *     
     */
    public void setFileStatusLink(FileStatusLink value) {
        this.fileStatusLink = value;
    }

    /**
     * Gets the value of the ipInterfaceListLink property.
     * 
     * @return
     *     possible object is
     *     {@link IPInterfaceListLink }
     *     
     */
    public IPInterfaceListLink getIPInterfaceListLink() {
        return ipInterfaceListLink;
    }

    /**
     * Sets the value of the ipInterfaceListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link IPInterfaceListLink }
     *     
     */
    public void setIPInterfaceListLink(IPInterfaceListLink value) {
        this.ipInterfaceListLink = value;
    }

    /**
     * Gets the value of the lfdi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getLFDI() {
        return lfdi;
    }

    /**
     * Sets the value of the lfdi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLFDI(byte[] value) {
        this.lfdi = value;
    }

    /**
     * Gets the value of the loadShedAvailabilityListLink property.
     * 
     * @return
     *     possible object is
     *     {@link LoadShedAvailabilityListLink }
     *     
     */
    public LoadShedAvailabilityListLink getLoadShedAvailabilityListLink() {
        return loadShedAvailabilityListLink;
    }

    /**
     * Sets the value of the loadShedAvailabilityListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoadShedAvailabilityListLink }
     *     
     */
    public void setLoadShedAvailabilityListLink(LoadShedAvailabilityListLink value) {
        this.loadShedAvailabilityListLink = value;
    }

    /**
     * Gets the value of the logEventListLink property.
     * 
     * @return
     *     possible object is
     *     {@link LogEventListLink }
     *     
     */
    public LogEventListLink getLogEventListLink() {
        return logEventListLink;
    }

    /**
     * Sets the value of the logEventListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link LogEventListLink }
     *     
     */
    public void setLogEventListLink(LogEventListLink value) {
        this.logEventListLink = value;
    }

    /**
     * Gets the value of the powerStatusLink property.
     * 
     * @return
     *     possible object is
     *     {@link PowerStatusLink }
     *     
     */
    public PowerStatusLink getPowerStatusLink() {
        return powerStatusLink;
    }

    /**
     * Sets the value of the powerStatusLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link PowerStatusLink }
     *     
     */
    public void setPowerStatusLink(PowerStatusLink value) {
        this.powerStatusLink = value;
    }

    /**
     * Gets the value of the sfdi property.
     * 
     * @return
     *     possible object is
     *     {@link SFDIType }
     *     
     */
    public SFDIType getSFDI() {
        return sfdi;
    }

    /**
     * Sets the value of the sfdi property.
     * 
     * @param value
     *     allowed object is
     *     {@link SFDIType }
     *     
     */
    public void setSFDI(SFDIType value) {
        this.sfdi = value;
    }

}
