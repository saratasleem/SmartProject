package com.example.SCMProject.Exception;

public class ResourceNotFoundException extends Exception {
    private static final long serialVersionUID=1L;

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(){
        super("resource not found");
    }

}
