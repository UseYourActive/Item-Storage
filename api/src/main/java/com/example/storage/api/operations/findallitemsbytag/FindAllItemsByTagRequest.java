package com.example.storage.api.operations.findallitemsbytag;

import com.example.storage.api.base.OperationInput;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FindAllItemsByTagRequest implements OperationInput {
    private UUID tagId;
    private Integer pageNumber;
    private Integer numberOfItemsPerPage;
}
