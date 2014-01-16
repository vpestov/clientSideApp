package com.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    public InetAddress getConnectionAddress(String address){
        InetAddress inetAddress = null;
        try{
            inetAddress = InetAddress.getByName(address);
        }catch (UnknownHostException e){
            LOGGER.error("could not get InetAddress with ip : {}",address);
        }

        return inetAddress;
    }

}
