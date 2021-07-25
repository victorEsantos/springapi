package com.victor.springapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.victor.springapi.domain.enums.EstadoPagamento;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
public abstract class Pagamento implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer estadoPagamento;

	//@JsonBackReference
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento()
	{

	}

	public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido)
	{
		this.id = id;
		this.estadoPagamento = (estadoPagamento == null) ? null : estadoPagamento.getCode();
		this.pedido = pedido;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public EstadoPagamento getEstadoPagamento()
	{
		return EstadoPagamento.getSafeEstadoPagamento(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento)
	{
		this.estadoPagamento = estadoPagamento.getCode();
	}

	public Pedido getPedido()
	{
		return pedido;
	}

	public void setPedido(Pedido pedido)
	{
		this.pedido = pedido;
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

		Pagamento pagamento = (Pagamento) o;

		return id != null ? id.equals(pagamento.id) : pagamento.id == null;
	}

	@Override
	public int hashCode()
	{
		return id != null ? id.hashCode() : 0;
	}
}
