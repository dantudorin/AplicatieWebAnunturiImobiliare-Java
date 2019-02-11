package webapp;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "displayanunt")
public class DisplayAnunt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        int anuntNr = Integer.parseInt(request.getParameter("afisanunt"));
        String userId = request.getParameter("userId");
        int idImobil =0;


        String sql1 = "SELECT TitluAnunt,IdImobil,Nume,Prenume,Email,DataPublicarii,Telefon FROM Anunt\n" +
                      "INNER JOIN User U on Anunt.idUser = U.idUser\n" +
                      "where idAnunt = ?";

        String sql2 = "SELECT DescriereCategorie,DescriereActiune,NumarCamere,Locatie,Pret,Suprafata,PozaImobil FROM Imobil\n" +
                "INNER JOIN Actiune A on Imobil.idActiune = A.idActiune\n" +
                "INNER JOIN CategorieImobil CI on Imobil.idCategorieImobil = CI.idCategorie\n" +
                "WHERE idImobil = ?";

        String sql3 = "SELECT DescriereDotare FROM `AnunturiImobiliareV2.0`.Imobil_Dotari\n" +
                "      INNER JOIN Dotari D on Imobil_Dotari.idDotari = D.idDotare\n" +
                      "where idImobil = ?";

        String dotari = "";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try{
            preparedStatement = SQLConnection.getConnection().prepareStatement(sql1);
            preparedStatement.setInt(1,anuntNr);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){

                request.setAttribute("titlu",resultSet.getString("TitluAnunt"));
                request.setAttribute("datapostare",resultSet.getString("DataPublicarii"));
                request.setAttribute("nrtelefon",resultSet.getString("Telefon"));
                idImobil = resultSet.getInt("idImobil");
                System.out.println(idImobil);
                request.setAttribute("email",resultSet.getString("Email"));
                request.setAttribute("nume",resultSet.getString("Nume"));
                request.setAttribute("prenume",resultSet.getString("Prenume"));

            }

            preparedStatement = SQLConnection.getConnection().prepareStatement(sql2);
            preparedStatement.setInt(1,idImobil);

            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){

                request.setAttribute("pret",resultSet.getFloat("Pret"));
                request.setAttribute("suprafata",resultSet.getFloat("Suprafata") + " mp");
                request.setAttribute("pozaapartament",resultSet.getString("PozaImobil"));
                request.setAttribute("numarcamere",resultSet.getInt("NumarCamere"));
                request.setAttribute("locatie",resultSet.getString("Locatie"));
                request.setAttribute("categorie",resultSet.getString("DescriereCategorie"));
                request.setAttribute("actiune",resultSet.getString("DescriereActiune"));

            }

            preparedStatement = SQLConnection.getConnection().prepareStatement(sql3);
            preparedStatement.setInt(1,idImobil);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                dotari = dotari +" . " + resultSet.getString("DescriereDotare");
            }
            if(dotari.isEmpty()){
                request.setAttribute("dotari","nu exista nicio dotare a imobilului");
            }else {
                request.setAttribute("dotari", dotari);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


        request.setAttribute("username",username);
        request.setAttribute("userId",userId);
        request.getRequestDispatcher("/detailedanunt.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
