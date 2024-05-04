package lt.viko.eif.m.trojanovskis.taksi.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lt.viko.eif.m.trojanovskis.taksi.Transform.PdfTransform;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

@WebServlet(name="TomcatServlet",urlPatterns = {"/pdf/*"})
public class TomcatServlet extends HttpServlet  {
    
    private void servePdfFile(HttpServletResponse response) throws IOException {
        Resource resource = new ClassPathResource("Orders1.pdf");
        if (!resource.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=Orders1.pdf");
        response.setContentLength((int) resource.contentLength());

        try (InputStream is = resource.getInputStream(); OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().endsWith("Orders1.pdf")) {
            servePdfFile(response);

        }else if (request.getRequestURI().endsWith("generatePDF")) {
            try {
                PdfTransform.convertToPDF();
                servePdfFile(response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
                    }else {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Open PDF</title>");
                out.println("<style>");
                out.println("html, body { height: 100%; margin: 0; padding: 0; display: flex; align-items: center; justify-content: center; font-family: Arial, sans-serif; }");
                out.println("body { background-color: #f4f4f9; }");
                out.println(".container { text-align: center; }");
                out.println("a { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; }");
                out.println("a:hover { background-color: #0056b3; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>Open PDF Page</h1>");
                out.println("<p><a href='/pdf/Orders1.pdf'>Open Existing PDF</a></p>");
                out.println("<p><a href='/pdf/generatePDF'>Generate PDF</a></p>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
    }
}
