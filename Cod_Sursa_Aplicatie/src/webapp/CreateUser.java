package webapp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreateUser {

        private String nume;
        private String prenume;
        private String username;
        private String password;
        private String email;


        public CreateUser(String nume, String prenume, String username, String password, String email){

            this.nume = nume;
            this.prenume = prenume;
            this.username = username;
            this.password = password;
            this.email = email;

        }

        public void addUser(){

            String sql = "INSERT INTO User(Nume,Prenume,Username,Password,Email) " +
                         "VALUES(?,?,?,?,?)";

            PreparedStatement preparedStatement;

            try{
                preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,nume);
                preparedStatement.setString(2,prenume);
                preparedStatement.setString(3,username);
                preparedStatement.setString(4,password);
                preparedStatement.setString(5,email);

                preparedStatement.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

        public boolean exists(){

            String sql = "SELECT * FROM User " +
                         "WHERE Username = ? OR Email = ?";

            PreparedStatement preparedStatement;
            ResultSet resultSet;

            try{
                preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,email);

                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    return true;
                }else{
                    return false;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            return false;
        }

    public String getEmail() {
        return email;
    }
}
