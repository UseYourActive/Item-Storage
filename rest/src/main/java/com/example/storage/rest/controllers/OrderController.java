package com.example.storage.rest.controllers;

import com.example.storage.api.operations.order.place.PlaceOrderOperation;
import com.example.storage.api.operations.order.place.PlaceOrderRequest;
import com.example.storage.api.operations.order.place.PlaceOrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final PlaceOrderOperation placeOrderOperation;

    //region GET
    //endregion

    //region POST
    @PostMapping("/place")
    ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody PlaceOrderRequest request) {
        return new ResponseEntity<>(placeOrderOperation.process(request), HttpStatus.OK);
    }
    //endregion

    //region PUT/PATCH
    //endregion

    //region DELETE
    //endregion
}
