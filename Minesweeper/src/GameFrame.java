import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

public class GameFrame extends JFrame {	//������ �ϴ� ����������
	private JButton[][] button;
	private CustomFrame cf;
	private ButtonClickListener bcl;
	private int[][] xy;
	
	public GameFrame(int x, int y, int mine) {
		bcl = new ButtonClickListener();
		
		makeMap(x,y,mine);
		makeFrame();
		makeMenuBar();
		makeBoard(x, y);
		pack();
		setResizable(false);
		setVisible(true);
		
	}
	private void makeFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("data/icon.jpg"); //������ ��ܿ� ������ �ֱ�
		setIconImage(img);
		setTitle("����ã��");
		setLocationRelativeTo(null); //�������� �ʱ���ġ�� ȭ�� �߾����� �Ѵ�.
	}
	private void makeMenuBar() { //�޴��� �����
		JMenuBar mb = new JMenuBar();
		
		JMenuItem[] item = new JMenuItem[9];
		item[0] = new JMenuItem("�� ����");
		item[1] = new JMenuItem("�ʱ�");
		item[2] = new JMenuItem("�߱�");
		item[3] = new JMenuItem("���");
		item[4] = new JMenuItem("����� ����");
		item[5] = new JCheckBoxMenuItem("�Ҹ�", true);
		item[6] = new JMenuItem("�ְ���");
		
		item[7] = new JMenuItem("���� ���");
		item[8] = new JMenuItem("������");
		
		JMenu menu = new JMenu("����");
		menu.add(item[0]);
		menu.addSeparator();
		menu.add(item[1]);
		menu.add(item[2]);
		menu.add(item[3]);
		menu.add(item[4]);
		menu.addSeparator();
		menu.add(item[5]);
		menu.addSeparator();
		menu.add(item[6]);
		
		JMenu menu2 = new JMenu("����");
		menu2.add(item[7]);
		menu2.add(item[8]);
		
		menuBarMouseAction(item);
		
		mb.add(menu);
		mb.add(menu2);
		mb.add(new JMenu("�¶���"));
		setJMenuBar(mb);
	}
	private void menuBarMouseAction(JMenuItem[] item) { //�޴����� �� ��ư�� Ŀ�ǵ� �Է�
		item[0].setActionCommand("reset");
		item[0].addActionListener(bcl);
		item[1].setActionCommand("beginner");
		item[1].addActionListener(bcl);
		item[2].setActionCommand("intermediate");
		item[2].addActionListener(bcl);
		item[3].setActionCommand("expert");
		item[3].addActionListener(bcl);
		item[4].setActionCommand("custom");
		item[4].addActionListener(bcl);
		item[5].setActionCommand("sound");
		item[5].addActionListener(bcl);
		item[6].setActionCommand("best");
		item[6].addActionListener(bcl);
		
		item[7].setActionCommand("how");
		item[7].addActionListener(bcl);
		item[8].setActionCommand("maker");
		item[8].addActionListener(bcl);
	}
	private void makeBoard(int x, int y) {
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout grid = new GridBagLayout();
		GridBagLayout grid2 = new GridBagLayout();
		
		JPanel mp = new JPanel();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		
		mp.setLayout(grid);
		
		p1.setLayout(new BorderLayout());
		p2.setLayout(new GridLayout(y, x));
		p3.setLayout(grid2);
		
		c.fill = GridBagConstraints.BOTH; //�׸���鷹�̾ƿ� ũ�Ⱑ ���� ���� ��� ���μ��� Ȯ��
		c.insets = new Insets(5, 5, 5, 5); //�׸���鷹�̾ƿ� ���� ��������
		
		JButton bLeft = new JButton("1");
		JButton resetButton = new JButton(new ImageIcon("data/yes.png"));
		
		StopWatch stopwatch=new StopWatch();
		TimeLabel tl=new TimeLabel(stopwatch);
		stopwatch.On();
		stopwatch.start();
		tl.start();
		JLabel time=tl.label;
		Font f1=new Font("����", Font.BOLD, 15);
		time.setFont(f1);

		bLeft.setPreferredSize(new Dimension(50, 30));
		resetButton.setPreferredSize(new Dimension(30, 30));
		time.setPreferredSize(new Dimension(50,30));
		time.setHorizontalAlignment(JLabel.RIGHT);
		
		resetButton.setActionCommand("reset");
		resetButton.addActionListener(bcl);
		p3.add(resetButton);
		
		p1.add("West", bLeft);
		p1.add("Center", p3);
		p1.add("East",time);
		
		addCom(mp, c, grid, p1, 0, 0, 1, 1);
		
		button = new JButton[y][x];
		ImageIcon icon;
		ImagePanel ipanel;
		
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				button[i][j] = new JButton();
//				button[i][j].setPreferredSize(new Dimension(20, 20)); //���� �� ���� ��ư ũ�� ����
				JButton b = button[i][j];
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						b.setEnabled(false);
						b.setVisible(false);
					}
				});
				
//				icon=new ImageIcon("data/1.png");
				icon=new ImageIcon("data/"+Integer.toString(xy[i][j])+".png");
				ipanel=new ImagePanel(icon.getImage());
				ipanel.setPreferredSize(new Dimension(20,20));
				ipanel.setLayout(new GridLayout(1,1));
//				ipanel.add(button[i][j]);
				p2.add(ipanel);
			}
		}
		
		addCom(mp, c, grid, p2, 0, 1, 1, 4);
		
		Container con = getContentPane();
		
		con.add(mp);
	}
	private void addCom(JPanel mp, GridBagConstraints c, GridBagLayout grid, Component com, int x, int y, int w, int h) { //�׸���鷹�̾ƿ� ���� �Լ�
		c.gridx = x;
		c.gridy = y;
		c.gridwidth = w;
		c.gridheight = h;
		grid.setConstraints(com, c);
		mp.add(com);
	}
	
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			if (command.equals("reset")) {
				dispose();
			}
			else if (command.equals("beginner")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write("9");
					out.newLine();
					out.write("9");
					out.newLine();
					out.write("10");
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dispose();
			}
			else if (command.equals("intermediate")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write("16");
					out.newLine();
					out.write("16");
					out.newLine();
					out.write("40");
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dispose();
			}
			else if (command.equals("expert")) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write("30");
					out.newLine();
					out.write("16");
					out.newLine();
					out.write("99");
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
				
				dispose();
			}
			else if (command.equals("custom")) {
				cf = new CustomFrame();
				cf.addWindowListener(new WinEvent()); //��������� �������� ���� �� �̺�Ʈ ����
			}
			else if (command.equals("sound")) {
				
			}
			else if (command.equals("best")) {
				new BestFrame();
			}
			else if (command.equals("how")) {
				new HowToPlayFrame();
			}
			else if (command.equals("maker")) {
				new InfoFrame();
			}
		}
	}
	
	private class WinEvent implements WindowListener {
		@Override
		public void windowClosed(WindowEvent e) { //â�� ������ ��
			if (cf.getX() != -1) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("data/size.txt"));
					
					out.write(Integer.toString(cf.getX()));
					out.newLine();
					out.write(Integer.toString(cf.getY()));
					out.newLine();
					out.write(Integer.toString(cf.getMine()));
					out.newLine();
					
					out.close();
				}
				catch (IOException ex) {
					System.exit(1);
				}
			}
			dispose();
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// ���� ����
		}

		@Override
		public void windowClosing(WindowEvent e) { //â�ݱ� ��ư�� ������ ��
			dispose();
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
	private void makeMap(int col, int row, int mineCnt)
	{
		//initializing
		xy=new int[row][col];
		for(int i=0 ; i<row ; i++)
		{
			for(int j=0 ; j<col ; j++) xy[i][j]=0;
		}
		//shuffle
		int[][] lst=new int[row*col][2];
		Random rand=new Random();
		int cnt=0;
		for(int i=0 ; i<row ; i++)
		{
			for(int j=0 ; j<col ; j++)
			{
				lst[cnt][0]=i;
				lst[cnt][1]=j;
				cnt++;
			}
		}
		for(int i=0 ; i<row*col ; i++)
		{
			int idx=rand.nextInt(row*col);
			int tmp=lst[i][0];
			lst[i][0]=lst[idx][0];
			lst[idx][0]=tmp;
			tmp=lst[i][1];
			lst[i][1]=lst[idx][1];
			lst[idx][1]=tmp;
		}
		for(int i=0 ; i<mineCnt ; i++)
		{
			xy[lst[i][0]][lst[i][1]]=-1;
		}
		//setting map
		int[] dy={-1,-1,-1,0,1,1,1,0};
		int[] dx={-1,0,1,1,1,0,-1,-1};
		for(int y=0 ; y<row ; y++)
		{
			for(int x=0 ; x<col ; x++)
			{
				if(xy[y][x]==-1) continue;
				for(int i=0 ; i<8 ; i++)
				{
					if(y+dy[i]<0 || y+dy[i]>=row || x+dx[i]<0 || x+dx[i]>=col) continue;
					if(xy[y+dy[i]][x+dx[i]]==-1) xy[y][x]++;
				}
			}
		}		
	}
}