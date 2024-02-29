package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Produto; 

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

private List<Produto> produtos = new ArrayList<>();    
 
//realizar agora as operaçoes CRUD Create-Read-Update-DeleteS    


//criar
@PostMapping
public ResponseEntity<Void> criarProduto(@RequestBody Produto produto) {
produtos.add(produto);
return new ResponseEntity<>(HttpStatus.CREATED);
}

//obter iremos fazer em duas etapas, sendo elas obter todos e obter por id

@GetMapping
public ResponseEntity<List<Produto>> listarProduto(){
return new ResponseEntity<>(produtos,HttpStatus.OK);    
}


 // Endpoint para obter um produto pelo ID
 @GetMapping("/{id}")
 public ResponseEntity<Produto> obterProdutoPorId(@PathVariable int id) {
     for (Produto produto : produtos) {
         if (produto.getId() == id) {
             return new ResponseEntity<>(produto, HttpStatus.OK);
         }
     }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 
    }
 //atualizar produto por id
 @PutMapping("/{id}")
 public ResponseEntity<Void> atualizarProduto(@PathVariable int id, @RequestBody Produto produtoAtualizado) {
     for (Produto produto : produtos) {
         if (produto.getId() == id) {
             produto.setNome(produtoAtualizado.getNome());
             produto.setQuantidade(produtoAtualizado.getQuantidade());
             produto.setPreco(produtoAtualizado.getPreco());
             return new ResponseEntity<>(HttpStatus.OK);
         }
     }
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }
//deletar produto por id
@DeleteMapping("/{id}")
public ResponseEntity<Void> deletarProduto(@PathVariable int id) {
    for (Produto produto : produtos) {
        if (produto.getId() == id) {
            produtos.remove(produto);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

} 

