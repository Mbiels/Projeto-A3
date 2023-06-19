package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entities.Aluno;

public class DAOAluno {
     private Connection conn;

    public DAOAluno(Connection conn){
        this.conn = conn;
    }

    public void inserirAluno(Aluno aluno){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "INSERT INTO aluno (cpf, nome, data_nascimento, mensalidade ,materia) VALUES (?, ?, ?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );

            st.setString(1, aluno.getCpf());
            st.setString(2, aluno.getNome());
            st.setString(3, aluno.getDataNascimento());
            st.setDouble(4, aluno.getMensalidade());
            st.setString(5, aluno.getMateria());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected > 0){
                rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    aluno.setId(id);
                }
            }else{
                throw new RuntimeException("Erro inesperado! Nenhuma linha foi afetada!");
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public void apagarAluno(Integer id){
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                "DELETE FROM aluno WHERE id = ?"
            );

            st.setInt(1, id);
            st.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    public Aluno econtrarPeloId(Integer id){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM aluno WHERE id = ?"
            );

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setNome(rs.getString("nome"));
                aluno.setDataNascimento(rs.getString("data_nascimento"));
                aluno.setMensalidade(rs.getDouble("mensalidade"));
                aluno.setMateria(rs.getString("materia"));
                return aluno;
            }
            return null;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public Aluno encontrarPeloCPF(String cpf){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM aluno WHERE cpf = ?"
            );

            st.setString(1, cpf);
            rs = st.executeQuery();

            if(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setNome(rs.getString("nome"));
                aluno.setDataNascimento(rs.getString("data_nascimento"));
                aluno.setMensalidade(rs.getDouble("mensalidade"));
                aluno.setMateria(rs.getString("materia"));
                return aluno;
            }
            return null;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    public void atualizarAluno(Aluno aluno){
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                "UPDATE aluno SET cpf = ?, nome = ?, data_nascimento = ?, mensalidade = ?, materia = ? WHERE id = ?"
            );

            st.setString(1, aluno.getCpf());
            st.setString(2, aluno.getNome());
            st.setString(3, aluno.getDataNascimento());
            st.setDouble(4, aluno.getMensalidade());
            st.setString(5, aluno.getMateria());
            st.setInt(6, aluno.getId());

            st.executeUpdate();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    public List<Aluno> encontrarTodos(){
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT * FROM aluno"
            );

            rs = st.executeQuery();

            List<Aluno> alunos = new ArrayList<>();

            while(rs.next()){
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setNome(rs.getString("nome"));
                aluno.setDataNascimento(rs.getString("data_nascimento"));
                aluno.setMensalidade(rs.getDouble("mensalidade"));
                aluno.setMateria(rs.getString("materia"));
                alunos.add(aluno);
            }
            return alunos;
    }catch(Exception e){
        throw new RuntimeException(e.getMessage());
    }finally{
        DB.closeResultSet(rs);
        DB.closeStatement(st);
    }
    }
}
