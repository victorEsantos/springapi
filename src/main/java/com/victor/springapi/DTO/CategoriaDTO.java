package com.victor.springapi.DTO;

import com.victor.springapi.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaDTO implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "o tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public CategoriaDTO(Categoria categoria)
	{
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
}
