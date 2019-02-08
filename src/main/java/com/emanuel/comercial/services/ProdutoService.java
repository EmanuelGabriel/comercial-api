package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import com.emanuel.comercial.model.Produto;

public interface ProdutoService {

	List<Produto> listaDeProdutos();

	Produto criarProduto(Produto produto);

	void deletarProduto(Long codigo);

	Optional<Produto> buscarPeloCodigo(Long codigo);
}
