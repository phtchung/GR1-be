package com.example.gr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name="user")
public class User implements UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="name", length = 255)
    private String name;

    @Column(name="email", length = 255)
    private String email;

    public String getPassword() {
        return password;
    }

    @Column(name="password", length = 255)
    private String password;

    @Column(name="phone_number", length = 255)
    private String phoneNumber;

    @Column(name="profile_url")
    private String profileUrl;

    @Column(name="about")
    private String about;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="role")
    private Integer role;

    @Column(name="gender")
    private String gender;

    @Column(name = "create_at")
    @CreatedDate
    private Instant createAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private Instant updateAt;

    public List<Token> getTokens() {
        return tokens;
    }

    @OneToMany(mappedBy = "user")
    private List<Token>tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getUsername() {
        return email;
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


//
//    @OneToMany(mappedBy = "user")
//    private List<Token> tokens;



//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
//    }



}
