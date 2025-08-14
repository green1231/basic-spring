package com.petr.spring.task.online_store.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petr.spring.task.online_store.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class StoreRepository {

    private final Path storagePath;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public StoreRepository(@Value("${store.file}") String storageFile) {
        this.storagePath = Path.of(storageFile);
        try {
            Files.createDirectories(this.storagePath.getParent());
            if (!Files.exists(this.storagePath)) {
                saveUsers(new ArrayList<>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize storage: " + storageFile, e);
        }
    }

    public synchronized List<User> loadUsers() {
        try {
            if (!Files.exists(storagePath) || Files.size(storagePath) == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(storagePath.toFile(), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load users", e);
        }
    }

    public synchronized void saveUsers(List<User> users) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(storagePath.toFile(), users);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save users", e);
        }
    }
}



