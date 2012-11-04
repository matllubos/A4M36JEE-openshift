package cz.cvut.fel.aos.service.facade;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for createReservationResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="createReservationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://facade.service.aos.fel.cvut.cz/}successfulReservation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "createReservationResponse", propOrder = {
        "_return"
} )
public class CreateReservationResponse
        implements Serializable {

    private final static long serialVersionUID = 1L;

    @XmlElement( name = "return" )
    protected SuccessfulReservation _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link SuccessfulReservation }
     */
    public SuccessfulReservation getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is {@link SuccessfulReservation }
     */
    public void setReturn( SuccessfulReservation value ) {
        this._return = value;
    }

}
