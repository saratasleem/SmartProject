package com.example.SCMProject.Utilities;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage(){
        try {
            System.out.println("remove message from session");
            HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
            
        } catch (Exception e) {
            System.out.println("Error resolving in session : "+e);
            e.printStackTrace();
        }}
        
    }

