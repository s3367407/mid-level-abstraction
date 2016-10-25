package imagene.imagegen.models;

import imagene.imagegen.polar.models.Coordinate;

public class PixelMatrix 
{
	private Pixel[][] matrix;
	
	public PixelMatrix(int xDim, int yDim)
	{
		matrix = new Pixel[yDim][xDim];
		set(new Pixel(0, 0, 0));
	}
	
	public PixelMatrix(Pixel p, int size) 
	{ 
		matrix = new Pixel[size][size]; 
		set(p);
	}
	
	public PixelMatrix() { }
	public PixelMatrix(PixelMatrix m) {	set(m); }
	public PixelMatrix(Pixel[][] m)	{ set(m); }

	public Pixel[][] get()
	{
		return this.matrix;
	}
	
	public Pixel get(int y, int x)
	{
		return this.matrix[y][x];
	}
	
	public void set(int y, int x, Pixel p)
	{
		matrix[y][x] = p;
	}
	
	public void set(Pixel[][] m)
	{
		matrix = m;
	}
	
	public void set(PixelMatrix m)
	{
		matrix = m.get();
	}
	
	public void set(Pixel p)
	{
		for(int i = 0; i < matrix[0].length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				matrix[j][i] = p;
			}
		}
	}
	
	public void print()
	{
		for(int i = 0; i < matrix[0].length; i++)
		{
			for(int j = 0; j < matrix.length; j++)
			{
				System.out.print(matrix[j][i].ToString());
			}
			System.out.println();
		}
	}
	
	public int xSize()
	{
		return matrix[0].length; 
	}
	
	public int ySize()
	{
		return matrix.length;
	}
	
	public byte[] getByteArray()
	{
		byte[] bArray = new byte[getSize()*3];
		
		for(int i = 0; i < matrix[0].length - 1; i++)
		{
			for(int j = 0; j < matrix.length - 1; j++)
			{
				bArray[(3 * (i + j))] = (byte) matrix[i][j].r();
				bArray[(3 * (i + j)) + 1] = (byte) matrix[i][j].g();
				bArray[(3 * (i + j)) + 2] = (byte) matrix[i][j].b();
			}
		}
		
		return bArray;
	}
	
	public int[] getIntArray()
	{
		int i = 0,	width = matrix[0].length, height = matrix.length;
		int[] iArray = new int[width*height];
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				iArray[i++] = getRGB(matrix[y][x].r(), matrix[y][x].g(), matrix[y][x].b());
			}
		}
		
		return iArray;
	}
	
	//TODO this is wrong. :c
	public int[] getPolarArray()
	{
		int  width = matrix[0].length, height = matrix.length;
		int[][] pArray = new int[height][width];
		
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				Coordinate c = getPolarCoordFromCartesian(y, x, height);
				try {
					pArray[c.y][c.x] = getRGB(matrix[y][x].r(), matrix[y][x].g(), matrix[y][x].b());
				} catch(Exception ex) {  }
			}
		}
		
		return dimensionalConversion(pArray);
	}
	
	//TODO Change this
	private int[] dimensionalConversion (int[][] array2D)
	{
		int[] array = new int[array2D.length*array2D[0].length];
		int i = 0;
	    for (int y= 0; y < array2D.length; y++) {
	        for (int x = 0; x < array2D[i].length; x++) {
	        	try {
	        		array[i] = array2D[y][x];
	        	} catch (Exception ex) {
	        		System.err.println("["+y+","+x+"] = "+i);
	        	}
	            i++;
	        }
	    } 
	    return array;
	}
	
	private Coordinate getPolarCoordFromCartesian(int y, int x, int height)
	{
		double r, q;
		
		r = Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
		try {
			q = Math.toDegrees(Math.atan2((double)y,(double)x));
		} catch(Exception e) {
			q = 0;
		}
		
		return new Coordinate((int)r, scale(q, height));
	}
	
	private int scale(double magnitude, double max) {
		  return (int)Math.round(max * magnitude/max);
	}
	
	public int getSize()
	{
		return matrix[0].length * matrix.length;
	}
	
	private int getRGB(int r, int g, int b)
	{
		int rgb = b;
		rgb = (rgb << 16) + r;
		rgb = (rgb << 8) + g;
		
		return rgb;
	}
}
