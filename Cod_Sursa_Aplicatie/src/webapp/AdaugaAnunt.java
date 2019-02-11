package webapp;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "adauga")
public class AdaugaAnunt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String userId = request.getParameter("userId");
        String email = "";

        try{
            String getEmailAdd = "SELECT Email FROM User WHERE idUser = ?";
            PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(getEmailAdd);
            preparedStatement.setInt(1, Integer.parseInt(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                email = resultSet.getString("Email");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        String titlu = request.getParameter("titlu");
        String telefon = request.getParameter("telefon");

        String pret = request.getParameter("pret");
        String locatie = request.getParameter("locatie");
        String categorie = request.getParameter("sources");
        String actiune = request.getParameter("radio");
        String suprafata = request.getParameter("suprafata");
        String numarcamere = request.getParameter("nrcamere");
        if(numarcamere.isEmpty()){
            numarcamere = "0";
        }
        String poza = request.getParameter("poza");

        int idImobil = 0,idCategorieImobil = 0,idActiune = 0;

        if(titlu.isEmpty() || telefon.isEmpty() || pret.isEmpty() || locatie.isEmpty() || categorie.isEmpty() || actiune.isEmpty()){
            request.setAttribute("username",username);
            request.setAttribute("userId",userId);
            request.setAttribute("errorLog","! Campurile \"Titlu\", \"Telefon\", \"Pret\",\"Locatie\", \"Categorie\" si \"Actiune\" trebuie completate obligatoriu");
            request.getRequestDispatcher("/adaugaAnunt.jsp").forward(request,response);
        }else{
            String adaugaImobil = "INSERT INTO Imobil(idCategorieImobil,NumarCamere,Locatie,Pret,Suprafata,PozaImobil,idActiune) \n" +
                                  "VALUES (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement;
            ResultSet resultSet;

            try{
                preparedStatement = SQLConnection.getConnection().prepareStatement(adaugaImobil);
                preparedStatement.setInt(2,Integer.parseInt(numarcamere));
                preparedStatement.setString(3,locatie);
                preparedStatement.setFloat(4,Float.parseFloat(pret));
                preparedStatement.setFloat(5,Float.parseFloat(suprafata));
                preparedStatement.setString(6,poza);

                switch (actiune){
                    case "Inchiriere" : preparedStatement.setInt(7,1);
                                        idActiune = 1;
                                        break;

                    case "Vanzare" :    preparedStatement.setInt(7,2);
                                        idActiune = 2;
                                        break;
                }

                switch (categorie){
                    case "Apartament":
                        preparedStatement.setInt(1,1);
                        idCategorieImobil = 1;
                        System.out.println("apartament cumetre");
                        break;
                    case "Garsoniera":
                        preparedStatement.setInt(1,2);
                        idCategorieImobil = 2;
                        System.out.println("garsoniera cumetre");
                        break;
                    case "Teren":
                        preparedStatement.setInt(1,4);
                        idCategorieImobil = 4;
                        System.out.println("Teren mosule");
                        break;
                    case "Altceva":
                        preparedStatement.setInt(1,5);
                        idCategorieImobil = 5;
                        System.out.println("altceva");
                        break;
                    case "Casa":
                        preparedStatement.setInt(1,3);
                        idCategorieImobil = 3;
                        break;
                }
                preparedStatement.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }

            String getIdImobil = "SELECT idImobil FROM Imobil \n" +
                    "WHERE Pret = ? AND idCategorieImobil =? AND NumarCamere = ? AND Locatie = ? AND Suprafata = ? AND PozaImobil = ? AND idActiune = ? ";


            try{
                preparedStatement = SQLConnection.getConnection().prepareStatement(getIdImobil);
                preparedStatement.setFloat(1, Float.parseFloat(pret));
                preparedStatement.setInt(2,idCategorieImobil);
                preparedStatement.setInt(3, Integer.parseInt(numarcamere));
                preparedStatement.setString(4,locatie);
                preparedStatement.setFloat(5, Float.parseFloat(suprafata));
                preparedStatement.setString(6,poza);
                preparedStatement.setInt(7,idActiune);

                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()){
                    idImobil = resultSet.getInt("idImobil");
                    System.out.println(idImobil);

                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            String adaugaAnunt = "INSERT INTO Anunt (TitluAnunt,idImobil,idUser,DataPublicarii,Telefon) \n" +
                                 "VALUES(?,?,?,CURRENT_TIMESTAMP(),?)";

            try{
                preparedStatement = SQLConnection.getConnection().prepareStatement(adaugaAnunt);
                preparedStatement.setString(1,titlu);
                preparedStatement.setInt(2,idImobil);
                preparedStatement.setInt(3, Integer.parseInt(userId));
                preparedStatement.setString(4,telefon);
                preparedStatement.execute();

            }catch (SQLException e){
                e.printStackTrace();
            }

            String adaugaDotare = "INSERT INTO Imobil_Dotari(idImobil,IdDotari) \n" +
                                  "VALUES(?,?)";


            try{

                preparedStatement = SQLConnection.getConnection().prepareStatement(adaugaDotare);
                preparedStatement.setInt(1,idImobil);

                if(request.getParameter("aer") != null){
                    preparedStatement.setInt(2,7);
                    preparedStatement.execute();
                }

                if(request.getParameter("centrala") != null){
                    preparedStatement.setInt(2,6);
                    preparedStatement.execute();
                }

                if(request.getParameter("izoe") != null){
                    preparedStatement.setInt(2,2);
                    preparedStatement.execute();
                }

                if(request.getParameter("izoi") != null){
                    preparedStatement.setInt(2,3);
                    preparedStatement.execute();
                }

                if(request.getParameter("loc") != null){
                    preparedStatement.setInt(2,1);
                    preparedStatement.execute();
                }

                if(request.getParameter("mobi") != null){
                    preparedStatement.setInt(2,4);
                    preparedStatement.execute();
                }

                if(request.getParameter("obi") != null){
                    preparedStatement.setInt(2,9);
                    preparedStatement.execute();
                }

                if(request.getParameter("tamp") != null){
                    preparedStatement.setInt(2,8);
                    preparedStatement.execute();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            request.setAttribute("username",username);
            request.setAttribute("userId",userId);
            new MailSender(email).sendEmail(1);
            request.getRequestDispatcher("/mainApp.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
