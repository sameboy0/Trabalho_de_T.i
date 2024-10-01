
package dao;

import magica.Conexao;
import beans.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {
    
    private Conexao conexao;
    private Connection conn;

    public CursoDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Curso Curso){
        String sql = "INSERT INTO cursos (id, descricao, qtde, preco) VALUES (?,?,?,?) ";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, Curso.getId());
            stmt.setString(2, Curso.getDescricao());
            stmt.setInt(3,Curso.getQtde());
            stmt.setDouble(4,Curso.getPreco());
            stmt.execute();
        }catch (Exception e){
            System.out.println("Erro ao inserir curso: "+ e.getMessage());
        }
    }

    public void alterar(Curso Curso){
        String sql = "UPDATE cursos SET id=?, descricao=?, qtde=?, preco=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, Curso.getId());
            stmt.setString(2, Curso.getDescricao());
            stmt.setInt(3,Curso.getQtde());
            stmt.setDouble(4,Curso.getPreco());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar linha: "+ e.getMessage());
        }
    }

    public void excluir(int id){
        String sql = "DELETE FROM cursos WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir id: "+ e.getMessage());
        }
    }
public Curso getCurso(int id){
        String sql = "SELECT * FROM cursos WHERE id =?";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Curso Curso = new Curso();
            rs.next();
            Curso.setId(rs.getInt("id"));
            Curso.setDescricao(rs.getString("descricao"));
            Curso.setQtde(rs.getInt("qtde"));
            Curso.setPreco(rs.getDouble ("preco"));
            
            return Curso;

        }catch(Exception e){
            System.out.println("Erro ao atualizar curso: "+ e.getMessage());
            return null;
        }
    }

    public List<Curso> getCurso(){
        String sql = "SELECT * FROM cursos";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Curso> listaCurso = new ArrayList<>();
            while(rs.next()){
                Curso p = new Curso();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQtde(rs.getInt("qtde"));
                p.setPreco(rs.getDouble("preco"));
                listaCurso.add(p);
            }
                return listaCurso;
        }catch(Exception e){
            return null;
        }
    }
}

