package com.ryuqq.core.external.buyma.response;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyMaResponse<T> {

    private T t;

    public T getT() {
        return t;
    }

    public static class Success {
        @JsonProperty("request_received_at")
        private LocalDateTime requestReceivedAt;
        @JsonProperty("request_uid")
        private String requestUid;

        public Success() {}

        public Success(LocalDateTime requestReceivedAt, String requestUid) {
            this.requestReceivedAt = requestReceivedAt;
            this.requestUid = requestUid;
        }

        public String getRequestUid() {
            return requestUid;
        }

        @Override
        public String toString() {
            return "Success{" +
                    "requestReceivedAt=" + requestReceivedAt +
                    ", requestUid='" + requestUid + '\'' +
                    '}';
        }
    }

    public static class Failure {
        private Map<String, String[]> errors;

        public Failure() {}

        public Failure(Map<String, String[]> errors) {
            this.errors = errors;
        }

        public Map<String, String[]> getErrors() {
            return errors;
        }

        @Override
        public String toString() {
            return "Failure{" +
                    "errors=" + errors +
                    '}';
        }
    }
}
