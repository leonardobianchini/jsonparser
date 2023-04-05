package com.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import com.application.entities.User;
import com.application.utils.OrderHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

  public static void main(String args[]) throws FileNotFoundException, IOException {
    try (
        BufferedReader buffer = new BufferedReader(new FileReader("./inputs/data_1.txt"))) {
      Writer writer = new FileWriter("parsed.json");
      String line = buffer.readLine();
      OrderHelper currentOrder = new OrderHelper();

      while (line != null) {
        currentOrder.getAttributes(line);

        int isRegistered = checkRegisteredUser(currentOrder.user_id);
        if (isRegistered >= 0) {
          userOrders.get(isRegistered).addOrder(currentOrder.order_id, currentOrder.date, currentOrder.product_id,
              currentOrder.value);
        } else {
          userOrders.add(new User(currentOrder.user_id, currentOrder.name, currentOrder.order_id, currentOrder.date,
              currentOrder.product_id, currentOrder.value));
        }
        line = buffer.readLine();
      }

      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(userOrders, writer);

      System.out.println("Finish parser.");
    } catch (Exception err) {
      System.out.println("Error to run project: " + err);
    }
  }
}