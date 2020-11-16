// package com.example.demo.model;

// import java.util.Arrays;
// import java.util.Collection;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;

// public class CuserDetails implements UserDetails {
//     String username;
//     String password;
//     String email;
//     List<SimpleGrantedAuthority> authority;

//     @Autowired
//     private PasswordEncoder bcrypt;

//     public CuserDetails(Cuser user) {
//         System.out.println(user.toString());
//         this.username = user.getUsername();
//         this.password = user.getPswd();
//         this.email = user.getEmail();
//         this.authority = Arrays.stream(user.getRoles().split(","))
//                         .map(SimpleGrantedAuthority::new)
//                         .collect(Collectors.toList());
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return this.authority;
//     }

//     @Override
//     public String getPassword() {
//         return this.password;
//     }

//     @Override
//     public String getUsername() {
//         return this.username;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
    
// }