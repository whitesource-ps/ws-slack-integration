package com.ws;

import com.ws.api.exception.ApiException;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class Api_tests {

    /* --- Test methods --- */
    // TODO rethink about the userKeys

    @Test
    @Ignore
    public void failApiConnection_test() throws IOException, ApiException {
        String userKey = "";
        String productToken = "";
        String url = "https://saas.whitesourcesoftware.com";
        String dir = "C:\\Users";
//        ApiRequestFactory.getCopyrightsTextFile(userKey, productToken, url, dir);
    }

    @Test
    public void wrongUserKey_test() {
        String userKey = "wrong_user_key";
        String productToken = "";
        String url = "https://saas.whitesourcesoftware.com";
        String dir = "C:\\Users";
//        try {
//            ApiRequestFactory.getCopyrightsTextFile(userKey, productToken, url, dir);
//        } catch (IOException | ApiException e) {
//            Assert.assertEquals(e.getMessage(), "Failed to execute getCopyrightsTextFile. Got error code: 5001, " +
//                    "error message: User is not allowed to perform this action");
//        }
    }

    @Test
    public void wrongProductToken_test() {
        String userKey = "dcaae87030cd46f78d5fa8b5dc9a7762d4c1adc17d264293bcad57d005bdf228";
        String productToken = "18b825e5e970443580c4079a95c0d64654522cbf25de4bcba0b1133e503e73c8";
        String url = "https://saas.whitesourcesoftware.com";
        String dir = "C:\\Users";
//        try {
//            ApiRequestFactory.getCopyrightsTextFile(userKey, productToken, url, dir);
//        } catch (IOException | ApiException e) {
//            Assert.assertEquals(e.getMessage(), "Failed to execute getCopyrightsTextFile. Got error code: 5001, " +
//                    "error message: User is not allowed to perform this action");
//        }
    }

    @Test
    public void wrongUrl_test() {

    }

}
