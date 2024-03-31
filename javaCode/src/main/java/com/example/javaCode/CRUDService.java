package com.example.javaCode;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class CRUDService {

    public String createCRUD(CRUD crud) throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user").document(crud.getDocumentId()).set(crud);

        return collectionApiFuture.get().getUpdateTime().toString();
    }
    
    public CRUD getCRUD(String documentId) throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection("user").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        CRUD crud;
        if (document.exists()) {
            crud = document.toObject(CRUD.class);
            return crud;
        }
        return null;

    }

    public String updateCRUD(CRUD crud) throws InterruptedException, ExecutionException{
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user").document(crud.getDocumentId()).set(crud);
        
        return collectionApiFuture.get().getUpdateTime().toString();
        
    }
    
    public String deleteCRUD(String documentId) {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFireStore.collection("user").document(documentId).delete();
        return "Successfully deleted " + documentId;
    }
}
