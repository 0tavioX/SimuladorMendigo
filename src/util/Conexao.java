package util;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	public static Connection getConexao() {
		Connection conn = null;
		try {
			File bd = new File("BancoDados.sqlite");
			if (bd.exists()) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:BancoDados.sqlite");
			}
		} catch (NullPointerException e) {
			System.out.println("erro ao conectar: ");
			
		} catch (Exception e) {
			System.out.println("erro ao conectar: ");
			e.printStackTrace();
			
		}
		return conn;
	}
}
