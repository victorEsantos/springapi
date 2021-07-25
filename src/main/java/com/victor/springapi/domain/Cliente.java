package com.victor.springapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.victor.springapi.domain.enums.Perfil;
import com.victor.springapi.domain.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.victor.springapi.domain.enums.TipoCliente.getSafeTipoCliente;

@Entity
@Getter
@Setter
public class Cliente implements Serializable
{
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String nome;

	@Column(unique = true)
	private String email;
	private String cpfOuCnpj;
	private Integer tipoCliente;

	@JsonIgnore
	private String senha;

	//JsonManagedReference para com referencia ciclica, porem dava alguns problemas com json e foi usado apenas um @JsonIgnore no backReference
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	//@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente()
	{
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String senha)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente == null ? null : tipoCliente.getCode();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}


	public TipoCliente getTipoCliente()
	{
		TipoCliente tc = getSafeTipoCliente(this.tipoCliente);
		return tc;
	}

	public void setTipoCliente(TipoCliente tipoCliente)
	{
		this.tipoCliente = tipoCliente.getCode();
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCode());
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

		Cliente cliente = (Cliente) o;

		return id != null ? id.equals(cliente.id) : cliente.id == null;
	}

	@Override
	public int hashCode()
	{
		return id != null ? id.hashCode() : 0;
	}
}
