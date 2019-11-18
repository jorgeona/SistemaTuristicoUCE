package com.uce.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uce.travel.model.Usuario;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<Usuario, Long> {
 
 Usuario findByEmail(String email);
}