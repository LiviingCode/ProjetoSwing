package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexao {
	//Utilizado banco de dados MySQL
	public Connection connectaDB() {
		Connection conexao = null;
		
		try {
			String user = "root"; //utilizar usuario do banco.
			String password = "2808"; // utilizar senha cadastrada no banco.
			String bd ="bancoteste"; //utilizar base de dados do banco.
			String url = "jdbc:mysql://127.0.0.1:3306/"+bd+"?serverTimezone=America/Sao_Paulo";
			conexao = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return conexao;
		
	}
	
}
