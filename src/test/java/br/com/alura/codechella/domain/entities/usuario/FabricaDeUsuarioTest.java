package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FabricaDeUsuarioTest {

    private FabricaDeUsuario fabrica;

    @BeforeEach
    void setUp() {
        fabrica = new FabricaDeUsuario();
    }

    @Test
    @DisplayName("Deve criar um usuário com nome, CPF, data de nascimento e email válidos")
    void deveCriarUsuarioValido() {
        String nome = "João Silva";
        String cpf = "123.456.789-00";
        LocalDate nascimento = LocalDate.of(1990, 1, 1);
        String email = "joao.silva@example.com";

        Usuario usuario = fabrica.comNomeCpfNascimentoEmail(nome, cpf, nascimento, email);

        assertNotNull(usuario, "Usuário não deve ser nulo");
        assertEquals(nome, usuario.getNome(), "Nome deve ser igual ao fornecido");
        assertEquals(cpf, usuario.getCpf(), "CPF deve ser igual ao fornecido");
        assertEquals(nascimento, usuario.getNascimento(), "Data de nascimento deve ser igual à fornecida");
        assertEquals(email, usuario.getEmail(), "Email deve ser igual ao fornecido");
        assertNull(usuario.getEndereco(), "Endereço inicial deve ser nulo");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao criar usuário com CPF inválido")
    void deveLancarExcecaoCpfInvalido() {
        String nome = "Maria Souza";
        String cpfInvalido = "12345678900"; // Formato incorreto
        LocalDate nascimento = LocalDate.of(1985, 5, 20);
        String email = "maria.souza@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fabrica.comNomeCpfNascimentoEmail(nome, cpfInvalido, nascimento, email);
        });

        assertEquals("CPF inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao criar usuário com email inválido")
    void deveLancarExcecaoEmailInvalido() {
        String nome = "Carlos Pereira";
        String cpf = "987.654.321-00";
        LocalDate nascimento = LocalDate.of(1975, 12, 15);
        String emailInvalido = "carlos.pereira"; // Formato inválido

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fabrica.comNomeCpfNascimentoEmail(nome, cpf, nascimento, emailInvalido);
        });

        assertEquals("E-mail inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalStateException ao tentar incluir endereço sem criar usuário primeiro")
    void deveLancarExcecaoSemUsuario() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fabrica.incluiEndereco("12345-678", 100, "Apto 101");
        });

        assertEquals("Usuário não foi criado. Chame comNomeCpfNascimentoEmail primeiro.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve incluir endereço válido no usuário criado")
    void deveIncluirEnderecoValido() {
        String nome = "Ana Clara";
        String cpf = "111.222.333-44";
        LocalDate nascimento = LocalDate.of(1995, 7, 30);
        String email = "ana.clara@example.com";

        fabrica.comNomeCpfNascimentoEmail(nome, cpf, nascimento, email);

        Usuario usuarioComEndereco = fabrica.incluiEndereco("54321-987", 50, "Casa Verde");

        assertNotNull(usuarioComEndereco.getEndereco(), "Endereço não deve ser nulo");
        assertEquals("54321-987", usuarioComEndereco.getEndereco().getCep(), "CEP deve ser igual ao fornecido");
        assertEquals(50, usuarioComEndereco.getEndereco().getNumero(), "Número deve ser igual ao fornecido");
        assertEquals("Casa Verde", usuarioComEndereco.getEndereco().getComplemento(), "Complemento deve ser igual ao fornecido");
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao incluir endereço com CEP inválido")
    void deveLancarExcecaoCepInvalido() {
        String nome = "Pedro Henrique";
        String cpf = "555.666.777-88";
        LocalDate nascimento = LocalDate.of(1988, 3, 10);
        String email = "pedro.henrique@example.com";

        fabrica.comNomeCpfNascimentoEmail(nome, cpf, nascimento, email);

        String cepInvalido = "12345678"; // Formato incorreto

        // Supondo que a classe Endereco não realiza validações adicionais,
        // precisamos ajustar a classe Endereco para validar o CEP.
        // Caso contrário, este teste não fará sentido.

        // Portanto, primeiro vamos adicionar validação na classe Endereco.

        // Exemplo de implementação com validação no construtor de Endereco:
        /*
        public Endereco(String cep, Integer numero, String complemento) {
            if (!isCepValido(cep)) throw new IllegalArgumentException("CEP inválido");
            if (numero == null) throw new IllegalArgumentException("Número não pode ser nulo");
            this.cep = cep;
            this.numero = numero;
            this.complemento = complemento;
        }

        private boolean isCepValido(String cep) {
            if (cep == null)
                return false;
            String regexCep = "\\d{5}-\\d{3}";
            return cep.matches(regexCep);
        }
        */

        // Após implementar a validação, o teste pode ser realizado:

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fabrica.incluiEndereco(cepInvalido, 200, "Sala 10");
        });

        assertEquals("CEP inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar IllegalArgumentException ao incluir endereço com número nulo")
    void deveLancarExcecaoNumeroNulo() {
        String nome = "Laura Martins";
        String cpf = "999.888.777-66";
        LocalDate nascimento = LocalDate.of(1992, 11, 25);
        String email = "laura.martins@example.com";

        fabrica.comNomeCpfNascimentoEmail(nome, cpf, nascimento, email);

        Integer numeroNulo = null;
        String cep = "67890-123";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            fabrica.incluiEndereco(cep, numeroNulo, "Casa Azul");
        });

        assertEquals("Número não pode ser nulo", exception.getMessage());
    }

    // Adicione mais testes conforme necessário para cobrir outros cenários
}
