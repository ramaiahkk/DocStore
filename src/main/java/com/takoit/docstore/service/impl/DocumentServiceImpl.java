package com.takoit.docstore.service.impl;

import com.takoit.docstore.mongodb.model.DocumentData;
import com.takoit.docstore.mongodb.repository.DocumentRepository;
import com.takoit.docstore.service.DocumentService;
import com.takoit.docstore.util.DocumentStoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    DocumentRepository documentRepository;

    @Override
    public String insertDocument(DocumentData documentData) {
        documentData.setId(DocumentStoreUtil.getDocId());
        documentRepository.insert(documentData);
        return documentData.getId();
    }

    @Override
    public DocumentData getDocumentData(String id) {
        Optional<DocumentData> optionalDocumentData = documentRepository.findById(id);
        if (optionalDocumentData.isPresent() && optionalDocumentData.get()!=null) {
            return optionalDocumentData.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No file found");
        }
    }

    @Override
    public String updateDocument(DocumentData documentData) {
        return documentRepository.save(documentData).getId();
    }

    @Override
    public void delete(String docId) {
        documentRepository.delete(getDocumentData(docId));
    }


}
