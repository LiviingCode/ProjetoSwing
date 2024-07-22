package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;

import javax.management.modelmbean.InvalidTargetObjectTypeException;
import javax.swing.JOptionPane;

import DTO.PacienteDTO;

public class PacienteDAO {
	
	Connection conexao;
	PreparedStatement state;
	ResultSet resultado;
	ResultSet resultado2;
	ArrayList<PacienteDTO> lista = new ArrayList<PacienteDTO>();
	PacienteDTO pacienteEdit = new PacienteDTO();
	
	
	public void cadastrarPaciente(PacienteDTO objPaciente) {
		String sql = "insert into pacientes (name,date,adress,obs) values(?,?,?,?)";
		conexao = new Conexao().connectaDB();
		
		try {
			state = conexao.prepareStatement(sql);
			state.setString(1, objPaciente.getName());
			state.setString(2,objPaciente.getDate());
			state.setString(3, objPaciente.getAdress());
			state.setString(4, objPaciente.getObs());
			state.execute();
			state.close();
			
			JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"PacienteDAO" + e);
		}
	}
	public ArrayList<PacienteDTO> listarTodosPacientes(){
		String sql = "select id, name, DATE_FORMAT(date, '%d/%m/%Y') as date, adress, obs from pacientes";
		conexao = new Conexao().connectaDB();
		try {
			state = conexao.prepareStatement(sql);
			resultado = state.executeQuery();
			
			while (resultado.next()) {
				PacienteDTO objPacienteDTO = new PacienteDTO();
				objPacienteDTO.setId(resultado.getInt("id"));
				objPacienteDTO.setName(resultado.getString("name"));
				objPacienteDTO.setAdress(resultado.getString("adress"));
				objPacienteDTO.setDate(resultado.getString("date"));
				objPacienteDTO.setObs(resultado.getString("obs"));
				lista.add(objPacienteDTO);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"PacienteDAO" + e);
		}
		return lista;
	}
	
	public PacienteDTO listarPaciente(String codigo){
		String sql = "select id, name, DATE_FORMAT(date, '%d/%m/%Y') as date, adress, obs from pacientes where id="+codigo;
		conexao = new Conexao().connectaDB();

		try {
			state = conexao.prepareStatement(sql);
			resultado = state.executeQuery();
			if(resultado.next()) {
			pacienteEdit.setId(resultado.getInt("id"));
			pacienteEdit.setName(resultado.getString("name"));
			pacienteEdit.setAdress(resultado.getString("adress"));
			pacienteEdit.setDate(resultado.getString("date"));
			pacienteEdit.setObs(resultado.getString("obs"));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"PacienteDAO" + e);
		}
		return pacienteEdit ;
	}

	public void atualizarPaciente(PacienteDTO objPaciente) {
		String sql = "UPDATE pacientes SET id = ?, name = ? , date = ? , adress = ? , obs = ? WHERE id =" + objPaciente.getId();
		conexao = new Conexao().connectaDB();
		try {
			state = conexao.prepareStatement(sql);
			state.setInt(1,objPaciente.getId());
			state.setString(2, objPaciente.getName());
			state.setString(3,objPaciente.getDate());
			state.setString(4, objPaciente.getAdress());
			state.setString(5, objPaciente.getObs());
			state.execute();
			state.close();
			
			JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"PacienteDAO" + e);
		}
	}
	
	public void deletarPaciente(String codigo) {
		String sql = "DELETE FROM pacientes WHERE id =" + codigo;
		conexao = new Conexao().connectaDB();
		try {
			state = conexao.prepareStatement(sql);
			state.execute();
			state.close();
			
			JOptionPane.showMessageDialog(null, "Paciente Excluido com Sucesso");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"PacienteDAO" + e);
		}
	}
	
}
