package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SensorTCPServer {

    private static final int PORT = 5000;

    public static void main(String[] args) {

        System.out.println("=== SMART SPACE TCP SERVER ===");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server dang chay tren cong: " + PORT);
            System.out.println("Dang cho ESP32 ket noi...");

            while (true) {

                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(
                             new InputStreamReader(
                                     clientSocket.getInputStream(),
                                     StandardCharsets.UTF_8))) {

                    System.out.println(
                            "Thiet bi ket noi: "
                            + clientSocket.getInetAddress().getHostAddress());

                    String line;

                    while ((line = reader.readLine()) != null) {
                        System.out.println("Du lieu nhan duoc: " + line);
                    }

                    System.out.println("Thiet bi da ngat ket noi.");

                } catch (IOException e) {
                    System.err.println("Loi khi xu ly ket noi: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Khong the khoi dong TCP Server: " + e.getMessage());
            System.err.println("Hay kiem tra xem cong " + PORT + " co dang duoc su dung khong.");
        }
    }
}