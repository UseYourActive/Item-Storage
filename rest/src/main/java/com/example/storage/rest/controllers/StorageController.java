package com.example.storage.rest.controllers;

import com.example.storage.api.operations.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.change.price.ChangeStoragePriceOperation;
import com.example.storage.api.operations.export.ExportStorageRequest;
import com.example.storage.api.operations.export.ExportStorageResponse;
import com.example.storage.api.operations.export.ExportStorageOperation;
import com.example.storage.api.operations.importing.ImportStorageRequest;
import com.example.storage.api.operations.importing.ImportStorageResponse;
import com.example.storage.api.operations.importing.ImportStorageOperation;
import com.example.storage.api.operations.register.RegisterNewItemRequest;
import com.example.storage.api.operations.register.RegisterNewItemResponse;
import com.example.storage.api.operations.register.RegisterNewItemOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage/item")
public class StorageController {
    private final ImportStorageOperation importStorageService;
    private final ExportStorageOperation exportStorageService;
    private final RegisterNewItemOperation registerNewItemService;
    private final ChangeStoragePriceOperation changeStoragePriceService;

    @Operation(description = "From the users request imports a given quantity to a given item that already exists in the database.",
            summary = "Imports a given quantity from an existing item.")
    @PatchMapping("/import")
    public ResponseEntity<ImportStorageResponse> importItem(@Valid @RequestBody ImportStorageRequest request){
        return new ResponseEntity<>(importStorageService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "From the users request exports a given quantity to a given item that already exists in the database.",
            summary = "Exports a given quantity from an existing item.")
    @PatchMapping("/export")
    public ResponseEntity<ExportStorageResponse> exportItem(@Valid @RequestBody ExportStorageRequest request) {
        return new ResponseEntity<>(exportStorageService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "From the users request registers a new item that does not exist in the database yet.",
            summary = "Registers a new item.")
    @PostMapping("/register")
    public ResponseEntity<RegisterNewItemResponse> registerNewItem(@Valid @RequestBody RegisterNewItemRequest request){
        return new ResponseEntity<>(registerNewItemService.process(request), HttpStatus.ACCEPTED);
    }

    @Operation(description = "From the users request changes the price of a given item that already exists in the database.",
            summary = "Changes the price of an item.")
    @PutMapping("/change-price")
    public ResponseEntity<ChangeStoragePriceResponse> changePrice(@Valid @RequestBody ChangeStoragePriceRequest request){
        return new ResponseEntity<>(changeStoragePriceService.process(request), HttpStatus.ACCEPTED);
    }


}
