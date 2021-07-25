package com.victor.springapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.victor.springapi.domain.enums.EstadoPagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
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


	public EstadoPagamento getEstadoPagamento()
	{
		return EstadoPagamento.getSafeEstadoPagamento(estadoPagamento);
	}

	public void setEstadoPagamento(EstadoPagamento estadoPagamento)
	{
		this.estadoPagamento = estadoPagamento.getCode();
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
