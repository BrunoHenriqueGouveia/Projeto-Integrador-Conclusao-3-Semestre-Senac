/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dados;

import java.sql.ResultSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bruno.hgsilva3
 */
public class AppDaoTest {
    
    public AppDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    

    /**
     * Test of qtdLojas method, of class AppDao.
     */
    @Test
    public void testQtdLojas() throws Exception {
        System.out.println("qtdLojas");
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.qtdLojas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of qtdItens method, of class AppDao.
     */
    @Test
    public void testQtdItens() throws Exception {
        System.out.println("qtdItens");
        String nomeLoja = "";
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.qtdItens(nomeLoja);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarUsuario method, of class AppDao.
     */
    @Test
    public void testSalvarUsuario() throws Exception {
        System.out.println("salvarUsuario");
        Usuario u = null;
        AppDao instance = new AppDao();
        instance.salvarUsuario(u);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarReserva method, of class AppDao.
     */
    @Test
    public void testSalvarReserva() throws Exception {
        System.out.println("salvarReserva");
        ReservaPedido reserva = null;
        AppDao instance = new AppDao();
        instance.salvarReserva(reserva);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarVendedor method, of class AppDao.
     */
    @Test
    public void testSalvarVendedor() throws Exception {
        System.out.println("salvarVendedor");
        Vendedor v = null;
        int idUsuario = 0;
        AppDao instance = new AppDao();
        instance.salvarVendedor(v, idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarLoja method, of class AppDao.
     */
    @Test
    public void testSalvarLoja() throws Exception {
        System.out.println("salvarLoja");
        LojaVendedor lv = null;
        int idVendedor = 0;
        AppDao instance = new AppDao();
        instance.salvarLoja(lv, idVendedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvaItem method, of class AppDao.
     */
    @Test
    public void testSalvaItem() throws Exception {
        System.out.println("salvaItem");
        Item i = null;
        int idVendedor = 0;
        AppDao instance = new AppDao();
        instance.salvaItem(i, idVendedor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of entrar method, of class AppDao.
     */
    @Test
    public void testEntrar() throws Exception {
        System.out.println("entrar");
        String u = "bruno@gmail.com";
        String s = "123456";
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.entrar(u, s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirUsuario method, of class AppDao.
     */
    @Test
    public void testExcluirUsuario() throws Exception {
        System.out.println("excluirUsuario");
        int id = 0;
        AppDao instance = new AppDao();
        int expResult = 0;
        int result = instance.excluirUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirTodosItens method, of class AppDao.
     */
    @Test
    public void testExcluirTodosItens() throws Exception {
        System.out.println("excluirTodosItens");
        int id = 0;
        AppDao instance = new AppDao();
        int expResult = 0;
        int result = instance.excluirTodosItens(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirLoja method, of class AppDao.
     */
    @Test
    public void testExcluirLoja() throws Exception {
        System.out.println("excluirLoja");
        int id = 0;
        AppDao instance = new AppDao();
        int expResult = 0;
        int result = instance.excluirLoja(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirVendedor method, of class AppDao.
     */
    @Test
    public void testExcluirVendedor() throws Exception {
        System.out.println("excluirVendedor");
        int id = 0;
        AppDao instance = new AppDao();
        int expResult = 0;
        int result = instance.excluirVendedor(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluirItem method, of class AppDao.
     */
    @Test
    public void testExcluirItem() throws Exception {
        System.out.println("excluirItem");
        int id = 0;
        String nomeItem = "";
        AppDao instance = new AppDao();
        int expResult = 0;
        int result = instance.excluirItem(id, nomeItem);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarReserva method, of class AppDao.
     */
    @Test
    public void testBuscarReserva() throws Exception {
        System.out.println("buscarReserva");
        int idReserva = 0;
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarReserva(idReserva);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarUsuario method, of class AppDao.
     */
    @Test
    public void testBuscarUsuario() throws Exception {
        System.out.println("buscarUsuario");
        int id = 0;
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarUsuario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarHistoricoCompras method, of class AppDao.
     */
    @Test
    public void testBuscarHistoricoCompras() throws Exception {
        System.out.println("buscarHistoricoCompras");
        int id = 0;
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarHistoricoCompras(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarHistoricoVendas method, of class AppDao.
     */
    @Test
    public void testBuscarHistoricoVendas() throws Exception {
        System.out.println("buscarHistoricoVendas");
        int id = 0;
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarHistoricoVendas(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarLoja method, of class AppDao.
     */
    @Test
    public void testBuscarLoja_int() throws Exception {
        System.out.println("buscarLoja");
        int id = 0;
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarLoja(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarLoja method, of class AppDao.
     */
    @Test
    public void testBuscarLoja_String() throws Exception {
        System.out.println("buscarLoja");
        String nomeLoja = "";
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarLoja(nomeLoja);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarTodasLojas method, of class AppDao.
     */
    @Test
    public void testBuscarTodasLojas() throws Exception {
        System.out.println("buscarTodasLojas");
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarTodasLojas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarItem method, of class AppDao.
     */
    @Test
    public void testBuscarItem() throws Exception {
        System.out.println("buscarItem");
        int id = 0;
        String nomeProduto = "";
        AppDao instance = new AppDao();
        ResultSet expResult = null;
        ResultSet result = instance.buscarItem(id, nomeProduto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarUsuario method, of class AppDao.
     */
    @Test
    public void testAlterarUsuario() throws Exception {
        System.out.println("alterarUsuario");
        Usuario u = null;
        int idUsuario = 0;
        AppDao instance = new AppDao();
        instance.alterarUsuario(u, idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarStatus method, of class AppDao.
     */
    @Test
    public void testAlterarStatus() throws Exception {
        System.out.println("alterarStatus");
        int idReserva = 0;
        String status = "";
        String statusPessoa = "";
        AppDao instance = new AppDao();
        instance.alterarStatus(idReserva, status, statusPessoa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarDadosLoja method, of class AppDao.
     */
    @Test
    public void testAlterarDadosLoja() throws Exception {
        System.out.println("alterarDadosLoja");
        String getNomeDaLoja = "";
        String getMiniDescricaoLoja = "";
        String getCategoria = "";
        String getLocalRetirada = "";
        String getHorarioVenda = "";
        int idUsuario = 0;
        AppDao instance = new AppDao();
        instance.alterarDadosLoja(getNomeDaLoja, getMiniDescricaoLoja, getCategoria, getLocalRetirada, getHorarioVenda, idUsuario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alterarItem method, of class AppDao.
     */
    @Test
    public void testAlterarItem() throws Exception {
        System.out.println("alterarItem");
        Item item = null;
        int idUsuario = 0;
        String nomeProduto = "";
        AppDao instance = new AppDao();
        instance.alterarItem(item, idUsuario, nomeProduto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class AppDao.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        AppDao instance = new AppDao();
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeStatement method, of class AppDao.
     */
    @Test
    public void testCloseStatement() {
        System.out.println("closeStatement");
        AppDao instance = new AppDao();
        instance.closeStatement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeResultSet method, of class AppDao.
     */
    @Test
    public void testCloseResultSet() {
        System.out.println("closeResultSet");
        AppDao instance = new AppDao();
        instance.closeResultSet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closePreparedStatement method, of class AppDao.
     */
    @Test
    public void testClosePreparedStatement() {
        System.out.println("closePreparedStatement");
        AppDao instance = new AppDao();
        instance.closePreparedStatement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
