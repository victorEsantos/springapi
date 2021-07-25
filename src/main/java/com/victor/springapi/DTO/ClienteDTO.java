package com.victor.springapi.DTO;

import com.victor.springapi.domain.Cliente;
import com.victor.springapi.services.validation.ClienteUpdate;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@ClienteUpdate
public class ClienteDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 80, message = "deve ter entre 5 e 80 caracteres")
	private String name;
	@NotEmpty
	@Email(message = "email invalido")
	private String email;

	public ClienteDTO()
	{

	}

	public ClienteDTO(Cliente cliente)
	{
		this.id = cliente.getId();
		this.name = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
