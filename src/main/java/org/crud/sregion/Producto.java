package org.crud.sregion;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Producto {
	static Connection connection;
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connectDBOracle() throws IOException, SQLException {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Cargo driver: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		try {
			connection=DriverManager.getConnection(URL,"System","Vegeta777");
			System.out.println("Conexion exitosa : Oracle11g");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	public static void insertarProducto(int id,String nombre,int precio) {
		try {
			connectDBOracle();
			String sql = "INSERT INTO PRODUCTO (ID,NOMBRE,PRECIO) VALUES (?,?,?)";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2,nombre);
			ps.setInt(3,precio);
			ps.execute();
			System.out.println("Inserto PRODUCTO:  "  + id + ","  + nombre + "," + precio);
		} catch (Exception e) {
			System.out.println("Exception insert : " + e.getMessage());
		}
	}

	public static void actualizarProducto(int id,String nombre,int precio) {
		try {
			connectDBOracle();
			String sql = "UPDATE PRODUCTO SET  NOMBRE=? , PRECIO =? WHERE ID =?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2,nombre);
			ps.setLong(3,precio);
			ps.execute();
			System.out.println("Actualizo PRODUCTO:  "  + id + ","  + nombre + "," + precio);
		} catch (Exception e) {
			System.out.println("Exception actualizacion : " + e.getMessage());
		}
	}

	public static void eliminarProducto(int id) {
		try {
			connectDBOracle();
			String sql = "Delete from PRODUCTO where ID=?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
			System.out.println("ELimino Producto:  "  + id );
		} catch (Exception e) {
			System.out.println("Exception Elimino : " + e.getMessage());
		}
	}
	
	

	public static void cosultarProducto(int id) throws IOException, SQLException{
		try {
			connectDBOracle();
			String sql = "SELECT *  from PRODUCTO where ID=?";
			PreparedStatement ps= connection.prepareStatement(sql);
			ps.setInt(1, id);
						ResultSet rs = ps.executeQuery();
						if(rs.next()) {
							System.out.println(rs.getInt("ID")+","+rs.getString("nombre")+","+rs.getLong("precio"));
						}
		} catch (Exception e) {
			System.out.println("Exception Consulta : " + e.getMessage());
		}
	}
	
	
	
	
	public static void cosultarGeneralProducto() throws IOException, SQLException{
		try {
			connectDBOracle();
			String sql = "SELECT *  from PRODUCTO ";
			PreparedStatement ps= connection.prepareStatement(sql);
						ResultSet rs = ps.executeQuery();
						while(rs.next()) {
							System.out.println(rs.getInt("ID")+","+rs.getString("nombre")+","+rs.getLong("precio"));
						}
		} catch (Exception e) {
			System.out.println("Exception Consulta : " + e.getMessage());
		}
	}
	
	
	
	public static void invocaProcedimiento(int id,String nombre,int precio) throws IOException, SQLException{
		try {
			connectDBOracle();
			CallableStatement cs= connection.prepareCall("call  ROC(?,?,?)");
			cs.setInt(1, id);
			cs.setString(2, nombre);
			cs.setLong(3, precio);
			cs.execute();
			System.out.println("Ejecuto con exito");
		} catch (Exception e) {
			System.out.println("Exception Procedimiento : " + e.getMessage());
		}
	}
	public static void main(String[] args) throws IOException, SQLException {
		//insertarProducto(1003,"Chiles",30);
		//actualizarProducto(1000,"Elotes",700);
		//eliminarProducto(999);
		//cosultarProducto(1003);
		//cosultarGeneralProducto();
		invocaProcedimiento(1002, "Pizza", 400);
	}
}
