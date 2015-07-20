import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;

import java.io.*;
import java.net.*;

public class PeerReqHandler {
	static Socket connectionSocket;
	
	public   String FILE_TO_SEND = "C:/Users/manasa/Desktop/";
	
	public static ArrayList<PeerList> PList=new ArrayList<PeerList>();
	public static ArrayList<String> RFCList=new ArrayList<String>();
	public PeerReqHandler(Socket s)
	  {
		connectionSocket=s;
		
      }

	
	void uploadRfc(String rfcNUM,String Fname){
		
		try{
		
	     
	     System.out.println("inside upload rfc");
	     FILE_TO_SEND= FILE_TO_SEND+Fname+rfcNUM+".txt";
	     System.out.println(FILE_TO_SEND);
        File myFile = new File (FILE_TO_SEND);
        byte [] mybytearray  = new byte [(int)myFile.length()];
        FileInputStream fis = new FileInputStream(myFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(mybytearray,0,mybytearray.length);
        OutputStream os = connectionSocket.getOutputStream();
        System.out.println("Sending " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
        os.write(mybytearray,0,mybytearray.length);
        os.flush();
        System.out.println("Done.");
        if (bis != null) bis.close();
        if (os != null) os.close();
        if (connectionSocket!=null) connectionSocket.close();
		}catch(IOException e){
			e.printStackTrace();
			
		} 
			
		
		
		
	}

	public static void main (String args[])
	{
		
			
	}
		
		
		
	}
	

	
	
	
	
	
	

