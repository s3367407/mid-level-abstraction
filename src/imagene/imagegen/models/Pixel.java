package imagene.imagegen.models;

public class Pixel
{
	private short r, g, b;
	private double magnitude;
	
	public Pixel(int r, int g, int b, double m)
	{
		this((short)r, (short)g, (short)b);
		magnitude = m;
	}
	
	public Pixel(int r, int g, int b)
	{
		this((short)r, (short)g, (short)b);
	}
	
	public Pixel(double r, double g, double b, double m)
	{
		this((short)Math.round(r), (short)Math.round(g), (short)Math.round(b));
		magnitude = m;
	}
	
	public Pixel(double r, double g, double b)
	{
		this((short)Math.round(r), (short)Math.round(g), (short)Math.round(b));
	}
	
	public Pixel(short r, short g, short b)
	{
		this.r = set(r);
		this.g = set(g);
		this.b = set(b);	
	}
	
	public Pixel(short i)
	{
		this.r = this.g = this.b = set(i);
	}
	
	public Pixel(double i)
	{
		this.magnitude = i;
	}

	public short r() { return this.r; }
	public short g() { return this.g; }
	public short b() { return this.b; }
	public double magnitude() { return this.magnitude; }
	
	public String ToString()
	{
		return String.format("[%3d,", this.r) + String.format("%3d,", this.g) + String.format("%3d]", this.b);
	}
	
	public short set(short a)
	{
		short result;
		if(a % 255 == 0)
			result = (short) 0;
		else
			result = (short) (a % 255);
		
		if(result < 0) result += 255;
		
		return result;
	}
	
	public short set(double a)
	{
		return (short)Math.round(a);
	}
}
