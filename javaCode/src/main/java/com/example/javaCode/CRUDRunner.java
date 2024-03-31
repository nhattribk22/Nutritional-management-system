package com.example.javaCode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;


@SpringBootApplication
public class CRUDRunner {
	public static void main(String[] args) throws IOException {
		// ClassLoader classLoader = CRUDRunner.class.getClassLoader();

		// File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		// FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
		// ClassLoader classLoader = CRUDRunner.class.getClassLoader();
		// String jsonFilePath = "serviceAccountKey.json";
		// File file = new File(new File(classLoader.getResource(jsonFilePath).getFile()), jsonFilePath);
		// FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
		FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.build();

		FirebaseApp.initializeApp(options);
		SpringApplication.run(CRUDRunner.class, args);
		
	}
}
