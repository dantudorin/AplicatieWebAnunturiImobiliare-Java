package webapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private String username;
    private String password;
    private int idUser;

    public User(String username,String password){
        this.username = username;
        this.password = password;
    }

    public int getIdUser(){
        return idUser;
    }

    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public ResultSet compareData(){
        String sql = "SELECT Username,Password,idUser FROM User " +
                     "WHERE Username = ? and Password = ? ";
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try{
            preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            resultSet = preparedStatement.executeQuery();

            return resultSet;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public String getUsername(){
        return username;
    }



}
