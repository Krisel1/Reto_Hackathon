package com.hackathon.bankingapp.dto.request;

public class LoginRequest {
    String identifier;
    String password;

    public LoginRequest() {
    }

    public LoginRequest(String identifier, String password) {
        this.identifier = identifier;
        this.password = password;
    }
    private LoginRequest(Builder builder) {
        this.identifier = builder.identifier;
        this.password = builder.password;
    }

    public String getPassword() {
        return password;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static class Builder {
        private String identifier;
        private String password;

        public Builder identifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginRequest that = (LoginRequest) o;

        if (!identifier.equals(that.identifier)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + identifier + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
