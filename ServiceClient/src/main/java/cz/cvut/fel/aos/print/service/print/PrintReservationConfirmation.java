
package cz.cvut.fel.aos.print.service.print;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for printReservationConfirmation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="printReservationConfirmation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservation" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flightNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="destinationFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinationTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="takeOff" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "printReservationConfirmation", propOrder = {
    "reservation",
    "password",
    "flightNumber",
    "destinationFrom",
    "destinationTo",
    "takeOff",
    "cost"
})
public class PrintReservationConfirmation
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    protected long reservation;
    protected String password;
    protected long flightNumber;
    protected String destinationFrom;
    protected String destinationTo;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(Adapter1 .class)
    @XmlSchemaType(name = "dateTime")
    protected Date takeOff;
    protected int cost;

    /**
     * Gets the value of the reservation property.
     * 
     */
    public long getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     * 
     */
    public void setReservation(long value) {
        this.reservation = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the flightNumber property.
     * 
     */
    public long getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the value of the flightNumber property.
     * 
     */
    public void setFlightNumber(long value) {
        this.flightNumber = value;
    }

    /**
     * Gets the value of the destinationFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationFrom() {
        return destinationFrom;
    }

    /**
     * Sets the value of the destinationFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationFrom(String value) {
        this.destinationFrom = value;
    }

    /**
     * Gets the value of the destinationTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationTo() {
        return destinationTo;
    }

    /**
     * Sets the value of the destinationTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationTo(String value) {
        this.destinationTo = value;
    }

    /**
     * Gets the value of the takeOff property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getTakeOff() {
        return takeOff;
    }

    /**
     * Sets the value of the takeOff property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTakeOff(Date value) {
        this.takeOff = value;
    }

    /**
     * Gets the value of the cost property.
     * 
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the value of the cost property.
     * 
     */
    public void setCost(int value) {
        this.cost = value;
    }

}
