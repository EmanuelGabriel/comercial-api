package com.emanuel.comercial.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emanuel.comercial.model.Produto;
import com.emanuel.comercial.repository.ProdutoRepository;

@Service
public class ProdutoServiceImplementacao implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public List<Produto> listaDeProdutos() {
		return produtoRepository.findAll();
	}

	@Override
	public Produto criarProduto(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public void deletarProduto(Long codigo) {
		produtoRepository.deleteById(codigo);
	}

	@Override
	public Optional<Produto> buscarPeloCodigo(Long codigo) {
		return produtoRepository.findById(codigo);
	}

}
