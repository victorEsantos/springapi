package com.victor.springapi.repository;

import com.victor.springapi.domain.Categoria;
import com.victor.springapi.domain.Produto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT prd FROM Produto prd INNER JOIN prd.categorias cat WHERE prd.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias,
		Pageable pageRequest);
}
