package com.ws.api.model;

import java.util.HashMap;
import java.util.Map;

public enum Environment {

    SAAS {
        @Override
        public String toString() {
            return "SaaS - US";
        }
    },
    APP {
        @Override
        public String toString() {
            return "App - US";
        }
    },
    APP_EU {
        @Override
        public String toString() {
            return "App - EU";
        }
    };

    /* --- Private methods --- */

    private static Map<Environment, String> envToStringMap;
    static {
        envToStringMap = new HashMap<>();
        envToStringMap.put(SAAS, "SaaS - US");
        envToStringMap.put(APP, "App - US");
        envToStringMap.put(APP_EU, "App - EU");
    }

    /* --- Public methods --- */

    public static String getEnv(Environment environment) {
        return envToStringMap.get(environment);
    }

    public static Environment create(String environment) {
        switch (environment) {
            case "SaaS - US":
                return SAAS;
            case "App - US":
                return APP;
            case "App - EU":
                return APP_EU;
            default:
                return SAAS;
        }
    }
}
