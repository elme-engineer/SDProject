import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.HashMap;

import Downloader;

public class IBarrelReceiver {
    private final String MULTICAST_ADDRESS = "224.3.2.1";
    private final int MULTICAST_PORT = 4444;

    /**
     * The maximum amount of bytes the UDP socket will try to read
     */
    public final int BUFFER_SIZE = 4098;

    private static Barrels barrel = new Barrels();

    /**
     * Allows this thread to write the information recieved to the 
     * datastructure
     * 
     * @param barrel The structure storing the data for the webpages
     */
    public IBarrelReceiver(Barrels barrel) {
        IBarrelReceiver.barrel = barrel;
    }

    @Override
    public void run() {
        HashMap<String, Integer> lastSeen = new HashMap<>();
        try (MulticastSocket socket = new MulticastSocket(MULTICAST_PORT)) {
            InetAddress group = InetAddress.getByName(MULTICAST_ADDRESS);
            socket.joinGroup(group);
            byte[] buffer = new byte[BUFFER_SIZE];
            while(true){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                //System.out.println(message);
                String[] content = message.split("  ");
                if (content.length > 0 && (content[0].compareTo("data") == 0)) {
                    if (lastSeen.containsKey(content[1]) == false) {
                        try {
                            lastSeen.put(content[1], Integer.parseInt(content[2]));
                        } catch(NumberFormatException e) {
                            lastSeen.put(content[1], 0);
                        }
                    } else {
                        int missing;
                        if (lastSeen.get(content[1]) + 1 != (missing = Integer.parseInt(content[2]))) {
                            repair(content[1], missing);
                        }
                    }
                    System.out.println(content[3]);
                }
                barrel.write(DataPackage.deserialize(content[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private void repair(String id, int missing){

    }
}
