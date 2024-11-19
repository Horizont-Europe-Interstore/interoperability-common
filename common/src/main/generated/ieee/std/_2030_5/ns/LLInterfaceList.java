//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import java.util.ArrayList;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * List of LLInterface instances.
 * 
 * <p>Java class for LLInterfaceList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LLInterfaceList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}List"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LLInterface" type="{urn:ieee:std:2030.5:ns}LLInterface" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LLInterfaceList", propOrder = {
    "llInterface"
})
public class LLInterfaceList
    extends ieee.std._2030_5.ns.List
{

    @XmlElement(name = "LLInterface")
    protected java.util.List<LLInterface> llInterface;

    /**
     * Gets the value of the llInterface property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the llInterface property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLLInterface().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LLInterface }
     * 
     * 
     */
    public java.util.List<LLInterface> getLLInterface() {
        if (llInterface == null) {
            llInterface = new ArrayList<LLInterface>();
        }
        return this.llInterface;
    }

}
