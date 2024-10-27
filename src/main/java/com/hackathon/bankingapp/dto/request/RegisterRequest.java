package com.hackathon.bankingapp.dto.request;

import com.hackathon.bankingapp.models.ERole;

public class RegisterRequest {
    String username;
    String email;
    String password;
    ERole role;

    public RegisterRequest() {
    }

    public RegisterRequest(String username, String email, String password, ERole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    private RegisterRequest(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ERole getRole() {
        return role;
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private ERole role;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(ERole role) {
            this.role = role;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegisterRequest that = (RegisterRequest) o;

        if (!username.equals(that.username)) return false;
        if (!email.equals(that.email)) return false;
        if (!password.equals(that.password)) return false;
        return role == that.role;
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
