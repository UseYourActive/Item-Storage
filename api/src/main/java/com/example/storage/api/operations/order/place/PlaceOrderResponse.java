package com.example.storage.api.operations.order.place;

import com.example.storage.api.base.OperationResult;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlaceOrderResponse implements OperationResult {
    private List<PlaceOrderSingleItem> orders;
    private LocalDateTime timestamp;
    private UUID userId;
    private BigDecimal orderPrice;

}
