package backEnd.dao;

import backEnd.entidad.Producto;
import backEnd.interfaces.ProductoDAO;
import backEnd.utils.MySqlConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductoDAO implements ProductoDAO {
    @Override
    public int insertarProd(Producto bean) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn= MySqlConexion.getConectar();
            String sql="insert into PRODUCTO values(null,?,?,?,?,?)";

            pstm=cn.prepareStatement(sql);

            pstm.setInt(1, bean.getId_prod());
            pstm.setString(2, bean.getNombre_prod());
            pstm.setDouble(3, bean.getPrecio());
            pstm.setInt(4, bean.getStk());
            pstm.setString(5, bean.getDescripcion());

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
    public int eliminarProd(int cod) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="delete from `PRODUCTO` where COD_ORDEN=?";

            pstm=cn.prepareStatement(sql);

            pstm.setInt(1, cod);

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
    public int actualizarProd(Producto bean) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="update PRODUCTO set ID_PRODUCTO=?,NOMBRE=?,PRECIO=?,STOCK=?,DESCRIPCION=? where COD_ORDEN=?";

            pstm=cn.prepareStatement(sql);

            pstm.setInt(1, bean.getId_prod());
            pstm.setString(2, bean.getNombre_prod());
            pstm.setDouble(3, bean.getPrecio());
            pstm.setInt(4, bean.getStk());
            pstm.setString(5, bean.getDescripcion());
            pstm.setInt(6, bean.getCod_orden());

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
    public Producto findById(int cod) {
        Producto bean=null;
        Connection cn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="select * from PRODUCTO where COD_ORDEN=?";
            pstm=cn.prepareStatement(sql);

            pstm.setInt(1,cod);

            rs=pstm.executeQuery();

            if(((ResultSet) rs).next()) {
                bean=new Producto();

                bean.setCod_orden(rs.getInt(1));
                bean.setId_prod(rs.getInt(2));
                bean.setNombre_prod(rs.getNString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStk(rs.getInt(5));
                bean.setDescripcion(rs.getString(6));

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
    public List<Producto> listAll() {
        List<Producto> lista=new ArrayList<Producto>();
        Producto bean=null;
        Connection cn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="select * from PRODUCTO";
            pstm=cn.prepareStatement(sql);

            rs=pstm.executeQuery();

            while(rs.next()) {
                bean=new Producto();

                bean.setCod_orden(rs.getInt(1));
                bean.setId_prod(rs.getInt(2));
                bean.setNombre_prod(rs.getNString(3));
                bean.setPrecio(rs.getDouble(4));
                bean.setStk(rs.getInt(5));
                bean.setDescripcion(rs.getString(6));

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
