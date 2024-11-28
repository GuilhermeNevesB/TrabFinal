package br.grupointegrado.trabalho.controller;

import br.grupointegrado.trabalho.model.Aluno;
import br.grupointegrado.trabalho.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluno save(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable Integer id, @RequestBody Aluno alunoAtualizado) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    aluno.setNome(alunoAtualizado.getNome());
                    aluno.setEmail(alunoAtualizado.getEmail());
                    aluno.setMatricula(alunoAtualizado.getMatricula());
                    aluno.setDataNascimento(alunoAtualizado.getDataNascimento());
                    return ResponseEntity.ok(alunoRepository.save(aluno));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
