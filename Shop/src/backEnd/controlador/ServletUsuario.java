package backEnd.controlador;

import backEnd.dao.MySqlUsuarioDAO;
import backEnd.entidad.Menu;
import backEnd.entidad.Usuario;
import backEnd.services.UsuarioServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioServices servicio=new UsuarioServices();
    private MySqlUsuarioDAO usuarioDAO;

    public ServletUsuario() {
        super();
        usuarioDAO = new MySqlUsuarioDAO();
    }


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action;
        action=request.getParameter("tipo");

        if(action.equals("INICIAR")) {
            sesionUsuario(request, response);
        }
        else if(action.equals("REGISTRAR")) {
            registrarUsuario(request, response);
        }
    }


    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomusu,nom,ape,dni,edad,sexo,pais,contra;

        nomusu=request.getParameter("usuario");
        nom=request.getParameter("nombre");
        ape=request.getParameter("apellido");
        dni=request.getParameter("dni");
        edad=request.getParameter("edad");
        sexo=request.getParameter("sexo");
        pais=request.getParameter("pais");
        contra=request.getParameter("contrasena");

        Usuario bean = new Usuario();
        bean.setNom_usu(nomusu);
        bean.setNombre(nom);
        bean.setApellido(ape);
        bean.setDni_usu(Integer.parseInt(dni));
        bean.setEdad_usu(Integer.parseInt(edad));
        bean.setSexo(sexo);
        bean.setPais(pais);
        bean.setContra(contra);

        int salida;
        salida = usuarioDAO.insertarUsuario(bean);

        request.getRequestDispatcher("/NewLogin.jsp").forward(request, response);
    }


    private void sesionUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String vNomUsu,vContraUsu;
        vNomUsu=request.getParameter("nombreusuario");
        vContraUsu=request.getParameter("contrasena");

        Usuario bean =servicio.LoginSesion(vNomUsu, vContraUsu);

        if (bean==null) {
            request.setAttribute("MENSAJE","Usuario y/o Contraseña incorrectos");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
        else {
            List<Menu> data=servicio.MenuDelUsuario(bean.getId_usu());
            request.setAttribute("LISTA", data);
            request.getRequestDispatcher("Barra.jsp").forward(request, response);
            /*request.getRequestDispatcher("/Barra.jsp").forward(request, response);*/
        }
    }
}
