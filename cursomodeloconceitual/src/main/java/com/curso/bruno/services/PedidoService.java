package com.curso.bruno.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.Pedido;
import com.curso.bruno.repositories.PedidoRepository;
import com.curso.bruno.services.exeption.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo; 
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não Encontrado! ID: "
		+ id + " Tipo "	+ Pedido.class.getName()));
		
	}
	
}
