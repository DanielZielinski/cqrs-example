package daniel.zielinski.cqrs.cart.query;

import daniel.zielinski.cqrs.cart.core.event.CartAddItemEvent;
import daniel.zielinski.cqrs.cart.core.event.CartCreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CartProjector {

    @EventHandler
    public void on(CartAddItemEvent event) {
        log.info("{}", event);
    }

    @EventHandler
    public void on(CartCreateEvent event) {
        log.info("{}", event);
    }

}
