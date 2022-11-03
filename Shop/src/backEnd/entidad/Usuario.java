package backEnd.entidad;

public class Usuario {
    private int id_usu,edad_usu,dni_usu;
    private String nom_usu,nombre,apellido,sexo,pais,contra;

    private String tipo;

    /**********************/

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public int getEdad_usu() {
        return edad_usu;
    }

    public void setEdad_usu(int edad_usu) {
        this.edad_usu = edad_usu;
    }

    public int getDni_usu() {
        return dni_usu;
    }

    public void setDni_usu(int dni_usu) {
        this.dni_usu = dni_usu;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
