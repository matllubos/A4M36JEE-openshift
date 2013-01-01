package cz.cvut.fel.beans;

import lombok.EqualsAndHashCode;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * <p>Base class for beans providing comfort functionality</p>
 *
 * @author Karel Cemus
 */
@EqualsAndHashCode
public class BeanBase {


    protected void addMessage( String formatMessage, Object... args ) {

        // create formatted message
        FacesMessage message = new FacesMessage( String.format( formatMessage, args ) );

        // add it to root message handler
        FacesContext.getCurrentInstance().addMessage( null, message );
    }
}
