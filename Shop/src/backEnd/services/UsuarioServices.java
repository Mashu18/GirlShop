package backEnd.services;

import backEnd.entidad.Menu;
import backEnd.entidad.Usuario;
import backEnd.fabrica.DAOFactory;
import backEnd.interfaces.UsuarioDAO;

import java.util.List;

public class UsuarioServices {
    private DAOFactory fabrica=DAOFactory.getDAOFactory(1);
    private UsuarioDAO objUsuario=fabrica.getUsuarioDAO();

    public Usuario LoginSesion(String nom_usu, String contra) {
        return objUsuario.iniciarSesion(nom_usu, contra);
    }


    public List<Menu> MenuDelUsuario(int id_usu) {
        return objUsuario.MenuUsuario(id_usu);
    }
}
