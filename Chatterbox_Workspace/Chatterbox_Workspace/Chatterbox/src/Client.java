
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	

	public static void main(String[] args) throws IOException {
		
					int port = 6555;	
					Scanner clientScan = new Scanner(System.in);
					Socket socket = new Socket("localhost",port);
					
					
					//Output (geht zum Server)
					
					Thread Output = new Thread(new Runnable(){
		        		 
			        	@Override
			        	public void run(){
			        		
			        			while(true){

			        				OutputStream output;
									try {
										output = socket.getOutputStream();
										PrintWriter senden = new PrintWriter(output);
										String message = clientScan.nextLine();
										senden.println(message);
				    					senden.flush();
										if(message.equals("/bye")){clientScan.close();senden.close();output.close();socket.close();}
									} catch (IOException e) {
										e.printStackTrace();
									}
			    					
			        	   					}
			        	   				}       	
			        	 			}
			        			 );
					Output.start();

					
                    //Input (komm vom Server)
					Thread Input = new Thread(new Runnable(){
		        		 
			        	@Override
			        	public void run(){
			        		
			        			while(true){

			                     
			    					try {
			    						InputStream input = socket.getInputStream();
			    						BufferedReader ServerReader = new BufferedReader(new InputStreamReader(input));
			    						String message = ServerReader.readLine();
										if(message.equals("/bye")){clientScan.close();ServerReader.close();input.close();socket.close();}
										System.out.println("Server schrieb: " + message);
									} catch (IOException e) {
										e.printStackTrace();
									}
			        		
			        	   					}
			        	   				}       	
			        	 			}
			        			 );
					Input.start();
			}
		}


