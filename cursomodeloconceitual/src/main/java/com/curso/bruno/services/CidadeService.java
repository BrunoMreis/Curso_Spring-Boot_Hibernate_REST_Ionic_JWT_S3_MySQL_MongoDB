package com.curso.bruno.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.Cidade;
import com.curso.bruno.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findCidades(Integer estado_id){
		return repo.findByEstadoIdOrderByNomeAsc(estado_id);
	}
}
