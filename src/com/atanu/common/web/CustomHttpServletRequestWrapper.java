package com.atanu.common.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

public class CustomHttpServletRequestWrapper extends HttpServletRequestWrapper {

private static final Logger logger = Logger.getLogger(CustomHttpServletRequestWrapper.class);
private final String body;

public CustomHttpServletRequestWrapper(ServletRequest request) {
    super((HttpServletRequest) request);

    StringBuilder stringBuilder = new StringBuilder();  
    BufferedReader bufferedReader = null;  

    try {  
        InputStream inputStream = request.getInputStream(); 

        if (inputStream != null) {  
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  

            char[] charBuffer = new char[128];  
            int bytesRead = -1;  

            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {  
                stringBuilder.append(charBuffer, 0, bytesRead);  
            }  
        } else {  
            stringBuilder.append("");  
        }  
    } catch (IOException ex) {  
        logger.error("Error reading the request body...");  
    } finally {  
        if (bufferedReader != null) {  
            try {  
                bufferedReader.close();  
            } catch (IOException ex) {  
                logger.error("Error closing bufferedReader...");  
            }  
        }  
    }  

    body = stringBuilder.toString();  
}

@Override  
public ServletInputStream getInputStream () throws IOException {          
    final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getBody().getBytes());

    ServletInputStream inputStream = new ServletInputStream() {  
        public int read () throws IOException {  
            return byteArrayInputStream.read();  
        }  
    };

    return inputStream;  
}

public String getBody() {
	return body;
} 
}