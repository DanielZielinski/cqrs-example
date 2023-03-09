package daniel.zielinski.cqrs.cart.command;

import daniel.zielinski.cqrs.cart.core.command.CartAddItemCommand;
import daniel.zielinski.cqrs.cart.core.command.CartCreateCommand;
import daniel.zielinski.cqrs.cart.core.event.CartAddItemEvent;
import daniel.zielinski.cqrs.cart.core.event.CartCreateEvent;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@NoArgsConstructor
@Aggregate
public class CartAggregate {

    @AggregateIdentifier
    private String customerId;
    private List<CartLine> cartLines;

    @AllArgsConstructor
    private class CartLine {
        private String sku;
        private int quantity;

        public void addQuantity(int quantity) {
            this.quantity += quantity;
        }
    }

    @CommandHandler
    public CartAggregate(CartCreateCommand command) {
        apply(CartCreateEvent.builder().customerId(command.getCustomerId()).build());
    }

    @CommandHandler
    public void CartAggregate(CartAddItemCommand command) {
        apply(CartAddItemEvent.builder()
                .customerId(command.getCustomerId())
                .sku(command.getSku())
                .quantity(command.getQuantity()).build()
        );
    }

    @EventSourcingHandler
    public void onCartCreateCommand(CartCreateEvent event) {
        this.customerId = event.getCustomerId();
        this.cartLines = new ArrayList<>();
    }

    @EventSourcingHandler
    public void onCartAddItemCommand(CartAddItemEvent event) {
        this.cartLines.stream()
                .filter(cartLine -> event.getSku().equals(cartLine.sku))
                .findFirst()
                .ifPresentOrElse(cartLine -> cartLine.addQuantity(cartLine.quantity),
                        () -> this.cartLines.add(new CartLine(event.getSku(), event.getQuantity())));
    }


}
