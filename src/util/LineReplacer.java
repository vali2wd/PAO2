package util;

import java.io.*;

public class LineReplacer {

    public void removeLine(String inputFile, int id) throws IOException {
        StringBuilder temp = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String currentLine = "k";

            while((currentLine = reader.readLine()) != null) {

                if (Integer.parseInt(currentLine.split(" ")[0]) != id){


                    temp.append(currentLine);
                    temp.append(System.lineSeparator());
                }
            }
            reader.close();

            PrintWriter writer = new PrintWriter(new FileWriter(inputFile));
            writer.print(temp);

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
