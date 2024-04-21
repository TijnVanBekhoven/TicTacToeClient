import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TicTacToeClient {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    private final String ip;
    private final int port;

    public TicTacToeClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        try {
            clientSocket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            Scanner consoleScanner = new Scanner(System.in);
            while (true) {
                System.out.println(readAllLines(in));

                String msg = consoleScanner.nextLine();
                sendMessage(msg);
                if (msg.equals("close")) break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(String msg) {
        out.println(msg);
    }

    public void stop() {
        try {
            out.close();
            in.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String readAllLines(BufferedReader reader) throws IOException {
        StringBuilder content = new StringBuilder();

        String line;
        while (!(line = reader.readLine()).equals("ETX")) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        return content.toString();
    }
}
