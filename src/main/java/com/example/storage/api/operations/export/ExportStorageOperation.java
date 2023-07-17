package com.example.storage.api.operations.export;

import com.example.storage.core.exceptions.NotEnoughQuantityOfSelectedItemException;

public interface ExportStorageOperation {
    ExportStorageResponse exportItem(ExportStorageRequest request) throws NotEnoughQuantityOfSelectedItemException;
}
