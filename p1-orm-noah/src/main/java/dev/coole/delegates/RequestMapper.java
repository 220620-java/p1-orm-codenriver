package dev.coole.delegates;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestMapper {
	// map of the delegates
	private Map<String, FrontControllerDelegate> delegateMap;
	
	// instance to initialize
	{
		delegateMap = new HashMap<>();
		
		//add each delegate
		delegateMap.put("message", new MessageDelegate());
		delegateMap.put("users", new UserDelegate());
		delegateMap.put("auth", new AuthDelegate());
	}
	
	public FrontControllerDelegate map(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		// if request is an OPTIONS request
		if("OPTIONS".equals(req.getMethod())) {
			return (req1, resp1) -> {};
		}
		
		StringBuilder uriString = new StringBuilder(req.getRequestURI());
		uriString.replace(0, req.getContextPath().length()+1, "");
		
		if(uriString.indexOf("/") != -1) {
			req.setAttribute("path", uriString.substring(uriString.indexOf("/")+1));
			
			uriString.replace(uriString.indexOf("/"), uriString.length(), "");
		}
		
		return delegateMap.get(uriString.toString());
	}
}
