import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class JsonParser {

  private static String formatDate(String date) {
    String year = date.substring(0,4);
    String month = date.substring(4,6);
    String day = date.substring(6,8);
    return year + "-" + month + "-" + day;
  }

  public static void main(String args[]) throws FileNotFoundException, IOException {
    try (
      BufferedReader buffer = new BufferedReader(new FileReader("../inputs/data_1.txt"))
    ) {
      String line = buffer.readLine();
      ArrayList<Orders> allOrders = new ArrayList<Orders>();
      BufferedWriter writer = new BufferedWriter(new FileWriter("parsed.json"));
  
      while (line != null) {
          Orders order = new Orders();

          String idUser = line.substring(0, 10);
          order.idUser = Integer.parseInt(idUser);
          String name = line.substring(10, 55);
          order.nameUser = name.trim();
          String idOrder = line.substring(55, 65);
          order.idOrder = Integer.parseInt(idOrder);
          String idProd = line.substring(65, 75);
          order.idProduct = Integer.parseInt(idProd);
          String value = line.substring(75, 87);
          order.valueProduct = Double.parseDouble(value);
          String date = line.substring(87, 95);
          order.dateOrder = formatDate(date);

          allOrders.add(order);
          line = buffer.readLine();
      }

      for(Orders order: allOrders) {
        writer.write(order.nameUser + System.lineSeparator());
      }
      writer.close();
      System.out.println("Finish parser.");
    }
    catch (Exception err) {
      System.out.println("Error to run project: " + err);
    }
  }
}