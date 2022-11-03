package backEnd.interfaces;

import backEnd.entidad.Pedido;
import java.util.List;

public interface PedidoDAO {
    public int insertarPed(Pedido bean);
    public int eliminarPed(int cod);
    public List<Pedido> listAll();
}
