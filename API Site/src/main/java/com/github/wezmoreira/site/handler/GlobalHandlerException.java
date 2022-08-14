package com.github.wezmoreira.site.handler;

import com.github.wezmoreira.site.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
    private static final String CARTAO_NAO_ENCONTRADO = "Cartao não encontrado";
    private static final String ITEM_NAO_ENCONTRADO = "Items não encontrado";

    private static final String CLIENTE_CADASTRADO = "Cliente ja cadastrado";

    private static final String SEM_ESTOQUE = "Sem estoque";





    @ExceptionHandler(value = ClienteNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerClienteNaoEncontrado(CartaoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(CARTAO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = CartaoNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerCartaoNaoEncontrado(ClienteNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(CLIENTE_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = ItemNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerItemNaoEncontrado(ItemNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(ITEM_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = ClienteCadastradoException.class)
    protected ResponseEntity<MensagemErro> handlerClienteJaCadastrado(ClienteCadastradoException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(CLIENTE_CADASTRADO));
    }

    @ExceptionHandler(value = SemEstoqueException.class)
    protected ResponseEntity<MensagemErro> handlerSemEstoque(SemEstoqueException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(SEM_ESTOQUE));
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                        "Campo: " + fieldError.getField() +
                                " || " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(validationList));
    }

}
