import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student extends JPanel{
	JTextField txtId = null;
	JTextField txtName = null;
	JTextField txtDepartment = null;
	JTextField txtAdd = null;
	
	JButton btnInsert = null; //���
	JButton btnSelect = null; //���
	JButton btnUpdate = null; //����
	JButton btnDelete = null; //����
	
	JButton btnSearch = null;
	
	DefaultTableModel model=null; //���̺��� ������ ���
	JTable table=null;
	
	public Student() {
		
		this.setLayout(new FlowLayout()); 
		
		this.add(new JLabel("�й�"));
		this.txtId = new JTextField(14);
		this.add(txtId);
		this.btnSearch = new JButton("�˻�");
		this.add(btnSearch);
		this.btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("�����");
					
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from student where id='"+txtId.getText()+"'");
					
					model.setNumRows(0);
					while(rs.next()) {
						String[] row = new String[4];
						row[0] = rs.getString("id");
						row[1] = rs.getString("name");
						row[2] = rs.getString("dept");
						row[3] = rs.getString("address");
						model.addRow(row);
						
						txtId.setText(rs.getString("id"));
						txtName.setText(rs.getString("name"));
						txtDepartment.setText(rs.getString("dept"));
						txtAdd.setText(rs.getString("address"));
					}
					
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e1) {
						e1.printStackTrace();
				} finally {}
			}
		});
		
		this.add(new JLabel("�̸�"));
		this.txtName = new JTextField(20);
		this.add(txtName);
		
		this.add(new JLabel("�а�"));
		this.txtDepartment = new JTextField(20);
		this.add(txtDepartment);
		
		this.add(new JLabel("�ּ�"));
		this.txtAdd = new JTextField(20);
		this.add(txtAdd);
		
		String[] colname = {"�й�", "�̸�", "�а�", "�ּ�"};
		this.model = new DefaultTableModel(colname, 0);
		this.table = new JTable(model);// model�� table�� ���� ���ε�, ����
		this.table.setPreferredScrollableViewportSize(new Dimension(250,270));
		this.add(this.table);
		JScrollPane sp=new JScrollPane(this.table);
		this.add(sp);
		
		this.table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				table=(JTable)e.getComponent();
				model=(DefaultTableModel)table.getModel();
				txtId.setText((String)model.getValueAt(table.getSelectedRow(), 0));
				txtName.setText((String)model.getValueAt(table.getSelectedRow(), 1));
				txtDepartment.setText((String)model.getValueAt(table.getSelectedRow(), 2));
				txtAdd.setText((String)model.getValueAt(table.getSelectedRow(), 3));
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		
		});
		
		this.btnInsert = new JButton("���");
		this.add(btnInsert);
		this.btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(txtId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�й��� �Էµ��� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�̸��� �Էµ��� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(txtDepartment.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�а��� �Էµ��� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(txtAdd.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�ּҰ� �Էµ��� �ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.", "�˸�", JOptionPane.INFORMATION_MESSAGE);
				}
				
				System.out.println("���");
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("�����");
					
					Statement stmt = conn.createStatement();
					
					//insert
					stmt.executeUpdate("insert into student values('"+txtId.getText()+"', '"+txtName.getText()+"', '"+txtDepartment.getText()+"','"+txtAdd.getText()+"')");
					
					ResultSet rs = stmt.executeQuery("select * from student");
					
					model.setNumRows(0);
					while(rs.next()) {
						String[] row = new String[4];
						row[0] = rs.getString("id");
						row[1] = rs.getString("name");
						row[2] = rs.getString("dept");
						row[3] = rs.getString("address");
						model.addRow(row);
					}
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e1) {
						e1.printStackTrace();
				} finally {}
			}
		});
		
		this.btnSelect = new JButton("���");
		this.add(btnSelect);
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("�����");
					
					Statement stmt = conn.createStatement();
					
					ResultSet rs = stmt.executeQuery("select * from student");
					
					model.setNumRows(0);
					while(rs.next()) {
						String[] row = new String[4];
						row[0] = rs.getString("id");
						row[1] = rs.getString("name");
						row[2] = rs.getString("dept");
						row[3] = rs.getString("address");
						model.addRow(row);
					}
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e1) {
						e1.printStackTrace();
				} finally {}
			}
		});
		
		this.btnUpdate = new JButton("����");
		this.add(btnUpdate);
		this.btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("�����");
					
					Statement stmt = conn.createStatement();
					
					stmt.executeUpdate("update student set name='"+txtName.getText()+"', dept='"+txtDepartment.getText()+"', address='"+txtAdd.getText()+"' where id='"+txtId.getText()+"'");
					
					ResultSet rs = stmt.executeQuery("select * from student where id='"+txtId.getText()+"'");
					
					model.setNumRows(0);
					while(rs.next()) {
						String[] row = new String[4];
						row[0] = rs.getString("id");
						row[1] = rs.getString("name");
						row[2] = rs.getString("dept");
						row[3] = rs.getString("address");
						model.addRow(row);
					}
					
					rs.close();
					stmt.close();
					conn.close();
					} catch (Exception e1) {
						e1.printStackTrace();
				} finally {}
			}
		});
		
		this.btnDelete = new JButton("����");
		this.btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "confirm", JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.YES_OPTION) {
					// ����Ŭ ���� ����(delete)ó��
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
						System.out.println("�����");
						
						Statement stmt = conn.createStatement();
						
						stmt.executeUpdate("delete from student where id='"+txtId.getText()+"'");
						
						ResultSet rs = stmt.executeQuery("select * from student");
						
						model.setNumRows(0);
						while(rs.next()) {
							String[] row = new String[4];
							row[0] = rs.getString("id");
							row[1] = rs.getString("name");
							row[2] = rs.getString("dept");
							row[3] = rs.getString("address");
							model.addRow(row);
						}
						
						txtId.setText("");
						txtName.setText("");
						txtDepartment.setText("");
						txtAdd.setText("");
						
						rs.close();
						stmt.close();
						conn.close();
						} catch (Exception e1) {
							e1.printStackTrace();
					} finally {
						
					}
					// ���� �� �޼��� ���
					JOptionPane.showInternalMessageDialog(null, "�����Ǿ����ϴ�.", "message", JOptionPane.INFORMATION_MESSAGE);
				} 
			}
			
		});
		this.add(btnDelete);
		
		this.setSize(280, 500);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Student();

	}

}