package com.example.storage.core.processors;

import com.example.storage.api.operations.findallitemsbytag.FindAllItemsByTagOperation;
import com.example.storage.api.operations.findallitemsbytag.FindAllItemsByTagRequest;
import com.example.storage.api.operations.findallitemsbytag.FindAllItemsByTagResponse;
import com.example.storage.persistence.entities.ItemStorage;
import com.example.storage.persistence.repositories.StorageRepository;
import com.example.zoostore.restexport.ZooStoreRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindAllItemsByTagOperationProcessor implements FindAllItemsByTagOperation {
    private final StorageRepository storageRepository;
    private final ZooStoreRestClient zooStoreRestClient;

    @Override
    public FindAllItemsByTagResponse process(FindAllItemsByTagRequest findAllItemsByTagRequest) {


        return null;
    }
}
