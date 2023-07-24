package com.example.bff.restexport;

import feign.Headers;

@Headers({"Content-Type: application/json"})
public interface StorageRestClient {

    //@RequestLine("GET /items/{request}")

}
