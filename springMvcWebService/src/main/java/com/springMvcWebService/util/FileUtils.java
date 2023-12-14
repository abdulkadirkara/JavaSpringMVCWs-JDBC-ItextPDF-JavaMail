package com.springMvcWebService.util;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class FileUtils {

	public static String fileSeparator = System.getProperty("file.separator");
	
	public static byte[] getFileContent(String url) {
		FileInputStream fin = null;
		byte[] fileContent = null;
		try {
			File file = new File(url);
			fin = new FileInputStream(file);
			fileContent = new byte[(int) file.length()];
			fin.read(fileContent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {fin.close();} catch (IOException e) {}
		}
		return fileContent;
	}
	
	public static byte[] getFileContent(File file) {
		FileInputStream fin = null;
		byte[] fileContent = null;
		try {
			fin = new FileInputStream(file);
			fileContent = new byte[(int) file.length()];
			fin.read(fileContent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {fin.close();} catch (IOException e) {}
		}
		return fileContent;
	}
	

	
	public static boolean renameFile(File file,String name){
		return file.renameTo(new File(name)); 
	}
	
	public static String readFile(String url, int lastRows){
		StringBuffer result = new StringBuffer();
		byte[] filecontent = getFileContent(url);
		String[] lines = new String(filecontent).split("\n");
		int rowcount = (lines.length <= lastRows) ? (lines.length - 1) : lastRows;
		for(int i = lines.length - 1 ; i > (lines.length - rowcount)  ; i--){
			result.append(lines[i] + "\n");
		}
		return result.toString();
	}
	
	public static String convertStreamToString(InputStream is){
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		try {
			while ((length = is.read(buffer)) != -1) {
			    result.write(buffer, 0, length);
			}
			return result.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getContentFromUrl(String a){
		URL url;

        try {
            // get URL content

            url = new URL(a);
            URLConnection conn = url.openConnection();

            // open the stream and put it into BufferedReader
            BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream(),"UTF-8"));

            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
            }
            br.close();
            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public static FileItem uploadedFile(HttpServletRequest request) {

		FileItem fileItem = null;
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); 
			List<FileItem> items = upload.parseRequest(request);
			
			for (int i = 0; i < items.size(); i++) {
				fileItem = items.get(i);
				if (!fileItem.isFormField() && fileItem.getInputStream() != null) {
					return fileItem;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
	public static List<FileItem> uploadedFiles(HttpServletRequest request) {

		FileItem fileItem = null;
		List<FileItem> result = new ArrayList<FileItem>();
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); 
			List<FileItem> items = upload.parseRequest(request);
			
			for (int i = 0; i < items.size(); i++) {
				fileItem = items.get(i);
				if (!fileItem.isFormField() && fileItem.getInputStream() != null) {
					result.add(fileItem);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return result;
	}
	
	public static byte[] fileItemToByte(FileItem item) {
		byte[] file = null;
		
		try {
			InputStream is = item.getInputStream();
			file = IOUtils.toByteArray(is);
//			if(file.length > 0 && file != null){
//				
//			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return file;
	}
	
}