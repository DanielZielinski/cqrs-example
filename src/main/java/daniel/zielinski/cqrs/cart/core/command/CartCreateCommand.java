package daniel.zielinski.cqrs.cart.core.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CartCreateCommand {
    @TargetAggregateIdentifier
    private String customerId;

}
