package com.victor.springapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Produto implements Serializable
{
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer id;
	private String nome;
	private Double preco;

	//@JsonBackReference
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA", joinColumns = @JoinColumn(name = "produto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "id.produto")
	private Set<ItemPedido> itens = new HashSet<>();


	public Produto()
	{
	}

	public Produto(Integer id, String nome, Double preco)
	{
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	@JsonIgnore
	public List<Pedido> getPedidos()
	{
		List<Pedido> pedidosList = new ArrayList<>();
		for (ItemPedido itemPedido : itens)
		{
			pedidosList.add(itemPedido.getPedido());
		}

		return pedidosList;
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

	public Double getPreco()
	{
		return preco;
	}

	public void setPreco(Double preco)
	{
		this.preco = preco;
	}

	public List<Categoria> getCategorias()
	{
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias)
	{
		this.categorias = categorias;
	}

	public Set<ItemPedido> getItens()
	{
		return itens;
	}

	public void setItens(Set<ItemPedido> itens)
	{
		this.itens = itens;
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

		Produto produto = (Produto) o;

		return id.equals(produto.id);
	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}
}
