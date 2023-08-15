package com.example.storage.restexport;

import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.storageitem.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.storageitem.checkifhasorders.CheckUserIfHasOrdersResponse;
import com.example.storage.api.operations.storageitem.export.ExportStorageRequest;
import com.example.storage.api.operations.storageitem.export.ExportStorageResponse;
import com.example.storage.api.operations.storageitem.find.all.FindAllStorageItemResponse;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbyid.FindAllStorageItemsByIdResponse;
import com.example.storage.api.operations.storageitem.find.byid.FindItemByIdResponse;
import com.example.storage.api.operations.storageitem.importing.ImportStorageRequest;
import com.example.storage.api.operations.storageitem.importing.ImportStorageResponse;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemRequest;
import com.example.storage.api.operations.storageitem.register.RegisterNewItemResponse;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveRequest;
import com.example.storage.api.operations.storageitem.remove.StorageItemRemoveResponse;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellRequest;
import com.example.storage.api.operations.storageitem.sell.StorageItemsSellResponse;
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
    FindAllStorageItemsByIdResponse findAllStorageItemsById(@Param FindAllStorageItemsByIdRequest request);

    @RequestLine("GET /user/{userId}")
    CheckUserIfHasOrdersResponse checkIfUserHasOrders(@Param String userId);

    @RequestLine("POST /storage/register")
    RegisterNewItemResponse registerNewItem(@Param RegisterNewItemRequest request);

    @RequestLine("PATCH /storage/import")
    ImportStorageResponse importItem(@Param ImportStorageRequest request);

    @RequestLine("PATCH /storage/export")
    ExportStorageResponse exportItem(@Param ExportStorageRequest request);

    @RequestLine("PATCH /storage/change-price")
    ChangeStoragePriceResponse changePrice(@Param ChangeStoragePriceRequest request);

    @RequestLine("PUT /storage/sell")
    StorageItemsSellResponse sellItems(@Param StorageItemsSellRequest storageItemsSellRequest);

    @RequestLine("DELETE /storage/remove")
    StorageItemRemoveResponse removeStorageItem(@Param StorageItemRemoveRequest storageItemRemoveRequest);
    //endregion
}
