package backEnd.controlador;

import backEnd.dao.MySqlEmpleadoDAO;
import backEnd.entidad.Empleado;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletEmpleado")
public class ServletEmpleado<HttpServletRequest, HttpServletResponse> extends HttpServlet{
    private static final long serialVersionUID = 1L;

    private MySqlEmpleadoDAO empleadoDAO;

    public ServletEmpleado() {
        super();
        empleadoDAO=new MySqlEmpleadoDAO();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion;
        accion=request.getParameter("tipo");

        if(accion.equals("LISTAREMPLEADOS")) {
            listarEmpleados(request,response);
        }
        else if(accion.equals("REGISTRAR")) {
            registrarEmpleado(request,response);
        }
        else if(accion.equals("ELIMINAREMPLEADO")) {
            eliminarEmpleado(request, response);
        }
    }

    private void registrarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id,nom,ape,dni,edad,dis;

        id=request.getParameter("idEmpleado");
        nom=request.getParameter("nombre");
        ape=request.getParameter("apellido");
        dni=request.getParameter("dni");
        edad=request.getParameter("edad");
        dis=request.getParameter("distrito");

        Empleado bean = new Empleado();
        bean.setIdEmp(Integer.parseInt(id));
        bean.setNombreEmp(nom);
        bean.setApellidoEmp(ape);
        bean.setDniEmp(Integer.parseInt(dni));
        bean.setEdadEmp(Integer.parseInt(edad));
        bean.setDistritoEmp(dis);

        int salida;
        salida = empleadoDAO.insertarEmp(bean);
        listarEmpleados(request, response);

    }

    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Empleado> data=empleadoDAO.listAll();
        request.setAttribute("listarEmpleados", data);
        request.getRequestDispatcher("/Empleado.jsp").forward(request, response);

    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cod=request.getParameter("idEmpleadoEliminar");
        int par;
        par=empleadoDAO.eliminarEmp(Integer.parseInt(cod));
        if(par>0) {
            listarEmpleados(request, response);
        }
        else {
            response.sendRedirect("Empleado.jsp?MENSAJE=Error en el empleado");
        }

    }
}
