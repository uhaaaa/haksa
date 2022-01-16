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
	JMenu menu1 = new JMenu("학생관리");
	JMenu menu2 = new JMenu("도서관리");
	JMenuItem miStudent = new JMenuItem("학생정보");
	JMenuItem miBookRent = new JMenuItem("도서대출");
	
	ImageIcon img = new ImageIcon("img/f_logo.png");
	JLabel imgLabel = new JLabel(img);
	
	JPanel panel; // 메뉴별 화면이 출력되는 부모패널
	
	public Haksa2() {
		this.setTitle("학사관리");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		this.menu1.add(this.miStudent);
		this.menu2.add(this.miBookRent);
		this.mb.add(this.menu1);
		this.mb.add(this.menu2);
		this.setJMenuBar(mb);
	
		this.miStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    panel.removeAll(); //모든컴포넌트 삭제
			    panel.revalidate(); //다시 활성화
			    panel.repaint();    //다시 그리기
			    panel.add(new Student()); //화면 생성.
			    panel.setLayout(null);//레이아웃적용안함
				
			}	
		});
		
		this.miBookRent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll(); //모든컴포넌트 삭제
			    panel.revalidate(); //다시 활성화
			    panel.repaint();    //다시 그리기
			    panel.add(new BookRent()); //화면 생성.
			    panel.setLayout(null);//레이아웃적용안함
			}
			
		});
		
		panel=new JPanel();//panel생성
		add(panel);//프레임에 패널 추가
		panel.add(imgLabel);
		
		
		this.setSize(500, 600);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		new Haksa2();

	}

}