package cz.cvut.fel.model;

import java.io.Serializable;

/** @author Karel Cemus */
public enum FlightStatus implements Serializable {

    /** flight is scheduled to take-off */
    SCHEDULED,

    /** flight is delayed */
    DELAYED,

    /** plane took-off */
    STARTED,

    /** plane landed */
    LANDED,

    /** flight was cancelled */
    CANCELLED
}
