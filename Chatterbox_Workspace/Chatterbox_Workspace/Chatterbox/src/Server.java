
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	Scanner severScan;

	public static void main(String[] args) throws IOException {
		
			int port = 6555;
		

					Scanner	severScan = new Scanner(System.in);

					ServerSocket serverSocket = new ServerSocket(port);
					Socket socket = serverSocket.accept();
					System.out.println("Verbindung hergestellt!");
					
                    //Input (kommt  von Client)

					Thread Input = new Thread(new Runnable(){
		        		 
			        	@Override
			        	public void run(){
			        			while(true){
			        				
			                    //Input (kommt  von Client)
			        				
			        				try {
			        					InputStream input = socket.getInputStream();
			        					BufferedReader ServerReader = new BufferedReader(new InputStreamReader(input));
										String message = ServerReader.readLine();
										if(message.equals("/bye")){severScan.close();ServerReader.close();input.close();socket.close();serverSocket.close();}
			        					System.out.println("Client schrieb: " + message);
									} catch (IOException e) {
										e.printStackTrace();
									} 
			        	   					}
			        	   				}       	
			        	 			}
			        			 );
					
					Input.start();
											
					//Output (geht an den Client)
					Thread Output = new Thread(new Runnable(){
		        		 
			        	@Override
			        	public void run(){
			        			while(true){
			        				try {

										OutputStream output = socket.getOutputStream();
										PrintWriter senden = new PrintWriter(output);
										String message = severScan.nextLine();
										senden.println(message);
				    					senden.flush();

										if(message.equals("/bye")){severScan.close();senden.close();output.close();socket.close();serverSocket.close();}
				    					

									} catch (IOException e) {
										e.printStackTrace();
									}
			        	   					}
			        	   				}       	
			        	 			}
			        			 );		
					Output.start();		
			}
		}


