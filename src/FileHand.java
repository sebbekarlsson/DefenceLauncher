import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;



public class FileHand {

	public static String getContent(String filepath){
		try{
		File file = new File(filepath);
	    FileInputStream fis = new FileInputStream(file);
	    byte[] data = new byte[(int)file.length()];
	    fis.read(data);
	    fis.close();
	    //
	    String s = new String(data, "UTF-8");
	    return s;
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
}
