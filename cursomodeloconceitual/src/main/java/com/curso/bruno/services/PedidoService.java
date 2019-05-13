package com.curso.bruno.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.bruno.domain.ItemPedido;
import com.curso.bruno.domain.PagamentoComBoleto;
import com.curso.bruno.domain.Pedido;
import com.curso.bruno.domain.enums.EstadoPagamento;
import com.curso.bruno.repositories.ItemPedidoRepository;
import com.curso.bruno.repositories.PagamentoRepository;
import com.curso.bruno.repositories.PedidoRepository;
import com.curso.bruno.services.exeption.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo; 
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SmtpEmailService smtpEmailService;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);	
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não Encontrado! ID: "
		+ id + " Tipo "	+ Pedido.class.getName()));
		
	}

	public @Valid Pedido inset(@Valid Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagamentoComBoleto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagmentoComBoleto(pagamentoComBoleto,obj.getInstante());
		}
		
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento()); 	
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getValor());
			ip.setPedido(obj);
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		smtpEmailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
}
