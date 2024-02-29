package com.example.demo.repository;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoRepository {

    private List<Produto> produtos = new ArrayList<>();

    public void criarProduto(Produto produto) {
        if (!produtos.contains(produto)) {
            produtos.add(produto);
        } else {
            throw new IllegalArgumentException("Produto já existe");
        }
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public Produto obterProdutoPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public void atualizarProduto(int id, Produto produtoAtualizado) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getId() == id) {
                produtos.set(i, produtoAtualizado);
                return;
            }
        }
        throw new IllegalArgumentException("Produto não encontrado");
    }

    public void deletarProduto(int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }
}
