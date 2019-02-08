package com.emanuel.comercial.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emanuel.comercial.model.Produto;
import com.emanuel.comercial.repository.ProdutoRepository;
import com.emanuel.comercial.services.ProdutoServiceImplementacao;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoServiceImplementacao produtoService;

	@GetMapping("/pesquisarProdutos")
	public List<Produto> pesquisarProdutos(@RequestParam String nome) {
		return produtoRepository.pesquisarProdutos(nome);
	}

	@GetMapping("/por-nome")
	public Produto porNome(@RequestParam String nome) {
		return produtoRepository.findByNome(nome);
	}

	@GetMapping("/por-nome-comecando-com")
	public List<Produto> porNomeComecandoCom(@RequestParam String nome) {
		return produtoRepository.findByNomeStartingWithIgnoreCase(nome);
	}

	@GetMapping("/sem-descricao")
	public List<Produto> semDescricao() {
		return produtoRepository.findByDescricaoIsNull();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Produto>> buscar(@PathVariable Long codigo) {

		Optional<Produto> produtos = produtoRepository.findById(codigo);

		if (!produtos.isPresent()) {
			return new ResponseEntity<Optional<Produto>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Optional<Produto>>(produtos, HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto salvar(@Valid @RequestBody Produto produto) {
		return produtoService.criarProduto(produto);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<Produto> deletar(@PathVariable Long codigo) {

		Optional<Produto> produtoId = produtoService.buscarPeloCodigo(codigo);

		if (!produtoId.isPresent())
			return new ResponseEntity<Produto>(HttpStatus.NOT_FOUND);

		produtoService.deletarProduto(codigo);

		return new ResponseEntity<Produto>(HttpStatus.NO_CONTENT);
	}

	@SuppressWarnings("deprecation")
	@GetMapping
	public Page<Produto> pesquisar(@RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "10") int numeroPorPagina,
			@RequestParam(defaultValue = "nome") String ordenacao,

			@RequestParam(defaultValue = "DESC") Sort.Direction direcao) {
		return produtoRepository.findAll(new PageRequest(pagina, numeroPorPagina, new Sort(direcao, ordenacao)));
	}

}
