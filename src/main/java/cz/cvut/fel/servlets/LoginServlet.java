package cz.cvut.fel.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p></p>
 *
 * @author Karel Cemus
 */
@WebServlet( name = "LoginServlet", urlPatterns = { "/login.xhtml", "/login.jsf" } )
public class LoginServlet extends HttpServlet {

    @Override
    protected void service( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        // Redirects back to the initial page.
        response.sendRedirect( request.getContextPath() );
    }
}
