package edu.ecm.blog.web.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ecm.blog.ejb.MoreSeriousEJB;

public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @EJB
    private MoreSeriousEJB moreSeriousEJB;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(moreSeriousEJB.hello("Steven"));
	}
}
