package backEnd.dao;

import backEnd.entidad.Empleado;
import backEnd.interfaces.EmpleadoDAO;
import backEnd.utils.MySqlConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlEmpleadoDAO implements EmpleadoDAO {
    @Override
    public int insertarEmp(Empleado bean) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn= MySqlConexion.getConectar();
			/*
			String sql="INSERT INTO EMPLEADO VALUES(?,?,?,?,?,?)";
			*/
            String sql="INSERT INTO EMPLEADO VALUES(null,?,?,?,?,?,?);";

            pstm=cn.prepareStatement(sql);

            pstm.setInt(1, bean.getIdEmp());
            pstm.setString(2, bean.getNombreEmp());
            pstm.setString(3, bean.getApellidoEmp());
            pstm.setInt(4, bean.getDniEmp());
            pstm.setInt(5, bean.getEdadEmp());
            pstm.setString(6, bean.getDistritoEmp());

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
    public int eliminarEmp(int cod) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn=MySqlConexion.getConectar();

            String sql="delete from `EMPLEADO` where NUM_ORDER=?";

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
    public List<Empleado> listAll() {
        List<Empleado> lista=new ArrayList<Empleado>();
        Empleado bean=null;
        Connection cn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="select *from EMPLEADO";
            pstm=cn.prepareStatement(sql);

            rs=pstm.executeQuery();

            while(rs.next()) {
                bean=new Empleado();

                bean.setNum_Order(rs.getInt(1));
                bean.setIdEmp(rs.getInt(2));
                bean.setNombreEmp(rs.getString(3));
                bean.setApellidoEmp(rs.getString(4));
                bean.setDniEmp(rs.getInt(5));
                bean.setEdadEmp(rs.getInt(6));
                bean.setDistritoEmp(rs.getString(7));

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
