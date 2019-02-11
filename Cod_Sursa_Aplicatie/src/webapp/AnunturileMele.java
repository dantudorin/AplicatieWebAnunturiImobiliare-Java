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
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "anunturileMele")
public class AnunturileMele extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String userId = request.getParameter("userId");

       if(request.getParameter("action").contains("modify")){
           String[] content = request.getParameter("action").split(" ");
           int anuntId = Integer.parseInt(content[1]);

           String titluAnunt,telefon,locatie,pozaImobil,descriereCategorie,descriereActiune;
           int idImobil = 0,numarCamere,idActiune;
           float pret,suprafata;

           String sql1 = "SELECT TitluAnunt,Telefon,Anunt.idImobil,NumarCamere,Locatie,Pret,Suprafata,PozaImobil,\n" +
                         "DescriereCategorie,idActiune FROM Anunt\n" +
                         "INNER JOIN Imobil I on Anunt.idImobil = I.idImobil\n" +
                         "INNER JOIN CategorieImobil CI on I.IdCategorieImobil = CI.IdCategorie\n" +
                         "WHERE idAnunt = ?";

           String sql2 = "SELECT DescriereActiune FROM Imobil\n" +
                         "INNER JOIN Actiune A on Imobil.idActiune = A.idActiune\n" +
                         "WHERE Imobil.idImobil = ?";

           String sql3 = "SELECT DescriereDotare FROM Imobil_Dotari\n" +
                         "INNER JOIN Dotari D on Imobil_Dotari.idDotari = D.idDotare\n" +
                         "WHERE idImobil = ?";

           PreparedStatement preparedStatement;
           ResultSet resultSet;

           try{
               preparedStatement = SQLConnection.getConnection().prepareStatement(sql1);
               preparedStatement.setInt(1,anuntId);
               resultSet = preparedStatement.executeQuery();

               if(resultSet.next()){
                   System.out.println(resultSet.getString("TitluAnunt"));
                   request.setAttribute("titlu",resultSet.getString("TitluAnunt"));
                   request.setAttribute("telefon",resultSet.getString("Telefon"));
                   idImobil = resultSet.getInt("idImobil");
                   request.setAttribute("nrCamere" , resultSet.getInt("NumarCamere"));
                   request.setAttribute("locatie" , resultSet.getString("Locatie"));
                   request.setAttribute("pret" , resultSet.getFloat("Pret"));
                   request.setAttribute("suprafata" , resultSet.getFloat("Suprafata"));
                   request.setAttribute("pozaImobil" , resultSet.getString("PozaImobil"));
                   descriereCategorie = resultSet.getString("DescriereCategorie");

                   if(descriereCategorie.equals("Apartament")){
                       String htmlApartament =  "<option selected value=\"Apartament\">Apartament</option>\n" +
                                                "<option value=\"Garsoniera\">Garsoniera</option>\n" +
                                                "<option value=\"Teren\">Teren</option>\n" +
                                                "<option value=\"Casa\">Casa</option>\n" +
                                                "<option value=\"Altceva\">Altceva</option>";

                       request.setAttribute("categorieImobil",htmlApartament);
                   }else if(descriereCategorie.equals("Garsoniera")){
                       String htmlGarsoniera =  "<option value=\"Apartament\">Apartament</option>\n" +
                                                "<option selected value=\"Garsoniera\">Garsoniera</option>\n" +
                                                "<option value=\"Teren\">Teren</option>\n" +
                                                "<option value=\"Casa\">Casa</option>\n" +
                                                "<option value=\"Altceva\">Altceva</option>";
                       request.setAttribute("categorieImobil",htmlGarsoniera);
                   }else if(descriereCategorie.equals("Teren")){
                       String htmlTeren =   "<option value=\"Apartament\">Apartament</option>\n" +
                                            "<option value=\"Garsoniera\">Garsoniera</option>\n" +
                                            "<option selected value=\"Teren\">Teren</option>\n" +
                                            "<option value=\"Casa\">Casa</option>\n" +
                                            "<option value=\"Altceva\">Altceva</option>";
                       request.setAttribute("categorieImobil",htmlTeren);
                   }else if(descriereCategorie.equals("Casa")){
                       String htmlCasa =     "<option value=\"Apartament\">Apartament</option>\n" +
                                             "<option value=\"Garsoniera\">Garsoniera</option>\n" +
                                             "<option value=\"Teren\">Teren</option>\n" +
                                             "<option selected value=\"Casa\">Casa</option>\n" +
                                             "<option value=\"Altceva\">Altceva</option>";
                       request.setAttribute("categorieImobil",htmlCasa);
                   }else {
                       String htmlAltceva = "<option value=\"Apartament\">Apartament</option>\n" +
                                            "<option value=\"Garsoniera\">Garsoniera</option>\n" +
                                            "<option value=\"Teren\">Teren</option>\n" +
                                            "<option value=\"Casa\">Casa</option>\n" +
                                            "<option selected value=\"Altceva\">Altceva</option>";
                       request.setAttribute("categorieImobil",htmlAltceva);
                   }

                   idActiune = resultSet.getInt("idActiune");
               }

           }catch (SQLException e){
               e.printStackTrace();
           }



           try{
               preparedStatement = SQLConnection.getConnection().prepareStatement(sql2);
               preparedStatement.setInt(1,idImobil);
               resultSet = preparedStatement.executeQuery();
               String actiune = "";

               if(resultSet.next()){
                   descriereActiune = resultSet.getString("DescriereActiune");

                   if(descriereActiune.equals("Vanzare")){
                       actiune = "<label class=\"container\">Vanzare\n" +
                                        "<input type=\"radio\" checked name=\"radio\" value=\"Vanzare\">\n" +
                                        "<span class=\"checkmark\"></span>\n" +
                                        "</label>\n" +
                                        "<label class=\"container\">Inchiriere\n" +
                                        "<input type=\"radio\" name=\"radio\" value=\"Inchiriere\">\n" +
                                        "<span class=\"checkmark\"></span>\n" +
                                        "</label>";
                   }else{
                        actiune = "<label class=\"container\">Vanzare\n" +
                                        "<input type=\"radio\" name=\"radio\" value=\"Vanzare\">\n" +
                                        "<span class=\"checkmark\"></span>\n" +
                                        "</label>\n" +
                                        "<label class=\"container\">Inchiriere\n" +
                                        "<input type=\"radio\" checked name=\"radio\" value=\"Inchiriere\">\n" +
                                        "<span class=\"checkmark\"></span>\n" +
                                        "</label>";
                   }
               }

               request.setAttribute("actiune",actiune);
           }catch (SQLException e){
               e.printStackTrace();
           }

           try{
               preparedStatement = SQLConnection.getConnection().prepareStatement(sql3);
               preparedStatement.setInt(1,idImobil);
               resultSet = preparedStatement.executeQuery();
               String[] allDotari ={
                       "Aer conditionat",
                       "Centrala termica",
                       "Izolatie termica exterioara",
                       "Izolatie termica interioara",
                       "Loc de parcare",
                       "Mobilat",
                       "Obiecte Sanitare",
                       "Tamplarie termopan"
               };
               Set<String> uncheckedDotari = new HashSet<>();
               Set<String> checkedDotari = new HashSet<>();
               for(String aux : allDotari){
                   uncheckedDotari.add(aux);
               }

               String checkboxes = "";

               while(resultSet.next()){

                    checkedDotari.add(resultSet.getString("DescriereDotare"));

               }
               uncheckedDotari.removeAll(checkedDotari);


               if(uncheckedDotari.contains("Aer conditionat")){
                       checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test1\" name=\"aer\" value=\"Aer conditionat\"/>\n"  +
                           "<label for=\"test1\">Aer conditionat</label>\n" +
                           "</p>\n";
               }else{
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test1\" name=\"aer\" checked value=\"Aer conditionat\"/>\n"  +
                           "<label for=\"test1\">Aer conditionat</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Centrala termica")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test2\" name=\"centrala\" value=\"Centrala termica\"/>\n"  +
                               "<label for=\"test2\">Centrala termica</label>\n" +
                               "</p>\n";
               }else{
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test2\" name=\"centrala\" checked value=\"Centrala termica\"/>\n"  +
                           "<label for=\"test2\">Centrala termica</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Izolatie termica exterioara")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test3\" name=\"izoe\" value=\"Izolatie termica exterioara\"/>\n"  +
                               "<label for=\"test3\">Izolatie termica exterioara</label>\n" +
                               "</p>\n";
               }else {
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test3\" name=\"izoe\" checked value=\"Izolatie termica exterioara\"/>\n"  +
                           "<label for=\"test3\">Izolatie termica exterioara</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Izolatie termica interioara")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test4\" name=\"izoi\" value=\"Izolatie termica interioara\"/>\n"  +
                               "<label for=\"test4\">Izolatie termica interioara</label>\n" +
                               "</p>\n";
               }else{
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test4\" name=\"izoi\" checked value=\"Izolatie termica interioara\"/>\n"  +
                           "<label for=\"test4\">Izolatie termica interioara</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Loc de parcare")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test5\" name=\"loc\" value=\"Loc de parcare\"/>\n"  +
                               "<label for=\"test5\">Loc de parcare</label>\n" +
                               "</p>\n";
               }else {
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test5\" name=\"loc\" checked value=\"Loc de parcare\"/>\n"  +
                           "<label for=\"test5\">Loc de parcare</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Mobilat")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test6\" name=\"mobi\" value=\"Mobilat\"/>\n"  +
                               "<label for=\"test6\">Mobilat</label>\n" +
                               "</p>\n";
               }else{
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test6\" name=\"mobi\" checked value=\"Mobilat\"/>\n"  +
                           "<label for=\"test6\">Mobilat</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Obiecte Sanitare")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test7\" name=\"obi\" value=\"Obiecte Sanitare\"/>\n"  +
                               "<label for=\"test7\">Obiecte Sanitare</label>\n" +
                               "</p>\n";
               }else{
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test7\" name=\"obi\" checked value=\"Obiecte Sanitare\"/>\n"  +
                           "<label for=\"test7\">Obiecte Sanitare</label>\n" +
                           "</p>\n";
               }

               if(uncheckedDotari.contains("Tamplarie termopan")){
                       checkboxes =  checkboxes + "<p>\n" +
                               "<input type=\"checkbox\" id=\"test8\" name=\"tamp\" value=\"Tamplarie termopan\"/>\n"  +
                               "<label for=\"test8\">Tamplarie termopan</label>\n" +
                               "</p>\n";
               }else {
                   checkboxes =  checkboxes + "<p>\n" +
                           "<input type=\"checkbox\" id=\"test8\" name=\"tamp\" checked value=\"Tamplarie termopan\"/>\n"  +
                           "<label for=\"test8\">Tamplarie termopan</label>\n" +
                           "</p>\n";
               }

               request.setAttribute("dotari",checkboxes);

           }catch (SQLException e){
               e.printStackTrace();
           }

           request.setAttribute("username",username);
           request.setAttribute("userId",userId);
           request.setAttribute("anuntId",anuntId);
           request.getRequestDispatcher("/modificaAnunt.jsp").forward(request,response);
       }else{
           String[] content = request.getParameter("action").split(" ");
           int anuntId = Integer.parseInt(content[1]);
           int idImobil = 0;

           String sql1 = "SELECT idImobil FROM Anunt where idAnunt = ?";
           String sql2 = "DELETE FROM Imobil where idImobil = ?";

           try {

               PreparedStatement preparedStatement = SQLConnection.getConnection().prepareStatement(sql1);
               preparedStatement.setInt(1,anuntId);
               ResultSet resultSet = preparedStatement.executeQuery();

               if(resultSet.next()){
                   idImobil = resultSet.getInt("idImobil");
               }

               preparedStatement = SQLConnection.getConnection().prepareStatement(sql2);
               preparedStatement.setInt(1,idImobil);
               preparedStatement.execute();


           } catch (SQLException e) {
               e.printStackTrace();
           }

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
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}


//Lista Dotari


