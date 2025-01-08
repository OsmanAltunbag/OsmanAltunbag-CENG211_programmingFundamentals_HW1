package FileIO;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {
    public static String[][] readCSV(String filePath) throws IOException {
        BufferedReader reader = null;
        String[][] data = new String[20][2]; // 20 satır ve her satırda 2 bilgi: isim ve bilet sayısı
        int row = 0;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            // Başlık satırını atla
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    data[row][0] = parts[0].trim(); // İsim
                    data[row][1] = parts[1].trim(); // Bilet sayısı
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return data;
    }
    
}
