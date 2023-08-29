package com.example.storage.api.operations.storageitem.checkifhasorders;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class CheckUserIfHasOrdersRequest implements OperationInput {
    private final String userId;
}
