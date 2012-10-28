package cz.cvut.fel.aos.payment.service.payment;

import cz.cvut.fel.aos.model.Reservation;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Karel Cemus
 */
@WebService(endpointInterface = "cz.cvut.fel.aos.payment.service.payment.PaymentService")
public class PaymentServiceImpl implements PaymentService{

    @Override
    public long payVisa( final long reservationId, final long creditCard, final String name, final Date validUntil, final int code ) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }


//    @Override
//    @Transactional
//    public Reservation pay( long reservation, String password, int amount ) {
//        Reservation entity = em.find( Reservation.class, reservation );
//
//        if ( entity == null ) // rezervace neexistuje
//        {
//            throw new IllegalArgumentException( "Reservation doesn't exists." );
//        }
//
//        // zkontroluj přístup k rezervaci
//        checkSecurity( entity, password );
//
//        // vlož platbu
//        entity.setPaid( entity.getPaid() + amount );
//
//        ReservationServiceImpl.log.info( "Reservation with ID '{}' received '{}' money. There is '{}'.", new Object[]{ entity.getId(), amount, entity.getPaid() } );
//
//        return entity;
//    }
//
//    @Override
//    @Transactional
//    public int withdrawCredit( long reservation, String password, int amount ) {
//        Reservation entity = em.find( Reservation.class, reservation );
//
//        if ( entity == null ) // rezervace neexistuje
//        {
//            throw new IllegalArgumentException( "Reservation doesn't exists." );
//        }
//
//        // zkontroluj přístup k rezervaci
//        checkSecurity( entity, password );
//
//        // omez množství vybraných peněz a vyber je
//        amount = Math.min( amount, entity.getPaid() );
//        entity.setPaid( entity.getPaid() - amount );
//
//        ReservationServiceImpl.log.info( "Reservation with ID '{}' withdrawn '{}' money. There left '{}'.", new Object[]{ entity.getId(), amount, entity.getPaid() } );
//
//        return amount;
//    }
}
