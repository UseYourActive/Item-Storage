package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersOperation;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersRequest;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersResponse;
import com.example.storage.persistence.repositories.SoldItemsHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CheckUserIfHasOrdersOperationProcessor implements CheckUserIfHasOrdersOperation {
    private final SoldItemsHistoryRepository soldItemsHistoryRepository;

    @Override
    public CheckUserIfHasOrdersResponse process(CheckUserIfHasOrdersRequest checkUserIfHasOrdersRequest) {
        log.info("Processing CheckUserIfHasOrdersRequest for user with ID: {}", checkUserIfHasOrdersRequest.getUserId());

        Boolean hasOrders = this.soldItemsHistoryRepository.existsByUserId(checkUserIfHasOrdersRequest.getUserId());
        log.info("User with ID {} has orders: {}", checkUserIfHasOrdersRequest.getUserId(), hasOrders);

        return CheckUserIfHasOrdersResponse.builder()
                .hasOrders(hasOrders)
                .build();
    }
}
