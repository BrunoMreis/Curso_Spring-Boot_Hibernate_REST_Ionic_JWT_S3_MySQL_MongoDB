package com.curso.bruno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.Categoria;
import com.curso.bruno.repositories.CategoriaRepository;
import com.curso.bruno.services.exeption.DataIntegrityException;
import com.curso.bruno.services.exeption.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo; 
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não Encontrado! ID: " + id + " Tipo "
				+ Categoria.class.getName()));
		
	}

	public Categoria inset(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um categoria que possui produto(s)");
		}
		
	}
	
}
