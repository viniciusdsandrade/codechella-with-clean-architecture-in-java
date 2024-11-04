package br.com.alura.codechella.domain.entities.usuario;

import java.time.LocalDate;

public class FabricaDeUsuario {

    private Usuario usuario;

    /**
     * Cria uma nova instância de {@link Usuario} com o nome, CPF, data de nascimento e email fornecidos.
     *
     * @param nome       O nome completo do usuário.
     * @param cpf        O CPF do usuário no formato "XXX.XXX.XXX-XX".
     * @param nascimento A data de nascimento do usuário.
     * @param email      O endereço de e-mail do usuário.
     * @return A instância criada de {@link Usuario}.
     * @throws IllegalArgumentException se os parâmetros fornecidos forem inválidos.
     */
    public Usuario comNomeCpfNascimentoEmail(String nome, String cpf, LocalDate nascimento, String email){
        this.usuario = new Usuario(cpf, nome, nascimento, email);
        return this.usuario;
    }

    /**
     * Adiciona um endereço ao {@link Usuario} previamente criado.
     *
     * @param cep         O CEP do endereço no formato "XXXXX-XXX".
     * @param numero      O número da residência.
     * @param complemento Informações complementares do endereço (opcional).
     * @return A instância atualizada de {@link Usuario} com o endereço incluído.
     * @throws IllegalStateException se nenhum usuário foi criado antes de chamar este método.
     * @throws IllegalArgumentException se os parâmetros fornecidos forem inválidos.
     */
    public Usuario incluiEndereco(String cep, Integer numero, String complemento) {
        if (this.usuario == null) throw new IllegalStateException("Usuário não foi criado. Chame comNomeCpfNascimentoEmail primeiro.");
        this.usuario.setEndereco(new Endereco(cep, numero, complemento));
        return this.usuario;
    }
}
