package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.export.ExportStorageItemOperation;
import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellOperation;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellRequest;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellResponse;
import com.example.storage.persistence.entities.SoldItemsHistory;
import com.example.storage.persistence.repositories.SoldItemsHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class StorageItemsSellOperationProcessor implements StorageItemsSellOperation {
    private final ExportStorageItemOperation exportStorageItemOperation;
    private final SoldItemsHistoryRepository soldItemsHistoryRepository;


    @Override
    public StorageItemsSellResponse process(StorageItemsSellRequest storageItemsSellRequest) {
        UUID userId = storageItemsSellRequest.getUserId();

        storageItemsSellRequest.getItems()
                .forEach(item -> {
                    String itemId = item.getItemId();
                    BigDecimal price = item.getPrice();
                    BigDecimal priceWithDiscount = item.getPriceWithDiscount();
                    Integer quantity = item.getQuantity();

                    ExportStorageRequest itemExport = ExportStorageRequest
                            .builder()
                            .id(UUID.fromString(itemId))
                            .quantity(quantity)
                            .build();

                    this.exportStorageItemOperation.process(itemExport);

                    SoldItemsHistory itemHistory = SoldItemsHistory
                            .builder()
                            .userId(userId)
                            .itemId(UUID.fromString(itemId))
                            .priceWithDiscount(priceWithDiscount)
                            .price(price)
                            .quantity(quantity)
                            .build();

                    this.soldItemsHistoryRepository.save(itemHistory);
                });

        return StorageItemsSellResponse
                .builder()
                .items(storageItemsSellRequest.getItems())
                .build();
    }
}
