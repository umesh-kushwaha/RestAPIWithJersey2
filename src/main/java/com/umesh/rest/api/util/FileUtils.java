package com.umesh.rest.api.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.umesh.rest.api.restcontrollers.UserController;

public class FileUtils {

	private static Logger logger = Logger.getLogger(FileUtils.class);

	/**
     * write input stream to file server
     * @param inputStream
     * @param fileName
     * @throws IOException
     */
    public static String writeToFileServer(InputStream inputStream, String fileName) throws IOException {
 
        OutputStream outputStream = null;
        String qualifiedUploadFilePath = "D:\\EclipseProject\\ProfileImg\\"+fileName;
        logger.info(qualifiedUploadFilePath);
        
        try {
            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
                logger.info("loop");
            }
            outputStream.flush();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            logger.info(ioe.getMessage());

        }
        finally{
            //release resource, if any
            outputStream.close();
        }
        return qualifiedUploadFilePath;
    }
}
