import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {

        String city;
        char lastLetter;

        try (ServerSocket serverSocket = new ServerSocket(ServerConfig.PORT)) {

            System.out.println("Ожидание подключения...");
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {
                out.println("Введите название любого города:");

                city = in.readLine();

                System.out.println("Был введен город: " + city);

                lastLetter = city.charAt(city.length() - 1);

                out.println("Принято");
            }

            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {

                    out.println("Введите название города на букву " + lastLetter);

                    city = in.readLine();

                    System.out.println("Был введен город: " + city);

                    if (lastLetter == city.charAt(0)) {
                        lastLetter = city.charAt(city.length() - 1);
                        out.println("Принято");
                    } else {
                        out.println("Неверное название города!");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(" Сервер не может стартовать");
            e.printStackTrace();
        }
    }
}
