package com.victor.springapi.services;

import com.victor.springapi.DTO.CategoriaDTO;
import com.victor.springapi.domain.Categoria;
import com.victor.springapi.repository.CategoriaRepository;
import com.victor.springapi.services.exceptions.DataIntegrityException;
import com.victor.springapi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService
{
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id)
	{
		Optional<Categoria> obj = repo.findById(id);

		//Se obj == null lanca exception
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj)
	{
		obj.setId(null);//certificar q é uma insercao e nao alteracao
		return repo.save(obj);
	}

	public Categoria update(Categoria categoria)
	{
		Categoria newCategoria = find(categoria.getId());
		updateData(newCategoria, categoria);
		return repo.save(newCategoria);
	}

	private void updateData(Categoria newCategoria, Categoria categoria)
	{
		newCategoria.setNome(categoria.getNome());
	}

	public void delete(Integer categoryId)
	{
		find(categoryId);
		try
		{
			repo.deleteById(categoryId);
		}
		catch (DataIntegrityViolationException e)
		{
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}

	public List<Categoria> findAll()
	{
		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria catFromDTO(CategoriaDTO categoriaDTO){
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}

}
