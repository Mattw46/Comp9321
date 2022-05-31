/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.mattw46.lab01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Matt W
 */
public class Lab01 {
    
    public static void main(String args[]) {
        try {
            URL url = new URL(args[0]);
            Socket socket = new Socket(url.getHost(), 80);
            
            OutputStream out = socket.getOutputStream();
            String msg = "GET" + url.getPath() + " HTTP/1.1" + "\r\n" + "Host: "
                            + url.getHost() + "\r\n\r\n";
            out.write(msg.getBytes());
            
            InputStream in = socket.getInputStream();
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(in, StandardCharsets.UTF_8));
            
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            
            
        } catch(Exception ex) {
            System.err.println("An error occured: invalid url - " + ex);
        }
        
    }
}
