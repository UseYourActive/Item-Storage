package com.example.storage.core.processors.order;

import com.example.storage.api.operations.order.place.*;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.core.exceptions.NotEnoughQuantityOfSelectedItemException;
import com.example.storage.persistence.entities.Order;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.OrderRepository;
import com.example.storage.persistence.repositories.StorageItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class PlaceOrderOperationProcessor implements PlaceOrderOperation {
    private final StorageItemRepository storageItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public PlaceOrderResponse process(PlaceOrderRequest placeOrderRequest) {
        return new PlaceOrderResponse(placeOrderRequest.getCartItems().stream()
                .map(this::exportItem)
                .toList()
        );
    }

    private PlaceOrderSingleItem exportItem(PlaceOrderInputCartItem cartItem) {
        StorageItem item = this.storageItemRepository
                .findById(cartItem.getReferencedItemId())
                .orElseThrow(ItemNotFoundInRepositoryException::new);

        int quantityDifference = item.getQuantity() - cartItem.getQuantity();

        if (quantityDifference < 0) {
            throw new NotEnoughQuantityOfSelectedItemException();
        }

        item.setQuantity(quantityDifference);

        StorageItem persistedStorageItem = this.storageItemRepository.save(item);

        Order order = Order.builder()
                .referencedItemId(persistedStorageItem.getTargetItemId())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .timestamp(LocalDateTime.now(Clock.systemUTC()))
                .user(cartItem.getUserId())
                .build();

        Order persistedOrder = this.orderRepository.save(order);

        return PlaceOrderSingleItem.builder()
                .id(persistedOrder.getId())
                .referencedItemId(persistedOrder.getReferencedItemId())
                .price(persistedOrder.getPrice().doubleValue())
                .quantity(persistedOrder.getQuantity())
                .timestamp(persistedOrder.getTimestamp())
                .userId(persistedOrder.getUser())
                .build();
    }
}
