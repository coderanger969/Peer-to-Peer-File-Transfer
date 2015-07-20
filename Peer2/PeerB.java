import java.io.*;

import java.net.*;
import java.util.*;


public class PeerB {
	//public final static String FILE_TO_SEND = "c:/Users/Silpa/Desktop/medicines take.txt";

	public static ArrayList<PeerList> PList=new ArrayList<PeerList>();
	public static ArrayList<String> RList=new ArrayList<String>();//which i have permanently
	public static ArrayList<String> RList1=new ArrayList<String>();//to store the recieved list

	String Hname; // Hostname of Peer
	int Cookie,ttl,Pnum,noA; // Pnum is port number, noA is number of times Active
	InetAddress ipa; // ipa is IP address of Peer machine
	Boolean Flag; 
	PeerB(){
		try{
			FileInputStream fstream = new FileInputStream("C:/Users/Silpa/Desktop/RFCB.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;
			
			//Read Rfc list from text file when Peer becomes active for the first time.
			while ((strLine = br.readLine()) != null)   {
				RList.add(strLine);			  		
				
			}

			//Close the input stream
			br.close();
			
			
		}catch(IOException e){
				e.printStackTrace();
				
			}
		
	}
	void Pquerry(){
		try{  
			/*String RFCnum;
			String[] result1 = RFCreq.split("#");
			RFCnum = result1[1]; */
			
		ArrayList<PeerList> ActivePeers = new ArrayList<PeerList>();
		  ActivePeers = GetPeerList();
		 // if(ActivePeers== null){ System.out.println(" Sorry Peer 1 has left !");}
		   Iterator<PeerList> I = ActivePeers.iterator();
			
			while(I.hasNext()){
				PeerList p = I.next();
				ipa= p.ip;  Pnum=p.Pnum;
				System.out.println("IP address"+ipa+"Port number"+Pnum);
				long cum_time=0;
				GetRFCIndex(ipa,Pnum);
				
				System.out.println("Enter the RFC number you want in format DWNLRFC#RFCXXXX ");
				Scanner input = new Scanner(System.in);
   	            String RFCreq= input.nextLine();
   	            String RFCnum;
			    String[] result1 = RFCreq.split("#");
			    RFCnum = result1[1];
				
				Iterator<String> Riterator = RList1.iterator();
				while(Riterator.hasNext()){
					
					 String record=Riterator.next();
					 String[] result = record.split("#");
						String PeerRFCnum= result[0];
						long startTime = System.currentTimeMillis();
						
				
						if(PeerRFCnum.equals(RFCnum)){
							System.out.println("downloading this     "+ record);
							DownloadRFC(RFCreq);}
						
						long elapsedTime = System.currentTimeMillis()-startTime;
						cum_time=cum_time+elapsedTime;
					//	System.out.println("it took   " + elapsedTime + "    milliseconds");
						
						
						
						
				}//inner while 
				
				
				System.out.println("cumulative time for Peer D is   " + cum_time+"milliseconds");		
				
				
			}// outter while
		  
		}//try
		catch(IOException e){
			e.printStackTrace();
			
		}
		
	}
	
	
	
	
	
	ArrayList<PeerList> GetPeerList()   {
		
		
			try{
			Socket clientSocket1 = new Socket("192.168.1.135",65423);
			

			String cmd;
			
			cmd = "REQP#Peer2#65440";
			

			ObjectOutputStream requestout1 = new ObjectOutputStream(clientSocket1.getOutputStream());
			  ObjectInputStream requestin1= new ObjectInputStream(clientSocket1.getInputStream());	
		
		

     System.out.println("requesting peer list");


    requestout1.writeObject(cmd);   
         
     		PList=(ArrayList<PeerList>)requestin1.readObject();
		if(PList.isEmpty()){System.out.println("PEER 1 HAS LEFT !! ");}


 	Iterator<PeerList> I = PList.iterator();
	while(I.hasNext()){
		PeerList p = I.next();
		System.out.println("host name : " + p.getHname());
		System.out.println("Port number : " + p.getPnum());
		
	} 
	
		
    requestout1.close();
    requestin1.close();
    clientSocket1.close();
 	}
 

			

		catch(IOException e){
			e.printStackTrace();
			System.out.println("PEER 1 HAS LEFT !! ");
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("PEER 1 HAS LEFT !! ");
		}
		return PList;
		
	}

		
		
		
		
	
	void register(){
		
		try{
			
			String cmd;
			
			Socket clientSocket = new Socket("192.168.1.135",65423);
			
			cmd = "REG#Peer2#65440";		
				
		ObjectOutputStream toServer = new ObjectOutputStream(clientSocket.getOutputStream());
		  ObjectInputStream fromServer= new ObjectInputStream(clientSocket.getInputStream());	
				
		toServer.writeObject(cmd);
		String p = fromServer.readObject().toString();
		Cookie = Integer.parseInt(p);
		System.out.println(" your cookie is    " +Cookie);
		//System.out.println("DOne");
		clientSocket.close();
		toServer.close();
        fromServer.close();
		
		}
		catch(IOException e){
			e.printStackTrace();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		}
	void GetRFCIndex(InetAddress RemoteAddr,int Port){
		try{
		
			//Socket PeerSocket = new Socket("192.168.1.42",5009);
			
			Socket PeerSocket = new Socket(RemoteAddr,Port);
			String cmd;
			
			cmd = "REQRF#65440";
			System.out.println("Requesting RFC index of Remote Peer...");

			ObjectOutputStream requestout = new ObjectOutputStream(PeerSocket.getOutputStream());
			  ObjectInputStream requestin= new ObjectInputStream(PeerSocket.getInputStream());	
			  
			  requestout.writeObject(cmd);
										
			  RList1=(ArrayList<String>)requestin.readObject();
								
				PeerSocket.close();
				requestout.close();
				requestin.close();
			
				
		
			 	Iterator<String> I = RList1.iterator();
				while(I.hasNext()){
					 
					System.out.println(I.next());
				}
				
				
				
	
		}
		
		 catch (IOException e){
			 e.printStackTrace();
		 } catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	void DownloadRFC(String cmd) throws IOException{
		
		  String FILE_TO_RECEIVED = "C:/Users/Silpa/Desktop/PeerB/";  
	
		  String RFCnum;
			String[] result1 = cmd.split("#");
			RFCnum = result1[1];
			FILE_TO_RECEIVED = FILE_TO_RECEIVED+RFCnum+".txt";

		 int FILE_SIZE = 6022386; // file size temporary hard coded
		                                               // should bigger than the file to be downloaded
		// String cmd="DWNLRFC#7241";
		 
		 

		  
		    int bytesRead;
		    int current = 0;
		   // System.out.println(" IP AND PORT IN DOWNLOAD rfc " + ipa +"    " +Pnum );
		    
		  //  	Socket sock = new Socket("192.168.1.42",65430);
		   Socket sock = new Socket(ipa,Pnum);
		      System.out.println("Connecting...Download");
		      
		      ObjectOutputStream requestout = new ObjectOutputStream(sock.getOutputStream());
			  ObjectInputStream requestin= new ObjectInputStream(sock.getInputStream());	
		      requestout.writeObject(cmd);
		      

		      // receive file
		      byte [] mybytearray  = new byte [FILE_SIZE];
		      InputStream is = sock.getInputStream();
		      FileOutputStream fos = new FileOutputStream(FILE_TO_RECEIVED);
		      BufferedOutputStream bos = new BufferedOutputStream(fos);
		       bytesRead = is.read(mybytearray,0,mybytearray.length);
		      current = bytesRead;

		      do {
		         bytesRead =
		            is.read(mybytearray, current, (mybytearray.length-current));
		         if(bytesRead >= 0) current += bytesRead;
		      } while(bytesRead > -1);

		      bos.write(mybytearray, 0 , current);
		      bos.flush();
		      System.out.println("File " + FILE_TO_RECEIVED
		          + " downloaded (" + current + " bytes read)");
		  
		    
		      if (fos != null) fos.close();
		      if (bos != null) bos.close();
		      if (sock != null) sock.close();
		      requestin.close();
		      requestout.close();
		    
		  }
	
	void Leave(){
		try {Socket PeerSocket = new Socket("192.168.1.135",65423);
		

		String cmd;
		
		cmd = "LEAVE#Peer2#65440";
		System.out.println("Leaving....");

		ObjectOutputStream requestout = new ObjectOutputStream(PeerSocket.getOutputStream());
		  ObjectInputStream requestin= new ObjectInputStream(PeerSocket.getInputStream());	
		  
		  requestout.writeObject(cmd);
		  String Response = (requestin.readObject()).toString();
		System.out.println("you have left peer system...");  
		requestout.close();
		requestin.close();
		}
		catch (IOException e){
				 e.printStackTrace();
			 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	void KeepAlive(){
		
		
		try {Socket PeerSocket = new Socket("192.168.1.135",65423);
		
		String cmd;
		
		cmd = "KeepAlive#Peer2#65440";
		System.out.println("Please RS make me alive !! ");

		ObjectOutputStream requestout = new ObjectOutputStream(PeerSocket.getOutputStream());
		  ObjectInputStream requestin= new ObjectInputStream(PeerSocket.getInputStream());	
		  
		  requestout.writeObject(cmd);
		  String Response = (requestin.readObject()).toString();
		System.out.println("u r made alive :)");  
		requestout.close();
		requestin.close();
		}
		catch (IOException e){
				 e.printStackTrace();
			 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public static void main (String args[])throws Exception{
		PeerB p1= new PeerB();
		ServerSocket welcomeSocket=new ServerSocket(65440);
		new Accept_thread(welcomeSocket,"PeerB/",RList);
		
		while(true){
			System.out.println("Please enter \n"+"1. for registration \n"+ "2. to get peerlist \n" +
					"3.Search and Download RFC \n"+"4. leave 5. Keep Alive\n");
			Scanner in = new Scanner(System.in);
			int s = Integer.parseInt(in.nextLine());
			switch (s) {
	        case 1:  p1.register(); break;
	        
	        case 2:  p1.GetPeerList();
	                 break;
	                 
	        case 3: p1.Pquerry();        	       
                     break;	       
                     
	        case 4 : p1.Leave();  
	                 break;
	                 
	        case 5 : p1.KeepAlive();
			}
			
				    if(s==4){break;} 
								      
				  }//while
				    				
					
	}//main
		
	}//class
	

 