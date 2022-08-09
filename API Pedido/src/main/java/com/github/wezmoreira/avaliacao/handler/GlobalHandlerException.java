package com.github.wezmoreira.avaliacao.handler;

import com.github.wezmoreira.avaliacao.exceptions.*;
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

    private static final String PEDIDO_NAO_ENCONTRADO = "Pedido não encontrado";
    private static final String ITEM_NAO_ENCONTRADO = "Item não encontrado.";
    private static final String CAMPO_NULL = "Campo não pode ser Nulo!";
    private static final String DATA_INVALIDA = "Data inválida, verifique a (data_criacao) e (data_validade), " +
            "os valores precisam ser exatos as regras pré-estabelecidas!";
    private static final String REMOCAO_INVALIDA = "Não pode remover um pedido que foi rejeitado ou aprovado!";
    private static final String DELECAO_INVALIDA = "Pedido ja deletado!";




    @ExceptionHandler(value = ItemNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerAssociadoNaoEncontrado(ItemNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(ITEM_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = PedidoNaoEncontradoException.class)
    protected ResponseEntity<MensagemErro> handlerPartidoNaoEncontrado(PedidoNaoEncontradoException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(PEDIDO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = DelecaoInvalidaException.class)
    protected ResponseEntity<MensagemErro> handlerDelecaoInvalida(DelecaoInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MensagemErro(DELECAO_INVALIDA));
    }

    @ExceptionHandler(value = CampoNullException.class)
    protected ResponseEntity<MensagemErro> handlerCargoNaoEncontrado(CampoNullException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(CAMPO_NULL));
    }

    @ExceptionHandler(value = DataInvalidaException.class)
    protected ResponseEntity<MensagemErro> handlerIdeologiaNaoEncontrado(DataInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(DATA_INVALIDA));
    }

    @ExceptionHandler(value = RemocaoInvalidaException.class)
    protected ResponseEntity<MensagemErro> handlerRemocaoInvalida(RemocaoInvalidaException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemErro(REMOCAO_INVALIDA));
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
