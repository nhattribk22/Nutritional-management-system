package com.healthy.food.Service;

import java.util.concurrent.ExecutionException;

import org.conscrypt.OpenSSLCipherRSA.Raw;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.healthy.food.Ingredients.RawMaterials;

@Service
public class FoodService {
    
    public RawMaterials getMaterials(String name) throws InterruptedException, ExecutionException {
        Firestore dbFireStore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFireStore.collection( RawMaterials.class.getAnnotation(CollectionName.class).value()).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();
        RawMaterials rawMaterials;
        if (document.exists()) {
            rawMaterials = document.toObject(RawMaterials.class);
            return rawMaterials;
        }
        return null;
    }

    public String updateMaterials(RawMaterials rawMaterials, String name) throws InterruptedException, ExecutionException {
        if (name.isEmpty()) 
            return "Update - You have to specify a name";
        RawMaterials findMaterials = getMaterials(name);
        if (findMaterials == null ) {
            return "Update - Can Not Found " + name + " in documentation";
        }
        Firestore dbFireStore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFireStore.collection(RawMaterials.class.getAnnotation(CollectionName.class).value() ).document(name).set(rawMaterials);

        return "Update - Successfully updated" + name+ " at: " +  writeResult.get().getUpdateTime().toString();
    }

    public String deleteMaterials(String name) throws InterruptedException, ExecutionException{
        if (name.isEmpty()) 
            return "Delete - You have to specify a name";
        if (getMaterials(name) == null) {
            return "Delete - Can't find " + name + " in the list of materials to delete";
        }
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(RawMaterials.class.getAnnotation(CollectionName.class).value()).document(name).delete();

        return "Delete - Successfully deleted: "  + name;

    }

    public String createMaterials(RawMaterials rawMaterials, String name) throws InterruptedException, ExecutionException {
        if (name.isEmpty()) 
            return "Create - You have to specify a name";

        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(RawMaterials.class.getAnnotation(CollectionName.class).value()).document(name).set(rawMaterials);
        return "Create - Successfully create: " + rawMaterials.toString() ; 
    }
}
