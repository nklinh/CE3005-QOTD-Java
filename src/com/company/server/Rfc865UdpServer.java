package com.company.server;

import sun.security.x509.IPAddressName;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class Rfc865UdpServer {
    private static DatagramSocket socket;

    public static void main(String[] args) {

        try {
            socket = new DatagramSocket(17);
            while(true) {
                try {
                    byte[] requestBuffer = new byte[512];
                    DatagramPacket request = new DatagramPacket(requestBuffer, requestBuffer.length);
                    System.out.println("UDP listening");
                    socket.receive(request);
                    System.out.println("Get datagram package from address " + request.getAddress() + " port " + request.getPort());
                    String clientName = new String(request.getData(), 0, request.getLength());
                    System.out.println("Log: " + clientName);
                    
                            
                    String quote = "\"Peace cannot be kept by force; it can only be achieved by understanding.\" -Albert Einstein";
                    byte[] replyBuffer = quote.getBytes();
                    System.out.println(replyBuffer.length);
                    DatagramPacket reply = new DatagramPacket(replyBuffer, replyBuffer.length, request.getAddress(), request.getPort());
                    socket.send(reply);

                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
