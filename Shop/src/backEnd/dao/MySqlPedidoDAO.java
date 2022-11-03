package backEnd.dao;

import backEnd.entidad.Pedido;
import backEnd.interfaces.PedidoDAO;
import backEnd.utils.MySqlConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPedidoDAO implements PedidoDAO {
    @Override
    public int insertarPed(Pedido bean) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn= MySqlConexion.getConectar();
            String sql="insert into `PEDIDO` values(NULL,?,?,?,?)";

            pstm=cn.prepareStatement(sql);

            pstm.setInt(1, bean.getIdproducto());
            pstm.setString(2, bean.getNombre_cli());
            pstm.setInt(3, bean.getDni());
            pstm.setInt(4, bean.getCantidad());

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
    public int eliminarPed(int cod) {
        int salida =-1;
        Connection cn=null;
        PreparedStatement pstm=null;

        try {
            cn=MySqlConexion.getConectar();
            String sql="delete from `PEDIDO` where NUM_PEDIDO=?";

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
    public List<Pedido> listAll() {
        List<Pedido> lista=new ArrayList<Pedido>();
        Pedido bean=null;
        Connection cn=null;
        PreparedStatement pstm=null;
        ResultSet rs=null;

        try {
            cn=MySqlConexion.getConectar();
            /*String sql="select * from PEDIDO";*/
            String sql="select pe.NUM_PEDIDO, pr.NOMBRE , pe.NOM_CLI, pe.DNI_CLI, pe.CANT_PRODUCTO "
                    + " from PEDIDO pe join PRODUCTO pr on pr.ID_PRODUCTO = pe.ID_PRODUCTO";

            pstm=cn.prepareStatement(sql);

            rs=pstm.executeQuery();

            while(((ResultSet) rs).next()) {
                bean=new Pedido();

                bean.setCod_pedido(rs.getInt(1));
                bean.setNombre_prod(rs.getString(2));
                bean.setNombre_cli(rs.getString(3));
                bean.setDni(rs.getInt(4));
                bean.setCantidad(rs.getInt(5));
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
