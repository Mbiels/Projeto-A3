package db;

public class DAOFactory {
    
    public static DAOProfessor createProfessor(){
        return new DAOProfessor(DB.getConnection());
    }

    public static DAOAluno createAluno(){
        return new DAOAluno(DB.getConnection());
    }

}
