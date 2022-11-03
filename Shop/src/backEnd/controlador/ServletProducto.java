package backEnd.controlador;

import backEnd.dao.MySqlProductoDAO;
import backEnd.entidad.Producto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletProducto")
public class ServletProducto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MySqlProductoDAO productoDAO;

    public ServletProducto() {
        super();
        productoDAO=new MySqlProductoDAO();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion;
        accion=request.getParameter("tipo");

        if(accion.equals("LISTARPRODUCTO")) {
            listarProductos(request,response);
        }
        else if(accion.equals("REGISTRAR")) {
            registrarProducto(request,response);
        }
        else if(accion.equals("ELIMINARPRODUCTO")) {
            eliminarProducto(request, response);
        }
        else if(accion.equals("ACTUALIZARPRODUCTO")) {
            actualizarProducto(request, response);
        }
    }

    private void registrarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id,nom,prec,stk,desc;

        id=request.getParameter("idProducto");
        nom=request.getParameter("nombre");
        prec=request.getParameter("precio");
        stk=request.getParameter("stock");
        desc=request.getParameter("descripcion");

        Producto bean = new Producto();
        bean.setId_prod(Integer.parseInt(id));
        bean.setNombre_prod(nom);
        bean.setPrecio(Double.parseDouble(prec));
        bean.setStk(Integer.parseInt(stk));
        bean.setDescripcion(desc);

        int salida;
        salida = productoDAO.insertarProd(bean);
        listarProductos(request, response);
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> data=productoDAO.listAll();
        request.setAttribute("listaProductos", data);
        request.getRequestDispatcher("/Productos.jsp").forward(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cod=request.getParameter("idProductoEliminar");
        int par;
        par=productoDAO.eliminarProd(Integer.parseInt(cod));
        if(par>0) {
            listarProductos(request, response);
        }
        else {
            response.sendRedirect("Productos.jsp?MENSAJE=Error en el producto");
        }

    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cod,id,nom,prec,stk,desc;

        cod=request.getParameter("codOrden");
        id=request.getParameter("idProducto");
        nom=request.getParameter("nombre");
        prec=request.getParameter("precio");
        stk=request.getParameter("stock");
        desc=request.getParameter("descripcion");

        Producto bean = new Producto();
        bean.setId_prod(Integer.parseInt(id));
        bean.setNombre_prod(nom);
        bean.setPrecio(Double.parseDouble(prec));
        bean.setStk(Integer.parseInt(stk));
        bean.setDescripcion(desc);

        bean.setCod_orden(Integer.parseInt(cod));

        int salida;
        salida = productoDAO.actualizarProd(bean);
        listarProductos(request, response);
    }
}
