*********Instruction for Executing Peer to Peer with Distributed Index (P2P-DI) System for downloading RFC’s*************

We have four folders:
1.Registration Server Folder.
2.Peer 1 Folder.
3.Peer 2 Folder.
4.Text files folder

**************************Text Files Folder*******************************************

Kindly use the text files we placed in the folder. Do not change the name of the files or name of the RFC's in this folder. This is because for the task 1 and 2, to download 60 RFC files, 
we didn't want to hardcode the file path and name of different folders containing RFC's into our program.So we left that part of the code as such, since this way it gives dynamic feel 
than the hard coded one.


If you have any doubt in using these text files, you can contact us. We are on campus students.



**************************Registration Server Folder*******************************************

Registration Server : This folder has the following .java files.

1. RegisterServer.java
2. ClientHandler.java.



**************************Peer 1 Folder*******************************************
Peer 1: This folder has the following .java files

1.Peer.java
2.accept_thread.java
3.PeerReqHandler.java
4.PeerList.java

Make the following changes to the files, before compiling the .java files.

Open Peer.java file

1)In line 20,change the path of the text file as per your requirement i.e Place RFCA.txt file somewhere in your system and give that path to the program. 
  RFCA.txt can be found in the Text files Folder. [i.e inside peer constructor ]

2)In line 103, change the IP address to IP address of your server[i.e inside GetPeerList() method].

3)In line 166, change the IP address to IP address of your server.[i.e inside Register() method]

4)In line 236,change the path according to your requirements i.e where you want your files to be saved (Better to save them in the folder PeerA, which we included in the text files folder,
for the smooth execution of the program).[i.e inside DownloadRFC() method]

5)In line 287 and 318, change the IP address to IP address of your server. [i.e inside Leave() method and KeepAlive()]

Now open PeerReqHandler.java

In that go to line 18 and change the path according to where you have put the PeerA folder in your system. This PeerA folder can be found in the Text files folder. For e.g on my
system, PeerA folder is placed on the desktop. So I gave "C:/Users/manasa/Desktop/". Note that there is no need to give the name of the folder in the path address. Our program 
is coded in such a way that it will automatically locate the PeerA folder in the given path.



**************************Peer 2 Folder*******************************************

Peer 2: This folder has the following .java files

1.PeerB.java
2.Accept_thread.java
3.PeerReqHandler.java
4.PeerList.java

Make the following changes to the files, before compiling the .java files.

Open PeerB.java file
1) In line 20,change the path of the text file as per your requirement i.e Place RFCB.txt file somewhere in your system and give that path to the program. 
  RFCB.txt can be found in the Text files Folder.[i.e inside peer constructor ]

2)In line 110,change the IP address to IP address of your server.[i.e inside GetPeerList() method]
3)In line 173,change the IP address to IP address of your server.[i.e inside Register() method]
4)In line 247,change the path according to your requirements i.e where you want your files to be saved.(Better to save them in the folder PeerB, which we included in the text files folder,
for the smooth execution of the program).[i.e inside DownloadRFC() method]
5)In lines 303 and 333, change the IP address to IP address of your server.[i.e inside Leave() method and KeepAlive()]

Now open PeerReqHandler.java

In that go to line 18 and change the path according to where you have put the PeerB folder in your system. This PeerB folder can be found in the Text files folder. For e.g on my
system, PeerB folder is placed on the desktop. So I gave "C:/Users/Silpa/Desktop/". Note that there is no need to give the name of the folder in the path address. Our program 
is coded in such a way that it will automatically locate the PeerB folder in the given path.



****************************************PROCESS OF EXECUTION*********************************************************
STEP:1
1.Open the java files in Eclipse software.
2.Place server and two clients in different machines.
3. First go to Server machine and add RegisterServer.java and ClientHandler.java to a project file in eclipse and  run the Registerserver.java file.
4.Then go to Peer1 machine and add all the java files in Peer 1 folder to a project file in eclipse and run Peer.java file. In the console, the program will ask you for input.
5.Type 1 in the console and register.
6.Server will give you a unique cookie and Peer 1 will be registered.
7. Repeat the same process on Peer 2 machine. Add all the java files in Peer 2 folder to a project file in eclipse and run PeerB.java file.Register Peer 2 in similar fashion.


Step-2:

1) By typing 2 in the Peer console, you can request RS Server to give the list of active peers registered with it.
2)By typing 3 in the Peer console, you can get the list of RFC's that other Peer has. Then the program will prompt you -"Enter RFC number in format DWNLRFC#RFCXXXX". By entering the 
RFC number in this format you can download that particular RFC from the other Peer.
3)By typing 4, peer can leave the system. 
4)By typing 5, You can request RS Server to keep peer active in the system.(Keep ALive).



****************************************POINTS TO BE NOTED*********************************************************

1. In our Peer Program we run 2 threads parallely:
   a) one for requesting as a client i.e the menu options which you see.
   b) other for listening to the remote peer requests and service them.
   So while running parallely,some times menu options are followed by some peer statements like " Sending the Rfc file..." but you can still enter your choice number thou the 
   menu options are printed little above and the peer reads them.

2. If you select the "Leave" option, you are removed from the system.So you can no more enter your choices.You have to Register again newly.

3. RFCA.txt file contains list of RFC's that peer 1 contains and PeerA folder contains the actual RFC files. Similarly RFCB.txt contains the list of RFC's that Peer B contains 
   and PeerB folder contains the actual RFC's.




























