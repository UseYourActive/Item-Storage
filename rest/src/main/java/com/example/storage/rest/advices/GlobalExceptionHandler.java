package com.example.storage.rest.advices;

import com.example.storage.core.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;

//(basePackageClasses = ) moje da otgovarq za edin specifichen kontroler
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handleRestClientException(RestClientException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred during API call! " + e.getMessage());
    }

    @ExceptionHandler(NotEnoughQuantityOfSelectedItemException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(NotEnoughQuantityOfSelectedItemException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("That was an invalid request input! " + e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleItemNotFoundInRepositoryException(ItemNotFoundInRepositoryException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No item has been found in the repository! " + e.getMessage());
    }
    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleItemAlreadyExistsInTheItemStorageException(ItemAlreadyExistsInTheItemStorageException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This item is already in the repository! " + e.getMessage());
    }
    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleItemStorageException(ItemStorageException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Item storage exception caught! " + e.getMessage());
    }
    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleItemStorageNotFoundException(ItemStorageNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No item has been found in the repository! " + e.getMessage());
    }
    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleNotAllItemIdsFoundInRepositoryException(NotAllItemIdsFoundInRepositoryException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not All item Ids were found in the repository! " + e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleNotEnoughQuantityOfSelectedItemException(NotEnoughQuantityOfSelectedItemException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough quantity in the repository of the item! " + e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleStorageItemIllegalQuantityException(StorageItemIllegalQuantityException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough quantity in the repository of the item! " + e.getMessage());
    }

    @ExceptionHandler(ItemNotFoundInRepositoryException.class)
    public ResponseEntity<String> handleStorageItemNotFoundException(StorageItemNotFoundException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Storage item not found in the repository! " + e.getMessage());
    }
}
