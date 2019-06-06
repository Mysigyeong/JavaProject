import java.awt.*;
import javax.swing.*;

public class HowToPlayFrame extends JFrame { //���ӹ�� ������
	public HowToPlayFrame() {
		makeFrame();
		
		JLabel l = new JLabel("<html>"
				+ "����ã��� ������ ����ã������ ���ڸ� ������ �����ϱ� ������ �ȵǴ� �׷� �����Դϴ�.<br><br>"
				+ "���� ��ܿ� �ִ� ���ڴ� ���� ��ã�� ������ ������ �ǹ��ϰ�,<br>"
				+ "������ ��ܿ� �ִ� ���ڴ� �ɸ� �ð��� �ʴ����� �˷��ݴϴ�.<br><br>"
				+ "��Ŭ���� �ϸ� ���ڰ� ������, ��Ŭ���� �ϸ� ������ ���� ���� ���� ����� ǥ�õ˴ϴ�.<br>"
				+ "�����ΰ� ���� ���� ����� �����ø� �ǰڽ��ϴ�.<br>"
				+ "���ڸ� ������ �� �ٴڿ� �ִ� ���ڴ� ���ڰ� �����ִ� ������ �ֺ� 8ĭ�߿� �ִ� ������ ������ �ǹ��մϴ�.<br>"
				+ "�������, ���ڰ� 2�� ������ �ֺ� 8ĭ �߿� ���ڰ� �ΰ��� �ִٴ� ���� �ǹ��մϴ�.<br><br>"
				+ "�׷��� �� ���ڸ� ���� �Ͽ� ������ ���� ����� ����, ���ڰ� �ƴ� ���� ��Ŭ���� �Ͽ� ���� �ǰڽ��ϴ�.<br>"
				+ "���ڸ� �ļ� ������ ������ ���� �ǰ�, ���ڰ� �ƴ� ������ ������ ��� ���� ���ӿ��� �¸��ϰ� �˴ϴ�.<br><br>"
				+ "�ϴٺ��� ������ ���� �� ���� ���� �� �ִµ� �װ� �׳� ��������.<br>"
				+ "����� ���ϴ�.</html>");
		
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		p.add(l);
		
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
		setTitle("���ӹ��");
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width/3, screenSize.height/3);
	}
}
