package dados;

import java.sql.*;

public class AppDao {

    private Connection conectado;
    private ResultSet resultado;
    private PreparedStatement st;
    private Statement stmt;

    private void conectar() throws ClassNotFoundException, SQLException {
        // Carrega o driver do MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Estabelece a conexão com o banco de dados "unistore"
        conectado = DriverManager.getConnection("jdbc:mysql://localhost:3306/unistore", "root", "P@$$w0rd");
    }

    public ResultSet qtdLojas() throws ClassNotFoundException, SQLException {
        conectar();// Abre a conexão
        st = conectado.prepareStatement("SELECT COUNT(*) AS total_lojas FROM loja");
        ResultSet lojas = st.executeQuery();// executa o select//import java.sql.ResultSet;
        return lojas;
    }

    public ResultSet qtdItens(String nomeLoja) throws ClassNotFoundException, SQLException {
        conectar();// Abre a conexão
        st = conectado.prepareStatement("SELECT \n"
                + "COUNT(item.idItem) AS qtd_itens\n"
                + "FROM loja\n"
                + "INNER JOIN item ON item.fk_loja = loja.fk_idVendedor\n"
                + "WHERE loja.nomeLoja = ?");

        st.setString(1, nomeLoja);
        ResultSet qtdItens = st.executeQuery();// executa o select//import java.sql.ResultSet;
        return qtdItens;
    }

    // Método para salvar um usuário no banco de dados
    public void salvarUsuario(Usuario u) throws ClassNotFoundException, SQLException {
        conectar(); // Abre a conexão com o banco de dados

        // Prepara a consulta SQL para inserir um novo usuário na tabela 'usuarios'
        String sql = "INSERT INTO usuarios (nome, email, senha, cpf) VALUES (?, ?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            // Define os parâmetros da consulta com os dados do objeto Usuario
            st.setString(1, u.getNome()); // Define o nome do usuário
            st.setString(2, u.getEmail()); // Define o email do usuário
            st.setString(3, u.getSenha()); // Define a senha do usuário
            st.setString(4, u.getCpf()); // Define o CPF do usuário

            // Executa a inserção no banco de dados
            st.execute(); // Salva os dados no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Log de erro ao executar a consulta
            throw e; // Re-lança a exceção para tratamento posterior, se necessário
        }
    }

    public void salvarReserva(ReservaPedido reserva) throws ClassNotFoundException, SQLException {
        conectar(); // Abre a conexão com o banco de dados

        // Prepara a consulta SQL para inserir uma nova reserva
        String sql = "INSERT INTO reserva (idItem, nomeProduto, qtd, valor, localDaReserva, horario, nomeLoja, nomeVendedor, data, idVendedor, idComprador, statusComprador, statusVendedor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            // Define os parâmetros da consulta com os dados do objeto ReservaPedido
            st.setInt(1, reserva.getIdItem()); // Define o ID do item
            st.setString(2, reserva.getNomeProduto()); // Define o nome do produto
            st.setInt(3, reserva.getQtd()); // Define a quantidade
            st.setDouble(4, reserva.getValor()); // Define o valor
            st.setString(5, reserva.getLocalDaReserva()); // Define o local da reserva
            st.setString(6, reserva.getHorario()); // Define o horário da reserva
            st.setString(7, reserva.getNomeLoja()); // Define o nome da loja
            st.setString(8, reserva.getNomeVendedor()); // Define o nome do vendedor
            st.setString(9, reserva.getData()); // Define a data da reserva
            st.setInt(10, reserva.getIdVendedor()); // Define o ID do vendedor
            st.setInt(11, reserva.getIdComprador()); // Define o ID do comprador
            st.setString(12, reserva.getStatusComprador()); // Define o status do comprador
            st.setString(13, reserva.getStatusVendedor()); // Define o status do vendedor

            // Executa a inserção no banco de dados
            st.execute(); // Salva os dados no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Log de erro ao executar a consulta
            throw e; // Re-lança a exceção para tratamento posterior, se necessário
        }
    }

    // Método para salvar um vendedor no banco de dados
    public void salvarVendedor(Vendedor v, int idUsuario) throws ClassNotFoundException, SQLException {
        conectar(); // Abre a conexão com o banco de dados

        // Prepara a consulta SQL para inserir um novo vendedor na tabela 'vendedor'
        String sql = "INSERT INTO vendedor (idVendedor, sobreVC, fk_idUsuario) VALUES (?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            // Define os parâmetros da consulta com os dados do objeto Vendedor
            st.setInt(1, idUsuario); // Define o ID do vendedor (idUsuario)
            st.setString(2, v.getSobreVc()); // Define a descrição do vendedor
            st.setInt(3, idUsuario); // Define a chave estrangeira para o ID do usuário

            // Executa a inserção no banco de dados
            st.execute(); // Salva os dados no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Log de erro ao executar a consulta
            throw e; // Re-lança a exceção para tratamento posterior, se necessário
        }
    }

    // Método para salvar uma loja no banco de dados
    public void salvarLoja(LojaVendedor lv, int idVendedor) throws ClassNotFoundException, SQLException {
        conectar(); // Abre a conexão com o banco de dados

        // Prepara a consulta SQL para inserir uma nova loja na tabela 'loja'
        String sql = "INSERT INTO loja (nomeLoja, miniDescricao, categoria, localRetirada, horarioVenda, fk_idVendedor) VALUES (?, ?, ?, ?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            // Define os parâmetros da consulta com os dados do objeto LojaVendedor
            st.setString(1, lv.getNomeDaLoja()); // Define o nome da loja
            st.setString(2, lv.getMiniDescricaoLoja()); // Define a mini descrição da loja
            st.setString(3, lv.getCategoria()); // Define a categoria da loja
            st.setString(4, lv.getLocalRetirada()); // Define o local de retirada
            st.setString(5, lv.getHorarioVenda()); // Define o horário de venda
            st.setInt(6, idVendedor); // Define a chave estrangeira para o ID do vendedor

            // Executa a inserção no banco de dados
            st.execute(); // Salva os dados no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Log de erro ao executar a consulta
            throw e; // Re-lança a exceção para tratamento posterior, se necessário
        }
    }

    // Método para salvar um item no banco de dados
    public void salvaItem(Item i, int idVendedor) throws ClassNotFoundException, SQLException {
        conectar(); // Abre a conexão com o banco de dados

        // Prepara a consulta SQL para inserir um novo item na tabela 'item'
        String sql = "INSERT INTO item (nomeProduto, miniDescricaoProduto, descricaoProduto, validade, qtdProduto, valor, fk_loja) VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Usando try-with-resources para garantir o fechamento automático dos recursos
        try (PreparedStatement st = conectado.prepareStatement(sql)) {
            // Define os parâmetros da consulta com os dados do objeto Item
            st.setString(1, i.getNomeProduto()); // Define o nome do produto
            st.setString(2, i.getMiniDescricaoProduto()); // Define a mini descrição do produto
            st.setString(3, i.getDescricaoProduto()); // Define a descrição do produto
            st.setString(4, i.getValidade()); // Define a validade do produto
            st.setInt(5, i.getQtdProduto()); // Define a quantidade do produto
            st.setDouble(6, i.getPreco()); // Define o preço do produto
            st.setInt(7, idVendedor); // Define a chave estrangeira para o ID da loja

            // Executa a inserção no banco de dados
            st.execute(); // Salva os dados no banco de dados
        } catch (SQLException e) {
            e.printStackTrace(); // Log de erro ao executar a consulta
            throw e; // Re-lança a exceção para tratamento posterior, se necessário
        }
    }

    // Método para autenticar um usuário com base no email e senha fornecidos
    public ResultSet entrar(String u, String s) throws ClassNotFoundException, SQLException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar o usuário e a senha na tabela do banco de dados
        st = conectado.prepareStatement("SELECT \n"
                + "    u.*, \n" // Seleciona todas as colunas da tabela 'usuarios'
                + "    CASE WHEN v.idVendedor IS NOT NULL THEN 'Sim' ELSE 'Não' END AS e_vendedor\n" // Verifica se o usuário é um vendedor
                + "FROM \n"
                + "    usuarios u\n" // Tabela de usuários
                + "LEFT JOIN \n"
                + "    vendedor v ON u.idUsuario = v.fk_idUsuario\n" // Faz um LEFT JOIN com a tabela 'vendedor' para verificar se o usuário é um vendedor
                + "WHERE \n"
                + "    u.email = ? \n" // Filtra pelo email do usuário
                + "    AND u.senha = ?"); // Filtra pela senha do usuário

        // 3. Define os parâmetros da consulta com os dados fornecidos
        st.setString(1, u); // Define o primeiro parâmetro como o email do usuário
        st.setString(2, s); // Define o segundo parâmetro como a senha do usuário

        // 4. Executa a consulta e armazena o resultado
        resultado = st.executeQuery(); // Executa o SELECT e obtém o ResultSet com os dados

        // 5. Retorna o ResultSet com os dados do usuário
        return resultado; // Retorna o resultado da consulta
    }

    // Método para excluir um usuário com base no ID fornecido
    public int excluirUsuario(int id) throws ClassNotFoundException, SQLException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para excluir um usuário da tabela 'Usuarios'
        st = conectado.prepareStatement("DELETE FROM Usuarios WHERE idUsuario = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do usuário a ser excluído

        // 3. Executa a consulta de exclusão e armazena o número de linhas afetadas
        int retorno = st.executeUpdate(); // Executa o DELETE e obtém o número de linhas afetadas

        // 4. Retorna o número de linhas afetadas (0 se nenhum usuário foi excluído)
        return retorno;
    }

// Método para excluir todos os itens associados a uma loja com base no ID da loja
    public int excluirTodosItens(int id) throws ClassNotFoundException, SQLException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para excluir todos os itens da tabela 'item' associados a uma loja
        st = conectado.prepareStatement("DELETE FROM item WHERE fk_loja = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID da loja

        // 3. Executa a consulta de exclusão e armazena o número de linhas afetadas
        int retorno = st.executeUpdate(); // Executa o DELETE e obtém o número de linhas afetadas

        // 4. Retorna o número de linhas afetadas (0 se nenhum item foi excluído)
        return retorno;
    }

// Método para excluir uma loja com base no ID do vendedor associado
    public int excluirLoja(int id) throws ClassNotFoundException, SQLException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para excluir uma loja da tabela 'loja'
        st = conectado.prepareStatement("DELETE FROM loja WHERE fk_idVendedor = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do vendedor

        // 3. Executa a consulta de exclusão e armazena o número de linhas afetadas
        int retorno = st.executeUpdate(); // Executa o DELETE e obtém o número de linhas afetadas

        // 4. Retorna o número de linhas afetadas (0 se nenhuma loja foi excluída)
        return retorno;
    }

// Método para excluir um vendedor com base no ID do usuário associado
    public int excluirVendedor(int id) throws ClassNotFoundException, SQLException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para excluir um vendedor da tabela 'vendedor'
        st = conectado.prepareStatement("DELETE FROM vendedor WHERE fk_idUsuario = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do usuário

        // 3. Executa a consulta de exclusão e armazena o número de linhas afetadas
        int retorno = st.executeUpdate(); // Executa o DELETE e obtém o número de linhas afetadas

        // 4. Retorna o número de linhas afetadas (0 se nenhum vendedor foi excluído)
        return retorno;
    }

// Método para excluir um item com base no ID da loja e no nome do item
    public int excluirItem(int id, String nomeItem) throws ClassNotFoundException, SQLException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para excluir um item da tabela 'item' com base no ID da loja e no nome do item
        st = conectado.prepareStatement("DELETE FROM item WHERE fk_loja = ? AND nomeProduto = ?");
        st.setInt(1, id); // Define o primeiro parâmetro como o ID da loja
        st.setString(2, nomeItem); // Define o segundo parâmetro como o nome do item

        // 3. Executa a consulta de exclusão e armazena o número de linhas afetadas
        int retorno = st.executeUpdate(); // Executa o DELETE e obtém o número de linhas afetadas

        // 4. Retorna o número de linhas afetadas (0 se nenhum item foi excluído)
        return retorno;
    }

    // Método para buscar uma reserva com base no ID da reserva fornecido
    public ResultSet buscarReserva(int idReserva) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar a reserva na tabela 'reserva'
        st = conectado.prepareStatement("SELECT * FROM reserva WHERE idReserva = ?");
        st.setInt(1, idReserva); // Define o parâmetro da consulta como o ID da reserva

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet usuario = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com os dados da reserva
        return usuario;
    }

// Método para buscar um usuário com base no ID do usuário fornecido
    public ResultSet buscarUsuario(int id) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar o usuário na tabela 'usuarios'
        st = conectado.prepareStatement("SELECT * FROM usuarios WHERE idUsuario = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do usuário

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet usuario = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com os dados do usuário
        return usuario;
    }

// Método para buscar o histórico de compras de um usuário com base no ID do comprador
    public ResultSet buscarHistoricoCompras(int id) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar o histórico de compras na tabela 'reserva'
        st = conectado.prepareStatement("SELECT * FROM reserva WHERE idComprador = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do comprador

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet historico = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com o histórico de compras
        return historico;
    }
    // Método para buscar o histórico de vendas de um vendedor com base no ID do vendedor

    public ResultSet buscarHistoricoVendas(int id) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar o histórico de vendas na tabela 'reserva'
        st = conectado.prepareStatement("SELECT \n"
                + "    reserva.*, \n"
                + "    usuario.nome AS nomeComprador \n"
                + "FROM \n"
                + "    reserva \n"
                + "JOIN \n"
                + "    usuarios usuario ON reserva.idComprador = usuario.idUsuario\n"
                + "WHERE \n"
                + "    reserva.idVendedor = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do vendedor

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet historico = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com o histórico de vendas
        return historico;
    }

// Método para buscar uma loja com base no ID do vendedor associado
    public ResultSet buscarLoja(int id) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar a loja na tabela 'loja'
        st = conectado.prepareStatement("SELECT * FROM loja WHERE fk_idVendedor = ?");
        st.setInt(1, id); // Define o parâmetro da consulta como o ID do vendedor

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet usuario = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com os dados da loja
        return usuario;
    }

// Método para buscar uma loja com base no nome da loja fornecido
    public ResultSet buscarLoja(String nomeLoja) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar a loja e seus detalhes associados
        st = conectado.prepareStatement("SELECT usuarios.nome, vendedor.sobreVC, item.*, loja.*\n"
                + "FROM loja\n"
                + "INNER JOIN usuarios ON usuarios.idUsuario = loja.fk_idVendedor\n"
                + "INNER JOIN vendedor ON loja.fk_idVendedor = vendedor.fk_idUsuario\n"
                + "INNER JOIN item ON loja.fk_idVendedor = item.fk_loja\n"
                + "WHERE loja.nomeLoja = ?");
        st.setString(1, nomeLoja); // Define o parâmetro da consulta como o nome da loja

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet usuario = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com os dados da loja
        return usuario;
    }

    // Método para buscar todas as lojas no banco de dados
    public ResultSet buscarTodasLojas() throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar todos os registros da tabela 'loja'
        st = conectado.prepareStatement("SELECT * FROM loja");

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet lojas = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com todos os dados das lojas
        return lojas;
    }

// Método para buscar um item específico em uma loja com base no ID da loja e no nome do produto
    public ResultSet buscarItem(int id, String nomeProduto) throws SQLException, ClassNotFoundException {
        // 1. Conectar ao banco de dados
        conectar();

        // 2. Preparar a consulta SQL para buscar um item na tabela 'item' com base no ID da loja e no nome do produto
        st = conectado.prepareStatement("SELECT * FROM item WHERE fk_loja = ? AND nomeProduto = ?");
        st.setInt(1, id); // Define o primeiro parâmetro da consulta como o ID da loja
        st.setString(2, nomeProduto); // Define o segundo parâmetro da consulta como o nome do produto

        // 3. Executa a consulta e armazena o resultado em um ResultSet
        ResultSet usuario = st.executeQuery(); // Executa o SELECT

        // 4. Retorna o ResultSet com os dados do item encontrado
        return usuario;
    }

    public void alterarUsuario(Usuario u, int idUsuario) throws ClassNotFoundException, SQLException {
        // Conecta ao banco de dados
        conectar();

        // Prepara a consulta SQL para atualizar os dados do usuário
        st = conectado.prepareStatement("UPDATE usuarios SET nome = ?, email = ?, senha = ?, cpf = ? WHERE idUsuario = ?");

        // Define os valores dos parâmetros da consulta
        st.setString(1, u.getNome()); // Define o nome do usuário
        st.setString(2, u.getEmail()); // Define o email do usuário
        st.setString(3, u.getSenha()); // Define a senha do usuário
        st.setString(4, u.getCpf()); // Define o CPF do usuário
        st.setInt(5, idUsuario); // Define o ID do usuário a ser atualizado

        // Executa a atualização no banco de dados
        st.executeUpdate(); // Executa o update
    }

    public void alterarStatus(int idReserva, String status, String statusPessoa) throws ClassNotFoundException, SQLException {
        // Conecta ao banco de dados
        conectar();

        // Monta a consulta SQL. O nome da coluna deve ser concatenado diretamente na string.
        String sql = "UPDATE reserva SET " + statusPessoa + " = ? WHERE idReserva = ?";

        // Prepara a consulta
        st = conectado.prepareStatement(sql);

        // Define os valores dos parâmetros
        st.setString(1, status); // Define o novo status
        st.setInt(2, idReserva); // Define o ID da reserva

        // Executa a atualização no banco de dados
        st.executeUpdate(); // Executa o update
    }

    public void alterarDadosLoja(String getNomeDaLoja, String getMiniDescricaoLoja, String getCategoria, String getLocalRetirada, String getHorarioVenda, int idUsuario) throws ClassNotFoundException, SQLException {
        // Conecta ao banco de dados
        conectar();

        // Prepara a consulta SQL para atualizar os dados da loja
        st = conectado.prepareStatement("UPDATE loja SET nomeLoja = ?, miniDescricao = ?, categoria = ?, localRetirada = ?, horarioVenda = ? WHERE fk_idVendedor = ?");

        // Define os valores dos parâmetros da consulta
        st.setString(1, getNomeDaLoja); // Define o nome da loja
        st.setString(2, getMiniDescricaoLoja); // Define a mini descrição da loja
        st.setString(3, getCategoria); // Define a categoria da loja
        st.setString(4, getLocalRetirada); // Define o local de retirada
        st.setString(5, getHorarioVenda); // Define o horário de venda
        st.setInt(6, idUsuario); // Define o ID do vendedor associado à loja

        // Executa a atualização no banco de dados
        st.executeUpdate(); // Executa o update
    }

    public void alterarItem(Item item, int idUsuario, String nomeProduto) throws ClassNotFoundException, SQLException {
        // Conecta ao banco de dados
        conectar();

        // Prepara a consulta SQL para atualizar os dados do item
        st = conectado.prepareStatement("UPDATE item SET nomeProduto = ?, miniDescricaoProduto = ?, descricaoProduto = ?, validade = ?, qtdProduto = ?, valor = ? WHERE fk_loja = ? AND nomeProduto = ?");

        // Define os valores dos parâmetros da consulta
        st.setString(1, item.getNomeProduto()); // Define o nome do produto
        st.setString(2, item.getMiniDescricaoProduto()); // Define a mini descrição do produto
        st.setString(3, item.getDescricaoProduto()); // Define a descrição do produto
        st.setString(4, item.getValidade()); // Define a validade do produto
        st.setInt(5, item.getQtdProduto()); // Define a quantidade do produto
        st.setDouble(6, item.getPreco()); // Define o valor do produto
        st.setInt(7, idUsuario); // Define o ID da loja associada ao item
        st.setString(8, nomeProduto); // Define o nome do produto a ser atualizado

        // Executa a atualização no banco de dados
        st.executeUpdate(); // Executa o update
    }

    public void closeConnection() {
        if (conectado != null) {
            try {
                conectado.close();
                conectado = null; // Define como null para evitar fechamentos múltiplos
            } catch (SQLException e) {
                System.err.println("Não foi possível fechar a conexão: " + e.getMessage());
            }
        }
    }

    public void closeStatement() {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null; // Define como null para evitar fechamentos múltiplos
            } catch (SQLException e) {
                System.err.println("Não foi possível fechar o Statement: " + e.getMessage());
            }
        }
    }

    public void closeResultSet() {
        if (resultado != null) {
            try {
                resultado.close();
                resultado = null; // Define como null para evitar fechamentos múltiplos
            } catch (SQLException e) {
                System.err.println("Não foi possível fechar o ResultSet: " + e.getMessage());
            }
        }
    }

    public void closePreparedStatement() {
        if (st != null) {
            try {
                st.close();
                st = null; // Define como null para evitar fechamentos múltiplos
            } catch (SQLException e) {
                System.err.println("Não foi possível fechar o PreparedStatement: " + e.getMessage());
            }
        }
    }

}
