package fr.eni.ecole.boardbook.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.JFreeChart;

import com.keypoint.PngEncoder;

import fr.eni.ecole.boardbook.bll.Manager;

public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest (request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest (request, response);
	}
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		  JFreeChart graph = Manager.getGraphique();
    	  response.setContentType("image/png"); 
          BufferedImage buf = graph.createBufferedImage(640, 400, null);
          PngEncoder encoder = new PngEncoder( buf, false, 0, 9 );
          response.getOutputStream().write(encoder.pngEncode() );
	}

}
