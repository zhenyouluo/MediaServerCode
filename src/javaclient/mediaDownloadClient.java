package ser321.media;

import java.io.IOException;
import java.io.*;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class mediaDownloadClient 
{
	private String hostName;
	private String fileName;
	private int portNumber;

	mediaDownloadClient(String hostName, int portNumber)
	{
		this.hostName = hostName;
		this.portNumber = portNumber;
		}
	public void SendMessage(String aString, String fileType)
	{
		//send to server
		try 
		{
			String fileOutName = System.getProperty("user.dir")+"/DataClient/"+aString;
			Socket mediaSocket = new Socket(this.hostName, this.portNumber);
			InputStream inStream = mediaSocket.getInputStream();
			OutputStream outStream = mediaSocket.getOutputStream();
			outStream.write(aString.getBytes(),0,aString.getBytes().length);
			
			FileOutputStream out = new FileOutputStream(fileOutName);
			int count;
				byte[] buffer = new byte[8192];
				while ((count = inStream.read(buffer)) > 0)
				{
					out.write(buffer, 0, count);
				}
				inStream.close();
				out.close();
		} 
		catch (Exception e) 
		{
			System.out.println("Error in method SendMessage: " + e.getMessage());
		}
	}
/*
	public static void main(String[] args) 
	{
		//String message;
		//Scanner reader = new Scanner(System.in);
		mediaDownloadClient mdc = new mediaDownloadClient("127.0.0.1", 3030, "aString");
		mdc.SendMessage(message);
		//System.out.println("Enter a message");
		//message = reader.nextLine();
		
	}
*/
}
