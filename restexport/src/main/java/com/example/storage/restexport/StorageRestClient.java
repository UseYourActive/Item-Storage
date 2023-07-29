package com.example.storage.restexport;

import com.example.storage.api.operations.change.price.ChangeStoragePriceRequest;
import com.example.storage.api.operations.change.price.ChangeStoragePriceResponse;
import com.example.storage.api.operations.export.ExportStorageRequest;
import com.example.storage.api.operations.export.ExportStorageResponse;
import com.example.storage.api.operations.findall.FindAllStorageItemResponse;
import com.example.storage.api.operations.findbyid.FindItemByIdResponse;
import com.example.storage.api.operations.importing.ImportStorageRequest;
import com.example.storage.api.operations.importing.ImportStorageResponse;
import com.example.storage.api.operations.register.RegisterNewItemRequest;
import com.example.storage.api.operations.register.RegisterNewItemResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface StorageRestClient {
    @RequestLine("GET /storage/{id}")
    FindItemByIdResponse getItemById(@Param("id") String id);

    @RequestLine("GET /storage")
    FindAllStorageItemResponse findAllStorageItems();

    @RequestLine("POST /storage/register")
    RegisterNewItemResponse registerNewItem(@Param RegisterNewItemRequest request);

    @RequestLine("PATCH storage/import")
    ImportStorageResponse importItem(@Param ImportStorageRequest request);

    @RequestLine("PATCH storage/export")
    ExportStorageResponse exportItem(@Param ExportStorageRequest request);

    @RequestLine("PATCH storage/change-price")
    ChangeStoragePriceResponse changePrice(@Param ChangeStoragePriceRequest request);
}
