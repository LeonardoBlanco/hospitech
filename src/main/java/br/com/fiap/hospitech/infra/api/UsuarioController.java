package br.com.fiap.hospitech.infra.api;

import br.com.fiap.hospitech.domain.entities.Usuario;
import br.com.fiap.hospitech.domain.usecases.CriarUsuarioUseCase;
import br.com.fiap.hospitech.domain.usecases.ObterUsuarioUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUseCase;
    private final ObterUsuarioUseCase obterUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUseCase, ObterUsuarioUseCase obterUseCase) {
        this.criarUseCase = criarUseCase;
        this.obterUseCase = obterUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criar(@RequestBody CriarUsuarioRequest request) {
        var command = new CriarUsuarioUseCase.CriarUsuarioCommand(
                request.nome(),
                request.email(),
                request.login(),
                request.ativo(),
                request.senha(),
                request.endereco(),
                request.role()
        );

        Usuario saved = criarUseCase.handle(command);

        var response = UsuarioResponse.fromEntity(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obter(@PathVariable String id) {
        Usuario usuario = obterUseCase.handle(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UsuarioResponse.fromEntity(usuario));
    }

    // DTOs (records)
    public record CriarUsuarioRequest(
            String nome,
            String email,
            String login,
            Boolean ativo,
            String senha,
            String endereco,
            Usuario.Role role
    ) {}

    public record UsuarioResponse(
            String id,
            String nome,
            String email,
            String login,
            Boolean ativo,
            String endereco,
            Usuario.Role role,
            String dataAlteracao
    ) {
        public static UsuarioResponse fromEntity(Usuario u) {
            return new UsuarioResponse(
                    u.getId(),
                    u.getNome(),
                    u.getEmail(),
                    u.getLogin(),
                    u.getAtivo(),
                    u.getEndereco(),
                    u.getRole(),
                    u.getDataAlteracao() != null ? u.getDataAlteracao().toString() : null
            );
        }
    }
}
