/*
 * Autor: Samuel Ivan Ramirez Navarro
 * Contacto: samuel.i.ramirez@gmail.com
 */

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.StringTokenizer;

public class Connection implements Runnable {

	static final int PORT = 8080;
	static final boolean verbose = true;
	private Socket connect;
	
	private FaceDataBase fdb;

	public Connection(Socket c) {
		connect = c;
		fdb = FaceDataBase.getInstance();
	}
	
	@Override
	public void run() {
		BufferedReader in = null; PrintWriter out = null; BufferedOutputStream dataOut = null;
		String fileRequested = null;
		String result = "{}";
		
		try {
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			out = new PrintWriter(connect.getOutputStream());
			dataOut = new BufferedOutputStream(connect.getOutputStream());
			String input = in.readLine();
			StringTokenizer parse = new StringTokenizer(input);
			String method = parse.nextToken().toUpperCase();
			fileRequested = parse.nextToken().toLowerCase();
			
			if (!method.equals("GET")) {
				if (verbose) {
					System.out.println("501 Not Implemented : " + method + " method.");
				}
				String contentMimeType = "text/html";
				byte[] fileData = result.getBytes();
				int fileLength = fileData.length;
					
				out.println("HTTP/1.1 501 Not Implemented");
				out.println("Server: Java HTTP Server from SSaurel : 1.0");
				out.println("Date: " + new Date());
				out.println("Content-type: " + contentMimeType);
				out.println("Content-length: " + "0");
				out.println();
				out.flush();
				dataOut.write(fileData, 0, fileLength);
				dataOut.flush();
				
			} else {
				// GET or HEAD method
				if (fileRequested.endsWith("/")) {
				}				
				fileRequested = fileRequested.substring(1);
				String content = "text/html";
				
				if (method.equals("GET")) { 
					String[] aux = fileRequested.split(";");
					
					if(aux[0].equals("add")) {
						result = fdb.addFace(aux[1], aux[2], aux[3]);
					}
					if(aux[0].equals("check")) {
						result = fdb.checkFace(aux[1]);
					}
					if(aux[0].equals("size")) {
						result = fdb.getSize().toString();
					}
					if(aux[0].equals("getall")) {
						result = fdb.getAll();
					}
					if(aux[0].equals("setthreads")) {
						result = fdb.setThreads(Integer.parseInt(aux[1]));;
					}
					if(aux[0].equals("delete")) {
						result = fdb.delete(Integer.parseInt(aux[1]));;
					}
					if(aux[0].equals("getthreads")) {
						result = fdb.getThreads();
					}
					byte[] fileData = result.getBytes();
					int fileLength = fileData.length;
					out.println("HTTP/1.1 200 OK");
					out.println("Server: Java HTTP Server from SSaurel : 1.0");
					out.println("Date: " + new Date());
					out.println("Content-type: " + content);
					out.println("Content-length: " + fileLength);
					out.println(); 
					out.flush(); 
					
					dataOut.write(fileData, 0, fileLength);
					dataOut.flush();
				}
				
				if (verbose) {
					System.out.println("File " + fileRequested + " of type " + content + " returned");
				}
				
			}
			
		} catch (IOException ioe) {
			System.err.println("Server error : " + ioe);
		} finally {
			try {
				in.close();
				out.close();
				dataOut.close();
				connect.close(); 
			} catch (Exception e) {
				System.err.println("Error closing stream : " + e.getMessage());
			} 
			
			if (verbose) {
				System.out.println("Connection closed.\n");
			}
	}
}}
