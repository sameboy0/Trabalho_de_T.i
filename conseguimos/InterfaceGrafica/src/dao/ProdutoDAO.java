
package dao;

import magica.Conexao;
import beans.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Conexao conexao;
    private Connection conn;

    public ProdutoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Produto Produto){
        String sql = "INSERT INTO produtos (id, descricao, qtde, preco) VALUES (?,?,?,?) ";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, Produto.getId());
            stmt.setString(2, Produto.getDescricao());
            stmt.setInt(3,Produto.getQtde());
            stmt.setDouble(4,Produto.getPreco());
            stmt.execute();
        }catch (Exception e){
            System.out.println("Erro ao inserir produto: "+ e.getMessage());
        }
    }

    public void alterar(Produto Produto){
        String sql = "UPDATE produtos SET id=?, descricao=?, qtde=?, preco=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, Produto.getId());
            stmt.setString(2, Produto.getDescricao());
            stmt.setInt(3,Produto.getQtde());
            stmt.setDouble(4,Produto.getPreco());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
        }
    }

    public void excluir(int id){
        String sql = "DELETE FROM produtos WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir id: "+ e.getMessage());
        }
    }
public Produto getProduto(int id){
        String sql = "SELECT * FROM produtos WHERE id =?";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Produto Produto = new Produto();
            rs.next();
            Produto.setId(rs.getInt("id"));
            Produto.setDescricao(rs.getString("descricao"));
            Produto.setQtde(rs.getInt("qtde"));
            Produto.setPreco(rs.getDouble ("preco"));
            
            return Produto;

        }catch(Exception e){
            System.out.println("Erro ao atualizar produto: "+ e.getMessage());
            return null;
        }
    }

    public List<Produto> getProduto(){
        String sql = "SELECT * FROM produtos";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Produto> listaProduto = new ArrayList<>();
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtde(rs.getInt("qtde"));
                p.setPreco(rs.getDouble("preco"));
                listaProduto.add(p);
            }
                return listaProduto;
        }catch(Exception e){
            return null;
        }
    }
}


