package cz.cvut.fel.dto;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.FlightStatus;
import cz.cvut.fel.model.Time;
import lombok.Data;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @author Karel Cemus */
@Data
public class FlightDTO {

    /** flight number */
    private String number;

    /** url of current resource */
    private String resourceUrl;

    /** departure date and time */
    private Time departure;

    /** arrival date and time */
    private Time arrival;

    /** flight price per seat */
    private int cost;

    /** departure destination */
    private String departureFrom;

    /** departure destination resource */
    private String departureFromUrl;

    /** arrival destination */
    private String arrivalTo;

    /** arrival destination resource */
    private String arrivalToUrl;

    /** total plane capacity */
    private int capacity;

    /** number of taken seats */
    private int seatsTaken;

    /** current status of flight */
    private FlightStatus status;

    /** available for booking */
    private boolean bookable;

    /** was deleted */
    private boolean deleted;


    public FlightDTO( UriInfo uri, Flight flight ) {
        this.number = flight.getNumber();
        this.resourceUrl = getURL( uri, number );

        this.departure = flight.getDeparture();
        this.departureFrom = flight.getFrom().getCode();
        this.departureFromUrl = DestinationDTO.getURL( uri, departureFrom );

        this.arrival = flight.getArrival();
        this.arrivalTo = flight.getTo().getCode();
        this.arrivalToUrl = DestinationDTO.getURL( uri, arrivalTo );


        this.cost = flight.getCost();
        this.capacity = flight.getCapacity();
        this.seatsTaken = flight.getSeatsTaken();
        this.status = flight.getStatus();
        this.bookable = flight.isBookable();
        this.deleted = flight.getValidUntil().getTime() <= new Date().getTime();
    }

    public static String getURL( final UriInfo uri, final String number ) {
        UriBuilder builder = uri.getBaseUriBuilder();
        builder.path( "flight" );
        builder.path( number );
        return builder.build().toString();
    }

    public static List<FlightDTO> convert( UriInfo uri, List<Flight> flights ) {
        List<FlightDTO> converted = new ArrayList<FlightDTO>( flights.size() );

        for ( Flight flight : flights ) {
            converted.add( new FlightDTO( uri, flight ) );
        }

        return converted;
    }
}
