package backEnd.controlador;

import backEnd.dao.MySqlPedidoDAO;
import backEnd.entidad.Pedido;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletPedido")
public class ServletPedido extends HttpServlet {
    private static final long serialVersionUID = 1L;


    private MySqlPedidoDAO pedidoDAO;

    public ServletPedido() {
        super();
        pedidoDAO=new MySqlPedidoDAO();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion;
        accion=request.getParameter("tipo");

        if(accion.equals("LISTARPEDIDO")) {
            listarPedidos(request,response);
        }
        else if(accion.equals("REGISTRARPEDIDO")) {
            registrarPedido(request,response);
        }
        else if(accion.equals("ELIMINARPEDIDO")) {
            eliminarPedido(request, response);
        }
    }


    private void listarPedidos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pedido> data=pedidoDAO.listAll();
        request.setAttribute("listaPedidos", data);
        request.getRequestDispatcher("/Pedidos.jsp").forward(request, response);
    }

    private void registrarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProd,idNom,iddni,idcant;

        idProd=request.getParameter("idProducto");
        idNom=request.getParameter("nombre");
        iddni=request.getParameter("DNI");
        idcant=request.getParameter("cantidad");


        Pedido bean = new Pedido();
        bean.setIdproducto(Integer.parseInt(idProd));
        bean.setNombre_cli(idNom);
        bean.setDni(Integer.parseInt(iddni));
        bean.setCantidad(Integer.parseInt(idcant));
        int salida;
        salida = pedidoDAO.insertarPed(bean);

        request.getRequestDispatcher("/Girl_Shop.jsp").forward(request, response);
    }

    private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cod=request.getParameter("idPedidoEliminar");
        int par;
        par=pedidoDAO.eliminarPed(Integer.parseInt(cod));
        if(par>0) {
            listarPedidos(request, response);
        }
        else {
            response.sendRedirect("Pedidos.jsp?MENSAJE=Error en el pedido");
        }

    }
}
