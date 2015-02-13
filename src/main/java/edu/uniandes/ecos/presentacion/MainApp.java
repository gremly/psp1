package edu.uniandes.ecos.presentacion;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import edu.uniandes.ecos.logica.Controller;
import edu.uniandes.ecos.logica.Estimator;

/**
 *Clase principal del programa
 *
 */
public class MainApp extends HttpServlet{
	
	/**
	 * Instancia del controlador
	 */
	private Controller controller;
	
	public MainApp(){

		Estimator estimator = new Estimator();
    	WebView webview = new WebView();
    	this.controller = new Controller(webview, estimator);
	}
	
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

	    if (req.getRequestURI().endsWith("/")) {
	      controller.showHome(req, resp);
	    } else {
	    	controller.showNotFound(req, resp);
	    }
	  }

	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {

	    if (req.getRequestURI().endsWith("/calculate")) {
	    	controller.calculate(req, resp);
	    } else {
	    	controller.showNotFound(req, resp);
	    }
	  }
	  
    public static void main( String[] args ) throws Exception{   	
    	Server server = new Server(Integer.valueOf(System.getenv("PORT")));
    	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    	context.setContextPath("/");
    	server.setHandler(context);
    	context.addServlet(new ServletHolder(new MainApp()),"/*");
    	server.start();
    	server.join();
    }
}
