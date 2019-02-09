package com.curso.bruno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.Categoria;
import com.curso.bruno.repositories.CategoriaRepository;
import com.curso.bruno.services.exeption.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo; 
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não Encontrado! ID: " + id + " Tipo "
				+ Categoria.class.getName()));
		
	}
	
}
