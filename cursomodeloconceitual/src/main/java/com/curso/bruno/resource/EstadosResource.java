package com.curso.bruno.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.bruno.domain.Cidade;
import com.curso.bruno.domain.Estado;
import com.curso.bruno.dto.CidadeDTO;
import com.curso.bruno.dto.EstadoDTO;
import com.curso.bruno.services.CidadeService;
import com.curso.bruno.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadosResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> list = estadoService.findAll();
		
		List<EstadoDTO> listDTO= new ArrayList<EstadoDTO>(); 
		for (Estado estado : list) {
			listDTO.add(new EstadoDTO(estado));
		}
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{estadoId}/cidades",method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId ) {
		List<Cidade> list = cidadeService.findCidades(estadoId);
		List<CidadeDTO> listDTO = new ArrayList<>(); 
		for (Cidade cidade : list) {
			listDTO.add(new CidadeDTO(cidade));
			
		}
		return ResponseEntity.ok().body(listDTO);
	}

}
