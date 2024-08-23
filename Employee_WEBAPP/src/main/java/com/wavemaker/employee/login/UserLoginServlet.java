package com.wavemaker.employee.login;

import com.wavemaker.employee.model.UserAuthentication;
import com.wavemaker.employee.service.UserAuthenticationService;
import com.wavemaker.employee.service.impl.UserAuthenticationServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    private static UserAuthenticationService userAuthService;
    @Override
    public void init() {
       userAuthService=new UserAuthenticationServiceImpl();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String username=req.getParameter("username");
        String password=req.getParameter("password");

    }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            if (username == null || password == null) {
                writeResponse(resp, "Missing required parameters.");
                return;
            }

            UserAuthentication userAuthentication = new UserAuthentication();
            userAuthentication.setUsername(username);
            userAuthentication.setPassword(password);

            // Create an instance of the service class
            userAuthService = new UserAuthenticationServiceImpl();
            boolean userAdded = userAuthService.addUserLogin(userAuthentication);

            if (userAdded) {
                writeResponse(resp, "User added login authentication.");
            } else {
                writeResponse(resp, "User login authentication is not added.");
            }
        }

        private void writeResponse(HttpServletResponse resp, String message) throws IOException {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/plain");
            out.println(message);
        }
    }


