package com.example.storage.api.operations.findall;

import com.example.storage.api.base.OperationInput;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindAllStorageItemRequest implements OperationInput {
}
