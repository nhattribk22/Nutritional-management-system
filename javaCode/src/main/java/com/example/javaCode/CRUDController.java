package com.example.javaCode;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController

public class CRUDController {
    
    public CRUDService crudService;

    public CRUDController(CRUDService crudService) {
        this.crudService = crudService;
    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException 
    {
        //TODO: process POST request
        
        return crudService.createCRUD(crud);
    }        

    @GetMapping("/get")
    public CRUD getCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        return crudService.getCRUD(documentId);
    }
    
    @PutMapping("/update")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        //TODO: process PUT request
        
        return crudService.updateCRUD(crud);
    }

    @PutMapping("/delete")
    public String deleteCRUD(@RequestParam String documentId) throws InterruptedException, ExecutionException{
        //TODO: process PUT request
        
        return crudService.deleteCRUD(documentId);
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> testGetEndpoint() {
        return ResponseEntity.ok("Test Get Endpoint is working");
    }    
}
