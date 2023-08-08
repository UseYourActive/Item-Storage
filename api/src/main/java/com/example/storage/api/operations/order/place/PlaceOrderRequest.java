package com.example.storage.api.operations.order.place;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceOrderRequest implements OperationInput {
    private List<PlaceOrderInputCartItem> cartItems;
    private UUID userId;
}
