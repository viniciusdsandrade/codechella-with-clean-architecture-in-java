package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static java.time.LocalDate.of;

public class UsuarioTest {

    /**
     * Testa a criação de um usuário com CPFs em formatos inválidos.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "CPF inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF no formato inválido")
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {
        // CPFs com formatos inválidos
        String cpfInvalidoFormato = "12345678900";          // Sem pontos e hífen
        String cpfInvalidoCaracteres = "123.456.789-0A";    // Contém caractere não numérico
        String cpfParcialmenteInvalido = "123.456.789-0";   // Dígitos insuficientes
        String cpfNulo = null;                              // CPF nulo
        String cpfComEspacos = "123. 456.789-00";           // CPF com espaços
        String cpfComCaracteresEspeciais = "123.456.789-00#";// CPF com caracteres especiais adicionais

        // Dados válidos para os demais campos
        String nome = "João Silva";
        LocalDate nascimento = of(1990, 5, 20);
        String email = "joao.silva@example.com";

        // Teste com CPF sem formatação adequada
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfInvalidoFormato, nome, nascimento, email));
        assertEquals("CPF inválido", exception1.getMessage());

        // Teste com CPF contendo caracteres inválidos
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfInvalidoCaracteres, nome, nascimento, email));
        assertEquals("CPF inválido", exception2.getMessage());

        // Teste com CPF parcialmente correto
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfParcialmenteInvalido, nome, nascimento, email));
        assertEquals("CPF inválido", exception3.getMessage());

        // Teste com CPF nulo
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfNulo, nome, nascimento, email));
        assertEquals("CPF inválido", exception4.getMessage());

        // Teste com CPF contendo espaços
        IllegalArgumentException exception5 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfComEspacos, nome, nascimento, email));
        assertEquals("CPF inválido", exception5.getMessage());

        // Teste com CPF contendo caracteres especiais adicionais
        IllegalArgumentException exception6 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfComCaracteresEspeciais, nome, nascimento, email));
        assertEquals("CPF inválido", exception6.getMessage());
    }

    /**
     * Testa a criação de um usuário com CPF no formato válido.
     * Verifica se o objeto Usuario é criado corretamente sem lançar exceções.
     */
    @Test
    @DisplayName("Deve cadastrar usuário com CPF no formato válido")
    public void deveCadastrarUsuarioComCpfNoFormatoValido() {
        // CPFs com formato válido
        String cpfValido1 = "123.456.789-00";
        String cpfValido2 = "935.411.347-80"; // Outro CPF válido exemplo

        // Dados válidos para os demais campos
        String nome = "Maria Oliveira";
        LocalDate nascimento = of(1985, 8, 15);
        String email = "maria.oliveira@example.com";

        // Tenta criar o usuário com primeiro CPF válido
        assertDoesNotThrow(() -> {
            Usuario usuario = new Usuario(cpfValido1, nome, nascimento, email);
            assertNotNull(usuario);
            assertEquals(cpfValido1, usuario.getCpf());
            assertEquals(nome, usuario.getNome());
            assertEquals(nascimento, usuario.getNascimento());
            assertEquals(email, usuario.getEmail());
        });

        // Tenta criar o usuário com segundo CPF válido
        assertDoesNotThrow(() -> {
            Usuario usuario = new Usuario(cpfValido2, nome, nascimento, email);
            assertNotNull(usuario);
            assertEquals(cpfValido2, usuario.getCpf());
            assertEquals(nome, usuario.getNome());
            assertEquals(nascimento, usuario.getNascimento());
            assertEquals(email, usuario.getEmail());
        });
    }

    /**
     * Testa a criação de um usuário com CPF vazio.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "CPF inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF vazio")
    public void naoDeveCadastrarUsuarioComCpfVazio() {
        String cpfVazio = "";

        String nome = "Carlos Pereira";
        LocalDate nascimento = of(1975, 12, 10);
        String email = "carlos.pereira@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfVazio, nome, nascimento, email));
        assertEquals("CPF inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com CPF contendo espaços.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "CPF inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF contendo espaços")
    public void naoDeveCadastrarUsuarioComCpfComEspacos() {
        String cpfComEspacos = "123. 456.789-00";

        String nome = "Ana Souza";
        LocalDate nascimento = of(1992, 3, 25);
        String email = "ana.souza@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfComEspacos, nome, nascimento, email));
        assertEquals("CPF inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com CPF contendo caracteres especiais adicionais.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "CPF inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF contendo caracteres especiais adicionais")
    public void naoDeveCadastrarUsuarioComCpfComCaracteresEspeciais() {
        String cpfComCaracteresEspeciais = "123.456.789-00#";

        String nome = "Pedro Almeida";
        LocalDate nascimento = of(1988, 7, 30);
        String email = "pedro.almeida@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfComCaracteresEspeciais, nome, nascimento, email));
        assertEquals("CPF inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com CPF com menos dígitos do que o necessário.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "CPF inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF com menos dígitos")
    public void naoDeveCadastrarUsuarioComCpfComMenosDigitos() {
        String cpfComMenosDigitos = "123.456.78-90";

        String nome = "Luiza Martins";
        LocalDate nascimento = of(1995, 11, 5);
        String email = "luiza.martins@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfComMenosDigitos, nome, nascimento, email));
        assertEquals("CPF inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com CPF com mais dígitos do que o necessário.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "CPF inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF com mais dígitos")
    public void naoDeveCadastrarUsuarioComCpfComMaisDigitos() {
        String cpfComMaisDigitos = "123.456.789-000";

        String nome = "Bruno Costa";
        LocalDate nascimento = of(1980, 1, 18);
        String email = "bruno.costa@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfComMaisDigitos, nome, nascimento, email));
        assertEquals("CPF inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com e-mail no formato válido.
     * Verifica se o objeto Usuario é criado corretamente sem lançar exceções.
     */
    @Test
    @DisplayName("Deve cadastrar usuário com e-mail no formato válido")
    public void deveCadastrarUsuarioComEmailValido() {
        // Dados válidos
        String cpfValido = "123.456.789-00";
        String nome = "Carla Mendes";
        LocalDate nascimento = of(1993, 4, 10);
        String emailValido = "carla.mendes@example.com";

        // Tenta criar o usuário e verifica se não lança exceção
        assertDoesNotThrow(() -> {
            Usuario usuario = new Usuario(cpfValido, nome, nascimento, emailValido);
            assertNotNull(usuario);
            assertEquals(cpfValido, usuario.getCpf());
            assertEquals(nome, usuario.getNome());
            assertEquals(nascimento, usuario.getNascimento());
            assertEquals(emailValido, usuario.getEmail());
        });
    }

    /**
     * Testa a criação de um usuário com e-mail no formato inválido.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "E-mail inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com e-mail no formato inválido")
    public void naoDeveCadastrarUsuarioComEmailInvalido() {
        // E-mails com formatos inválidos
        String emailSemArroba = "usuarioexample.com";                // Falta '@'
        String emailSemDominio = "usuario@.com";                     // Falta domínio antes do '.'
        String emailComEspacos = "usuario@ exemplo.com";             // Contém espaços
        String emailComCaracteresEspeciais = "usuario@exemplo!.com"; // Caracteres especiais inválidos
        String emailParcialmenteInvalido = "usuario@exemplo.c";      // Extensão de domínio muito curta

        // Dados válidos para os demais campos
        String cpfValido = "123.456.789-00";
        String nome = "Fernanda Lima";
        LocalDate nascimento = of(1991, 6, 22);

        // Teste com e-mail sem '@'
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailSemArroba));
        assertEquals("E-mail inválido", exception1.getMessage());

        // Teste com e-mail sem domínio antes do '.'
        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailSemDominio));
        assertEquals("E-mail inválido", exception2.getMessage());

        // Teste com e-mail contendo espaços
        IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailComEspacos));
        assertEquals("E-mail inválido", exception3.getMessage());

        // Teste com e-mail contendo caracteres especiais inválidos
        IllegalArgumentException exception4 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailComCaracteresEspeciais));
        assertEquals("E-mail inválido", exception4.getMessage());

        // Teste com e-mail com extensão de domínio muito curta
        IllegalArgumentException exception5 = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailParcialmenteInvalido));
        assertEquals("E-mail inválido", exception5.getMessage());
    }

    /**
     * Testa a criação de um usuário com e-mail nulo.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "E-mail inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com e-mail nulo")
    public void naoDeveCadastrarUsuarioComEmailNulo() {
        String emailNulo = null;

        String cpfValido = "123.456.789-00";
        String nome = "Gabriel Santos";
        LocalDate nascimento = of(1987, 9, 14);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailNulo));
        assertEquals("E-mail inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com e-mail vazio.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "E-mail inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com e-mail vazio")
    public void naoDeveCadastrarUsuarioComEmailVazio() {
        String emailVazio = "";

        String cpfValido = "123.456.789-00";
        String nome = "Ricardo Alves";
        LocalDate nascimento = of(1994, 2, 28);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailVazio));
        assertEquals("E-mail inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com e-mail contendo múltiplos '@'.
     * Verifica se uma IllegalArgumentException é lançada com a mensagem "E-mail inválido".
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com e-mail contendo múltiplos '@'")
    public void naoDeveCadastrarUsuarioComEmailMultiploArroba() {
        String emailMultiploArroba = "usuario@@exemplo.com";

        String cpfValido = "123.456.789-00";
        String nome = "Sofia Ribeiro";
        LocalDate nascimento = of(1996, 7, 19);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailMultiploArroba));
        assertEquals("E-mail inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com CPF válido e e-mail inválido.
     * Verifica se a exceção lançada é referente ao e-mail inválido.
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com CPF válido e e-mail inválido")
    public void naoDeveCadastrarUsuarioComCpfValidoEEmailInvalido() {
        String cpfValido = "123.456.789-00";
        String nome = "Lucas Silva";
        LocalDate nascimento = of(1992, 10, 5);
        String emailInvalido = "lucas.silva@exemplo"; // Sem extensão de domínio

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfValido, nome, nascimento, emailInvalido));
        assertEquals("E-mail inválido", exception.getMessage());
    }

    /**
     * Testa a criação de um usuário com e-mail válido e CPF inválido (máscara inválida).
     * Verifica se a exceção lançada é referente ao CPF inválido.
     */
    @Test
    @DisplayName("Não deve cadastrar usuário com e-mail válido e CPF inválido")
    public void naoDeveCadastrarUsuarioComEmailValidoECpfInvalido() {
        String cpfInvalido = "12345678910"; // Máscara inválida: Sem pontos e hífen
        String nome = "Mariana Costa";
        LocalDate nascimento = of(1993, 12, 15);
        String emailValido = "mariana.costa@example.com";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Usuario(cpfInvalido, nome, nascimento, emailValido));
        assertEquals("CPF inválido", exception.getMessage());
    }
}
