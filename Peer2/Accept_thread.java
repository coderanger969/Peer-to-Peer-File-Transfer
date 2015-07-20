import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


class Accept_thread implements Runnable{

	 Socket connectionSocket;
	 ServerSocket welcomesocket;
	 String RFC_Fname;
	 public static ArrayList<String> tempRlist1=new ArrayList<String>();
	// public static ArrayList<String> RFCList=new ArrayList<String>();
	  Accept_thread(ServerSocket welsocket,String RFCFolder,ArrayList<String> tempRlist)
	 {
		welcomesocket=welsocket;
		
		tempRlist1=tempRlist;
		System.out.println("in Accept Peer B");
		Thread t= new Thread(this,"Accept Thread B");
		t.start();
		RFC_Fname=RFCFolder;
	 }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String req,cmd;int RpeerPort;
			while(true)
			{
				try {
					connectionSocket = welcomesocket.accept();
				//	PeerReqHandler Pereq =	new PeerReqHandler(connectionSocket,RFCList);
					
					ObjectInputStream Accept_input = new    
		            ObjectInputStream(connectionSocket.getInputStream());

		         ObjectOutputStream Accept_output = new 
		            ObjectOutputStream(connectionSocket.getOutputStream());
		         req= Accept_input.readObject().toString();
		     	
					String[] result = req.split("#");
					cmd= result[0];
					System.out.println("peer has requested    " +  cmd);
				//	RpeerPort=Integer.parseInt(result[1]);
					PeerReqHandler Pereq =	new PeerReqHandler(connectionSocket);
					
					
					if(cmd.equals("REQRF")){
						
						//Pereq.sendRfc();
						Accept_output.writeObject(PeerB.RList);
						System.out.println("sent rfc list in Accept thread");
						
					                        }
					if(cmd.equals("DWNLRFC")){
						String rfcNUM = result[1];
						Pereq.uploadRfc(rfcNUM,RFC_Fname);
						
					        }
					Accept_output.close();
					Accept_input.close();
					
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	 
	 
 }
	
	
