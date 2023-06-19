package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entities.Professor;

public class DAOProfessor {
    private Connection conn;

    public DAOProfessor(Connection conn){
        this.conn = conn;
    }

    public Professor inserirProfessor(Professor professor){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "INSERT INTO professor (cpf, nome, data_nascimento) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            st.setString(1, professor.getCpf());
            st.setString(2, professor.getNome());
            st.setString(3, professor.getDataNascimento());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    professor.setId(id);
                }
            }else{
                throw new RuntimeException("Erro inesperado! Nenhuma linha foi afetada!");
            }

            return professor;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public Professor econtrarPeloId(Integer id){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM professor WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setCpf(rs.getString("cpf"));
                professor.setNome(rs.getString("nome"));
                professor.setDataNascimento(rs.getString("dataNascimento"));

                return professor;
            }

            return null;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public Professor encontrarPeloNomeECpf(String nome, String cpf){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM professor WHERE nome = ? AND cpf = ?"
            );

            st.setString(1, nome);
            st.setString(2, cpf);
            rs = st.executeQuery();

            if(rs.next()){
                Professor professor = new Professor();
                professor.setId(rs.getInt("id"));
                professor.setCpf(rs.getString("cpf"));
                professor.setNome(rs.getString("nome"));
                professor.setDataNascimento(rs.getString("data_nascimento"));

                return professor;
            }

            return null;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public boolean cpfJaExiste(String cpf){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM professor WHERE cpf = ?"
            );

            st.setString(1, cpf);
            rs = st.executeQuery();

            if(rs.next()){
                return true;
            }

            return false;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}