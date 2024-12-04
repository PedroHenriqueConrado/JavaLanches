package grupointergado.com.br.trabalho.controller;


import grupointergado.com.br.trabalho.dto.TurmaRequestDTO;
import grupointergado.com.br.trabalho.model.Curso;
import grupointergado.com.br.trabalho.model.Turma;
import grupointergado.com.br.trabalho.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll(){
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id){
        Turma turma = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public Turma save(@RequestBody Turma dto){

        Turma turma = new Turma();
        turma.setAno(dto.getAno());
        turma.setSemestre(dto.getSemestre());
        turma.setCurso(dto.getCurso());

        return repository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id,
                        @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        // Busca a entidade Curso pelo curso_id no DTO
        Curso curso = turma.getCurso();
        findById(dto.curso_id());

        // Define o curso na turma
        turma.setCurso(curso);

        return repository.save(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));
        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }

}
