package grupointergado.com.br.trabalho.model;

import jakarta.persistence.*;

@Entity
@Table
public class Turma {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private Integer ano;

    @Column
    private Integer semestre;

    @ManyToOne( fetch = FetchType.EAGER,optional = false)
    @JoinColumn( name = "curso_id", nullable = false)
    private Curso curso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }


    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
