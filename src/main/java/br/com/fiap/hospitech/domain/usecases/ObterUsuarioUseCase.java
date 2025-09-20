package br.com.fiap.hospitech.domain.usecases;

import br.com.fiap.hospitech.domain.entities.Usuario;

public interface ObterUsuarioUseCase {
    Usuario handle(String id);
}