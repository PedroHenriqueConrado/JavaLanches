package grupointergado.com.br.trabalho.controller;


import grupointergado.com.br.trabalho.dto.CursoRequestDTO;
import grupointergado.com.br.trabalho.dto.TurmaRequestDTO;
import grupointergado.com.br.trabalho.model.Curso;
import grupointergado.com.br.trabalho.repository.CursoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cursos")
public class CursoController {

    @Autowired
    private CursoRespository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll(){
        return  ResponseEntity.ok(repository.findAll());
    };

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id){

        Curso curso = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public Curso save(@RequestBody Curso dto){

        Curso curso = new Curso();
        curso.setNome(dto.getNome());
        curso.setCodigo(dto.getCodigo());
        curso.setCarga_horaria(dto.getCarga_horaria());

        return repository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Integer id,
                        @RequestBody CursoRequestDTO dto){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontradi"));

        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return repository.save(curso);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){

        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        this.repository.delete(curso);

        return ResponseEntity.noContent().build();
    }
}
