package imagene.imagegen.api.interfaces;

import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;

public interface IGenInterface 
{
	public PixelMatrix CreateImage(int x, int y, IManipulator m);
}
