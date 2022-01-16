import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Haksa2 extends JFrame{
	
	JMenuBar mb = new JMenuBar();
	JMenu menu1 = new JMenu("�л�����");
	JMenu menu2 = new JMenu("��������");
	JMenuItem miStudent = new JMenuItem("�л�����");
	JMenuItem miBookRent = new JMenuItem("��������");
	
	ImageIcon img = new ImageIcon("img/f_logo.png");
	JLabel imgLabel = new JLabel(img);
	
	JPanel panel; // �޴��� ȭ���� ��µǴ� �θ��г�
	
	public Haksa2() {
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		this.menu1.add(this.miStudent);
		this.menu2.add(this.miBookRent);
		this.mb.add(this.menu1);
		this.mb.add(this.menu2);
		this.setJMenuBar(mb);
	
		this.miStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    panel.removeAll(); //���������Ʈ ����
			    panel.revalidate(); //�ٽ� Ȱ��ȭ
			    panel.repaint();    //�ٽ� �׸���
			    panel.add(new Student()); //ȭ�� ����.
			    panel.setLayout(null);//���̾ƿ��������
				
			}	
		});
		
		this.miBookRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); //���������Ʈ ����
			    panel.revalidate(); //�ٽ� Ȱ��ȭ
			    panel.repaint();    //�ٽ� �׸���
			    panel.add(new BookRent()); //ȭ�� ����.
			    panel.setLayout(null);//���̾ƿ��������
			}
			
		});
		
		panel=new JPanel();//panel����
		add(panel);//�����ӿ� �г� �߰�
		panel.add(imgLabel);
		
		
		this.setSize(500, 600);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Haksa2();

	}

}