package sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import auxiliar.Punto;

@WebServlet("/CreaSesion")
public class CreaSesion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // se vac�a la sesi�n si existe
            if (request.getSession(false) != null) {  // si existe una sesi�n
                request.getSession().invalidate();  // ... la elimina
            }
            
            // se crea una sesi�n asociada a la petici�n
            HttpSession laSesion = request.getSession(true);
                        
            // se imprime el contenido de la sesi�n vac�a
            Enumeration enumeracionAtributos;
            String nombreAtributo, valorAtributo;
            enumeracionAtributos = laSesion.getAttributeNames();
            out.println("<h1>PasoVariablesSesion</h1>");
            out.println("<h3>Servlet CreaSesion</h3>");
            out.println("<p>Variables de sesi�n antes:</p>");
            while (enumeracionAtributos.hasMoreElements()) {
                nombreAtributo = enumeracionAtributos.nextElement().toString();
                valorAtributo = laSesion.getAttribute(nombreAtributo).toString();
                out.println(nombreAtributo + " = " + valorAtributo + " <br />");
            }
            out.println("<hr /> <br />");
            
            // se registran variables de sesi�n (objetos de diferentes clases)
            int entero = 12;
            double real = 3.1416;
            String cadena = "Hola sesi�n";
            Date fecha = new Date();
            Map<String,String> semaforo = new LinkedHashMap<String, String>();
            semaforo.put("R", "Rojo");semaforo.put("A", "Amarillo");semaforo.put("V", "Verde");
            Punto unPunto = new Punto(2,3);
            
            
            laSesion.setAttribute("entero", entero);
            laSesion.setAttribute("real", real);
            laSesion.setAttribute("texto", cadena);
            laSesion.setAttribute("fecha", fecha);
            laSesion.setAttribute("semaforo", semaforo);
            laSesion.setAttribute("unPunto", unPunto);
            
            // Se imprime el contenido de la sesi�n rellena
            enumeracionAtributos = laSesion.getAttributeNames();
            out.println("<p>Variables de sesi�n despu�s;</p>");
            while (enumeracionAtributos.hasMoreElements()) {
                nombreAtributo = enumeracionAtributos.nextElement().toString();
                valorAtributo = laSesion.getAttribute(nombreAtributo).toString();
                out.println(nombreAtributo + " = " + valorAtributo + "<br />");
            }
            out.println("<hr /> <br />");
            
            out.println("<a href=\"RecuperaSesion\">RecuperaSesion</a>");    
        }           
        catch (Exception e){ 
            out.println("Se produce una excepci�n <br />");
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}   