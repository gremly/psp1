package edu.uniandes.ecos.presentacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebView {

	 public void showHome(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {
		  	
		  	PrintWriter pw = resp.getWriter();
		  	String content = "";
		  	
		  	content +="<form action=\"calculate\" method=\"POST\">";
		  	content +="<table style=\"bogrder: 1px solid black;\">";
		  	content += "<tr>";
		  	content +="<th><label for=\"xdata\">Datos en X: </label></th>";
		  	content +="<td style=\"text-align: center;\"><input type=\"text\" name=\"xdata\" id=\"xdata\"  placeholder=\"Ingrese los datos separados por comas.\" ></td>";
		  	content +="</tr>";
		  	content +="<tr>";
		  	content +="<th><label for=\"ydata\">Datos en Y: </label></th>";
		  	content +="<td style=\"text-align: center;\"><input type=\"text\" name=\"ydata\" id=\"ydata\" placeholder=\"Ingrese los datos separados por comas.\" ></td>";
		  	content +="</tr>";
		  	content +="<tr>";
		  	content +="<th><label for=\"estimate\">Estimación: </label></th>";
		  	content +="<td style=\"text-align: center;\"><input type=\"text\" name=\"estimate\" id=\"estimate\" ></td>";
		  	content +="</tr>";
		  	content +="<tr>";
		  	content +="<td style=\"text-align: center;\"><input type=\"submit\" name=\"calculate\" value=\"Calcular\" ></td>";
		  	content +="</tr>";
		  	content +="</table>";
		  	content +="</form>";
		  	
		  	this.writePage(pw, content);
	  }
	 
	  public void showNotFound(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		  PrintWriter pw = resp.getWriter();
		   this.writePage(pw, "<h3>Error: 404 <em>Página no encontrada!</em></h3>");
	  }

	public void showResults(HttpServletRequest req, HttpServletResponse resp, HashMap<String, Double> results) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		String content = "";
		
		content += "<h2>Resultados</h2>";
		content +="<table style=\"bogrder: 1px solid black;\">";
		
		for (Map.Entry<String, Double> entry : results.entrySet()) {
			content += "<tr>";
			content +="<th>"+ entry.getKey().toUpperCase() +"</th>";
			content +="<td style=\"text-align: center;\">"+ entry.getValue() +"</td>";
			content +="</tr>";
		}
		
		content +="</table>";
		content +="<a href=\"/\"><< Regresar</a>";
			  	
		this.writePage(pw, content);
		
	}
	
	private void writePage(PrintWriter pw, String content){
	  	pw.write("<html>");
	  	pw.write("<head><title>PSP1</title></head>");
	  	pw.write("<body>");
	  	pw.println("<h3> Estimación </h3>");
	  	pw.write(content);
	  	pw.write("</body>");
	  	pw.write("</html>");
	}
	 
}
