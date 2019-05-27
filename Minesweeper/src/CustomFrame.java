import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CustomFrame extends JFrame { //��������� ������
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private int x = -1;
	private int y = -1;
	private int numOfMine = -1;
	
	public CustomFrame() {
		makeFrame();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 3, 3, 3));
		p.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		
		JLabel l1 = new JLabel("����(9 ~ 30)");
		JLabel l2 = new JLabel("����(9 ~ 24)");
		JLabel l3 = new JLabel("����(10 ~ 667)");
		t1 = new JTextField(5);
		t2 = new JTextField(5);
		t3 = new JTextField(5);
		JButton b1 = new JButton("����");
		JButton b2 = new JButton("���");
		
		b1.setActionCommand("setting");
		b1.addActionListener(new ButtonClickListener());
		b2.setActionCommand("close");
		b2.addActionListener(new ButtonClickListener());
		
		p.add(l1);
		p.add(t1);
		p.add(b1);
		p.add(l2);
		p.add(t2);
		p.add(b2);
		p.add(l3);
		p.add(t3);
		
		Container c = getContentPane();
		c.add(p);
		
		pack();
		setVisible(true);
		setResizable(false);
	}
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg");
		setIconImage(img);
		setTitle("����� ����");
		setLocationRelativeTo(null);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.equals("setting")) { //������ư ������ ��
				int tempX = 0;
				int tempY = 0;
				int tempMine = 0;
				
				try {
					tempX = Integer.parseInt(t1.getText());
					tempY = Integer.parseInt(t2.getText());
					tempMine = Integer.parseInt(t3.getText());
				}
				catch (NumberFormatException ex) { //���� �ƴ� ���ڰ� ������ ��� �׳� â�� �ݴ´�.
					dispose();
				}
				
				if (tempX > 30) { //���ΰ��� 9~30, ���ΰ��� 9~24, ���ڰ����� 10~667 ���� �ʰ��ϸ� �ִ� �Ǵ� �ּڰ��� �Է��Ѵ�.
					tempX = 30;
				}
				else if (tempX < 9) {
					tempX = 9;
				}
				
				if (tempY > 24) {
					tempY = 24;
				}
				else if (tempY < 9) {
					tempY = 9;
				}
				
				if (tempMine < 10) {
					tempMine = 10;
				}
				else if (tempMine > 667) {
					tempMine = 667;
				}
				
				x = tempX;
				y = tempY;
				numOfMine = tempMine;
				
				dispose();
			}
			else if (command.equals("close")) {
				dispose();
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getMine() {
		return numOfMine;
	}
}
