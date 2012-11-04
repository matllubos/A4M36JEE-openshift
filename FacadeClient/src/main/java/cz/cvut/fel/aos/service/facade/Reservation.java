package cz.cvut.fel.aos.service.facade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for reservation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="reservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="canceled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="cost" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paid" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "reservation", propOrder = {
        "canceled",
        "cost",
        "count",
        "id",
        "paid",
        "password",
        "flight"
} )
public class Reservation
        implements Serializable {

    private final static long serialVersionUID = 1L;

    protected boolean canceled;

    protected int cost;

    protected int count;

    protected long id;

    protected int paid;

    protected String password;

    protected Flight flight;

    /** Gets the value of the canceled property. */
    public boolean isCanceled() {
        return canceled;
    }

    /** Sets the value of the canceled property. */
    public void setCanceled( boolean value ) {
        this.canceled = value;
    }

    /** Gets the value of the cost property. */
    public int getCost() {
        return cost;
    }

    /** Sets the value of the cost property. */
    public void setCost( int value ) {
        this.cost = value;
    }

    /** Gets the value of the count property. */
    public int getCount() {
        return count;
    }

    /** Sets the value of the count property. */
    public void setCount( int value ) {
        this.count = value;
    }

    /** Gets the value of the id property. */
    public long getId() {
        return id;
    }

    /** Sets the value of the id property. */
    public void setId( long value ) {
        this.id = value;
    }

    /** Gets the value of the paid property. */
    public int getPaid() {
        return paid;
    }

    /** Sets the value of the paid property. */
    public void setPaid( int value ) {
        this.paid = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is {@link String }
     */
    public void setPassword( String value ) {
        this.password = value;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight( final Flight flight ) {
        this.flight = flight;
    }
}
