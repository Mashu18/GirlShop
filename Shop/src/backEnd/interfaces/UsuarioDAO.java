package backEnd.interfaces;

import backEnd.entidad.Menu;
import backEnd.entidad.Usuario;
import java.util.List;

public interface UsuarioDAO {
    public int insertarUsuario (Usuario bean);
    public Usuario iniciarSesion(String nom_usu, String contra);
    public List<Menu> MenuUsuario(int id_usu);
}
