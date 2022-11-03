package backEnd.dao;

import backEnd.entidad.Menu;
import backEnd.entidad.Usuario;
import backEnd.interfaces.UsuarioDAO;
import backEnd.utils.MySqlConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUsuarioDAO implements UsuarioDAO {

    @Override
    public int insertarUsuario(Usuario bean) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn= MySqlConexion.getConectar();
            String sql="insert into `USUARIO` values(null,?,?,?,?,?,?,?,?)";

            pstm=cn.prepareStatement(sql);

            pstm.setString(1, bean.getNom_usu());
            pstm.setString(2, bean.getNombre());
            pstm.setString(3, bean.getApellido());
            pstm.setInt(4, bean.getDni_usu());
            pstm.setInt(5, bean.getEdad_usu());
            pstm.setString(6, bean.getSexo());
            pstm.setString(7, bean.getPais());
            pstm.setString(8, bean.getContra());

            salida=pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstm!=null) pstm.close();
                if (cn!=null) cn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return salida;
    }

    @Override
    public Usuario iniciarSesion(String nom_usu, String contra) {
        Usuario bean = null;
        Connection cn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="select ID_USUARIO,NOMBRE_COMP, APELLIDO_COMP from USUARIO where NOMBRE_USUARIO=? and CONTRASENA=?";
            pstm=cn.prepareStatement(sql);

            pstm.setString(1, nom_usu);
            pstm.setString(2, contra);

            rs=pstm.executeQuery();

            while(rs.next()) {
                bean=new Usuario();

                bean.setId_usu(1);
                bean.setNombre(rs.getString(2));
                bean.setApellido(rs.getString(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                if(cn!=null) cn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return bean;
    }

    @Override
    public List<Menu> MenuUsuario(int id_usu) {
        List<Menu> lista=new ArrayList<Menu>();
        Menu bean;
        Connection cn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="select a.COD_MEN, m.DES_MEN, m.URL_MEN from ACCESO a join MENU m on a.COD_MEN=m.COD_MEN where a.ID_USUARIO=?";
            pstm=cn.prepareStatement(sql);

            pstm.setInt(1, id_usu);

            rs=pstm.executeQuery();

            while(rs.next()) {
                bean=new Menu();

                bean.setCodigo(1);
                bean.setNombre(rs.getString(2));
                bean.setUrl(rs.getString(3));

                lista.add(bean);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            try {
                if(rs!=null) rs.close();
                if(pstm!=null) pstm.close();
                if(cn!=null) cn.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return lista;
    }
}
