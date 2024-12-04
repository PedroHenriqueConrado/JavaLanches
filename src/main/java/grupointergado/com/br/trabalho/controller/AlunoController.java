package grupointergado.com.br.trabalho.controller;


import ch.qos.logback.core.net.server.Client;
import grupointergado.com.br.trabalho.dto.AlunoRequestDTO;
import grupointergado.com.br.trabalho.model.Aluno;
import grupointergado.com.br.trabalho.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {

    return ResponseEntity.ok(repository.findAll());

    };

@GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {

    Aluno aluno = repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

    return ResponseEntity.ok(aluno);
}

@PostMapping
    public Aluno save(@RequestBody Aluno dto) {

    Aluno aluno= new Aluno();
    aluno.setNome(dto.getNome());
    aluno.setEmail(dto.getEmail());
    aluno.setMatricula(dto.getMatricula());
    aluno.setData_nascimento(dto.getData_nascimento());

    return this.repository.save(aluno);
}

@PutMapping("/{id}")
public Aluno update(@PathVariable Integer id,
                    @RequestBody AlunoRequestDTO dto) {
    Aluno aluno= this.repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

    aluno.setNome(dto.nome());
    aluno.setEmail(dto.email());
    aluno.setMatricula(dto.matricula());
    aluno.setData_nascimento(dto.data_nascimento());

    return repository.save(aluno);

}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

    Aluno aluno = this.repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aluno encontrado"));

    this.repository.delete(aluno);

    return ResponseEntity.noContent().build();
}
}
