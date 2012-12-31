package cz.cvut.fel.service;

import cz.cvut.fel.exception.NoSuchDestinationException;
import cz.cvut.fel.model.Destination;

import java.util.Collection;

/**
 * Defines all available operation over destinations.
 *
 * @author Karel Cemus
 */
public interface DestinationService {

    /** Returns active destination with given code */
    Destination findByCode( String code ) throws NoSuchDestinationException;

    /** Returns all active destination where the company flies to. */
    Collection<Destination> findAllDestinations();

    /** saves given destination whether it is new or modified only */
    Destination save( Destination destination );

    /** deletes the selected destination and it will not be available any more */
    void delete( long id ) throws NoSuchDestinationException;
}
