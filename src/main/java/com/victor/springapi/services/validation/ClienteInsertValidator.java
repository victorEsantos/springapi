package com.victor.springapi.services.validation;

import com.victor.springapi.DTO.ClienteNewDTO;
import com.victor.springapi.controller.exception.FieldErrorMessage;
import com.victor.springapi.domain.Cliente;
import com.victor.springapi.domain.enums.TipoCliente;
import com.victor.springapi.repository.ClienteRepository;
import com.victor.springapi.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>
{


	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann)
	{
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context)
	{
		List<FieldErrorMessage> list = new ArrayList<>();

		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldErrorMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldErrorMessage("cpfOuCnpj", "CNPJ inválido"));
		}

		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldErrorMessage("email", "Email já existente"));
		}

		for (FieldErrorMessage e : list)
		{
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
				.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}


