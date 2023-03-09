package daniel.zielinski.cqrs.cart.core.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CartAddItemEvent {

    private String id;
    private String customerId;
    private String sku;
    private int quantity;
}
