package br.com.alura.codechella.domain.entities.usuario;

public class Endereco {

    private String cep;
    private Integer numero;
    private String complemento;

    public Endereco(String cep, Integer numero, String complemento) {
        if (!isCepValido(cep)) throw new IllegalArgumentException("CEP inválido");
        if (numero == null) throw new IllegalArgumentException("Número não pode ser nulo");
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    private boolean isCepValido(String cep) {
        if (cep == null) return false;
        String regexCep = "\\d{5}-\\d{3}";
        return cep.matches(regexCep);
    }

    public Integer getNumero() {
        return numero;
    }
    public String getCep() {
        return cep;
    }
    public String getComplemento() {
        return complemento;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
