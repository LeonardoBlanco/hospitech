package br.com.fiap.hospitech.infra.events;

public class UsuarioCriadoEvent {
    private final String usuarioId;
    private final String email;

    public UsuarioCriadoEvent(String usuarioId, String email) {
        this.usuarioId = usuarioId;
        this.email = email;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public String getEmail() {
        return email;
    }
}