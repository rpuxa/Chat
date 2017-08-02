import java.net.*;
import java.io.*;

class Client {
    public static void main(String[] ar) {
        int serverPort = 7158;
        String address = "85.140.2.68";


        try {
            Socket socket;
            InetAddress ipAddress = InetAddress.getByName(address);
            System.out.println("Подключение к  " + address + ":" + serverPort + "...");
            while (true) {
                try {
                    socket = new Socket(ipAddress, serverPort);
                    break;
                } catch (ConnectException ignored) {
                }
            }

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println("Связь установленна!");
            System.out.println();

            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(in.readUTF());
                    } catch (IOException e) {
                    }
                }
            }).start();

            while (true) {
                line = keyboard.readLine();
                out.writeUTF(line);
                out.flush();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}