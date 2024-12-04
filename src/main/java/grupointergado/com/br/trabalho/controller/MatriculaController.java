package grupointergado.com.br.trabalho.controller;


import grupointergado.com.br.trabalho.dto.MatriculaResquestDTO;
import grupointergado.com.br.trabalho.model.Aluno;
import grupointergado.com.br.trabalho.model.Disciplina;
import grupointergado.com.br.trabalho.model.Matricula;
import grupointergado.com.br.trabalho.model.Turma;
import grupointergado.com.br.trabalho.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {return ResponseEntity.ok(repository.findAll());}

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id) {
        Matricula matricula = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("matricula não encontrada"));

        return ResponseEntity.ok(matricula);

    }

    @PostMapping
    public Matricula save(@RequestBody Matricula dto) {
        Matricula matricula = new Matricula();
        matricula.setAluno(dto.getAluno());
        matricula.setTurma(dto.getTurma());

        return repository.save(matricula);
    }

    @PutMapping
    public Matricula update(@RequestBody Integer id,
                            @RequestBody MatriculaResquestDTO dto) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula inexistente"));

        Aluno aluno = matricula.getAluno();
        findById(dto.aluno_id());

        matricula.setAluno(aluno);

        Turma turma = matricula.getTurma();
        findById(dto.turma_id());

        matricula.setTurma(turma);

        return repository.save(matricula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));
        this.repository.delete(matricula);
        return ResponseEntity.noContent().build();
    }

}
