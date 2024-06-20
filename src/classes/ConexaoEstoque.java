/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cirol
 */
public class ConexaoEstoque {

    private ConexaoPrincipal conexao;
    private Connection conn;

    /*public Connection conn; // criando um objeto de tipo connection chamado conn
    public PreparedStatement st;
    public ResultSet rs;
    public String url = "jdbc:mysql://localhost:3306/projetointegrador"; //Nome da base de dados
    public String user = "root"; //Nome de usuario do MySQL
    public String password = "Ass4ssyn#"; //senha do MySQL*/
    public boolean conectar() {

        this.conexao = new ConexaoPrincipal();
        this.conn = this.conexao.getConexao();
        if (this.conn == null) {
            return false;
        } else {
            return true;
        }
    }

    
    public int salvarMaterial(Estoque est) {
    int status;
    try (
        // Preparar a declaração SQL para inserção
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Material(Descrição_Material) VALUES (?)");
    ) {
        // Definir o parâmetro da instrução de inserção
        ps.setString(1, est.getDescricaoEst());

        // Executar a inserção e obter o número de linhas afetadas
        status = ps.executeUpdate();

        return status;
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar para realizar o cadastro " + ex.getMessage());
        return ex.getErrorCode();
    }
}

    public List<Estoque> listagemEst(String termoBusca) {
        try {

            List<Estoque> lista = new ArrayList<>();
            String sqlFiltro = "SELECT E.id, M.Descrição_Material, E.Quantidade_Estoque, E.Obra \n" +
"FROM Material AS M\n" +
"INNER JOIN Estoque AS E ON E.material_id = M.id";
            if (!termoBusca.isEmpty()) {
                sqlFiltro = sqlFiltro + " WHERE obra like ?";

            }
            PreparedStatement st = conn.prepareStatement(sqlFiltro);
            if (!termoBusca.isEmpty()) {
                st.setString(1, "%" + termoBusca + "%");
            }
            // armazenar o resultado da pesquisa
            ResultSet rs = st.executeQuery();
            // por ser uma lista usamos o while 
           while (rs.next()) {
                Estoque estEncontrado = new Estoque();
                estEncontrado.setId(rs.getInt("id"));
                estEncontrado.setDescricaoEst(rs.getString("descrição_material"));
                estEncontrado.setQuantidade(rs.getDouble("quantidade_estoque"));
                estEncontrado.setObraEst(rs.getString("obra"));

                lista.add(estEncontrado);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar para realizar a listagem " + ex.getMessage());
            return null;
        }
    }
    public List<Estoque> listagemMat(String termoBusca) {
    List<Estoque> lista = new ArrayList<>();
    String sqlFiltro = "SELECT id, Descrição_Material FROM Material";

    if (!termoBusca.isEmpty()) {
        sqlFiltro = sqlFiltro + " WHERE Descrição_Material LIKE ?";
    }

    try (PreparedStatement st = conn.prepareStatement(sqlFiltro)) {
        if (!termoBusca.isEmpty()) {
            st.setString(1, "%" + termoBusca + "%");
        }

        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Estoque estEncontrado = new Estoque();
                estEncontrado.setId(rs.getInt("id"));
                estEncontrado.setDescricaoEst(rs.getString("Descrição_Material"));

                lista.add(estEncontrado);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar para realizar a listagem: " + ex.getMessage());
    }

    return lista;
}

    public boolean excluir(int id) {
    try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM estoque WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar para realizar a exclusão " + ex.getMessage());
            return false;
        }
}

    public int retirar(int id, Double quant) {
    int status;
    try {
        // Primeiro, atualizar a quantidade
        PreparedStatement st = conn.prepareStatement(
            "UPDATE Estoque AS E " +
            "JOIN Material AS M ON E.material_id = M.id " +
            "SET E.Quantidade_Estoque = E.Quantidade_Estoque - ? " +
            "WHERE E.id = ?;"
        );
        st.setDouble(1, quant);
        st.setInt(2, id);
        status = st.executeUpdate();

        // Verificar se a quantidade é 0 para remover a linha
        if (status > 0) {
            // Obter a quantidade atualizada
            PreparedStatement checkSt = conn.prepareStatement(
                "SELECT E.Quantidade_Estoque FROM Estoque AS E " +
                "WHERE E.id = ?;"
            );
            checkSt.setInt(1, id);
            ResultSet rs = checkSt.executeQuery();

            if (rs.next()) {
                double quantidadeAtual = rs.getDouble("Quantidade_Estoque");
                if (quantidadeAtual <= 0) {
                    // Remover a linha se a quantidade for 0 ou menos
                    PreparedStatement deleteSt = conn.prepareStatement(
                        "DELETE FROM Estoque WHERE id = ?;"
                    );
                    deleteSt.setInt(1, id);
                    deleteSt.executeUpdate();
                }
            }
        }

        return status;
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar para relatar o uso: " + ex.getMessage());
        return ex.getErrorCode();
    } catch (Exception e) {
        System.out.println("Não foi possível encontrar o respectivo produto: " + e.getMessage());
        return 0;
    }
}

    public List<Estoque> getEstoqueDesc() {
        String sql = "SELECT descrição_material FROM material";
        try {

            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Estoque> listaEstoque = new ArrayList<>();

            while (rs.next()){
            Estoque est = new Estoque();
            est.setDescricaoEst(rs.getString("descrição_material"));
            //est.setQuantidade(rs.getDouble("quantidade_estoque"));
            //est.setObraEst(rs.getString("obra"));
            listaEstoque.add(est);
            }
            return listaEstoque;

            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
            return null;
        }
    }
    

    public int alterar(Estoque est) {
    int status;
    try {
        // Consulta SQL para obter o id do material com base na descrição fornecida
        String query = "SELECT id FROM Material WHERE Descrição_Material = ?";
        
        // Preparar a declaração SQL
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, est.getDescricaoEst());
        
        // Executar a consulta e obter o ResultSet
        ResultSet rs = ps.executeQuery();
        
        // Verificar se há um resultado
        if (rs.next()) {
            // Se houver um resultado, obter o id do material
            int materialId = rs.getInt("id");
            
            // Instrução SQL para atualizar na tabela entrega com o material_id correspondente
            String updateQuery = "UPDATE estoque SET material_id=?, quantidade_estoque=?, obra=? "
                    + "WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(updateQuery);
            
            // Definir os parâmetros da instrução de atualização
            st.setInt(1, materialId);
            st.setDouble(2, est.getQuantidade());
            st.setString(3, est.getObraEst());
            st.setInt(4, est.getId());
            
            // Executar a declaração de atualização e obter o status
            status = st.executeUpdate();
            return status;
        } else {
            // Se não houver nenhum resultado para a descrição fornecida
            System.out.println("Material não encontrado para a descrição fornecida");
            return 0; // ou qualquer valor de status que indique que não foi possível encontrar o material
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar para alterar " + ex.getMessage());
        return ex.getErrorCode();
    }
}

    public void desconectar() {
        try {
            this.conn.close();
        } catch (SQLException ex) {

        }
    }
}
