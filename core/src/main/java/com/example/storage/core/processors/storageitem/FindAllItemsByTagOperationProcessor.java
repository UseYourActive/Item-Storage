package com.example.storage.core.processors.storageitem;

import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagOperation;
import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagRequest;
import com.example.storage.api.operations.storageitem.find.allitemsbytag.FindAllItemsByTagResponse;
import com.example.storage.persistence.repositories.StorageItemRepository;
import com.example.zoostore.restexport.ZooStoreRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindAllItemsByTagOperationProcessor implements FindAllItemsByTagOperation {
    private final StorageItemRepository storageRepository;
    private final ZooStoreRestClient zooStoreRestClient;

    @Override
    public FindAllItemsByTagResponse process(FindAllItemsByTagRequest findAllItemsByTagRequest) {


        return null;
    }
}
