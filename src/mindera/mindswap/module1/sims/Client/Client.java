package mindera.mindswap.module1.sims.Client;

import mindera.mindswap.module1.sims.Server.Server;

import java.io.*;
import java.net.Socket;

public class Client extends Server {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public Client(){
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connectServer();
        client.startListeningToServer();
        client.sendMessage();

    }

    private void connectServer() {
        String hostName = "localhost";
        int port = 1010;
        try{
            socket = new Socket(hostName, port);
            out = new PrintWriter(socket.getOutputStream(), true);
        }
        catch (IOException e){
        System.out.println("Can't connect to server.");
        }
    }

    private void startListeningToServer() {
        try {
            new Thread(new ServerListener(socket.getInputStream())).start();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private void sendMessage() {
        try {
            String message = in.readLine();
            out.println(message);
            sendMessage();
        } catch (IOException e) {
            System.out.println("Server couldn't get the message.");
        }
    }

    private class ServerListener implements Runnable{

        private BufferedReader in;
        public ServerListener(InputStream inputStream){
            this.in = new BufferedReader(new InputStreamReader(inputStream));
        }
        @Override
        public void run() {
            readMessage();
        }

        private void readMessage(){
            try {
                String message = in.readLine();
                if (message==null){
                    System.out.println("Disconnected...");
                    System.exit(1);
                }
                System.out.println(message);
                readMessage();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
