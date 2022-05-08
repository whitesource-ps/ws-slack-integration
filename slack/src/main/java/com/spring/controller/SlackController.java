package com.spring.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.policy.PolicyRejectionModel;
import com.spring.service.SlackService;
import com.ws.api.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.concurrent.CompletableFuture;

@RestController
public class SlackController {

    /* --- Static members --- */

    private static final String POLICY_REJECTION_SUMMARY_JSON = "policyRejectionSummary.json";
    private static final String SLASH_SEPARATOR = "/";
    private static final String TMP_DIR = "java.io.tmpdir";

    /* --- Members --- */

    @Autowired
    private SlackService slackService;

    /* --- Public methods --- */

    @RequestMapping(value = "/checkPolicyViolations", consumes = "multipart/form-data")
    @ResponseBody
    public String checkPolicyViolations(@RequestParam("file") MultipartFile multipartFile,
                                        @RequestParam(value = "project", required = false) String project,
                                        @RequestParam(value = "build", required = false) String build) throws IOException {
        System.out.println("***** Received request for project: " + project + ", build #" + build +" *****");

        File file = new File(System.getProperty(TMP_DIR) + SLASH_SEPARATOR + POLICY_REJECTION_SUMMARY_JSON);
        multipartFile.transferTo(file);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        String json = sb.toString().replace("\\", "\\\\");
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        PolicyRejectionModel models = gson.fromJson(json, PolicyRejectionModel.class);
        if (models != null) {
            // async policies check
            CompletableFuture.runAsync(() -> {
                try {
                    slackService.checkForPolicyViolations(models, project, build);
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        return "[WhiteSource] Successfully received request. Starting to check for policy violations.";
    }

    @RequestMapping("/getSecurityAlerts")
    public void getSecurityAlerts() {
        try {
            slackService.getSecurityAlerts();
        } catch (IOException | ApiException e) {
            System.out.println("Error fetching alerts.\n");
            e.printStackTrace();
        }
    }

    @RequestMapping("/healthCheck")
    public String healthCheck() {
        System.out.println("It's alive!!!");
        return "It's alive!!!";
    }
}
