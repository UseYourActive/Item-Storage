package com.example.storage.restexport;

import com.example.storage.api.operations.order.place.PlaceOrderRequest;
import com.example.storage.api.operations.order.place.PlaceOrderResponse;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemRequest;
import com.example.storage.api.operations.storageitem.create.CreateStorageItemResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.export.ExportStorageResponse;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemResponse;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdResponse;
import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagResponse;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageRequest;
import com.example.storage.api.operations.storageitem.importing.ImportStorageResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface StorageRestClient {
    //region StorageItem
    @RequestLine("GET /storage/{id}")
    FindItemByIdResponse findItemById(@Param("id") String id);

    @RequestLine("GET /storage")
    FindAllStorageItemResponse findAllStorageItems();

    @RequestLine("GET /storage/find-all-by-id")
    FindAllStorageItemsByIdResponse findAllStorageItemsById(); //TODO add needed arguments

    @RequestLine("GET /storage/find-all-by-tag")
    FindAllItemsByTagResponse findAllStorageItemsByTag(); //TODO add needed arguments

    @RequestLine("POST /storage/register")
    RegisterNewItemResponse registerNewItem(@Param RegisterNewItemRequest request);

    @RequestLine("POST /storage/create")
    CreateStorageItemResponse createStorageItem(@Param CreateStorageItemRequest request);

    @RequestLine("PATCH /storage/import")
    ImportStorageResponse importItem(@Param ImportStorageRequest request);

    @RequestLine("PATCH /storage/export")
    ExportStorageResponse exportItem(@Param ExportStorageRequest request);

    @RequestLine("PATCH /storage/change-price")
    ChangeStoragePriceResponse changePrice(@Param ChangeStoragePriceRequest request);
    //endregion

    //region Order
    @RequestLine("POST /order/place")
    PlaceOrderResponse placeOrder(@Param PlaceOrderRequest request);
    //endregion
}
