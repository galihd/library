package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cuser;

@Repository
public interface CuserdaoInt extends JpaRepository<Cuser, String>{
}
