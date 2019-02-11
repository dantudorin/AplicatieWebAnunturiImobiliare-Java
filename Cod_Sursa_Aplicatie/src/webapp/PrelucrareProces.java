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

@WebServlet(name = "prelucrareproces")
public class PrelucrareProces extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String userId = request.getParameter("userId");

        if("+ ADAUGA ANUNT".equals(request.getParameter("action"))){
            System.out.println("intrat pe adauga anunt");
            System.out.println(username);
            request.setAttribute("username",username);
            request.setAttribute("userId",userId);
            request.getRequestDispatcher("/adaugaAnunt.jsp").forward(request,response);
        }

        if("Anunturile Mele".equals(request.getParameter("action"))){

            String sql = "SELECT IdAnunt,TitluAnunt,Pret,PozaImobil,DataPublicarii FROM Anunt\n" +
                         "INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                         "INNER JOIN User U on Anunt.idUser = U.idUser\n" +
                         "where Username = ?";
            String lista = "";
            PreparedStatement preparedStatement;
            ResultSet resultSet;

            try{
                preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                preparedStatement.setString(1,username);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){
                    lista = lista + "<li>";
                    lista = lista + " <div class=\"anunt\">";
                    lista = lista + "<img src =" + "\"" + resultSet.getString("PozaImobil") + "\"" + ">\n";
                    lista = lista + "<p1>" + resultSet.getString("TitluAnunt") + "</p1>\n";
                    lista = lista + "<p2>" + resultSet.getFloat("Pret") + " " + new Character('€') + "</p2>\n";
                    lista = lista + "<p3>" + resultSet.getString("DataPublicarii") + "</p3>\n";
                    lista = lista + "<button class = \"modifybutton\" name=\"action\" value=\"modify " + resultSet.getInt("IdAnunt") + "\">\n";
                    lista = lista + "Modify";
                    lista = lista + "</button>\n";
                    lista = lista + "<button class = \"deletebutton\" name=\"action\" value=\"delete " + resultSet.getInt("IdAnunt") + "\">\n";
                    lista = lista + "Delete";
                    lista = lista + "</button>\n";
                    lista = lista + "</button>\n";
                    lista = lista + "</div>";
                    lista = lista + "</li>\n";
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

            if(lista.isEmpty()){
                request.setAttribute("username",username);
                request.setAttribute("userId",userId);
                request.setAttribute("errorlog","You don't have any submissions.");
                request.getRequestDispatcher("/anunturilemele.jsp").forward(request,response);
            }else{
                request.setAttribute("username",username);
                request.setAttribute("userId",userId);
                request.setAttribute("lista",lista);
                request.getRequestDispatcher("/anunturilemele.jsp").forward(request,response);
            }

        }

        if("Sorteaza".equals(request.getParameter("action"))) {

            String tipSortare = request.getParameter("sorteaza");

            switch (tipSortare) {

                case "toateanunturile":
                    request.setAttribute("username", username);
                    request.setAttribute("userId", userId);
                    request.getRequestDispatcher("/mainApp.jsp").forward(request, response);
                    break;

                case "pretcrescator":
                    request.setAttribute("username", username);
                    request.setAttribute("userId", userId);
                    request.getRequestDispatcher("/sortatcrescator.jsp").forward(request, response);
                    break;

                case "pretdescrescator":
                    request.setAttribute("username", username);
                    request.setAttribute("userId", userId);
                    request.getRequestDispatcher("/sortatdescrescator.jsp").forward(request, response);
                    break;

                case "datapublicarii":
                    request.setAttribute("username", username);
                    request.setAttribute("userId", userId);
                    request.getRequestDispatcher("/sortatdatapublicare.jsp").forward(request, response);
                    break;

            }
        }
        if("Despre noi".equals(request.getParameter("action"))){
            System.out.println("Instrat pe despre noi ");
            System.out.println(username);
                request.setAttribute("username",username);
                request.setAttribute("userId",userId);
                request.getRequestDispatcher("/aboutus.jsp").forward(request,response);
        }

        if("Search".equals(request.getParameter("action"))){
            String condition = request.getParameter("searchString");
            if(condition.isEmpty()){
                request.setAttribute("username",username);
                request.setAttribute("userId",userId);
                request.setAttribute("errorlog","Sorry! Nothing was found");
                request.getRequestDispatcher("/cauta.jsp").forward(request,response);
            }else{
                String rightStringFormat = condition.toLowerCase();
                String lista ="";

                if(rightStringFormat.contains("apartament") ||
                        rightStringFormat.contains("garsoniera") ||
                        rightStringFormat.contains("altceva") ||
                        rightStringFormat.contains("casa") ||
                        rightStringFormat.contains("teren")){

                    try{
                        String sql = "SELECT IdAnunt,TitluAnunt,Pret,PozaImobil,DataPublicarii FROM Anunt\n" +
                                "INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                                "INNER JOIN `AnunturiImobiliareV2.0`.CategorieImobil CI on I.IdCategorieImobil = CI.IdCategorie\n" +
                                "where DescriereCategorie = ?";

                        PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                        preparedStatement.setString(1,rightStringFormat);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next() == false) {
                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("errorlog","Sorry! Nothing was found");
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);
                        }else{
                            do{
                                lista = lista + "<li>\n";
                                lista = lista + "<button name =\"afisanunt\" value =\"" + resultSet.getInt("IdAnunt") + "\">\n";
                                lista = lista + "<img src =" + "\"" + resultSet.getString("PozaImobil") + "\"" + ">\n";
                                lista = lista + "<p1>" + resultSet.getString("TitluAnunt") + "</p1>\n";
                                lista = lista + "<p2>" + resultSet.getFloat("Pret") + " " + new Character('€') + "</p2>\n";
                                lista = lista + "<p3>" + resultSet.getString("DataPublicarii") + "</p3>\n";
                                lista = lista + "</button>\n";
                                lista = lista + "</li>\n";
                            }while(resultSet.next());

                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("lista",lista);
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);
                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                }else if(rightStringFormat.contains("nicio dotare")){

                    try{
                        String sql = "SELECT A.idAnunt,A.TitluAnunt,A.Pret,A.PozaImobil,A.DataPublicarii\n" +
                                     "FROM (SELECT idAnunt,TitluAnunt,Pret,PozaImobil,DataPublicarii\n" +
                                     "FROM Anunt INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                                     "WHERE Anunt.idImobil NOT IN (SELECT DISTINCT idImobil FROM Imobil_Dotari)\n" +
                                     ") as A";

                        PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next() == false){
                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("errorlog","Sorry! Nothing was found");
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);
                        }else{
                            do{
                                lista = lista + "<li>\n";
                                lista = lista + "<button name =\"afisanunt\" value =\"" + resultSet.getInt("IdAnunt") + "\">\n";
                                lista = lista + "<img src =" + "\"" + resultSet.getString("PozaImobil") + "\"" + ">\n";
                                lista = lista + "<p1>" + resultSet.getString("TitluAnunt") + "</p1>\n";
                                lista = lista + "<p2>" + resultSet.getFloat("Pret") + " " + new Character('€') + "</p2>\n";
                                lista = lista + "<p3>" + resultSet.getString("DataPublicarii") + "</p3>\n";
                                lista = lista + "</button>\n";
                                lista = lista + "</li>\n";
                            }while (resultSet.next());

                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("lista",lista);
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);

                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                }else if(rightStringFormat.matches(".*\\d+ dotar.*")){
                    int nrDotari = Integer.parseInt(rightStringFormat.split("")[0]);

                    try{
                        String sql = "SELECT idAnunt,TitluAnunt,Pret,PozaImobil,DataPublicarii\n" +
                                "FROM Anunt INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                                "WHERE Anunt.idImobil = (SELECT idImobil FROM Imobil_Dotari\n" +
                                "                        group by Imobil_Dotari.idImobil\n" +
                                "                        having count(ALL idDotari) = ?);";

                        PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                        preparedStatement.setInt(1,nrDotari);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next() == false){
                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("errorlog","Sorry! Nothing was found");
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);
                        }else{
                            do{
                                lista = lista + "<li>\n";
                                lista = lista + "<button name =\"afisanunt\" value =\"" + resultSet.getInt("IdAnunt") + "\">\n";
                                lista = lista + "<img src =" + "\"" + resultSet.getString("PozaImobil") + "\"" + ">\n";
                                lista = lista + "<p1>" + resultSet.getString("TitluAnunt") + "</p1>\n";
                                lista = lista + "<p2>" + resultSet.getFloat("Pret") + " " + new Character('€') + "</p2>\n";
                                lista = lista + "<p3>" + resultSet.getString("DataPublicarii") + "</p3>\n";
                                lista = lista + "</button>\n";
                                lista = lista + "</li>\n";
                            }while (resultSet.next());

                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("lista",lista);
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);

                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                }else if(rightStringFormat.contains("centrala termica") ||
                        rightStringFormat.contains("aer conditionat") ||
                        rightStringFormat.contains("izolatie termica exterioara") ||
                        rightStringFormat.contains("izolatie termica interioara")||
                        rightStringFormat.contains("loc de parcare") ||
                        rightStringFormat.contains("mobilat") ||
                        rightStringFormat.contains("obiecte sanitare") ||
                        rightStringFormat.contains("tamplarie termopan")){

                    String letter = rightStringFormat.substring(0,1).toUpperCase();
                    rightStringFormat = letter + rightStringFormat.substring(1);

                    try{
                        String sql = "SELECT idAnunt,TitluAnunt,Pret,PozaImobil,DataPublicarii\n" +
                                "FROM Anunt INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                                "WHERE Anunt.idImobil IN (SELECT idImobil FROM Imobil_Dotari\n" +
                                "                        INNER JOIN Dotari D on Imobil_Dotari.idDotari = D.idDotare\n" +
                                "                        WHERE DescriereDotare = ?\n" +
                                "                        )\n";

                        PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                        preparedStatement.setString(1,rightStringFormat);

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next() == false){
                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("errorlog","Sorry! Nothing was found");
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);
                        }else{
                            do{
                                lista = lista + "<li>\n";
                                lista = lista + "<button name =\"afisanunt\" value =\"" + resultSet.getInt("IdAnunt") + "\">\n";
                                lista = lista + "<img src =" + "\"" + resultSet.getString("PozaImobil") + "\"" + ">\n";
                                lista = lista + "<p1>" + resultSet.getString("TitluAnunt") + "</p1>\n";
                                lista = lista + "<p2>" + resultSet.getFloat("Pret") + " " + new Character('€') + "</p2>\n";
                                lista = lista + "<p3>" + resultSet.getString("DataPublicarii") + "</p3>\n";
                                lista = lista + "</button>\n";
                                lista = lista + "</li>\n";
                            }while (resultSet.next());

                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("lista",lista);
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);

                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                }else{

                    try{
                        String sql = "SELECT idAnunt,TitluAnunt,Pret,PozaImobil,DataPublicarii\n" +
                                     "FROM Anunt INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                                     "WHERE idAnunt IN (SELECT idAnunt FROM Anunt INNER JOIN Imobil I2 on Anunt.idImobil = I2.idImobil\n" +
                                     "WHERE I2.Locatie like ?);";

                        PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(sql);
                        preparedStatement.setString(1,rightStringFormat+"%");

                        ResultSet resultSet = preparedStatement.executeQuery();

                        if(resultSet.next() == false){
                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("errorlog","Sorry! Nothing was found");
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);
                        }else{
                            do{
                                lista = lista + "<li>\n";
                                lista = lista + "<button name =\"afisanunt\" value =\"" + resultSet.getInt("IdAnunt") + "\">\n";
                                lista = lista + "<img src =" + "\"" + resultSet.getString("PozaImobil") + "\"" + ">\n";
                                lista = lista + "<p1>" + resultSet.getString("TitluAnunt") + "</p1>\n";
                                lista = lista + "<p2>" + resultSet.getFloat("Pret") + " " + new Character('€') + "</p2>\n";
                                lista = lista + "<p3>" + resultSet.getString("DataPublicarii") + "</p3>\n";
                                lista = lista + "</button>\n";
                                lista = lista + "</li>\n";
                            }while (resultSet.next());

                            request.setAttribute("username",username);
                            request.setAttribute("userId",userId);
                            request.setAttribute("lista",lista);
                            request.getRequestDispatcher("/cauta.jsp").forward(request,response);

                        }
                    }catch (SQLException e){
                        e.printStackTrace();
                    }

                }
            }
        }

     }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
