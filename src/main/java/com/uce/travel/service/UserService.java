package com.uce.travel.service;

import com.uce.travel.model.Usuario;

public interface UserService {
  
 public Usuario findUserByEmail(String email);
 
 public void saveUser(Usuario user);
}