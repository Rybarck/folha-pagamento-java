package br.com.empresa.folha.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(String mensagem) { super(mensagem); }
}
