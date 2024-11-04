package br.com.alura.codechella.domain.entities.usuario;

import java.time.LocalDate;

public class Usuario {
    private Long id;
    private String cpf;
    private String nome;
    private LocalDate nascimento;
    private String email;
    private Endereco endereco;

    /**
     * Construtor da classe Usuario.
     *
     * @param cpf        CPF do usuário no formato "XXX.XXX.XXX-XX".
     * @param nome       Nome completo do usuário.
     * @param nascimento Data de nascimento do usuário.
     * @param email      Endereço de e-mail do usuário.
     * @throws IllegalArgumentException se o CPF for nulo ou não corresponder ao formato esperado,
     *                                  ou se o e-mail for nulo ou não corresponder ao formato esperado.
     */
    public Usuario(String cpf,
                   String nome,
                   LocalDate nascimento,
                   String email) {

        if (!isCpfValido(cpf)) throw new IllegalArgumentException("CPF inválido");
        if (!isEmailValido(email)) throw new IllegalArgumentException("E-mail inválido");

        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
    }

    /**
     * Valida o formato do CPF.
     *
     * @param cpf O CPF a ser validado no formato "XXX.XXX.XXX-XX".
     * @return true se o CPF for válido, false caso contrário.
     */
    private boolean isCpfValido(String cpf) {
        if (cpf == null)
            return false;

        // Expressão regular para verificar o formato "XXX.XXX.XXX-XX"
        String regexCpf = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return cpf.matches(regexCpf);
    }

    /**
     * Valida o formato do e-mail utilizando uma expressão regular básica.
     *
     * @param email O e-mail a ser validado.
     * @return true se o e-mail for válido, false caso contrário.
     */
    private boolean isEmailValido(String email) {
        if (email == null || email.isEmpty()) return false;
        String regexEmail = "^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$";
        return email.matches(regexEmail);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public String getNome() {
        return nome;
    }
    public LocalDate getNascimento() {
        return nascimento;
    }
    public String getEmail() {
        return email;
    }
    public String getCpf() {
        return cpf;
    }
    public Long getId() {
        return id;
    }
}
