package neutralspacestudios.airband;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

// Sends a byte (as a datagram) to the server.
public class SendStrumGram extends AsyncTask<Byte,Void,Void> {

    //These will be assigned before we can even make a connection.
    public static int PORT;
    public static String ip;

    // We only want to send one message, ignore if many.
    private static boolean sending = false;
    // A bunch of socket stuff
    private static DatagramSocket socket;
    private static byte[] buf;
    private static InetAddress address;
    private static DatagramPacket packet;

    //!!! The network protocol !!!
    public static final byte PIANO_PLAY = 10;
    public static final byte GUITAR_STRUM = 20;
    public static final byte BASS_STRUM = 30;
    // a range.
    public static final byte SYNTH_LOW = 40;
    public static final byte SYNTH_HIGH= 47;

    public static final byte SAX_LOW = 50;
    public static final byte SAX_HIGH = 54;
    public static final byte SAX_OFF = 55;

    public static final byte TRUMPET_LOW = 60;
    public static final byte TRUMPET_HIGH = 61;
    public static final byte TRUMPET_OFF = 63;

    public static final byte FLUTE_LOW = 70;
    public static final byte FLUTE_HIGH = 74;
    public static final byte FLUTE_OFF = 75;


    @Override
    protected Void doInBackground(Byte... params) {
        if (sending)
            return null;
        sending = true;

        // get a datagram socket
        if (socket == null) {
            try {
                socket = new DatagramSocket();
                buf = new byte[3];
                buf[0] = 4;
                buf[1] = 20;
                address = InetAddress.getByName(ip);
                packet = new DatagramPacket(buf, buf.length, address, PORT);
            } catch (Exception e) {
                return null;
            }
        }
        try
        {
            Log.v("NeutralDe","Sending a " + params[0]);
            buf[2] = params[0];
            socket.send(packet);
            sending = false;
        } catch (Exception e) {
            Log.v("Error!",e.getMessage());
            socket.close();
        }
        return null;
    }

}
