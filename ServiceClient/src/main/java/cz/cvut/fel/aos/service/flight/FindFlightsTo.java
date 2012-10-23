
package cz.cvut.fel.aos.service.flight;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for findFlightsTo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="findFlightsTo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intervalFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="intervalTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="codeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findFlightsTo", propOrder = {
    "intervalFrom",
    "intervalTo",
    "codeTo"
})
public class FindFlightsTo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar intervalFrom;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar intervalTo;
    protected String codeTo;

    /**
     * Gets the value of the intervalFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIntervalFrom() {
        return intervalFrom;
    }

    /**
     * Sets the value of the intervalFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIntervalFrom(XMLGregorianCalendar value) {
        this.intervalFrom = value;
    }

    /**
     * Gets the value of the intervalTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIntervalTo() {
        return intervalTo;
    }

    /**
     * Sets the value of the intervalTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIntervalTo(XMLGregorianCalendar value) {
        this.intervalTo = value;
    }

    /**
     * Gets the value of the codeTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeTo() {
        return codeTo;
    }

    /**
     * Sets the value of the codeTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeTo(String value) {
        this.codeTo = value;
    }

}
