package com.client.test;

import com.client.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    private final static int SERVER_PORT = 6666;
    private final static String IP_ADDRESS= "127.0.0.1";



    public static void main(String[] args){

        ClientService clientService = new ClientService();

        final InetAddress connectionAddress = clientService.getConnectionAddress(IP_ADDRESS);

        if(connectionAddress != null){
            try {
                Socket socket = new Socket(connectionAddress,SERVER_PORT);
                LOGGER.info("socket connected with ip : {} and port :",connectionAddress,SERVER_PORT);

                final InputStream ios = socket.getInputStream();
                final OutputStream ous = socket.getOutputStream();

                final DataInputStream inputStream = new DataInputStream(ios);
                final DataOutputStream outputStream = new DataOutputStream(ous);

                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

                String line;
                while (true){
                    line = keyboard.readLine();
                    outputStream.writeUTF(line);
                    outputStream.flush();
                    line = inputStream.readUTF();
                    System.out.println("server: " + line);
                }
            }catch (IOException e ){
                LOGGER.error("Cold not create socket with ip : {} and port : {}",connectionAddress,SERVER_PORT);
            }
        }

    }
}
