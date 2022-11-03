package backEnd.controlador;

import backEnd.dao.MySqlProductoDAO;
import backEnd.entidad.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import gson.src.main.java.com.google.gson.Gson;

@WebServlet("/ServletProductoJSON")
public class ServletProductoJSON<Gson> extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletProductoJSON() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cod=request.getParameter("codOrden");
        Producto lista = new MySqlProductoDAO().findById(Integer.parseInt(cod));
        Gson gson=new Gson();
        String json=gson.toJson(lista);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter salida=response.getWriter();
        salida.println(json);
    }
}
