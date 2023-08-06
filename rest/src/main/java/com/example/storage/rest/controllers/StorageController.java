package com.example.storage.rest.controllers;

import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceOperation;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemOperation;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemRequest;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.export.ExportStorageResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageOperation;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemOperation;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemRequest;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemResponse;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdOperation;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdResponse;
import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagOperation;
import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagResponse;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdOperation;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdRequest;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageRequest;
import com.example.storage.api.operations.storageitem.importing.ImportStorageResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageOperation;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageController {
    private final CreateStorageItemOperation createStorageItemOperation;
    private final ImportStorageOperation importStorageService;
    private final ExportStorageOperation exportStorageService;
    private final RegisterNewItemOperation registerNewItemService;
    private final ChangeStoragePriceOperation changeStoragePriceService;
    private final FindItemByIdOperation findItemByIdOperation;
    private final FindAllStorageItemOperation FindAllStorageItemOperation;
    private final FindAllStorageItemsByIdOperation findAllStorageItemsByIdOperation;
    private final FindAllItemsByTagOperation findAllItemsByTagOperation;

    //region GET
    @GetMapping("{id}")
    public ResponseEntity<FindItemByIdResponse> getItemByReferencedItemId(@PathVariable String id) {
        FindItemByIdRequest build = FindItemByIdRequest.builder().id(UUID.fromString(id)).build();
        return new ResponseEntity<>(findItemByIdOperation.process(build), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<FindAllStorageItemResponse> findAllStorageItems() {
        FindAllStorageItemRequest build = FindAllStorageItemRequest.builder().build();
        return new ResponseEntity<>(FindAllStorageItemOperation.process(build), HttpStatus.OK);
    }

    @GetMapping("/find-all-by-id")
    public ResponseEntity<FindAllStorageItemsByIdResponse> findAllStorageItemsById() { //TODO add needed arguments
        FindAllStorageItemsByIdRequest build = FindAllStorageItemsByIdRequest.builder().build();
        return new ResponseEntity<>(findAllStorageItemsByIdOperation.process(build), HttpStatus.OK);
    }

    @GetMapping("/find-all-by-tag")
    public ResponseEntity<FindAllItemsByTagResponse> findAllStorageItemsByTag() { //TODO add needed arguments
        FindAllItemsByTagRequest build = FindAllItemsByTagRequest.builder().build();
        return new ResponseEntity<>(findAllItemsByTagOperation.process(build), HttpStatus.OK);
    }
    //endregion

    //region POST
    @Operation(description = "From the users request registers a new item that does not exist in the database yet.",
            summary = "Registers a new item.")
    @PostMapping("/register")
    public ResponseEntity<RegisterNewItemResponse> registerNewItem(@Valid @RequestBody RegisterNewItemRequest request){
        return new ResponseEntity<>(registerNewItemService.process(request), HttpStatus.CREATED);
    }

    @Operation(description = "From the users request registers a new item that does not exist in the database yet.",
            summary = "Registers a new item.")
    @PostMapping("/create")
    public ResponseEntity<CreateStorageItemResponse> createStorageItem(@Valid @RequestBody CreateStorageItemRequest request){
        return new ResponseEntity<>(createStorageItemOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
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

    @Operation(description = "From the users request changes the price of a given item that already exists in the database.",
            summary = "Changes the price of an item.")
    @PatchMapping("/change-price")
    public ResponseEntity<ChangeStoragePriceResponse> changePrice(@Valid @RequestBody ChangeStoragePriceRequest request){
        return new ResponseEntity<>(changeStoragePriceService.process(request), HttpStatus.ACCEPTED);
    }


    //endregion

    //region DELETE
    //endregion





}
