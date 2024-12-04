package grupointergado.com.br.trabalho.controller;

import grupointergado.com.br.trabalho.dto.ProfessorRequestDTO;
import grupointergado.com.br.trabalho.model.Professor;
import grupointergado.com.br.trabalho.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {

        Professor professor = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public Professor save(@RequestBody Professor dto) {

        Professor professor= new Professor();
        professor.setNome(dto.getNome());
        professor.setEmail(dto.getEmail());
        professor.setTelefone(dto.getTelefone());
        professor.setEspecialidade(dto.getEspecialidade());

        return this.repository.save(professor);
    }


    @PutMapping("/{id}")
    public Professor update(@PathVariable Integer id,
                            @RequestBody ProfessorRequestDTO dto) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return repository.save(professor);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor encontrado"));

        this.repository.delete(professor);

        return ResponseEntity.noContent().build();
    }

}
