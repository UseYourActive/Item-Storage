package com.example.storage.api.operations.storageitem.checkifhasorders;

import com.example.storage.api.base.OperationResult;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CheckUserIfHasOrdersResponse implements OperationResult {
    private Boolean hasOrders;
}
