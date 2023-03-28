import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class JsonParser {

  public static void main(String args[]) throws FileNotFoundException, IOException {
    try (
      BufferedReader buffer = new BufferedReader(new FileReader("../inputs/data_1.txt"))
    ) {
      StringBuilder stringBuilder = new StringBuilder();
      String line = buffer.readLine();
      int countLines = 0;
  
      while (line != null) {
          countLines++;
          stringBuilder.append(line);
          stringBuilder.append(System.lineSeparator());
          line = buffer.readLine();
      }

      String everything = stringBuilder.toString();
      System.out.println(everything + countLines);
    }
    catch (Exception err) {
      System.out.println("Error to run project: " + err);
    }
  }
}