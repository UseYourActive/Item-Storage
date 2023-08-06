package com.example.storage.api.operations.order.place;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.util.List;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceOrderResponse implements OperationResult {
    private List<PlaceOrderSingleItem> orders;
}
