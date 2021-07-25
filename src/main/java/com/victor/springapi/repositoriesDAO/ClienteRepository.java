package com.victor.springapi.repositoriesDAO;


import com.victor.springapi.domain.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);
}
