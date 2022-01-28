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
	
	JButton btnInsert = null; //등록
	JButton btnSelect = null; //목록
	JButton btnUpdate = null; //수정
	JButton btnDelete = null; //삭제
	
	JButton btnSearch = null;
	
	DefaultTableModel model=null; //테이블의 데이터 담당
	JTable table=null;
	
	public Student() {
		
		this.setLayout(new FlowLayout()); 
		
		this.add(new JLabel("학번"));
		this.txtId = new JTextField(14);
		this.add(txtId);
		this.btnSearch = new JButton("검색");
		this.add(btnSearch);
		this.btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("연결됨");
					
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
		
		this.add(new JLabel("이름"));
		this.txtName = new JTextField(20);
		this.add(txtName);
		
		this.add(new JLabel("학과"));
		this.txtDepartment = new JTextField(20);
		this.add(txtDepartment);
		
		this.add(new JLabel("주소"));
		this.txtAdd = new JTextField(20);
		this.add(txtAdd);
		
		String[] colname = {"학번", "이름", "학과", "주소"};
		this.model = new DefaultTableModel(colname, 0);
		this.table = new JTable(model);// model과 table이 서로 바인딩, 연동
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
		
		this.btnInsert = new JButton("등록");
		this.add(btnInsert);
		this.btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(txtId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "학번이 입력되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "이름이 입력되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(txtDepartment.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "학과가 입력되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				} else if(txtAdd.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "주소가 입력되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					JOptionPane.showMessageDialog(null, "등록되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
				
				System.out.println("등록");
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("연결됨");
					
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
		
		this.btnSelect = new JButton("목록");
		this.add(btnSelect);
		this.btnSelect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("연결됨");
					
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
		
		this.btnUpdate = new JButton("수정");
		this.add(btnUpdate);
		this.btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
					System.out.println("연결됨");
					
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
				// 수정 후 메세지 출력
				JOptionPane.showInternalMessageDialog(null, "되었습니다.", "message", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		this.btnDelete = new JButton("삭제");
		this.btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "confirm", JOptionPane.YES_NO_OPTION);
				if(result ==JOptionPane.YES_OPTION) {
					// 오라클 연동 삭제(delete)처리
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
						System.out.println("연결됨");
						
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
					// 삭제 후 메세지 출력
					JOptionPane.showInternalMessageDialog(null, "삭제되었습니다.", "message", JOptionPane.INFORMATION_MESSAGE);
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
