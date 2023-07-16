package com.example.storage.api.operations.importing;

import lombok.*;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ImportStorageResponse {
    private UUID item_id;
    private Integer quantity;
}
