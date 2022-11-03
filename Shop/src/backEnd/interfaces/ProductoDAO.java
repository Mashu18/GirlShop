package backEnd.interfaces;

import backEnd.entidad.Producto;
import java.util.List;

public interface ProductoDAO {
    public int insertarProd(Producto bean);
    public int eliminarProd(int cod);
    public int actualizarProd(Producto bean);
    public Producto findById(int cod);
    public List<Producto> listAll();
}
