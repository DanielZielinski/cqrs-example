package daniel.zielinski.cqrs.cart.core.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CartAddItemCommand {

    @TargetAggregateIdentifier
    private String customerId;
    private String sku;
    private int quantity;
}
