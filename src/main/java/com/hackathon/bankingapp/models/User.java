package com.hackathon.bankingapp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    private String email;
    private String password;
    private String address;
    private int phoneNumber;
    private String accountNumber;
    private String pin;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public User(Long id, String username, String email, String password, String address, int phoneNumber, String accountNumber, String pin, ERole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.role = role;
    }

    public User() {
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role != null) {
            return List.of(new SimpleGrantedAuthority(role.name()));
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public ERole getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(ERole role) {
        this.role = role;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private String address;
        private int phoneNumber;
        private String accountNumber;
        private String pin;
        private ERole role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

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

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(int phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder pin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder role(ERole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(id, username, email, password, address, phoneNumber, accountNumber, pin, role);

        }
    }
}
