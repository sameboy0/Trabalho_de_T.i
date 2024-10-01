
package dao;

import magica.Conexao;
import beans.Webinar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class WebinarDAO {
    
    private Conexao conexao;
    private Connection conn;

    public WebinarDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Webinar Webinar){
        String sql = "INSERT INTO webinars (id, descricao, qtde, preco) VALUES (?,?,?,?) ";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, Webinar.getId());
            stmt.setString(2, Webinar.getDescricao());
            stmt.setInt(3,Webinar.getQtde());
            stmt.setDouble(4,Webinar.getPreco());
            stmt.execute();
        }catch (Exception e){
            System.out.println("Erro ao inserir webinar: "+ e.getMessage());
        }
    }

    public void alterar(Webinar Webinar){
        String sql = "UPDATE webinars SET id=?, descricao=?, qtde=?, preco=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, Webinar.getId());
            stmt.setString(2, Webinar.getDescricao());
            stmt.setInt(3,Webinar.getQtde());
            stmt.setDouble(4,Webinar.getPreco());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar webinar: "+ e.getMessage());
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
public Webinar getWebinar(int id){
        String sql = "SELECT * FROM webinars WHERE id =?";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Webinar Webinar = new Webinar();
            rs.next();
            Webinar.setId(rs.getInt("id"));
            Webinar.setDescricao(rs.getString("descricao"));
            Webinar.setQtde(rs.getInt("qtde"));
            Webinar.setPreco(rs.getDouble ("preco"));
            
            return Webinar;

        }catch(Exception e){
            System.out.println("Erro ao atualizar webinar: "+ e.getMessage());
            return null;
        }
    }

    public List<Webinar> getWebinar(){
        String sql = "SELECT * FROM webinars";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Webinar> listaWebinar = new ArrayList<>();
            while(rs.next()){
                Webinar p = new Webinar();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtde(rs.getInt("qtde"));
                p.setPreco(rs.getDouble("preco"));
                listaWebinar.add(p);
            }
                return listaWebinar;
        }catch(Exception e){
            return null;
        }
    }
}

