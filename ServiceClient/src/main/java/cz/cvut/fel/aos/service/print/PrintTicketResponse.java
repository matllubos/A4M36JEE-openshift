package cz.cvut.fel.aos.service.print;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for printTicketResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="printTicketResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "printTicketResponse", propOrder = {
        "_return"
} )
public class PrintTicketResponse
        implements Serializable {

    private final static long serialVersionUID = 1L;

    @XmlElement( name = "return" )
    @XmlMimeType( "application/octet-stream" )
    protected DataHandler _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link DataHandler }
     */
    public DataHandler getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is {@link DataHandler }
     */
    public void setReturn( DataHandler value ) {
        this._return = value;
    }

}
