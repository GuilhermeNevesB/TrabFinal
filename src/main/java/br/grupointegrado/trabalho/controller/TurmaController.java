package br.grupointegrado.trabalho.controller;

import br.grupointegrado.trabalho.model.Turma;
import br.grupointegrado.trabalho.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        return turmaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Turma save(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> update(@PathVariable Integer id, @RequestBody Turma turmaAtualizada) {
        return turmaRepository.findById(id)
                .map(turma -> {
                    turma.setNome(turmaAtualizada.getNome());
                    turma.setCurso(turmaAtualizada.getCurso());
                    turma.setDataInicio(turmaAtualizada.getDataInicio());
                    turma.setDataFim(turmaAtualizada.getDataFim());
                    return ResponseEntity.ok(turmaRepository.save(turma));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
