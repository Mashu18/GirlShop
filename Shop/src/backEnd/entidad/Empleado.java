package backEnd.entidad;

public class Empleado {
    private int num_Order, idEmp,dniEmp,edadEmp;
    private String nombreEmp,apellidoEmp,distritoEmp;

    public int getNum_Order() {
        return num_Order;
    }

    public void setNum_Order(int num_Order) {
        this.num_Order = num_Order;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getDniEmp() {
        return dniEmp;
    }

    public void setDniEmp(int dniEmp) {
        this.dniEmp = dniEmp;
    }

    public int getEdadEmp() {
        return edadEmp;
    }

    public void setEdadEmp(int edadEmp) {
        this.edadEmp = edadEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getApellidoEmp() {
        return apellidoEmp;
    }

    public void setApellidoEmp(String apellidoEmp) {
        this.apellidoEmp = apellidoEmp;
    }

    public String getDistritoEmp() {
        return distritoEmp;
    }

    public void setDistritoEmp(String distritoEmp) {
        this.distritoEmp = distritoEmp;
    }
}
