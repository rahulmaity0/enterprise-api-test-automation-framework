package com.enterprise.automation.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthRequest {
    private String username;
    private String password;
    private String grantType;
    private String clientId;

    public AuthRequest() {}

    public AuthRequest(String username, String password, String grantType, String clientId) {
        this.username = username;
        this.password = password;
        this.grantType = grantType;
        this.clientId = clientId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private String grantType;
        private String clientId;

        public Builder username(String username) { this.username = username; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder grantType(String grantType) { this.grantType = grantType; return this; }
        public Builder clientId(String clientId) { this.clientId = clientId; return this; }

        public AuthRequest build() {
            return new AuthRequest(username, password, grantType, clientId);
        }
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getGrantType() { return grantType; }
    public void setGrantType(String grantType) { this.grantType = grantType; }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
}
