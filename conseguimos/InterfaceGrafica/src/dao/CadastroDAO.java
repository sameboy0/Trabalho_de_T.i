/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import magica.Conexao;
import beans.CadastroObjetos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CadastroDAO {
    private Conexao conexao;
    private Connection conn;

    public CadastroDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(CadastroObjetos cadastroObjetos){
        String sql = "INSERT INTO cadastros (nome, sobrenome, data_nascimento, celular, email, senha) VALUES (?,?,?,?,?,?) ";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cadastroObjetos.getNome());
            stmt.setString(2, cadastroObjetos.getSobrenome());
            stmt.setString(3,cadastroObjetos.getData_nascimento());
            stmt.setString(4, cadastroObjetos.getCelular());
            stmt.setString(5, cadastroObjetos.getEmail());
            stmt.setString(6, cadastroObjetos.getSenha());
            stmt.execute();
        }catch (Exception e){
            System.out.println("Erro ao inserir cadastro: "+ e.getMessage());
        }
    }

    public void alterar(CadastroObjetos cadastroObjetos){
        String sql = "UPDATE cadastros SET nome=?, sobrenome=?, data_nascimento=?, celular=?, email=?, senha=? WHERE id=?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, cadastroObjetos.getNome());
            stmt.setString(2, cadastroObjetos.getSobrenome());
            stmt.setString(3,cadastroObjetos.getData_nascimento());
            stmt.setString(4, cadastroObjetos.getCelular());
            stmt.setString(5, cadastroObjetos.getEmail());
            stmt.setString(6, cadastroObjetos.getSenha());
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao atualizar cadastro: "+ e.getMessage());
        }
    }

    public void excluir(int id){
        String sql = "DELETE FROM cadastros WHERE id = ?";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(Exception e){
            System.out.println("Erro ao excluir cadastro: "+ e.getMessage());
        }
    }
public CadastroObjetos getCadastroObjetos(int id){
        String sql = "SELECT * FROM cadastros WHERE id =?";

        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            CadastroObjetos cadastroObjetos = new CadastroObjetos();
            rs.next();
            cadastroObjetos.setId(rs.getInt("id"));
            cadastroObjetos.setNome(rs.getString("nome"));
            cadastroObjetos.setSobrenome(rs.getString("sobrenome"));
            cadastroObjetos.setData_nascimento(rs.getString("data_nascimento"));
            cadastroObjetos.setCelular(rs.getString("celular"));
            cadastroObjetos.setEmail( rs.getString("email"));
            cadastroObjetos.setSenha(rs.getString("senha"));
            
            return cadastroObjetos;

        }catch(Exception e){
            System.out.println("Erro ao atualizar cadastro: "+ e.getMessage());
            return null;
        }
    }

    public List<CadastroObjetos> getCadastroObjetos(){
        String sql = "SELECT * FROM cadastros";
        try{
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<CadastroObjetos> listacadastroObjetos = new ArrayList<>();
            while(rs.next()){
                CadastroObjetos p = new CadastroObjetos();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setSobrenome(rs.getString("sobrenome"));
                p.setData_nascimento(rs.getString("data_nascimento"));
                p.setCelular(rs.getString("celular"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                listacadastroObjetos.add(p);
            }
            return listacadastroObjetos;
        }catch(Exception e){
            return null;
        }
    }
}
        
    
   

