package com.takoit.docstore.util;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

public class DocumentStoreUtil {
    public static String getDocId(){
        SecureRandom rand = new SecureRandom();
        byte[] randomBytes = new byte[20];
        rand.nextBytes(randomBytes);
        UUID.nameUUIDFromBytes(randomBytes);
        String docId = new String(Base64.getEncoder().encode(UUID.nameUUIDFromBytes(randomBytes).toString().getBytes()));
        docId =  docId.substring(0,20).toUpperCase();
        return docId;
    }
}
