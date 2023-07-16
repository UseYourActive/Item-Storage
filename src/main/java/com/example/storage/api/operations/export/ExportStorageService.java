package com.example.storage.api.operations.export;

import com.example.storage.exceptions.NotEnoughQuantityOfSelectedItemException;

public interface ExportStorageService {
    ExportStorageResponse exportItem(ExportStorageRequest request) throws NotEnoughQuantityOfSelectedItemException;
}
