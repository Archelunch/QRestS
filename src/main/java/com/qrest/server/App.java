package com.qrest.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.fabric.jdbc.FabricMySQLDriver;


/**
 * Hello world!
 *
 */
public class App implements Runnable 
{
	private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static ResultSet rs;
    private static String message;
    private static final String URL = "jdbc:mysql://localhost:3306/qrcontestdb";
	private static final String US = "root";
	private static final String PASS = "root";
    private static String answer;
    private static Connection connection;
    private static Statement statement;
    private static String nid = "";
    private static String score = "0";
    App(Socket clientSocket){
    	this.clientSocket = clientSocket;
    }
    public static void main(String[] args)  throws IOException {
    	try {
			Driver driver = new FabricMySQLDriver();
			DriverManager.registerDriver(driver);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
        try {
            serverSocket = new ServerSocket(4444);  //Server socket
            while (true) {
                Socket sock = serverSocket.accept();
                System.out.println("Connected");
                new Thread(new App(sock)).start();
             }
        } catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
        }
 
        
         
    }
    public void run() {
        try {
        	System.out.println("Server started. Listening to the port 4444");
	        
            message = "";
	        while (true) {
	        	
	        	 
		        InputStream cin = clientSocket.getInputStream();
                OutputStream cout = clientSocket.getOutputStream();
                 
                Scanner sc = new Scanner(cin);
                PrintWriter pw = new PrintWriter(cout);
                while(!sc.hasNextLine()){
            		 
            	 }
	                message = sc.nextLine();
	                System.out.println(message);
	                 if(message.equals("_-_")){
	                	 while(!sc.hasNextLine()){
	                		 
	                	 }
	                	 message = sc.nextLine();
	                	 try {
	             			  connection = DriverManager.getConnection(URL, US, PASS);
	             			 statement = connection.createStatement();
	             			rs = statement.executeQuery("SELECT id, score FROM users WHERE name = '" +message+ "';");		
	             			while (rs.next()) {
	        				    
	             				nid = rs.getString(1) + "";
	             				score = rs.getString(2) + "";
	         				   
	         				 }
	             			if(nid.equals("")){
	             			statement.executeUpdate("INSERT INTO users (name) VALUES ('"+ message +"');");
	             			rs = statement.executeQuery("SELECT id FROM users WHERE name = '" +message+ "';");		
	             			while (rs.next()) {
	        				    
	             				nid = rs.getString(1) + "";
	         				   score = "";
	         				 }
	             			}
	             		} catch (SQLException e) {
	             			
	             			// TODO Auto-generated catch block
	             			e.printStackTrace();
	             		}
	                	 System.out.println(score);
	                	 pw.println(score);
	                	 pw.flush();
	                 }else{
	                	 String id = "";
	                	 try {
		             			  connection = DriverManager.getConnection(URL, US, PASS);
		             			 statement = connection.createStatement();
		             			rs = statement.executeQuery("SELECT id FROM tasks WHERE nick = '" +message+ "';");		
		             			while (rs.next()) {
		        				    
		             				id = rs.getString(1);
		         				   
		         				 }
		             					
		             					  
		             		} catch (SQLException e) {
		             			// TODO Auto-generated catch block
		             			e.printStackTrace();
		             		}
	                	 if(id.equals("")){
	                		 pw.println("Error404");
		                	 pw.flush();
		                	System.out.println("Error404");
	                	 }else{
	                		 try {
			             			  connection = DriverManager.getConnection(URL, US, PASS);
			             			 statement = connection.createStatement();
			             			rs = statement.executeQuery("SELECT text, answer FROM tasks WHERE id = '" +id+ "';");	
			             			String text = "";
			             			String ans = "";
			             			while (rs.next()) {
			             				text = rs.getString(1);
			             				ans = rs.getString(2);
			         				 }
			             					 System.out.println(text);
			             					pw.println(text);
			  		                	  pw.flush();
			  		                	  pw.println(ans);
			  		                	  pw.flush();
			  		                	System.out.println("Yep");
			  		                	 while(!sc.hasNextLine()){
			  		                		 
			  		                	 }
			  		                	 answer = sc.nextLine();
			  		                	 System.out.println(answer);
			  		                	 
			  		                	if(answer.equals(ans)){
			  		                		pw.println("Задача решена правильно");
			  		                		System.out.println("Good");
			  		                		 pw.flush();
			  		                		try {
			  		             			  connection = DriverManager.getConnection(URL, US, PASS);
			  		             			 statement = connection.createStatement();
			  		             			statement.executeUpdate("UPDATE users SET  score= score+1 WHERE id='"+ nid + "';");
			  		             		 
			  		             			 
			  		             		} catch (SQLException e) {
			  		             			// TODO Auto-generated catch block
			  		             			e.printStackTrace();
			  		             		}
			  		                		 
			  		                	}else{
			  		                		pw.println("Неправильно");
			  		                		 pw.flush();
			  		                		 System.out.println("Bad");
			  		                	} 
			  		                	 answer = "";
					             			 
			             		} catch (SQLException e) {
			             			// TODO Auto-generated catch block
			             			e.printStackTrace();
			             		}
	                	 }
	                 
	                 }
	                sc.close();
	                pw.close();
	                cin.close();
	                cout.close();
	                clientSocket.close();
	        }
        }
        catch (IOException e) {
           System.out.println(e);
        }
     }
}
