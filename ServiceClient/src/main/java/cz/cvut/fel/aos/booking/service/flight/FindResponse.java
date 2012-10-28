package cz.cvut.fel.aos.booking.service.flight;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for findResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="findResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://flight.cz.cvut.fel.aos.payment.service.service.booking.aos.fel.cvut.cz/}flight" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "findResponse", propOrder = {
        "_return"
} )
public class FindResponse
        implements Serializable {

    private final static long serialVersionUID = 2L;

    @XmlElement( name = "return" )
    protected Flight _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link Flight }
     */
    public Flight getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is {@link Flight }
     */
    public void setReturn( Flight value ) {
        this._return = value;
    }

}