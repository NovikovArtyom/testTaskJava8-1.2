import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static void main(String[] args) {
        try {
            int PORT = 8080;
            ServerSocket serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                InputStream inputStream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                String request = new String(buffer, 0, bytesRead);
                System.out.println(request);

                String response = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "Content-Length: 12\r\n"
                        + "\r\n"
                        + "Hello, World";

                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(response.getBytes());

                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("Ошибка!");
        }
    }
}