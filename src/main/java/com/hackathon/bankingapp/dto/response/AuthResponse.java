package com.hackathon.bankingapp.dto.response;

import com.hackathon.bankingapp.models.ERole;
import java.util.Objects;

public class AuthResponse {

    private String token;
    private ERole role;

    public AuthResponse(String token, ERole role) {
        this.token = token;
        this.role = role;
    }
    private AuthResponse(Builder builder) {
        this.token = builder.token;
        this.role = builder.role;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public static class Builder {
        private String token;
        private ERole role;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder role(ERole role) {
            this.role = role;
            return this;
        }

        public AuthResponse build() {
            return new AuthResponse(this);
        }
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthResponse that = (AuthResponse) o;
        return Objects.equals(token, that.token) && role == that.role;
    }
    public int hashCode() {
        return Objects.hash(token, role);
    }
}
