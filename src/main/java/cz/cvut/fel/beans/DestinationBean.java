package cz.cvut.fel.beans;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.service.DestinationService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/** @author Karel Cemus */
@ViewScoped
@Named( "destination" )
public class DestinationBean extends BeanBase implements Serializable {

    @Inject
    private DestinationService service;

    @Inject
    private DestinationsBean destinations;

    @Getter
    @Setter
    private Destination destination = new Destination();

    public String save() {
        try {
            boolean isNew = destination.getId() == 0;

            // persist the destination
            service.save( destination );

            // log it
            if ( isNew ) {
                addInformation( "Destination successfully created." );
            } else {
                addInformation( "Destination successfully updated." );
            }

            // reload all destinations
            destinations.update();

            return "destinations";

        } catch ( Throwable ex ) {
            addError( processException( ex ) );
            return null;
        }
    }
}
