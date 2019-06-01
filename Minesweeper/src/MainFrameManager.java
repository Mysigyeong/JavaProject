import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainFrameManager { //GameFrame(����������)�����ϴ� Ŭ����
	private GameFrame frame;
	private WinEvent w;
	private File file;
	private Scanner scn;
	
	public MainFrameManager() {
		int[] num = new int[5];
		w = new WinEvent();
		
		try {
			file = new File("data/size.txt"); //����Ǿ��ִ� ����� �ҷ��ͼ� ������������ �����.
			scn = new Scanner(file);
			
			for (int i = 0; i < 5; i++) {
				num[i] = Integer.parseInt(scn.nextLine());
			}
			
			scn.close();
		}
		catch (FileNotFoundException e) {
			System.exit(1);
		}
		catch (NumberFormatException ex) {
			System.exit(1);
		}
		
		frame = new GameFrame(num[0], num[1], num[2], num[3], num[4]);
		frame.addWindowListener(w);                    //���� ����â�� ���� ���� �̺�Ʈ �߰�
	}
	
	
	private class WinEvent implements WindowListener {
		@Override
		public void windowClosed(WindowEvent e) {
			int[] num = new int[5];
			
			try {
				file = new File("data/size.txt");
				scn = new Scanner(file);
				
				for (int i = 0; i < 5; i++) {
					num[i] = Integer.parseInt(scn.nextLine());
				}
				
				scn.close();
			}
			catch (FileNotFoundException exc) {
				System.exit(1);
			}
			catch (NumberFormatException ex) {
				System.exit(1);
			}
			
			frame = new GameFrame(num[0], num[1], num[2], num[3], num[4]);
			frame.addWindowListener(w);
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// ���� ����
		}

		@Override
		public void windowClosing(WindowEvent e) { //â �ݱ� ��ư�� ������ ���� ���� â�� ������ ��쿡�� ���α׷��� ������ �����Ų��.
			System.exit(0);                        //�� �̿��� ������� ���� ����â�� ������ ��쿡�� ������ ������ �ϴ� ���� �����Ͽ� �ٽ� ���� �����.
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// ���� ����
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// ���� ����
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// ���� ����
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// ���� ����
		}
	}
}

