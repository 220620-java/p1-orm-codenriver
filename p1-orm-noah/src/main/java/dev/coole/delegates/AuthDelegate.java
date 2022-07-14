package dev.coole.delegates;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthDelegate implements FrontControllerDelegate {
	private ObjectMapper objMapper = new ObjectMapper();
	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String method = req.getMethod();
			
			switch(method) {
			case "POST":
				post(req, resp);
				break;
			default:
				resp.sendError(HttpServletRespone.SC_METHOD_NOT_ALLOWED);
			}
	}
	
	private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Map<String, String> credentials = objMapper.readValue(req.getInputStream(), Map.class);
			if(credentials == null) throw new RuntimeException();
			// Not sure what to put here...
		} catch (MismatchedInputException | RuntimeException e) {
			resp.sendError(400, "the request body was empty");
		}
	}
}