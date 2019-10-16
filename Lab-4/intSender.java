import java.net.*;
import java.util.Scanner;
import java.util.Random;

public class UDPSenderLAB4 {

        private final static int PACKETSIZE = 100 ;
    
	public static void main(String[] args) 
   {
		  Random random = new Random();
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port number_of_messages" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
                 int n    = Integer.parseInt( args[2] );
	         socket = new DatagramSocket() ;
     
	         Scanner in;
	         in = new Scanner (System.in);
	         String message = null;
	         for (int i = 0; i < n; i++) {
	        		message = Integer.toString(random.nextInt());
	        		if (message.length()==0) break;
	        		byte [] data = message.getBytes() ;
	        		DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
	        		socket.send( packet ) ;
                                 
                                DatagramPacket sendBackPacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
                                socket.receive(sendBackPacket);
                                byte [] sendBackData = sendBackPacket.getData();
                                System.out.println( new String(sendBackData).trim() ) ;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}

