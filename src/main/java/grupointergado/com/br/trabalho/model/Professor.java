package grupointergado.com.br.trabalho.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professores")
public class Professor {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String telefone;

    @Column
    private String especialidade;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
