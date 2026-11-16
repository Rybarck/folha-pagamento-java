package br.com.empresa.folha.exception;

public class FuncionarioJaCadastradoException extends RuntimeException {
    public FuncionarioJaCadastradoException(String mensagem) { super(mensagem); }
}
