package daniel.zielinski.cqrs.cart.core.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartCreateEvent {

    private String customerId;
}
