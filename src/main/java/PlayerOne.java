import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class PlayerOne {

    public static void main(String[] args) {

        Scanner scanner;

        try (Socket socket = new Socket(ServerConfig.HOST, ServerConfig.PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            System.out.println(in.readLine());

            scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            out.println(input);

            System.out.println(in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
