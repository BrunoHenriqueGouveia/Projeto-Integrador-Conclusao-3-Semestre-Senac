/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dados;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 *
 * @author bruno
 */
public class UtilitariosTest {
    
    public UtilitariosTest() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
    public void testCPFValido() {
        assertTrue(Utilitarios.isCPF("37476402812")); // CPF válido
        assertTrue(Utilitarios.isCPF("98765432100")); // CPF válido
    }

    @Test
    public void testCPFInvalido() {
        assertFalse(Utilitarios.isCPF("00000000000")); // Sequência inválida
        assertFalse(Utilitarios.isCPF("12345678900")); // Dígito verificador incorreto
        assertFalse(Utilitarios.isCPF("123")); // Muito curto
        assertFalse(Utilitarios.isCPF("abcdefghijk")); // Letras
    }

    @ParameterizedTest
    @ValueSource(strings = {"João Silva", "Ana Maria", "Pedro da Silva"})
    public void testNomeValido(String nome) {
        assertTrue(Utilitarios.isNome(nome));
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "Maria!", "Carlos123", ""})
    public void testNomeInvalido(String nome) {
        assertFalse(Utilitarios.isNome(nome));
    }

    @ParameterizedTest
    @ValueSource(strings = {"teste@exemplo.com", "usuario@dominio.com.br", "test@exemplo.com"})
    public void testEmailValido(String email) {
        assertTrue(Utilitarios.isEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"usuario@dominio", "usuario@@dominio.com", "usuario@.com", "usuario@dominio.", ""})
    public void testEmailInvalido(String email) {
        assertFalse(Utilitarios.isEmail(email));
    }

    @Test
    public void testDataValidadeValida() {
        assertTrue(Utilitarios.validarDataValidade("15/10/2025")); // Data futura
        assertTrue(Utilitarios.validarDataValidade("01/01/2024")); // Ano seguinte
    }

    @Test
    public void testDataValidadeInvalida() {
        assertFalse(Utilitarios.validarDataValidade("32/12/2025")); // Dia inválido
        assertFalse(Utilitarios.validarDataValidade("29/02/2023")); // Ano não bissexto
        assertFalse(Utilitarios.validarDataValidade("01/01/2020")); // Data passada
    }



}
