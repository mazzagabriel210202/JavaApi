package com.example.demo.service;

import com.example.demo.dto.ProdutoDTO;
import com.example.demo.exeption.Exception;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        Produto novoProduto = new Produto(
                produtoDTO.getId(),
                produtoDTO.getNome(),
                produtoDTO.getQuantidade(),
                produtoDTO.getPreco()
        );
        produtoRepository.criarProduto(novoProduto);
        return produtoDTO;
    }

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.listarProdutos().stream()
                .map(this::produtoToDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO obterProdutoPorId(int id) {
        Produto produto = produtoRepository.obterProdutoPorId(id);
        if (produto == null) {
            throw new Exception("Produto com ID " + id + " não encontrado");
        }
        return produtoToDTO(produto);
    }

    public void atualizarProduto(int id, ProdutoDTO produtoDTO) {
        Produto produtoExistente = produtoRepository.obterProdutoPorId(id);
        if (produtoExistente == null) {
            throw new Exception("Produto com ID " + id + " não encontrado");
        }
        Produto produtoAtualizado = new Produto(
                id,
                produtoDTO.getNome(),
                produtoDTO.getQuantidade(),
                produtoDTO.getPreco()
        );
        produtoRepository.atualizarProduto(id, produtoAtualizado);
    }

    public void deletarProduto(int id) {
        Produto produtoExistente = produtoRepository.obterProdutoPorId(id);
        if (produtoExistente == null) {
            throw new Exception("Produto com ID " + id + " não encontrado");
        }
        produtoRepository.deletarProduto(id);
    }

    private ProdutoDTO produtoToDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getQuantidade(),
                produto.getPreco()
        );
    }
}
