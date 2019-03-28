package com.curso.bruno.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.Categoria;
import com.curso.bruno.dto.CategoriaDTO;
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
	@Transactional
	public Categoria inset(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		update(newObj, obj);
		return repo.save(newObj);
	}

	

	public void delete(Integer id) {
		find(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um categoria que possui produto(s)");
		}
		
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void update(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
}
