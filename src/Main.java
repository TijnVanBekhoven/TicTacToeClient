public class Main {
    public static void main(String[] args) {
        TicTacToeClient client = new TicTacToeClient("127.0.0.1", 12345);
        client.start();
        client.stop();

        System.exit(0);
    }
}