package br.edu.iff.ccc.gerenciadorapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovimentoEstoqueNaoEncontradoException extends RuntimeException {

    public MovimentoEstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
