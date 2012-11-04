package cz.cvut.fel.aos.service.facade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for createReservation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="createReservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="flightNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="count" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "createReservation", propOrder = {
        "flightNumber",
        "password",
        "count"
} )
public class CreateReservation
        implements Serializable {

    private final static long serialVersionUID = 1L;

    protected String flightNumber;

    protected String password;

    protected int count;

    /**
     * Gets the value of the flightNumber property.
     *
     * @return possible object is {@link String }
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the value of the flightNumber property.
     *
     * @param value allowed object is {@link String }
     */
    public void setFlightNumber( String value ) {
        this.flightNumber = value;
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

    /** Gets the value of the count property. */
    public int getCount() {
        return count;
    }

    /** Sets the value of the count property. */
    public void setCount( int value ) {
        this.count = value;
    }

}
