package br.grupointegrado.lanches.controller;
import br.grupointegrado.lanches.dto.ClienteRequestDTO;
import br.grupointegrado.lanches.dto.ProdutoRequestDTO;
import br.grupointegrado.lanches.model.Produto;
import br.grupointegrado.lanches.repository.ClienteRepository;
import br.grupointegrado.lanches.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/produtos")

public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        //   return this.repository.findAll();

        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Integer id) {

//        return this.repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado"));

        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado"));

        return ResponseEntity.ok(produto);

    }

    @PostMapping
    public Produto save(@RequestBody ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.nome());
        produto.setValor(dto.valor());
        produto.setDescricao(dto.descricao());

        return this.repository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto update(@PathVariable Integer id, @RequestBody ProdutoRequestDTO dto) {
        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado"));

        produto.setNome(dto.nome());
        produto.setValor(dto.valor());
        produto.setDescricao(dto.descricao());

        return this.repository.save(produto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id) {

        Produto produto = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado"));

        this.repository.delete(produto);
        return ResponseEntity.noContent().build();
    }

}
