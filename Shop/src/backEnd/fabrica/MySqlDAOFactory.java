package backEnd.fabrica;

import backEnd.interfaces.PedidoDAO;
import backEnd.interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory{
    @Override
    public PedidoDAO getPedidoDAO() {
        return null;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return null;
    }
}
