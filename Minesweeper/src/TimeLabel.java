import javax.swing.JLabel;

public class TimeLabel implements Runnable
{
	private Thread t;
	public JLabel label;
	StopWatch sw;
	TimeLabel(StopWatch stp)
	{
		sw=stp;
		label=new JLabel();
	}
	public void run()
	{
		try
		{
			while(sw.checkOn()==true)
			{
				int t=sw.getSec();
				String secString=Integer.toString(t);
				label.setText(secString+"��");
				Thread.sleep(50);
			}
		}
		catch(InterruptedException e) {}
	}
	public void start()
	{
		if(t==null)
		{
			t=new Thread(this);
			t.start();
		}
	}
}