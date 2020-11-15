package com.example.demo.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.model.Cuser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTconfig {
    private String SECRET_KEY = "tugascloud";
    private Date expirationDate;
    private String username;
    private boolean isExpired;

    private Boolean Tokenvalid(String token,Cuser user){
        return false;
    }

    private String CreateToken(Map<String,Object> claims,String Subject){
        return Jwts.builder().setClaims(claims).setSubject(Subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
        .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    private String GenerateToken(Cuser user){
        Map<String,Object> claims = new HashMap<>();
        return CreateToken(claims,user.getUsername());
    }
}
