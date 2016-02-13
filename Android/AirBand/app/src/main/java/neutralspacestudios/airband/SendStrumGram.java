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

public class SendStrumGram extends AsyncTask<AirBand,Void,Boolean> {

    private String cert;
    private static final int PORT = 45320;
    private static final String ip = "172.19.37.183";
    private static int strumCount = 0;
    private static AirBand airband;
    private static boolean sending = false;
    private static DatagramSocket socket;
    private static byte[] buf;
    private static InetAddress address;
    private static DatagramPacket packet;

    @Override
    protected Boolean doInBackground(AirBand... params) {
        if (sending)
            return true;
        sending = true;

        // get a datagram socket
        if (socket == null) {
            try {
                socket = new DatagramSocket();
                buf = new byte[3];
                buf[0] = 4;
                buf[1] = 20;
                address = InetAddress.getByName(ip);
                airband = params[0];
                packet = new DatagramPacket(buf, buf.length, address, PORT);
            } catch (Exception e) {
                return false;
            }
        }
        try
        {
            buf[2] = airband.getID();
            socket.send(packet);
            sending = false;
        } catch (Exception e) {
            Log.v("Error!",e.getMessage());
            socket.close();
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result)
        {
            strumCount++;
            airband.counter.setText("Strum Count" + strumCount);
        }
        else
        {
            airband.counter.setText("ERROR!");
        }

    }
}
