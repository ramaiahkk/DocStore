package com.takoit.docstore.service;

import com.takoit.docstore.mongodb.model.DocumentData;

public interface DocumentService {
    String insertDocument(DocumentData documentData);
    DocumentData getDocumentData(String id);
    String updateDocument(DocumentData documentData);
    void delete(String docId);

}
