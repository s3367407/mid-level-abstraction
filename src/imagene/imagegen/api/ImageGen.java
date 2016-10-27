package imagene.imagegen.api;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.Pixel;
import imagene.imagegen.models.PixelMatrix;
import imagene.imagegen.Timer;
import imagene.imagegen.api.interfaces.IGenInterface;

public class ImageGen implements IGenInterface
{
	
	public ImageGen()
	{
		
	}
	
	@Override
	public PixelMatrix CreateImage(int x, int y, IManipulator m)
	{
		long startTime = Timer.start();
		PixelMatrix matrix = new PixelMatrix(x, y);
		double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		
		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				Pixel p = new Pixel(m.manipulate(dimX, dimY));
				if(p.magnitude() > max) max = p.magnitude();
				if(p.magnitude() < min) min = p.magnitude();
				matrix.set(dimY, dimX, p);
			}
		}
		matrix = ScaleImage(matrix, min, max);
		long totalTime = Timer.stop(startTime);
		
		System.out.println(" CREATED in "+totalTime+" milliseconds.");
		System.out.println("MIN is "+min+", MAX is "+max);
		return matrix;
	}
	
	@Override
	public PixelMatrix CreateImage(int x, int y, IManipulator[] m)
	{
		long startTime = Timer.start();
		PixelMatrix matrix = new PixelMatrix(x, y);
		double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
		
		for(int channel = 0; channel < 3; channel++)
		{
			for(int dimY = 0; dimY < y; dimY++)
			{
				for(int dimX = 0; dimX < x; dimX++)
				{
					Pixel p = new Pixel(m[channel].manipulate(dimX, dimY));
					if(p.magnitude() > max) max = p.magnitude();
					if(p.magnitude() < min) min = p.magnitude();
					matrix.set(dimY, dimX, p);
				}
			}
		}
		
		matrix = ScaleImage(matrix, min, max);
		long totalTime = Timer.stop(startTime);
		
		System.out.println(" CREATED in "+totalTime+" milliseconds.");
		System.out.println("MIN is "+min+", MAX is "+max);
		return matrix;
	}
	
	@Override
	public boolean Validate(int x, int y, IManipulator m)
	{
		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				try {
					m.manipulate(dimX, dimY);
				} catch(Exception ex) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private PixelMatrix ScaleImage(PixelMatrix m, double min, double max)
	{
		long startTime = Timer.start();
		int x = m.xSize(), y = m.ySize();
		
		for(int dimY = 0; dimY < y; dimY++)
		{
			for(int dimX = 0; dimX < x; dimX++)
			{
				m.set(dimY, dimX, new Pixel(ScalePixel(m.get(dimY, dimX).magnitude(), min, max)));
			}
		}
		
		long totalTime = Timer.stop(startTime);
		
		System.out.print("Image of size ["+x+"x"+y+"] SCALED in "+totalTime+" milliseconds,");
		return m;		
	}
	
	private short ScalePixel(double magnitude, double min, double max) {
		  return (short)Math.round(255 * (magnitude - min) / (max - min));
	}
}
