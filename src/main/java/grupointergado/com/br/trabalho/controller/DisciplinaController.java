package grupointergado.com.br.trabalho.controller;


import grupointergado.com.br.trabalho.dto.DisciplinaRequestDTO;
import grupointergado.com.br.trabalho.model.Curso;
import grupointergado.com.br.trabalho.model.Disciplina;
import grupointergado.com.br.trabalho.model.Disciplina;
import grupointergado.com.br.trabalho.model.Professor;
import grupointergado.com.br.trabalho.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id){
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public Disciplina save(@RequestBody Disciplina dto){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.getNome());
        disciplina.setCodigo(dto.getCodigo());
        disciplina.setCurso(dto.getCurso());
        disciplina.setProfessor(dto.getProfessor());

        return repository.save(disciplina);
    }

    @PutMapping
    public Disciplina update(@PathVariable Integer id,
                             @RequestBody DisciplinaRequestDTO dto){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = disciplina.getCurso();
        findById(dto.curso_id());

        disciplina.setCurso(curso);

        Professor professor = disciplina.getProfessor();
        findById(dto.professor_id());

        disciplina.setProfessor(professor);



        return repository.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}
