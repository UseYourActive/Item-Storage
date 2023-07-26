package com.example.bff.restexport;

import com.example.storage.api.operations.findbyid.FindItemByIdResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json"})
public interface StorageRestClient {
    @RequestLine("GET /storage/item/find/{id}")
    FindItemByIdResponse getItemById(@Param("id") String id);
}
