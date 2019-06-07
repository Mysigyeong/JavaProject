import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

public class GetBestNameFrame extends JFrame{
	private JTextField t;
	public GetBestNameFrame(int time, int difficulty) {
		makeFrame();
		
		t = new JTextField("�͸�");
		t.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField)ke.getSource();
				if (src.getText().length() >= 7) {
					ke.consume();
				}
			}
		});
		
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		
		JButton b = new JButton("Ȯ��");
		
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p.setLayout(new GridLayout(4, 1, 5, 5));
		p2.setLayout(new GridBagLayout());
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] str = new String[12];
				
				try {
					File file = new File("data/best.txt");    //����Ǿ��ִ� ��ϵ� �ҷ�����
					Scanner scn = new Scanner(file);
					
					for (int i = 0; i < 12; i++) {
						str[i] = scn.nextLine();
					}
					
					if (difficulty == 1) {
						str[1] = t.getText();
						str[2] = Integer.toString(time);
					}
					else if (difficulty == 2) {
						str[5] = t.getText();
						str[6] = Integer.toString(time);
					}
					else if (difficulty == 3) {
						str[9] = t.getText();
						str[10] = Integer.toString(time);
					}
					
					BufferedWriter out = new BufferedWriter(new FileWriter("data/best.txt"));
					
					for (int i = 0; i < 12; i++) {
						out.write(str[i]);
						out.newLine();
					}
					
					scn.close();
					out.close();
					
				}
				catch (FileNotFoundException ex) {
					System.exit(1);
				}
				catch (IOException ioe) {
					System.exit(1);
				}
				
				new MineClient(difficulty, t.getText(), time);
				
				dispose();
				new BestFrame();
			}
		});
		
		p2.add(b);
		
		String s = new String();
		if (difficulty == 1) {
			s = new String("�ʱ�");
		}
		else if (difficulty == 2) {
			s = new String("�߱�");
		}
		else if (difficulty == 3) {
			s = new String("���");
		}
		
		JLabel l = new JLabel("<html><center>�����մϴ�.</center>"
				+ "����� " + s + "���� �ְ����� �������ϴ�.</html>");
		
		JLabel l2 = new JLabel("<html>�̸��� �����ּ���.<br>"
				+ "<center>(�ִ� 7��)</center></html>");
		
		l.setHorizontalAlignment(JLabel.CENTER);
		l2.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(l);
		p.add(l2);
		p.add(t);
		p.add(p2);
		
		Container c = getContentPane();
		c.add(p);
		
		setResizable(false);
		setVisible(true);
	}
	
	private void makeFrame() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg"); //������â�� ������ �ֱ�
		setIconImage(img);
		setTitle("�ְ��ϰ���");
		setSize(300, 230);
		setLocationRelativeTo(null); //ȭ�� �߾ӿ� ����
	}
}
