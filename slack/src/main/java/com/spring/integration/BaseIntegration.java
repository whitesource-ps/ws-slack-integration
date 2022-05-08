package com.spring.integration;

import com.spring.model.VulnerabilityInfo;
import com.spring.policy.PolicyRejectionModel;
import com.spring.response.BaseResponse;

import java.util.Collection;
import java.util.Map;

public interface BaseIntegration {

    BaseResponse createNewPublishedVulnerabilitiesResponse(Collection<VulnerabilityInfo> vulnerabilities, String productToken,
                                                           String productName, String wssUrl, Map<String, Collection<Integer>> libraryOccurrencesMap);

    BaseResponse createPolicyViolationsResponse(PolicyRejectionModel policyRejectionModel, String wssUrl, String job, String build);
}
