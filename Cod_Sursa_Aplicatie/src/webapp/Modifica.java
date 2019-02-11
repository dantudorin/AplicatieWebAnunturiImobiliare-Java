package webapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "modifica")
public class Modifica extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String userId = request.getParameter("userId");
        int anuntId = Integer.parseInt(request.getParameter("anuntId"));

        String pozaImobil = request.getParameter("pozamodificat");
        String titlu = request.getParameter("titlumodificat");
        String telefon = request.getParameter("telefonmodificat");
        String categoriImobil = request.getParameter("sources");
        int nrCamere = Integer.parseInt(request.getParameter("nrcameremodificat"));
        String locatie = request.getParameter("locatiemodificat");
        float pret = Float.parseFloat(request.getParameter("pretmodificat"));
        float suprafata = Float.parseFloat(request.getParameter("suprafatamodificat"));
        String actiune = request.getParameter("radio");


        String aerConditionat = request.getParameter("aer");
        String centrala = request.getParameter("centrala");
        String izoe = request.getParameter("izoe");
        String izoi = request.getParameter("izoi");
        String locParcare = request.getParameter("loc");
        String moilat = request.getParameter("mobi");
        String obiecteSanitare = request.getParameter("obi");
        String tamplarieTermopan = request.getParameter("tamp");


        System.out.println(aerConditionat + " " + centrala + " " + izoe + " " + izoi + " " + " " + locParcare + " " + moilat + " " + obiecteSanitare + " " + tamplarieTermopan);

        String updateTitluTelefon = "UPDATE Anunt\n" +
                     "SET TitluAnunt = ?,Telefon=?\n" +
                     "WHERE idAnunt = ?";

        String getImobilId = "SELECT idImobil FROM Anunt \n" +
                             "WHERE idAnunt = ?";

        String updateImobil = "UPDATE Imobil \n" +
                              "SET idCategorieImobil =?, NumarCamere = ?, Locatie = ?, Pret = ?," +
                              "Suprafata = ?, PozaImobil = ?, idActiune= ? " +
                              "WHERE idImobil = ?";

        String deleteDotari = "DELETE FROM Imobil_Dotari WHERE idImobil = ?";
        String updateDotari = "INSERT INTO Imobil_Dotari (idImobil,IdDotari) VALUES (?,?)";
        int idImobil = 0;

        try{
            PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(updateTitluTelefon);
            preparedStatement.setString(1,titlu);
            preparedStatement.setString(2,telefon);
            preparedStatement.setInt(3,anuntId);
            preparedStatement.execute();

            System.out.println("Executat asta");

            preparedStatement = SQLConnection.getConnection().prepareStatement(getImobilId);
            preparedStatement.setInt(1,anuntId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("A ajuns si aici");

            if(resultSet.next()){
                idImobil = resultSet.getInt("idImobil");
                System.out.println("Intrat pe aici");

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(updateImobil);
            preparedStatement.setInt(2,nrCamere);
            preparedStatement.setString(3,locatie);
            preparedStatement.setFloat(4,pret);
            preparedStatement.setFloat(5,suprafata);
            preparedStatement.setString(6,pozaImobil);

            switch (categoriImobil){
                case "Apartament":
                                    preparedStatement.setInt(1,1);
                                    break;
                case "Garosniera":
                                    preparedStatement.setInt(1,2);
                                    break;
                case "Teren":
                                    preparedStatement.setInt(1,4);
                                    break;
                case "Altceva":
                                    preparedStatement.setInt(1,5);
                                    break;
                case "Casa":
                                    preparedStatement.setInt(1,3);
                                    break;
            }


            switch (actiune){
                case "Vanzare":
                                    preparedStatement.setInt(7,2);
                                    break;
                case "Inchiriere":
                                    preparedStatement.setInt(7,1);
                                    break;
            }
            preparedStatement.setInt(8,idImobil);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(deleteDotari);
            preparedStatement.setInt(1,idImobil);
            preparedStatement.execute();

            preparedStatement = SQLConnection.getConnection().prepareStatement(updateDotari);

            if(aerConditionat != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,7);
                preparedStatement.execute();
            }

            if(centrala != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,6);
                preparedStatement.execute();
            }

            if(izoe != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,2);
                preparedStatement.execute();
            }

            if(izoi != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,3);
                preparedStatement.execute();
            }

            if(locParcare != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,1);
                preparedStatement.execute();
            }

            if(moilat != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,4);
                preparedStatement.execute();
            }

            if(obiecteSanitare != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,9);
                preparedStatement.execute();
            }

            if(tamplarieTermopan != null){
                preparedStatement.setInt(1,idImobil);
                preparedStatement.setInt(2,8);
                preparedStatement.execute();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        request.setAttribute("username",username);
        request.setAttribute("userId",userId);
        request.getRequestDispatcher("/mainApp.jsp").forward(request,response);

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
