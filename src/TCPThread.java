import java.net.*;
import java.io.*;
class Server {

    static String address = "85.140.2.68";

    public static void main(String[] ar)    {
        int port = 7158; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            ServerSocket ss = new ServerSocket(port,3,InetAddress.getByName(address)); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");


            Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
            System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;

            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(in.readUTF());
                    } catch (IOException e) {
                    }
                }
            }).start();

            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}