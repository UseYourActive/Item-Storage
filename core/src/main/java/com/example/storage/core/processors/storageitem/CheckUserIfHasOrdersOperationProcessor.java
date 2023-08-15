package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersOperation;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersRequest;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersResponse;
import com.example.storage.persistence.repositories.SoldItemsHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckUserIfHasOrdersOperationProcessor implements CheckUserIfHasOrdersOperation {
    private final SoldItemsHistoryRepository soldItemsHistoryRepository;

    @Override
    public CheckUserIfHasOrdersResponse process(CheckUserIfHasOrdersRequest checkUserIfHasOrdersRequest) {
        Boolean hasOrders = this.soldItemsHistoryRepository.existsByUserId(checkUserIfHasOrdersRequest.getUserId());

        return CheckUserIfHasOrdersResponse
                .builder()
                .hasOrders(hasOrders)
                .build();
    }
}
