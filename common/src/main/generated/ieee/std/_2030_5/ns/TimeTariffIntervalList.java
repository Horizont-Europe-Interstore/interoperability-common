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
 * A List element to hold TimeTariffInterval objects.
 * 
 * <p>Java class for TimeTariffIntervalList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TimeTariffIntervalList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}SubscribableList"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TimeTariffInterval" type="{urn:ieee:std:2030.5:ns}TimeTariffInterval" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TimeTariffIntervalList", propOrder = {
    "timeTariffInterval"
})
public class TimeTariffIntervalList
    extends SubscribableList
{

    @XmlElement(name = "TimeTariffInterval")
    protected List<TimeTariffInterval> timeTariffInterval;

    /**
     * Gets the value of the timeTariffInterval property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the timeTariffInterval property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTimeTariffInterval().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TimeTariffInterval }
     * 
     * 
     */
    public List<TimeTariffInterval> getTimeTariffInterval() {
        if (timeTariffInterval == null) {
            timeTariffInterval = new ArrayList<TimeTariffInterval>();
        }
        return this.timeTariffInterval;
    }

}
