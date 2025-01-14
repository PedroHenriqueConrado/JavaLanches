package grupointergado.com.br.trabalho.model;

import jakarta.persistence.*;

@Entity
@Table (name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    @JoinColumn ( name = "turma_id", nullable = false)
    private Turma turma;

    public Matricula() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
