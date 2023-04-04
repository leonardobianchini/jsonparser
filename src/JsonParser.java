import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import entities.User;

class JsonParser {

  private static ArrayList<User> userOrders = new ArrayList<User>();

  private static int checkRegisteredUser(Integer userId) {
    for (User user : userOrders) {
      if (user.getUserId().equals(userId)) {
        return userOrders.indexOf(user);
      }
    }
    return -1;
  }

  private static String formatDate(String date) {
    String year = date.substring(0, 4);
    String month = date.substring(4, 6);
    String day = date.substring(6, 8);
    return year + "-" + month + "-" + day;
  }

  public static void main(String args[]) throws FileNotFoundException, IOException {
    try (
      BufferedReader buffer = new BufferedReader(new FileReader("../inputs/data_1.txt"))
    ){
      FileOutputStream writeData = new FileOutputStream("output.json");
      PrintWriter printWriter = new PrintWriter(writeData);
      String line = buffer.readLine();

      while (line != null) {
        String idUser = line.substring(0, 10);
        int userId = Integer.parseInt(idUser);
        String name = line.substring(10, 55);
        String idOrder = line.substring(55, 65);
        int orderId = Integer.parseInt(idOrder);
        String idProd = line.substring(65, 75);
        int idProduct = Integer.parseInt(idProd);
        String value = line.substring(75, 87);
        Double valueProduct = Double.parseDouble(value);
        String date = formatDate(line.substring(87, 95));

        int isRegistered = checkRegisteredUser(userId);
        if (isRegistered >= 0) {
          userOrders.get(isRegistered).addOrder(orderId, date, idProduct, valueProduct);
        } else {
          userOrders.add(new User(userId, name, orderId, date, idProduct, valueProduct));
        }
        line = buffer.readLine();
      }

      printWriter.println("[");
      for (User user : userOrders) {
        printWriter.println(user.getUserOrders());
      }
      printWriter.printf("]");

      printWriter.close();
      writeData.close();

      System.out.println("Finish parser.");
    } catch (Exception err) {
      System.out.println("Error to run project: " + err);
    }
  }
}