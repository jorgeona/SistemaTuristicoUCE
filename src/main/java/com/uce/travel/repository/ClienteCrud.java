package com.uce.travel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uce.travel.entity.Cliente;

@Repository
public interface ClienteCrud extends CrudRepository<Cliente, Long> {

	List<Cliente> findByName(String name);

}
