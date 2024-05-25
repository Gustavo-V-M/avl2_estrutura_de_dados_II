import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static boolean writePath(String sourceFilePath, String destFilePath){
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFilePath));
        BufferedReader bw = new BufferedWriter(new FileWriter(destFilePath))){
            String line;
            while ((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
            }
        } catch(IOException e){
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
            return false;
        }
        return true;
    }
}
