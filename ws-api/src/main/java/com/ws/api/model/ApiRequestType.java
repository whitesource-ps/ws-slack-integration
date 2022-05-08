package com.ws.api.model;

/**
 * Holds API request types
 */
public enum ApiRequestType {

    GET_COPYRIGHT_FILE_REQUEST {
        @Override
        public String toString() {
            return "getCopyrightsTextFile";
        }
    },
    GET_LICENSES_ZIP_FILE_REQUEST {
        @Override
        public String toString() {
            return "getProjectLicensesTextZip";
        }
    },
    GET_PRODUCT_DUE_DILIGENCE_REPORT {
        @Override
        public String toString() {
            return "getProductDueDiligenceReport";
        }
    },
    GET_PROJECT_DUE_DILIGENCE_REPORT {
        @Override
        public String toString() {
            return "getProjectDueDiligenceReport";
        }
    },
    GET_PROJECT_INVENTORY {
        @Override
        public String toString() {
            return "getProjectInventory";
        }
    },
    GET_VULNERABILITY_OCCURRENCES {
        @Override
        public String toString() {
            return "getVulnerabilityOccurrences";
        }
    },
    GET_ORG_ALERTS_BY_TYPE {
        @Override
        public String toString() {
            return "getOrganizationAlertsByType";
        }
    },
    GET_PROJECT_ALERTS_BY_TYPE {
        @Override
        public String toString() {
            return "getProjectAlertsByType";
        }
    },
    GET_PRODUCT_ALERTS_BY_TYPE {
        @Override
        public String toString() {
            return "getProductAlertsByType";
        }
    },
    GET_PRODUCT_TAGS {
        @Override
        public String toString() {
            return "getProductTags";
        }
    },
    GET_ORGANIZATION_DETAILS {
        @Override
        public String toString() {
            return "getOrganizationDetails";
        }
    }
}
