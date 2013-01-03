package cz.cvut.fel.cdi;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

/**
 * <p>CDI extension to bridge JSF ViewScope to CDI</p>
 *
 * @author Karel Cemus
 */
public class ViewContextExtension implements Extension {

    public void afterBeanDiscovery( @Observes AfterBeanDiscovery event, BeanManager manager ) {
        event.addContext( new ViewScopedContext() );
    }

}
