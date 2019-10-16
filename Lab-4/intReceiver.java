
import java.net.*;

public class UDPReceiver {

	private final static int PACKETSIZE = 100 ;

	public static void main( String args[] )
	{ 
	      // Check the arguments
	      if( args.length != 1 )
	      {
	         System.out.println( "usage: UDPReceiver port" ) ;
	         return ;
	      }
	      try
	      {
	         // Convert the argument to ensure that is it valid
	         int port = Integer.parseInt( args[0] ) ;

	         // Construct the socket
	         DatagramSocket socket = new DatagramSocket( port ) ;

	         for( ;; )
	         {
		        System.out.println( "Receiving on port " + port ) ;
		        DatagramPacket packet = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
	            socket.receive( packet ) ;

                    byte [] data = packet.getData();
                    InetAddress senderAddr = packet.getAddress();
                    int senderPort = packet.getPort();
                    
                    String msg = "ACK: " + new String(data);
                    byte [] sendData = msg.getBytes();
                    
	            System.out.println( senderAddr + " " + senderPort + ": " + new String(data).trim() ) ;
                    DatagramPacket sendBack = new DatagramPacket( sendData, sendData.length, senderAddr, senderPort ) ;
                    socket.send(sendBack);
	        }  
	     }
	     catch( Exception e )
	     {
	        System.out.println( e ) ;
	     }
  }
}

