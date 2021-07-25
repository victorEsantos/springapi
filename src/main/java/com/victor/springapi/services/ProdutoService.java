package com.victor.springapi.services;

import com.victor.springapi.domain.Categoria;
import com.victor.springapi.domain.Produto;
import com.victor.springapi.repository.CategoriaRepository;
import com.victor.springapi.repository.ProdutoRepository;
import com.victor.springapi.services.exceptions.ObjectNotFoundException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService
{
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto buscar(Integer id)
	{
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	public Page<Produto> search(String name, List<Integer> ids, Integer page, Integer linesPerPage,
		String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest
			.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.search(name, categorias, pageRequest);
	}
}
