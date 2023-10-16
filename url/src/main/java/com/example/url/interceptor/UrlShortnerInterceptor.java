package com.example.url.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component
public class UrlShortnerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get the body
//        // body -> string -> class object -> ObjectMapper -> validity
//        request.setAttribute("valid", true);
        String requestWrapperBody = new GetRequestBody(request).getBody();
        // check for validity
        request.setAttribute("body", requestWrapperBody);
        request.setAttribute("valid", true);

        System.out.println("print from interceptor");

        System.out.println(requestWrapperBody);

        return true;
    }

    public class GetRequestBody {
        private final String body;
        public GetRequestBody(HttpServletRequest request) throws IOException {

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
                throw ex;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
            }
            body = stringBuilder.toString();
        }

        public String getBody() {
            return this.body;
        }
    }
}
