package com.healthy.food;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class FoodApplication {

	public static void main(String[] args) throws IOException {
		FileInputStream serviceAccount =
	new FileInputStream("firebasekey.json");

	FirebaseOptions options = new FirebaseOptions.Builder()
	.setCredentials(GoogleCredentials.fromStream(serviceAccount))
	.build();

	FirebaseApp.initializeApp(options);

	SpringApplication.run(FoodApplication.class, args);
	}

}
