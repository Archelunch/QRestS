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
    private static final String URL = "jdbc:mysql://uf63wl4z2daq9dbb.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/e614ufgw4yiuop1u";
	private static final String US = "y9cws2ckx7af597g";
	private static final String PASS = "oitf8ekaqb130os5";
    private static String answer;
    private static Connection connection;
    private static Statement statement;
    private static String nid = "";
    private static String score = "0";
    private static String ps = "_";
    private static String nc = "";
    private static String pc = "";
    private static String text = "";
    private static String ans = "";
    private static String tname = "";
    private static String n1 = "";
    private static String p1 = "";
    App(Socket clientSocket){
    	this.setClientSocket(clientSocket);
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
            serverSocket = new ServerSocket(5656);  //Server socket
            while (true) {
                Socket sock = serverSocket.accept();
                System.out.println("Connected");
                new Thread(new App(sock)).start();
             }
        } catch (IOException e) {
            System.out.println("Could not listen on port: 5656");
        }
 
        
         
    }
    public void run() {
        try {
        	System.out.println("Server started. Listening to the port 5656");
	        
            message = "";
	        while (true) {
	        	
	        	 
		        InputStream cin = getClientSocket().getInputStream();
                OutputStream cout = getClientSocket().getOutputStream();
                 
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
	                	 while(!sc.hasNextLine()){
	                		 
	                	 }
	                	 ps = sc.nextLine();
	                	 System.out.println(ps);
	                	 try {
	             			  connection = DriverManager.getConnection(URL, US, PASS);
	             			 statement = connection.createStatement();
	             			rs = statement.executeQuery("SELECT id FROM users WHERE name = '" +message+ "' and pass = '" + ps + "';");		
	             			while (rs.next()) {
	        				    
	             				nid = rs.getString(1) + "";
	             				
	         				   
	         				 }
	             			if(nid.equals("")){
	             			statement.executeUpdate("INSERT INTO users (name, pass) VALUES ('"+ message +"','" + ps +"');");
	             			 
	             			rs = statement.executeQuery("SELECT id, score FROM users WHERE name = '" +message+ "' and pass = '" + ps + "';");		
	             			while (rs.next()) {
	        				    
	             				nid = rs.getString(1) + "";
	             				score = rs.getString(2) + "";
	         				   
	         				 }
	             			}else{
	             				rs = statement.executeQuery("SELECT score FROM users WHERE name = '" +message+ "' and pass = '" + ps + "';");		
		             			while (rs.next()) {
		        				    
		             				
		             				score = rs.getString(1) + "";
		         				   
		         				 }
	             			}
	             			statement.close();
	             			connection.close();
	             		} catch (SQLException e) {
	             			
	             			// TODO Auto-generated catch block
	             			e.printStackTrace();
	             		}
	                	 System.out.println(score);
	                	 pw.println(score);
	                	 pw.flush();
	                 }else if(message.equals("^-^)")){
	                	 try {
							connection = DriverManager.getConnection(URL, US, PASS);
							statement = connection.createStatement();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							nc = sc.nextLine();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							pc = sc.nextLine();
							statement.executeUpdate("CREATE TABLE " + nc +" (id INT NOT NULL AUTO_INCREMENT,type VARCHAR(45) NOT NULL,value VARCHAR(150) NOT NULL,score INT(11) NULL DEFAULT 0,pass VARCHAR(45) NULL,answer VARCHAR(45) NULL,  PRIMARY KEY (id, type, value),UNIQUE INDEX `idnew_table_UNIQUE` (id ASC));");
							statement.executeUpdate("INSERT INTO " + nc + " (type, value) VALUES ('pass','" + pc +"');");
							statement.close();
	             			connection.close();
	                	 } catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
             			 
	                 }else if(message.equals("stroomp%^)")){
	                	 try {
	                	 connection = DriverManager.getConnection(URL, US, PASS);
							statement = connection.createStatement();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							tname = sc.nextLine();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							text = sc.nextLine();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							ans = sc.nextLine();
							statement.executeUpdate("INSERT INTO " + nc + " (type, value, pass, answer) VALUES ('quest','" + text +"','"+tname+"','"+ans+"');");
							statement.close();
	             			connection.close();
	                	 }catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                 }else if(message.equals("cont%!@$@")){
	                	 try{
	                	 connection = DriverManager.getConnection(URL, US, PASS);
							statement = connection.createStatement();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							n1 = sc.nextLine();
							while(!sc.hasNextLine()){
		                		 
		                	 }
							p1 = sc.nextLine();
							rs = statement.executeQuery("SELECT value FROM " +n1 +" WHERE id = 1;");
							String t = "";
							while (rs.next()) {
	             				t = rs.getString(1) + "";
	         				 }
//							if(t.equals(p1)){
//								pw.println("godd");
//								pw.flush();
//							}else{
//								pw.println("Nono");
//								pw.flush();
//							}
							statement.close();
	             			connection.close();
	                	 }catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                 }
	                 else{
	                	 String id = "";
	                	 try {
		             			  connection = DriverManager.getConnection(URL, US, PASS);
		             			 statement = connection.createStatement();
		             			rs = statement.executeQuery("SELECT id FROM " +n1 +" WHERE pass = '" +message+ "';");		
		             			while (rs.next()) {
		        				    
		             				id = rs.getString(1);
		         				   
		         				 }
		             					
		             			statement.close();
		             			connection.close();		  
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
			             			rs = statement.executeQuery("SELECT value, answer FROM " +n1 +" WHERE id = '" +id+ "';");	
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
			  		                	statement.close();
				             			connection.close();
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
	                getClientSocket().close();
	        }
        }
        catch (IOException e) {
           System.out.println(e);
        }
     }

	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		App.clientSocket = clientSocket;
	}
}
