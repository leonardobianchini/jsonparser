import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class JsonParser {

  private static List<Orders> allOrders = new ArrayList<Orders>();

  private static String formatDate(String date) {
    String year = date.substring(0,4);
    String month = date.substring(4,6);
    String day = date.substring(6,8);
    return year + "-" + month + "-" + day;
  }

  private static int getIndexOfOrder(Integer userId) {
    for(Orders orderObj : allOrders) {
      if(orderObj.idUser.equals(userId)){
        return allOrders.indexOf(orderObj);
      }
     }
    return -1; 
  }

  public static String orderToString(Orders order) {
    return "{" +
      "\"user_id\":" + order.idUser + "," +
      "\"name\":\"" + order.nameUser + "\"," +
      "\"orders\":[{" +
      "\"order_id\": " + order.idOrder + "," +
      "\"date\": \"" + order.dateOrder + "\"," +
      "\"total\": \"" + order.totalOrder + "\"," +
      "\"products\": [{" +
      "\"product_id\": " + order.idProduct + "," +
      "\"value\": \"" + order.valueProduct + "\"" +
      "}]}]},\n";
  }

  public static void main(String args[]) throws FileNotFoundException, IOException {
    try (
      BufferedReader buffer = new BufferedReader(new FileReader("../inputs/data_1.txt"))
    ) {
      String line = buffer.readLine();
      BufferedWriter writer = new BufferedWriter(new FileWriter("output.json"));
  
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
          order.valueProduct = Float.parseFloat(value);
          order.totalOrder = Float.parseFloat(value);
          String date = line.substring(87, 95);
          order.dateOrder = formatDate(date);

          int indexOrd = getIndexOfOrder(order.idUser);
          if(indexOrd >= 0){
            allOrders.get(indexOrd).totalOrder += order.valueProduct;
          } else {
            allOrders.add(order);
          }
          line = buffer.readLine();
      }

      writer.write("[\n");
      for(Orders order: allOrders) {
        writer.write(orderToString(order));
      }
      writer.write("]");
      writer.close();

      System.out.println("Finish parser.");
    }
    catch (Exception err) {
      System.out.println("Error to run project: " + err);
    }
  }
}