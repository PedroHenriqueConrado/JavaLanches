package grupointergado.com.br.trabalho.repository;

import grupointergado.com.br.trabalho.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
