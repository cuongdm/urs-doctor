package com.ursdentist;

import static spark.Spark.*;

public class App 
{
	public static void main(String[] args) {
        get("/hello", (request, response) -> "Hello world");
        
        post("/hi", (request, response) -> {
        		return "SUCCESS";
        });
        
        
    }
}
