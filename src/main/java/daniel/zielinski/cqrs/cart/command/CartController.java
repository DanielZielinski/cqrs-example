package daniel.zielinski.cqrs.cart.command;

import daniel.zielinski.cqrs.cart.core.command.CartAddItemCommand;
import daniel.zielinski.cqrs.cart.core.request.CartAddItemRequest;
import daniel.zielinski.cqrs.cart.core.command.CartCreateCommand;
import daniel.zielinski.cqrs.cart.core.request.CartCreateRequest;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CommandGateway commandGateway;

    @PostMapping
    public void createCart(@RequestBody CartCreateRequest cartCreateRequest) {
        commandGateway.send(
                CartCreateCommand.builder()
                        .customerId(cartCreateRequest.getCustomerId())
                        .build());
    }

    @PostMapping("/item")
    public void addProductToCart(@RequestBody CartAddItemRequest cartAddItemRequest) {
        commandGateway.send(
                CartAddItemCommand.builder()
                        .customerId(cartAddItemRequest.getCustomerId())
                        .sku(cartAddItemRequest.getSku())
                        .quantity(cartAddItemRequest.getQuantity())
                        .build());
    }
}
