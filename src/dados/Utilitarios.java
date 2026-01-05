/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

import java.util.InputMismatchException;

/**
 *
 * @author bruno
 */
public class Utilitarios {

    // verificador cpf
    public static boolean isCPF(String CPF) {
        // considera-se erro CPF"s formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
                || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
                || CPF.equals("99999999999") || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere "0" no inteiro 0
                // (48 eh a posicao de "0" na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    // mascara de cpf 999.999.999-99.
    public static String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
                + CPF.substring(9, 11));
    }

    public static boolean isNome(String nome) {

        if (nome == null || nome.length() < 2) {
            return false;
        }

        // Converte o nome para um array de caracteres
        char[] caracteres = nome.toCharArray();

        // Verifica cada caractere
        for (char c : caracteres) {
            // Verifica se o caractere é uma letra (A-Z, a-z) ou espaço
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || c == ' ')) {
                return false; // Se não for letra ou espaço, retorna falso
            }
        }

        return true; // Se todos os caracteres forem válidos, retorna verdadeiro

    }

    public static boolean isEmail(String email) {

        // Verifica a quantidade de '@'
        int ctd = 0;
        for (char c : email.toCharArray()) {
            if (c == '@') {
                ctd++;
            }
        }
        // Deve haver exatamente um '@'
        if (ctd != 1) {
            return false;
        }

        // Divide o e-mail em duas partes antes do @ e depois do @
        String[] partes = email.split("@");
        String antes = partes[0];
        String depois = partes[1];

        // Verifica se a parte antes @ e depois@ não estão vazios
        if (antes.isEmpty() || depois.isEmpty()) {
            return false;
        }

        // Verifica se o domínio contém pelo menos um ponto
        int ctdPonto = 0;
        for (char c : depois.toCharArray()) {
            if (c == '.') {
                ctdPonto++;
            }
        }

        // Deve haver pelo menos um ponto no domínio
        if (ctdPonto < 1) {
            return false;
        }

        // Verifica se o e-mail contém caracteres válidos
        for (char c : email.toCharArray()) {
            if (!((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')
                    || (c >= '0' && c <= '9') || c == '@' || c == '.' || c == '_' || c == '-')) {
                return false; // Se não for um caractere válido, retorna falso
            }
        }

        return true; // Se todas as condições forem atendidas, retorna verdadeiro

    }

    public static boolean validarDataValidade(String dataValidade) {
        // Verifica se a data está no formato correto
        if (!dataValidade.matches("\\d{2}/\\d{2}/\\d{4}")) {

            System.out.println("Data de validade inválida. O formato deve ser DD/MM/AAAA.");
            return false;

        }

        // Separa o dia, mês e ano
        String[] partes = dataValidade.split("/");
        int dia = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int ano = Integer.parseInt(partes[2]);

        // Verifica se a data é válida
        if (!dataEhValida(dia, mes, ano)) {

            System.out.println("Data de validade inválida.");
            return false;
        }

        // Obtém a data atual
        String[] dataAtualPartes = obterDataAtual().split("/");
        int diaAtual = Integer.parseInt(dataAtualPartes[0]);
        int mesAtual = Integer.parseInt(dataAtualPartes[1]);
        int anoAtual = Integer.parseInt(dataAtualPartes[2]);

        // Verifica se a data de validade é anterior à data atual
        if (ano < anoAtual || (ano == anoAtual && mes < mesAtual) || (ano == anoAtual && mes == mesAtual && dia < diaAtual)) {

            System.out.println("A data de validade está expirada.");
            return false;

        } else {
            System.out.println("A data de validade é válida.");
            return true;
        }
    }

    private static boolean dataEhValida(int dia, int mes, int ano) {
        // Verifica se o ano é bissexto
        boolean anoBissexto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);

        // Verifica os dias de cada mês
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return dia >= 1 && dia <= 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return dia >= 1 && dia <= 30;
            case 2:
                return anoBissexto ? dia >= 1 && dia <= 29 : dia >= 1 && dia <= 28;
            default:
                return false; // Mês inválido
        }
    }

    private static String obterDataAtual() {
        // Obtém a data atual no formato DD/MM/AAAA
        java.util.Calendar calendario = java.util.Calendar.getInstance();
        int dia = calendario.get(java.util.Calendar.DAY_OF_MONTH);
        int mes = calendario.get(java.util.Calendar.MONTH) + 1; // Janeiro é 0
        int ano = calendario.get(java.util.Calendar.YEAR);
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }

    // Método que valida se a string contém apenas números
    public static boolean ehNumeroValido(String entrada) {
        return entrada.matches("\\d+");
    }

   // Método que valida se a string pode ser convertida para um número do tipo double
public static boolean ehDoubleValido(String entrada) {
    return entrada.matches("[+]?\\d*\\.?\\d+");
}

    public static void main(String[] args) {

        // Exemplos de teste Email
        String[] emailsParaTestar = {
            "teste@exemplo.com",
            "usuario@dominio",
            "usuario@dominio.com.",
            "usuario@.com",
            "usuario@dominio.c",
            "test@exemplo.com",
            "test@exemplo..com",
            "test@exemplo.com.br"
        };

        for (String email : emailsParaTestar) {
            if (isEmail(email)) {
                System.out.println(email + " e um e-mail valido.");
            } else {
                System.out.println(email + " e um e-mail invalido.");
            }
        }

        // Exemplos de teste Nome
        String[] nomesParaTestar = {
            "João Silva",
            "Ana Maria",
            "Carlos123",
            "A",
            "Maria!",
            "Pedro da Silva"
        };

        for (String nome : nomesParaTestar) {
            if (isNome(nome)) {
                System.out.println(nome + " e um nome valido.");
            } else {
                System.out.println(nome + " e um nome invalido.");
            }
        }

        String data = "15/10/2023"; // Altere esta data para testar
        boolean resultado = validarDataValidade(data);
        System.out.println(resultado);
        
        System.out.println("dados.Utilitarios.main()");
        System.out.println(ehDoubleValido("123.45")); // true
    System.out.println(ehDoubleValido("-123.45")); // true
    System.out.println(ehDoubleValido("+123.45")); // true
    System.out.println(ehDoubleValido("123")); // true
    System.out.println(ehDoubleValido("abc")); // false
    System.out.println(ehDoubleValido("123.")); // false
    System.out.println(ehDoubleValido(".45")); // true
    }

}
