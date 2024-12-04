package grupointergado.com.br.trabalho.controller;

import grupointergado.com.br.trabalho.dto.NotaRequestDTO;
import grupointergado.com.br.trabalho.model.Nota;
import grupointergado.com.br.trabalho.model.Matricula;
import grupointergado.com.br.trabalho.model.Disciplina;
import grupointergado.com.br.trabalho.repository.NotaRepository;
import grupointergado.com.br.trabalho.repository.MatriculaRepository;
import grupointergado.com.br.trabalho.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/nota")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(notaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Integer id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        return ResponseEntity.ok(nota);
    }

    @PostMapping
    public ResponseEntity<Nota> save(@RequestBody NotaRequestDTO dto) {
        Matricula matricula = matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        Nota nota = new Nota();
        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.data_lancamento());

        Nota savedNota = notaRepository.save(nota);
        return ResponseEntity.ok(savedNota);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(@PathVariable Integer id, @RequestBody NotaRequestDTO dto) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        Matricula matricula = matriculaRepository.findById(dto.matricula_id())
                .orElseThrow(() -> new IllegalArgumentException("Matrícula não encontrada"));

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplina_id())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);
        nota.setNota(dto.nota());
        nota.setDataLancamento(dto.data_lancamento());

        Nota updatedNota = notaRepository.save(nota);
        return ResponseEntity.ok(updatedNota);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Nota nota = notaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));
        notaRepository.delete(nota);
        return ResponseEntity.noContent().build();
    }
}
