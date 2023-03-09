package daniel.zielinski.cqrs.cart.core.request;

import lombok.Data;

@Data
public class CartAddItemRequest {

    private String customerId;
    private String sku;
    private int quantity;

}
