/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author cirol
 */
public class ConexaoEntrega {

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

    public int salvar(Entrega ent) {
    int status;
    try {
        String query = "SELECT id FROM Material WHERE Descrição_Material = ?";
        
        // Preparar a declaração SQL
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ent.getDescricaoEnt());
        
        // Executar a consulta e obter o ResultSet
        ResultSet rs = ps.executeQuery();
        
        // Verificar se há um resultado
        if (rs.next()) {
            // Se houver um resultado, obter o id do material
            int materialId = rs.getInt("id");
            
            // Instrução SQL para inserir na tabela entrega com o material_id correspondente
            String insertQuery = "INSERT INTO entrega(material_id, quantidade_entrega, data_recebimento, obra) VALUES (?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(insertQuery);
            
            // Definir os parâmetros da instrução de inserção
            st.setInt(1, materialId);
            st.setDouble(2, ent.getQuantidade());
            st.setDate(3, (Date) ent.getRecebimento());
            st.setString(4, ent.getObra());
            // Supondo que você deseja usar a data atual para data_recebimento
            
            
            // Executar a instrução de inserção e obter o status
            status = st.executeUpdate();
            return status;
        } else {
            // Se não houver nenhum resultado para a descrição fornecida
            System.out.println("Material não encontrado para a descrição fornecida");
            return -1; // ou qualquer valor de status que indique que não foi possível encontrar o material
        }
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar para realizar o cadastro " + ex.getMessage());
        return ex.getErrorCode();
    }
}

    

    public List<Entrega> listagemEnt(String termoBusca) {
        try {

            List<Entrega> lista = new ArrayList<>();
            String sqlFiltro = "SELECT E. id, M.Descrição_Material, E.Quantidade_Entrega, E.Data_Recebimento, E.Obra \n" +
"FROM Material AS M\n" +
"INNER JOIN Entrega AS E ON E.material_id = M.id;";
            if (!termoBusca.isEmpty()) {
                sqlFiltro = sqlFiltro + " WHERE nome like ?";
                
            }
            PreparedStatement st = conn.prepareStatement(sqlFiltro);
            if (!termoBusca.isEmpty()) {
                st.setString(1, "%" + termoBusca + "%");
            }
            // armazenar o resultado da pesquisa
            ResultSet rs = st.executeQuery();
            // por ser uma lista usamos o while
            while (rs.next()) {
                Entrega entEncontrado = new Entrega();
                entEncontrado.setId(rs.getInt("id"));
                entEncontrado.setDescricaoEnt(rs.getString("descrição_material"));
                entEncontrado.setQuantidade(rs.getDouble("quantidade_entrega"));
                entEncontrado.setRecebimento(rs.getDate("data_recebimento"));
                entEncontrado.setObra(rs.getString("obra"));

                lista.add(entEncontrado);
            }
            return lista;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar para realizar a listagem " + ex.getMessage());
            return null;
        }
    }

    public boolean excluir(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM entrega WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar para realizar a exclusão " + ex.getMessage());
            return false;
        }
    }
    public int receber(String desc, Double quant, String obra) {
    int status;
    try {
        // Tentar atualizar o estoque existente
        PreparedStatement st = conn.prepareStatement(
            "UPDATE Estoque AS E " +
            "JOIN Material AS M ON E.material_id = M.id " +
            "SET E.Quantidade_Estoque = E.Quantidade_Estoque + ? " +
            "WHERE M.Descrição_Material = ? AND E.Obra = ?"
        );
        st.setDouble(1, quant);
        st.setString(2, desc);
        st.setString(3, obra);
        status = st.executeUpdate();

        // Se nenhum registro foi atualizado, inserir novo registro no estoque
        if (status == 0) {
            // Primeiro, buscar o ID do material
            PreparedStatement ps2 = conn.prepareStatement(
                "SELECT id FROM Material WHERE Descrição_Material = ?"
            );
            ps2.setString(1, desc);
            ResultSet rs2 = ps2.executeQuery();

            if (rs2.next()) {
                int materialId = rs2.getInt("id");

                // Inserir novo registro no estoque
                PreparedStatement stInsert = conn.prepareStatement(
                    "INSERT INTO Estoque(material_id, Quantidade_Estoque, Obra) VALUES (?, ?, ?)"
                );
                stInsert.setInt(1, materialId);
                stInsert.setDouble(2, quant);
                stInsert.setString(3, obra);
                status = stInsert.executeUpdate();

                stInsert.close();
            } else {
                System.out.println("Material não encontrado para a descrição fornecida");
                status = -1;
            }

            rs2.close();
            ps2.close();
        }

        st.close();
        return status;
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar para confirmar a entrega " + ex.getMessage());
        return ex.getErrorCode();
    } catch (Exception e) {
        System.out.println("Não foi possível encontrar o respectivo produto " + e.getMessage());
        return 0;
    }
}
    
    public void fillComboBox(JComboBox<String> comboBox) {
        String query = "SELECT descrição_material FROM material";
        
        try (PreparedStatement statement = this.conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            
            while (resultSet.next()) {
                String item = resultSet.getString("descrição_material");
                comboBox.addItem(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   

public int alterar(Entrega ent) {
    int status;
    try {
        // Consulta SQL para obter o id do material com base na descrição fornecida
        String query = "SELECT id FROM Material WHERE Descrição_Material = ?";
        
        // Preparar a declaração SQL
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, ent.getDescricaoEnt());
        
        // Executar a consulta e obter o ResultSet
        ResultSet rs = ps.executeQuery();
        
        // Verificar se há um resultado
        if (rs.next()) {
            // Se houver um resultado, obter o id do material
            int materialId = rs.getInt("id");
            
            // Instrução SQL para atualizar na tabela entrega com o material_id correspondente
            String updateQuery = "UPDATE entrega SET material_id=?, quantidade_entrega=?, "
                    + "data_recebimento=?, obra=? WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(updateQuery);
            
            // Verificar se a data de recebimento é nula
            java.util.Date recebimento = ent.getRecebimento();
            java.sql.Date sqlDate = null;
            if (recebimento != null) {
                // Converter java.util.Date para java.sql.Date apenas com a data (sem horário)
                sqlDate = new java.sql.Date(recebimento.getTime());
            }
            
            // Definir os parâmetros da instrução de atualização
            st.setInt(1, materialId);
            st.setDouble(2, ent.getQuantidade());
            st.setDate(3, sqlDate); // Pode ser nulo se recebimento for nulo
            st.setString(4, ent.getObra());
            st.setInt(5, ent.getId());
            
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
