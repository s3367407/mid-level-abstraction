package imagene.imagegen;

import java.util.Calendar;

public class Timer 
{
	public static long start()
	{
		return Calendar.getInstance().getTimeInMillis();
	}
	
	public static long stop(long start)
	{
		return Calendar.getInstance().getTimeInMillis() - start;
	}
}
