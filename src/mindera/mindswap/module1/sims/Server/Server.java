package mindera.mindswap.module1.sims.Server;

import mindera.mindswap.module1.sims.SimsGame;
import mindera.mindswap.module1.sims.util.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private ServerSocket serverSocket;
    private static List<PlayerHandler> players;
    private int numOfPlayers = 1;

    public static void main(String[] args) {
        Server server = new Server();
        server.startServer(1010);
        server.acceptPlayers();
    }

    private void startServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            players = new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Server Finished");
            System.exit(1);
        }
        System.out.println("Server started !");

    }

    private void acceptPlayers() {
        System.out.println("Waiting players to join...");

        if(players.size()<numOfPlayers){
            try {
                Socket socket = serverSocket.accept(); //blocking method
                PlayerHandler player = new PlayerHandler(socket);
                players.add(player);
                new Thread(player).start();

            } catch (IOException e) {
                System.out.println("Something went wrong in acceptPlayers");
            }
            finally {
                acceptPlayers();
            }
        }
        else{
            System.out.println("Sims Game started !");
            SimsGame simsgame = new SimsGame(players);
            new Thread(simsgame).start();
            players = new ArrayList<>();
            acceptPlayers();
        }
    }

    public static class PlayerHandler implements Runnable {

        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        private String username;
        private boolean isRunning;

        public PlayerHandler(Socket socket) {
            this.socket = socket;
            isRunning = true;
        }

        @Override
        public void run() {
            initializeBuffers();
        }

        private void initializeBuffers(){
            try{
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            }
            catch (IOException e){
                System.out.println("Something went wrong in initializeBuffers");
            }
        }
        public void sendMessageToPlayer(String message){
            out.println(message);
        }
        public String receiveMessageFromPlayer(){
            String message = null;
            try{
                message = in.readLine();
            }
            catch (IOException e){
                System.out.println("Couldn't receive the message.");
            }
            return message;
        }
        public String insertUserName(){
            sendMessageToPlayer("Insert your username: ");
            String name = null;
            try {
                name = in.readLine();
            }
            catch (IOException e){
                System.out.println("Couldn't get the username");
            }
            return name;
        }
        public void disconnectPlayer(){
            try {
                isRunning = false;
                this.socket.close();
            } catch (IOException e) {
                System.out.println("Couldn't disconnect the player.");
            }
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
