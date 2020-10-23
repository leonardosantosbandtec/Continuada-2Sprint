package com.bandtec.projetoindividual.repositorio;

import com.bandtec.projetoindividual.dominios.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}

