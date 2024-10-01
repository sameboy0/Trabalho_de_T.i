
package dao;

import magica.Conexao;
import beans.FeedbackObjetos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {
    
     private Conexao conexao;
    private Connection conn;

    public FeedbackDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(FeedbackObjetos feedbackObjetos){
        String sql = "INSERT INTO feedback (nome, senha, opiniao) VALUES (?,?,?) ";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, feedbackObjetos.getNome());
            stmt.setString(2, feedbackObjetos.getSenha());
            stmt.setString(3,feedbackObjetos.getOpiniao());
            stmt.execute();
        }catch (Exception e){
            System.out.println("Erro ao inserir feedback: "+ e.getMessage());
        }
    }

    public void alterar(FeedbackObjetos feedbackObjetos){
        String sql = "UPDATE feedback SET nome=?, senha=?, opiniao=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, feedbackObjetos.getNome());
            stmt.setString(2, feedbackObjetos.getSenha());
            stmt.setString(3,feedbackObjetos.getOpiniao());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar feedback: "+ e.getMessage());
        }
    }

    public void excluir(int id){
        String sql = "DELETE FROM feedback WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir feedback: "+ e.getMessage());
        }
    }
public FeedbackObjetos getFeedbackObjetos(int id){
        String sql = "SELECT * FROM feedback WHERE id =?";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            FeedbackObjetos feedbackObjetos = new FeedbackObjetos();
            rs.next();
            feedbackObjetos.setId(rs.getInt("id"));
            feedbackObjetos.setNome(rs.getString("nome"));
            feedbackObjetos.setSenha(rs.getString("senha"));
            feedbackObjetos.setOpiniao(rs.getString("opiniao"));
            
            return feedbackObjetos;

        }catch(Exception e){
            System.out.println("Erro ao atualizar feedback: "+ e.getMessage());
            return null;
        }
    }

    public List<FeedbackObjetos> getFeedbackObjetos(){
        String sql = "SELECT * FROM feedback";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<FeedbackObjetos> listafeedbackObjetos = new ArrayList<>();
            while(rs.next()){
                FeedbackObjetos p = new FeedbackObjetos();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSenha(rs.getString("senha"));
                p.setOpiniao(rs.getString("opiniao"));
                listafeedbackObjetos.add(p);
            }
                return listafeedbackObjetos;
        }catch(Exception e){
            return null;
        }
    }
}

