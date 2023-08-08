package com.example.storage.core.processors.order;

import com.example.storage.api.operations.order.place.*;
import com.example.storage.core.exceptions.ItemNotFoundInRepositoryException;
import com.example.storage.core.exceptions.ItemStorageNotFoundException;
import com.example.storage.core.exceptions.NotEnoughQuantityOfSelectedItemException;
import com.example.storage.persistence.entities.Order;
import com.example.storage.persistence.entities.OrderItem;
import com.example.storage.persistence.entities.StorageItem;
import com.example.storage.persistence.repositories.OrderRepository;
import com.example.storage.persistence.repositories.StorageItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class PlaceOrderOperationProcessor implements PlaceOrderOperation {
    private final StorageItemRepository storageItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public PlaceOrderResponse process(PlaceOrderRequest placeOrderRequest) {

        placeOrderRequest.getCartItems()
                .stream()
                .map(this::mapPlaceOrderInputCartItemToOrderItem)
                .map(this::verifyItemExists)
                .forEach(this::exportCartItem);

        Set<OrderItem> orderItems = placeOrderRequest.getCartItems()
                .stream()
                .map(this::mapPlaceOrderInputCartItemToOrderItem)
                .collect(Collectors.toSet());

        Order order = Order.builder()
                .timestamp(LocalDateTime.now(Clock.systemUTC()))
                .user(UUID.fromString(String.valueOf(placeOrderRequest.getUserId())))
                .items(orderItems)
                .orderPrice(orderItems.stream()
                        .map(this::getItemPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                .build();

        Order persistedOrder = this.orderRepository.save(order);

        return PlaceOrderResponse.builder()
                .items(persistedOrder.getItems().stream().map(this::mapOrderItemToPlaceOrderResultSingleItem).toList())
                .timestamp(persistedOrder.getTimestamp())
                .user(persistedOrder.getUser())
                .orderPrice(persistedOrder.getOrderPrice().doubleValue())
                .build();

//        return new PlaceOrderResponse(placeOrderRequest.getCartItems().stream()
//                .map(this::exportItem)
//                .toList()
//        );
    }

    private BigDecimal getItemPrice(OrderItem orderItem) {
        return orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
    }

    private void exportCartItem(OrderItem orderItem) {
        StorageItem storageItem = this.storageItemRepository
                .findStorageItemById(orderItem.getReferencedItemId())
                .orElseThrow(ItemStorageNotFoundException::new);

        Integer difference = storageItem.getQuantity() - orderItem.getQuantity();

        if (difference < 0) {
            throw new NotEnoughQuantityOfSelectedItemException();
        }

        storageItem.setQuantity(difference);
        this.storageItemRepository.save(storageItem);
    }

    private OrderItem verifyItemExists(OrderItem orderItem) {
        UUID itemId = orderItem.getReferencedItemId();

        if (!this.storageItemRepository.existsItemStorageByTargetItemId(itemId)) {
            throw new ItemStorageNotFoundException();
        }

        return orderItem;
    }

    private OrderItem mapPlaceOrderInputCartItemToOrderItem(PlaceOrderInputCartItem item) {
        return OrderItem.builder()
                .referencedItemId(item.getReferencedItemId())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();
    }

    private PlaceOrderSingleItem mapToPlaceOrderSingleItem(OrderItem orderItem){
        return PlaceOrderSingleItem.builder()
                .referencedItemId(orderItem.getReferencedItemId())
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .build();
    }

//    private PlaceOrderSingleItem exportItem(PlaceOrderInputCartItem cartItem) {
//        StorageItem item = this.storageItemRepository
//                .findById(cartItem.getReferencedItemId())
//                .orElseThrow(ItemNotFoundInRepositoryException::new);
//
//        int quantityDifference = item.getQuantity() - cartItem.getQuantity();
//
//        if (quantityDifference < 0) {
//            throw new NotEnoughQuantityOfSelectedItemException();
//        }
//
//        item.setQuantity(quantityDifference);
//
//        StorageItem persistedStorageItem = this.storageItemRepository.save(item);
//
//        Order order = Order.builder()
//                .referencedItemId(persistedStorageItem.getTargetItemId())
//                .price(cartItem.getPrice())
//                .quantity(cartItem.getQuantity())
//                .timestamp(LocalDateTime.now(Clock.systemUTC()))
//                //.user(cartItem.getUserId())
//                .build();
//
//        Order persistedOrder = this.orderRepository.save(order);
//
//        return PlaceOrderSingleItem.builder()
//                .id(persistedOrder.getId())
//                .referencedItemId(persistedOrder.getReferencedItemId())
//                .price(persistedOrder.getPrice().doubleValue())
//                .quantity(persistedOrder.getQuantity())
//                .timestamp(persistedOrder.getTimestamp())
//                .userId(persistedOrder.getUser())
//                .build();
//    }
}
