package com.takoit.docstore.mongodb.repository;

import com.takoit.docstore.mongodb.model.DocumentData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<DocumentData, String> {
}
