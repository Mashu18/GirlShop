package backEnd.interfaces;

import backEnd.entidad.Empleado;
import java.util.List;

public interface EmpleadoDAO {
    public int insertarEmp(Empleado bean);
    public int eliminarEmp(int cod);
    public List<Empleado> listAll();
}
