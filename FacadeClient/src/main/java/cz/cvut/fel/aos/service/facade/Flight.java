package cz.cvut.fel.aos.service.facade;

import cz.cvut.fel.aos.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>Java class for flight complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="flight">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arrival" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="capacity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="capacityLeft" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="departure" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="from" type="{http://facade.service.aos.fel.cvut.cz/}destination" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="to" type="{http://facade.service.aos.fel.cvut.cz/}destination" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "flight", propOrder = {
        "arrival",
        "capacity",
        "capacityLeft",
        "cost",
        "departure",
        "from",
        "id",
        "number",
        "to"
} )
public class Flight
        implements Serializable {

    private final static long serialVersionUID = 1L;

    @XmlElement( type = String.class )
    @XmlJavaTypeAdapter( DateAdapter.class )
    @XmlSchemaType( name = "dateTime" )
    protected Date arrival;

    protected int capacity;

    protected int capacityLeft;

    protected int cost;

    @XmlElement( type = String.class )
    @XmlJavaTypeAdapter( DateAdapter.class )
    @XmlSchemaType( name = "dateTime" )
    protected Date departure;

    protected Destination from;

    protected long id;

    protected String number;

    protected Destination to;

    /**
     * Gets the value of the arrival property.
     *
     * @return possible object is {@link String }
     */
    public Date getArrival() {
        return arrival;
    }

    /**
     * Sets the value of the arrival property.
     *
     * @param value allowed object is {@link String }
     */
    public void setArrival( Date value ) {
        this.arrival = value;
    }

    /** Gets the value of the capacity property. */
    public int getCapacity() {
        return capacity;
    }

    /** Sets the value of the capacity property. */
    public void setCapacity( int value ) {
        this.capacity = value;
    }

    /** Gets the value of the capacityLeft property. */
    public int getCapacityLeft() {
        return capacityLeft;
    }

    /** Sets the value of the capacityLeft property. */
    public void setCapacityLeft( int value ) {
        this.capacityLeft = value;
    }

    /** Gets the value of the cost property. */
    public int getCost() {
        return cost;
    }

    /** Sets the value of the cost property. */
    public void setCost( int value ) {
        this.cost = value;
    }

    /**
     * Gets the value of the departure property.
     *
     * @return possible object is {@link String }
     */
    public Date getDeparture() {
        return departure;
    }

    /**
     * Sets the value of the departure property.
     *
     * @param value allowed object is {@link String }
     */
    public void setDeparture( Date value ) {
        this.departure = value;
    }

    /**
     * Gets the value of the from property.
     *
     * @return possible object is {@link Destination }
     */
    public Destination getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     *
     * @param value allowed object is {@link Destination }
     */
    public void setFrom( Destination value ) {
        this.from = value;
    }

    /** Gets the value of the id property. */
    public long getId() {
        return id;
    }

    /** Sets the value of the id property. */
    public void setId( long value ) {
        this.id = value;
    }

    /**
     * Gets the value of the number property.
     *
     * @return possible object is {@link String }
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     *
     * @param value allowed object is {@link String }
     */
    public void setNumber( String value ) {
        this.number = value;
    }

    /**
     * Gets the value of the to property.
     *
     * @return possible object is {@link Destination }
     */
    public Destination getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     *
     * @param value allowed object is {@link Destination }
     */
    public void setTo( Destination value ) {
        this.to = value;
    }

}
