import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;


public class FileDownloader {

	public static void downloadFile(String url,String output){
		
		try{
		URL ur = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(ur.openStream());
		FileOutputStream fos = new FileOutputStream(output);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
