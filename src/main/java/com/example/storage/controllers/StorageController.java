package com.example.storage.controllers;

import com.example.storage.api.operations.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.change.price.ChangeStoragePriceService;
import com.example.storage.api.operations.export.ExportStorageRequest;
import com.example.storage.api.operations.export.ExportStorageResponse;
import com.example.storage.api.operations.export.ExportStorageService;
import com.example.storage.api.operations.importing.ImportStorageRequest;
import com.example.storage.api.operations.importing.ImportStorageResponse;
import com.example.storage.api.operations.importing.ImportStorageService;
import com.example.storage.api.operations.register.RegisterNewItemRequest;
import com.example.storage.api.operations.register.RegisterNewItemResponse;
import com.example.storage.api.operations.register.RegisterNewItemService;
import com.example.storage.exceptions.NotEnoughQuantityOfSelectedItemException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage/item")
public class StorageController {
    private final ImportStorageService importStorageService;
    private final ExportStorageService exportStorageService;
    private final RegisterNewItemService registerNewItemService;
    private final ChangeStoragePriceService changeStoragePriceService;

    @PatchMapping("/import")
    public ResponseEntity<ImportStorageResponse> importItem(@RequestBody ImportStorageRequest request){
        return new ResponseEntity<>(importStorageService.importItem(request), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/export")
    public ResponseEntity<ExportStorageResponse> exportItem(@RequestBody ExportStorageRequest request) throws NotEnoughQuantityOfSelectedItemException {
        return new ResponseEntity<>(exportStorageService.exportItem(request), HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterNewItemResponse> registerNewItem(@RequestBody RegisterNewItemRequest request){
        return new ResponseEntity<>(registerNewItemService.registerNewItem(request), HttpStatus.ACCEPTED);
    }

    @PutMapping("/change-price")
    public ResponseEntity<ChangeStoragePriceResponse> changePrice(@RequestBody ChangeStoragePriceRequest request){
        return new ResponseEntity<>(changeStoragePriceService.changePrice(request), HttpStatus.ACCEPTED);
    }
}
