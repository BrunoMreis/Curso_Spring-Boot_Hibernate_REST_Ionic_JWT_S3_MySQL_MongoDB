package com.curso.bruno;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.bruno.domain.Categoria;
import com.curso.bruno.domain.Cidade;
import com.curso.bruno.domain.Cliente;
import com.curso.bruno.domain.Endereco;
import com.curso.bruno.domain.Estado;
import com.curso.bruno.domain.ItemPedido;
import com.curso.bruno.domain.Pagamento;
import com.curso.bruno.domain.PagamentoComBoleto;
import com.curso.bruno.domain.PagamentoComCartao;
import com.curso.bruno.domain.Pedido;
import com.curso.bruno.domain.Produto;
import com.curso.bruno.domain.enums.EstadoPagamento;
import com.curso.bruno.domain.enums.TipoCliente;
import com.curso.bruno.repositories.CategoriaRepository;
import com.curso.bruno.repositories.CidadeRepository;
import com.curso.bruno.repositories.ClienteRepository;
import com.curso.bruno.repositories.EnderecoRepository;
import com.curso.bruno.repositories.EstadoRepository;
import com.curso.bruno.repositories.ItemPedidoRepository;
import com.curso.bruno.repositories.PagamentoRepository;
import com.curso.bruno.repositories.PedidoRepository;
import com.curso.bruno.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomodeloconceitualApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	EstadoRepository estadoRepository;

	@Autowired
	CidadeRepository cidadeRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

	@Autowired
	PagamentoRepository pagamentoRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomodeloconceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Eletrônica");
		Categoria cat4 = new Categoria(null, "Elétrica");
		Categoria cat5 = new Categoria(null, "Mecánica");
		Categoria cat6 = new Categoria(null, "Auto");
		Categoria cat7 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat8 = new Categoria(null, "Enlatados");
		Categoria cat9 = new Categoria(null, "Vegetais");
		Categoria cat10 = new Categoria(null, "Frios");
		Categoria cat11 = new Categoria(null, "Peixária");
		Categoria cat12 = new Categoria(null, "cereais");
		Categoria cat13 = new Categoria(null, "Padaria");
		Categoria cat14 = new Categoria(null, "Roupas");
		Categoria cat15 = new Categoria(null, "Lavanderia");
		Categoria cat16 = new Categoria(null, "Limpeza");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(
				cat1, cat2, cat3, cat4, cat5, cat6,
				cat7, cat8, cat9, cat10, cat11,
				cat12, cat13, cat14, cat15, cat16
				));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}