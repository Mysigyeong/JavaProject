public class StopWatch implements Runnable
{
	private Thread t;
	private boolean isOn;
	private long curTime, nxtTime;
	public int sec;
	StopWatch()
	{
		isOn=false;
		sec=0;
	}
	public void On() { isOn=true;}
	public boolean checkOn() {return isOn;}
	public int getSec() {return sec;}
	public void run()
	{
		try
		{
			curTime=System.currentTimeMillis();
			nxtTime=curTime+1000;
			while(isOn)
			{
				Thread.sleep(100);
				curTime=System.currentTimeMillis();
				if(curTime>=nxtTime)
				{
					sec++;
					nxtTime=curTime+1000;
				}
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
