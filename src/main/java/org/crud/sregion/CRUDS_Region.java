package org.crud.sregion;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

public class CRUDS_Region {
					
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
		
		public static void insertarS_Region(int id,String name) {
			try {
				connectDBOracle();
				String sql = "INSERT INTO S_REGION (ID,NAME) VALUES (?,?)";
				PreparedStatement ps= connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2,name);
				ps.execute();
				System.out.println("Inserto Region:  "  + id + ","  + name );
			} catch (Exception e) {
				System.out.println("Exception insert : " + e.getMessage());
			}
		}

		public static void actualizarS_Region(int id,String name) {
			try {
				connectDBOracle();
				String sql = "UPDATE S_REGION SET  NAME=? WHERE ID =?";
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1,name);
				ps.setInt(2, id);
				ps.execute();
				System.out.println("Actualizo Region:  "  + id + ","  + name );
			} catch (Exception e) {
				System.out.println("Exception insert : " + e.getMessage());
			}
		}

		public static void eliminarS_Region(int id) {
			try {
				connectDBOracle();
				String sql = "Delete from S_REGION  where ID=?";
				PreparedStatement ps= connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.execute();
				System.out.println("ELimino Region:  "  + id );
			} catch (Exception e) {
				System.out.println("Exception Elimino : " + e.getMessage());
			}
		}
		
		

		public static void cosultarS_Region(int id) throws IOException, SQLException{
			try {
				connectDBOracle();
				String sql = "SELECT *  from S_REGION where ID=?";
				PreparedStatement ps= connection.prepareStatement(sql);
				ps.setInt(1, id);
							ResultSet rs = ps.executeQuery();
							if(rs.next()) {
								System.out.println(rs.getInt("ID")+","+rs.getString("name"));
							}
			} catch (Exception e) {
				System.out.println("Exception Consulta : " + e.getMessage());
			}
		}
		
		
		
		
		public static void cosultarGeneralRegion() throws IOException, SQLException{
			try {
				connectDBOracle();
				String sql = "SELECT *  from S_REGION ";
				PreparedStatement ps= connection.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							while(rs.next()) {
								System.out.println(rs.getInt("ID")+","+rs.getString("name"));
							}
			} catch (Exception e) {
				System.out.println("Exception Consulta : " + e.getMessage());
			}
		}
		
		
		
		public static void invocaProcedimiento(int id,String name) throws IOException, SQLException{
			try {
				connectDBOracle();
				CallableStatement cs= connection.prepareCall("call  proc(?,?)");
				cs.setInt(1, id);
				cs.setString(2, name);
				cs.execute();
				System.out.println("Ejecuto con exito");
			} catch (Exception e) {
				System.out.println("Exception Procedimiento : " + e.getMessage());
			}
		}
		public static void main(String[] args) throws IOException, SQLException {
			//connectDBOracle();
			//insertarS_Region(7,"Yucatan");
			//actualizarS_Region(6,"CALIFORNIA");
			//eliminarS_Region(3);
			//cosultarS_Region(1);
			//cosultarGeneralRegion();
			invocaProcedimiento(8,"Moneterrey");
		}
}
