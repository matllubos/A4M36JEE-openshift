package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.service.facade.FacadeService;
import cz.cvut.fel.aos.service.facade.FacadeServiceImplService;
import lombok.Delegate;
import org.springframework.stereotype.Service;

/**
 * <p>Wrapper pro WS klienta poskytujícího přístup k serveru 2nd vrstvy.</p>
 *
 * @author Karel Cemus
 */
@Service
public class InterfaceService {

    @Delegate
    private FacadeService client;

    public InterfaceService() {
        FacadeServiceImplService factory = new FacadeServiceImplService();
        client = factory.getFacadeServiceImplPort();
    }
}
