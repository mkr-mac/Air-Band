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

    @Override
    protected Boolean doInBackground(AirBand... params) {
        airband = params[0];
        try
        {
            // get a datagram socket
            DatagramSocket socket = new DatagramSocket();
            // send request
            byte[] buf = new byte[3];
            buf[0] = 4;
            buf[1] = 20;
            buf[2] = airband.getID();
            InetAddress address = InetAddress.getByName(ip);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
            socket.send(packet);
            socket.close();
        } catch (Exception e) {
            Log.v("Error!",e.getMessage());
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
