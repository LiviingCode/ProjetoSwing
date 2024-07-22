package CrudPacientes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import DAO.PacienteDAO;
import DTO.PacienteDTO;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;

import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PacientesGUI {

	private JFrame frame;
	private JTextField nameField;
	private JTextField obsField;
	private JTextField adressField;
	public MaskFormatter formattedData;
	private JTable table;
	private JTextField srcFild;
	private JTextField idField;
	private JLabel dateField;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PacientesGUI window = new PacientesGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public PacientesGUI() throws ParseException {
		formattedData = new MaskFormatter("##/##/####");
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 796, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 780, 543);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel labelTitle = new JLabel("Tela de Cadastros");
		labelTitle.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		labelTitle.setFont(new Font("Arial", Font.BOLD, 20));
		labelTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitle.setBounds(10, 11, 760, 24);
		panel.add(labelTitle);
		
		JLabel lbID = new JLabel("Código do Paciente");
		lbID.setHorizontalTextPosition(SwingConstants.CENTER);
		lbID.setHorizontalAlignment(SwingConstants.LEFT);
		lbID.setFont(new Font("Arial", Font.PLAIN, 14));
		lbID.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		lbID.setBounds(20, 49, 142, 24);
		panel.add(lbID);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setHorizontalTextPosition(SwingConstants.LEFT);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Arial", Font.PLAIN, 14));
		lblName.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		lblName.setBounds(20, 82, 142, 24);
		panel.add(lblName);
		
		JLabel lblDate = new JLabel("Data de Nascimento");
		lblDate.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDate.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		lblDate.setBounds(20, 115, 142, 24);
		panel.add(lblDate);
		
		JLabel lblAdress = new JLabel("Endereço");
		lblAdress.setHorizontalTextPosition(SwingConstants.LEFT);
		lblAdress.setHorizontalAlignment(SwingConstants.LEFT);
		lblAdress.setFont(new Font("Arial", Font.PLAIN, 14));
		lblAdress.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		lblAdress.setBounds(20, 148, 142, 24);
		panel.add(lblAdress);
		
		JLabel lblObs = new JLabel("Observações");
		lblObs.setHorizontalTextPosition(SwingConstants.LEFT);
		lblObs.setHorizontalAlignment(SwingConstants.LEFT);
		lblObs.setFont(new Font("Arial", Font.PLAIN, 14));
		lblObs.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		lblObs.setBounds(20, 181, 142, 24);
		panel.add(lblObs);
		
		
		idField = new JTextField();
		idField.setBounds(164, 49, 50, 24);
		panel.add(idField);
		idField.setColumns(10);
		idField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 255)));
		idField.setFont(new Font("Arial", Font.PLAIN, 14));
		idField.setColumns(10);
		idField.setEditable(false);
		
		nameField = new JTextField();
		nameField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 255)));
		nameField.setBounds(164, 82, 300, 24);
		nameField.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(nameField);
		nameField.setColumns(10);
		
		obsField = new JTextField();
		obsField.setColumns(10);
		obsField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 255)));
		obsField.setBounds(164, 181, 300, 24);
		nameField.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(obsField);
		
		adressField = new JTextField();
		adressField.setColumns(10);
		adressField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 255)));
		adressField.setBounds(164, 148, 300, 24);
		nameField.setFont(new Font("Arial", Font.PLAIN, 14));
		panel.add(adressField);
		
		srcFild = new JTextField();
		srcFild.setVisible(false);
		srcFild.setFont(new Font("Arial", Font.PLAIN, 14));
		srcFild.setColumns(10);
		srcFild.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 255)));
		srcFild.setBounds(224, 250, 96, 24);
		panel.add(srcFild);
		
		JFormattedTextField dateField = new JFormattedTextField(formattedData);
		dateField.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		dateField.setHorizontalAlignment(SwingConstants.CENTER);
		dateField.setFont(new Font("Arial", Font.PLAIN, 14));
		dateField.setBounds(164, 115, 132, 24);
		panel.add(dateField);
		dateField.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 128, 255)));
		
		JButton buttonCad = new JButton("Cadastrar");
		buttonCad.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonCad.setBounds(20, 216, 95, 23);
		buttonCad.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonCad.setBackground(new Color(255 ,250 ,250));
		panel.add(buttonCad);
		

		
		JButton buttonList = new JButton("Listar");
		buttonList.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonList.setBackground(new Color(255 ,250 ,250));
		buttonList.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonList.setBounds(268, 216, 89, 23);
		panel.add(buttonList);
		
		JButton buttonDelete = new JButton("Excluir");
		buttonDelete.setMnemonic(KeyEvent.VK_ENTER);
		buttonDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonDelete.setBackground(new Color(255 ,250 ,250));
		buttonDelete.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonDelete.setBounds(389, 216, 89, 23);
		buttonDelete.setVisible(false);
		panel.add(buttonDelete);
				
	    JButton buttonSave = new JButton("Salvar");
	    buttonSave.setVisible(false);
	    buttonSave.setMnemonic(KeyEvent.VK_ENTER);
	    buttonSave.setHorizontalTextPosition(SwingConstants.CENTER);
	    buttonSave.setFont(new Font("Arial", Font.PLAIN, 13));
	    buttonSave.setBackground(new Color(255, 250, 250));
	    buttonSave.setBounds(442, 251, 89, 23);
	    panel.add(buttonSave);
	    
	    JButton buttonSrc = new JButton("Carregar");
	    buttonSrc.setVisible(false);
	    buttonSrc.setMnemonic(KeyEvent.VK_ENTER);
	    buttonSrc.setHorizontalTextPosition(SwingConstants.CENTER);
	    buttonSrc.setFont(new Font("Arial", Font.PLAIN, 13));
	    buttonSrc.setBackground(new Color(255, 250, 250));
	    buttonSrc.setBounds(330, 251, 89, 23);
	    panel.add(buttonSrc);
	    
		JButton buttonEdit = new JButton("Pesquisar");
		buttonEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonEdit.setBackground(new Color(255 ,250 ,250));
		buttonEdit.setFont(new Font("Arial", Font.PLAIN, 13));
		buttonEdit.setBounds(148, 216, 95, 23);
		panel.add(buttonEdit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 285, 760, 247);
		panel.add(scrollPane);
		
		JTable clientTable = new JTable();
		clientTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nome", "Data de Nascimento", "Endereço", "Observações"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		clientTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		clientTable.getColumnModel().getColumn(2).setPreferredWidth(150);
		clientTable.getColumnModel().getColumn(3).setPreferredWidth(250);
		clientTable.getColumnModel().getColumn(4).setPreferredWidth(250);
		scrollPane.setViewportView(clientTable);
		clientTable.setBackground(Color.white);
		clientTable.setForeground(new Color(0, 0, 0));
		clientTable.setSelectionBackground(new Color(128, 255, 255));
		clientTable.setGridColor(new Color(0, 0, 0));
		clientTable.setFont(new Font("Arial", Font.PLAIN, 13));
		clientTable.setRowHeight(30);
		clientTable.setAutoCreateColumnsFromModel(true);
		clientTable.setAutoCreateRowSorter(true);
		
		JLabel lblCodP = new JLabel("Digite o Código do Paciente");
		lblCodP.setHorizontalTextPosition(SwingConstants.LEFT);
		lblCodP.setHorizontalAlignment(SwingConstants.LEFT);
		lblCodP.setFont(new Font("Arial", Font.PLAIN, 14));
		lblCodP.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
		lblCodP.setBounds(20, 250, 194, 24);
		panel.add(lblCodP);
		lblCodP.setVisible(false);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{labelTitle, panel, lbID, lblName, lblDate, lblAdress, lblObs, nameField, obsField, adressField, dateField, buttonCad, buttonEdit, buttonList, buttonDelete, idField, clientTable, buttonSrc, buttonSave, lblCodP}));
		
		//Cadastrar
		buttonCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String name;
				 String date;
				 String adress;
				 String obs;
				
				 name = nameField.getText();
				 date = dateField.getText();
				 adress = adressField.getText();
				 obs = obsField.getText();
				 
				 try {
					 DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					 DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					 LocalDate localdate = LocalDate.parse(date, inputFormatter);
					 String dataformatada = localdate.format(outputFormatter);
					
					 PacienteDTO pacienteDTO = new PacienteDTO();
					
					 pacienteDTO.setName(name);
					 pacienteDTO.setDate(dataformatada);
					 pacienteDTO.setAdress(adress);
					 pacienteDTO.setObs(obs);
					 
					 PacienteDAO pacienteDAO = new PacienteDAO();
					 pacienteDAO.cadastrarPaciente(pacienteDTO);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Necessário digitar todos os dados");
				}
			}
		});
		//Listar
		buttonList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PacienteDAO pacientes = new PacienteDAO();
					DefaultTableModel model = (DefaultTableModel) clientTable.getModel();
					model.setNumRows(0);
					
					ArrayList<PacienteDTO> lista = pacientes.listarTodosPacientes();
					
					for(int i = 0; i < lista.size(); i++) {
						model.addRow(new Object[] {
							lista.get(i).getId(),
							lista.get(i).getName(),
							lista.get(i).getDate(),
							lista.get(i).getAdress(),
							lista.get(i).getObs()
						});
					}
				}catch (Exception errorlist) {
					JOptionPane.showMessageDialog(null,"Listar Valores VIEW: " + errorlist);
				}
			}
		});
		//Pesquisar
		buttonEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srcFild.setVisible(true);
				buttonSrc.setVisible(true);
				lblCodP.setVisible(true);
			}
		});
		//Carregar
		buttonSrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoPaciente = srcFild.getText();
					buttonDelete.setVisible(true);
					buttonSave.setVisible(true);
					PacienteDAO paciente = new PacienteDAO();
					PacienteDTO pacienteEncontrado = paciente.listarPaciente(codigoPaciente);
					idField.setText(String.valueOf(pacienteEncontrado.getId()));
					nameField.setText(pacienteEncontrado.getName());
					dateField.setText(pacienteEncontrado.getDate());
					adressField.setText(pacienteEncontrado.getAdress());
					obsField.setText(pacienteEncontrado.getObs());
					srcFild.setText("");

			}
		});
		//Atualizar
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoPaciente = srcFild.getText();
				PacienteDAO paciente = new PacienteDAO();
				String name;
				String date;
				String adress;
				String obs;
				int id;
					
				 id = Integer.parseInt(idField.getText());
				 name = nameField.getText();
				 date = dateField.getText();
				 adress = adressField.getText();
				 obs = obsField.getText();
				 
				 try {
					 DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					 DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					 LocalDate localdate = LocalDate.parse(date, inputFormatter);
					 String dataformatada = localdate.format(outputFormatter);
					
					 PacienteDTO pacienteDTO = new PacienteDTO();
					
					 pacienteDTO.setId(id);
					 pacienteDTO.setName(name);
					 pacienteDTO.setDate(dataformatada);
					 pacienteDTO.setAdress(adress);
					 pacienteDTO.setObs(obs);
					 
					 PacienteDAO pacienteDAO = new PacienteDAO();
					 pacienteDAO.atualizarPaciente(pacienteDTO);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Necessário digitar todos os dados");
				}
			}
		});
			

		//Deletar
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String codigoPaciente = idField.getText();
				PacienteDAO paciente = new PacienteDAO();
				paciente.deletarPaciente(codigoPaciente);
				srcFild.setText("");
				
			}
		});
		
	
		       	
	}
}
