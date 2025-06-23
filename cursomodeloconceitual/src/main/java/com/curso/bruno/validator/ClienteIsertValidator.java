package com.curso.bruno.validator;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.curso.bruno.domain.Cliente;
import com.curso.bruno.domain.enums.TipoCliente;
import com.curso.bruno.dto.ClienteNewDTO;
import com.curso.bruno.repositories.ClienteRepository;
import com.curso.bruno.resource.exeption.FieldMessage;

public class ClienteIsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
// inclua os testes aqui, inserindo erros na lista//////////////////////////////////////////////
	
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()) ) {
			list.add(new FieldMessage("cpfOuCnpj"," CPF invalido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()) ) {
			list.add(new FieldMessage("cpfOuCnpj"," CNPJ invalido"));
		}
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if( aux !=null) {
			list.add(new FieldMessage("email"," E-mail já usado"));
			
		}
////////////////////////////////////////////////////////////////////////////////////////////////		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMsg()).addPropertyNode(e.getNome())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}