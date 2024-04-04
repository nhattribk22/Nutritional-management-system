package com.healthy.food.Service;

import java.util.concurrent.ExecutionException;

import org.conscrypt.OpenSSLCipherRSA.Raw;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.storage.v2.Object;
import com.healthy.food.Ingredients.RawMaterials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            DocumentReference documentReference= dbFirestore.collection(RawMaterials.class.getAnnotation(CollectionName.class).value()).document(name);
            if (documentReference != null) {
                return "Create - Can't create " + name + " because of existing file";
            }
        ApiFuture<WriteResult> writeResult = documentReference.set(rawMaterials);
        return "Create - Successfully create: " + rawMaterials.toString() ; 
    }

    public List<Map<String, RawMaterials>> GetAll() throws InterruptedException, ExecutionException {
        List<String> resList = new ArrayList<String>();
        List<Map<String, RawMaterials>> allList = new ArrayList<Map<String, RawMaterials>>();
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("Ingredient").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            Map<String, RawMaterials> map1 = new HashMap<>();
            resList.add(document.getId());
            
            map1.put(document.getId(), document.toObject(RawMaterials.class));
            allList.add (map1);

        }
        return allList;   
    }
    public List<String> GetAll1() throws InterruptedException, ExecutionException {
        List<String> resList = new ArrayList<String>();
        Firestore db = FirestoreClient.getFirestore();
        // asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future = db.collection("Ingredient").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (QueryDocumentSnapshot document : documents) {
            resList.add(document.getId());
            

        }
        return resList;   
    }
}
