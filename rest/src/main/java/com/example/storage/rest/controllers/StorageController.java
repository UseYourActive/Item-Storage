package com.example.storage.rest.controllers;

import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceOperation;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersOperation;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersRequest;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.export.ExportStorageResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageItemOperation;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemOperation;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemRequest;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemResponse;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdOperation;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdResponse;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdOperation;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdRequest;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageRequest;
import com.example.storage.api.operations.storageitem.importing.ImportStorageResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageOperation;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemOperation;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveOperation;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveRequest;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveResponse;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellOperation;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellRequest;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/storage")
public class StorageController {
    private final ImportStorageOperation importStorageOperation;
    private final ExportStorageItemOperation exportStorageItemOperation;
    private final RegisterNewItemOperation registerNewItemOperation;
    private final ChangeStoragePriceOperation changeStoragePriceOperation;
    private final FindItemByIdOperation findItemByIdOperation;
    private final FindAllStorageItemOperation FindAllStorageItemOperation;
    private final FindAllStorageItemsByIdOperation findAllStorageItemsByIdOperation;
    private final StorageItemRemoveOperation storageItemRemoveOperation;
    private final StorageItemsSellOperation storageItemsSellOperation;
    private final CheckUserIfHasOrdersOperation checkUserIfHasOrdersOperation;

    //region GET
    //@RestExport
    @Operation(description = "By the users request by id finds an existing one in the database.",
            summary = "Finds an item by id.")
    @GetMapping(path ="{id}")
    public ResponseEntity<FindItemByIdResponse> findItemById(@PathVariable String id) {
        FindItemByIdRequest build = FindItemByIdRequest.builder().id(UUID.fromString(id)).build();
        return new ResponseEntity<>(findItemByIdOperation.process(build), HttpStatus.OK);
    }

    //@RestExport
    @Operation(description = "When pressed fetches all items from the database.",
            summary = "Finds all items in the database.")
    @GetMapping()
    public ResponseEntity<FindAllStorageItemResponse> findAllStorageItems() {
        FindAllStorageItemRequest build = FindAllStorageItemRequest.builder().build();
        return new ResponseEntity<>(FindAllStorageItemOperation.process(build), HttpStatus.OK);
    }

    //@RestExport
    @Operation(description = "By the given by a user Ids fetches them all if they do exist in the database.",
            summary = "Finds all items with the given ids.")
    @GetMapping(path ="/find-all-by-id/{request}")
    public ResponseEntity<FindAllStorageItemsByIdResponse> findAllStorageItemsById(@PathVariable List<String> request) {
        FindAllStorageItemsByIdRequest build = FindAllStorageItemsByIdRequest.builder()
                .itemIds(request.stream()
                        .map(UUID::fromString)
                        .toList())
                .build();
        return new ResponseEntity<>(findAllStorageItemsByIdOperation.process(build), HttpStatus.OK);
    }

    //@RestExport
    @Operation(description = "Checks if a provided with an id user in the database has any orders.",
            summary = "Check if user has orders.")
    @GetMapping(path ="/user/{userId}")
    public ResponseEntity<CheckUserIfHasOrdersResponse> checkIfUserHasOrders(@PathVariable String userId) {
        CheckUserIfHasOrdersRequest userRequest = CheckUserIfHasOrdersRequest
                .builder()
                .userId(UUID.fromString(userId))
                .build();

        return new ResponseEntity<>(this.checkUserIfHasOrdersOperation.process(userRequest), HttpStatus.OK);
    }
    //endregion

    //region POST
    //@RestExport
    @Operation(description = "From the users request registers a new item that does not exist in the database yet.",
            summary = "Registers a new item.")
    @PostMapping(path ="/register")
    public ResponseEntity<RegisterNewItemResponse> registerNewItem(@Valid @RequestBody RegisterNewItemRequest request){
        return new ResponseEntity<>(registerNewItemOperation.process(request), HttpStatus.CREATED);
    }
    //endregion

    //region PUT/PATCH
    //@RestExport
    @Operation(description = "From the users request imports a given quantity to a given item that already exists in the database.",
            summary = "Imports a given quantity from an existing item.")
    @PatchMapping(path ="/import")
    public ResponseEntity<ImportStorageResponse> importItem(@Valid @RequestBody ImportStorageRequest request){
        return new ResponseEntity<>(importStorageOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "From the users request exports a given quantity to a given item that already exists in the database.",
            summary = "Exports a given quantity from an existing item.")
    @PatchMapping(path ="/export")
    public ResponseEntity<ExportStorageResponse> exportItem(@Valid @RequestBody ExportStorageRequest request) {
        return new ResponseEntity<>(exportStorageItemOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "From the users request changes the price of a given item that already exists in the database.",
            summary = "Changes the price of an item.")
    @PatchMapping(path = "/change-price")
    public ResponseEntity<ChangeStoragePriceResponse> changePrice(@Valid @RequestBody ChangeStoragePriceRequest request){
        return new ResponseEntity<>(changeStoragePriceOperation.process(request), HttpStatus.ACCEPTED);
    }

    //@RestExport
    @Operation(description = "From the users request sells a given by id item from the database.",
            summary = "Sells an item.")
    @Transactional
    @PutMapping(path ="/sell")
    public ResponseEntity<StorageItemsSellResponse> sellItems(@Valid @RequestBody StorageItemsSellRequest storageItemsSellRequest) {
        return new ResponseEntity<>(this.storageItemsSellOperation.process(storageItemsSellRequest), HttpStatus.OK);
    }
    //endregion

    //region DELETE
    //@RestExport
    @Operation(description = "From the users request removes an item from the database.",
            summary = "Removes an item.")
    @DeleteMapping(path ="/remove")
    public ResponseEntity<StorageItemRemoveResponse> removeStorageItem(@Valid @RequestBody StorageItemRemoveRequest storageItemRemoveRequest) {
        return new ResponseEntity<>(this.storageItemRemoveOperation.process(storageItemRemoveRequest), HttpStatus.OK);
    }
    //endregion
}
