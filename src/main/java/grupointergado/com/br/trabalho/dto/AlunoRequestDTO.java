package grupointergado.com.br.trabalho.dto;

import java.time.LocalDate;

public record AlunoRequestDTO(String nome, String email, String matricula, LocalDate data_nascimento) {
}
