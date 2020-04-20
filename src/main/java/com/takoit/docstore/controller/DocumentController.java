package com.takoit.docstore.controller;

import com.takoit.docstore.mongodb.model.DocumentData;
import com.takoit.docstore.service.DocumentService;
import com.takoit.docstore.util.DocumentStoreUtil;
import org.bson.internal.UuidHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.netty.http.server.HttpServerResponse;

import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

/**
 * Create - POST /storage/documents
 * Query - GET /storage/documents/{docId}
 * Update - PUT /storage/documents/{docId}
 * Delete - DELETE /storage/documents/{docId}
 */
@RestController
@RequestMapping("/storage/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String uploadDocument(@RequestBody String requestBody, HttpServletResponse httpServletResponse) {
        DocumentData documentData = new DocumentData();
        documentData.setContent(requestBody);
        String docId = documentService.insertDocument(documentData);
        httpServletResponse.setStatus(201);
        return docId;
    }

    @RequestMapping(value = "/{docId}", method = RequestMethod.GET)
    public String queryDocument(@PathVariable String docId) {
        return documentService.getDocumentData(docId).getContent();
    }

    @RequestMapping(value = "/{docId}", method = RequestMethod.PUT)
    public void updateDocument(@PathVariable String docId, @RequestBody String requestBody, HttpServletResponse httpServletResponse) {

        DocumentData documentData = documentService.getDocumentData(docId);
        documentData.setContent(requestBody);
        documentService.updateDocument(documentData);
        httpServletResponse.setStatus(204);

    }

    @RequestMapping(value = "/{docId}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable String docId, HttpServletResponse response) {
        documentService.delete(docId);
        response.setStatus(204);
    }


}


