package grupointergado.com.br.trabalho.repository;

import grupointergado.com.br.trabalho.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
