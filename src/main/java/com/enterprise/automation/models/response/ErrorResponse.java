package com.enterprise.automation.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<ValidationError> errors;

    public ErrorResponse() {}

    public ErrorResponse(String timestamp, Integer status, String error, String message, String path, List<ValidationError> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String timestamp;
        private Integer status;
        private String error;
        private String message;
        private String path;
        private List<ValidationError> errors;

        public Builder timestamp(String timestamp) { this.timestamp = timestamp; return this; }
        public Builder status(Integer status) { this.status = status; return this; }
        public Builder error(String error) { this.error = error; return this; }
        public Builder message(String message) { this.message = message; return this; }
        public Builder path(String path) { this.path = path; return this; }
        public Builder errors(List<ValidationError> errors) { this.errors = errors; return this; }

        public ErrorResponse build() {
            return new ErrorResponse(timestamp, status, error, message, path, errors);
        }
    }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public List<ValidationError> getErrors() { return errors; }
    public void setErrors(List<ValidationError> errors) { this.errors = errors; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ValidationError {
        private String field;
        private String message;
        private Object rejectedValue;

        public ValidationError() {}
        public ValidationError(String field, String message, Object rejectedValue) {
            this.field = field;
            this.message = message;
            this.rejectedValue = rejectedValue;
        }

        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Object getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(Object rejectedValue) { this.rejectedValue = rejectedValue; }
    }
}
