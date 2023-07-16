package com.example.storage.api.operations.export;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ExportStorageResponse {
    private UUID id;
    private UUID item_id;
    private Integer quantity;
}
