package com.example.storage.api.operations.storageitem.find.byid;

import com.example.storage.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class FindItemByIdRequest implements OperationInput {
    private final String id;
}
