package br.com.fiap.hospitech.domain.entities;
import br.com.fiap.hospitech.domain.usecases.AlterarUsuarioUseCase;
import br.com.fiap.hospitech.domain.usecases.CriarUsuarioUseCase;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, length = 200)
    private String nome;

    @Column(nullable = false, length = 200, unique = true)
    private String email;

    @Column(nullable = false, length = 100, unique = true)
    private String login;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = true, length = 255)
    private String senha;

    @Column(name = "data_alteracao")
    private LocalDate dataAlteracao;

    @Column(length = 500)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Role role;

    // Protected constructor for factory methods (keeps similar style to seu exemplo)
    protected Usuario(String nome, String email, String login, Boolean ativo, String senha, LocalDate dataAlteracao, String endereco, Role role) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.login = login;
        this.ativo = ativo;
        this.senha = senha;
        this.dataAlteracao = dataAlteracao;
        this.endereco = endereco;
        this.role = role;
    }

    public static Usuario create(String nome, String email, String login, Boolean ativo, String senha,
                                 LocalDate dataAlteracao, String endereco, Role role){
        return new Usuario(nome, email, login, ativo, senha, dataAlteracao, endereco, role);
    }

    public static Usuario incluir(CriarUsuarioUseCase.CriarUsuarioCommand criarUsuario){
        var usuario = create(
                criarUsuario.nome(),
                criarUsuario.email(),
                criarUsuario.login(),
                criarUsuario.ativo(),
                criarUsuario.senha(),
                LocalDate.now(),
                criarUsuario.endereco(),
                criarUsuario.role()
        );

        usuario.setId(UUID.randomUUID().toString());
        return usuario;
    }

    public static Usuario alterar(AlterarUsuarioUseCase.AlterarUsuarioCommand alterarDTO){
        return create(
                alterarDTO.nome(),
                alterarDTO.email(),
                alterarDTO.login(),
                alterarDTO.ativo(),
                null,
                LocalDate.now(),
                alterarDTO.endereco(),
                alterarDTO.role()
        );
    }

    public void alterarSenha(String novaSenha){
        this.senha = novaSenha;
    }

    /* setters utilizados internamente */
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public enum Role {
        MEDICO,
        ENFERMEIRO,
        PACIENTE
    }
}
