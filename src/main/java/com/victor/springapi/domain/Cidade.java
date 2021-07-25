package com.victor.springapi.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Cidade implements Serializable
{

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String nome;

	//JsonManagedReference para com referencia ciclica, porem dava alguns problemas com json e foi usado apenas um @JsonIgnore no backReference
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	public Cidade()
	{
	}

	public Cidade(Integer id, String nome, Estado estado)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		Cidade cidade = (Cidade) o;

		return id != null ? id.equals(cidade.id) : cidade.id == null;
	}

	@Override
	public int hashCode()
	{
		return id != null ? id.hashCode() : 0;
	}
}
