package imagene.viewmodel;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import imagene.arithmeticParser.ParserInterface;
import imagene.arithmeticParser.parserExceptions.IncorrectVariablesException;
import imagene.arithmeticParser.parserExceptions.InvalidArgumentException;
import imagene.arithmeticParser.parserNodes.ArithmeticNode;
import imagene.imagegen.api.ImageGen;
import imagene.imagegen.api.interfaces.IGenInterface;
import imagene.imagegen.manipulator.interfaces.IManipulator;
import imagene.imagegen.models.PixelMatrix;
import imagene.watchmaker.endpoint.Watchmaker;
import imagene.watchmaker.gp.node.Node;

public class ImageneViewModel 
{
	private final int PopulationSize;
	
	private IGenInterface imageGen;
	private ParserInterface parser;
	private Watchmaker<Node> watchmaker;
	
	public ImageneViewModel(final int populationSize)
	{
		PopulationSize = populationSize;
		imageGen = new ImageGen();
		parser = ParserInterface.getInstance();
		watchmaker = new Watchmaker<Node>(PopulationSize);
	}
	
	public List<Image> getPopulation(int width, int height) throws InvalidArgumentException, IncorrectVariablesException
	{
		List<ArithmeticNode> formulas = new ArrayList<ArithmeticNode>();
		List<PixelMatrix> matrices = new ArrayList<PixelMatrix>();
		List<Image> images = new ArrayList<Image>();

		for(Node n : watchmaker.getPopulation())
		{
			formulas.add(parser.getArithmetic(n.toString()));
		}
		
		for(int i = 0; i < formulas.size() / 3 ; )
		{
			matrices.add(imageGen.CreateImage(width, height, channelify(formulas.get(i++), formulas.get(i++), formulas.get(i++))));
		}
		
		for(PixelMatrix m : matrices)
		{
			images.add(makeImage(width, height, m));
		}
		
		return images;
	}
	
	private IManipulator[] channelify(ArithmeticNode r, ArithmeticNode g, ArithmeticNode b)
	{
		return new IManipulator[] { 
				(x, y) -> r.operation(x, y), 
				(x, y) -> g.operation(x, y), 
				(x, y) -> b.operation(x, y) 
		};
	}
	
	public static BufferedImage makeImage(int width, int height, PixelMatrix m)
	{
		int[] data = m.getIntArray();
	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    System.out.println("WIDTH: " + width + ", HEIGHT: "+ height);
	    image.setRGB(0, 0, width, height, data, 0, width);
	    
	    return image;
	}
	
	
	public List<Image> getPopulation(int dimensions) throws InvalidArgumentException, IncorrectVariablesException
	{
		return getPopulation(dimensions, dimensions);
	}
	
	public void chooseWinners(int[] winners)
	{
		watchmaker.chooseWinners(winners);
	}
	
	public void chooseWinners(List<Integer> winners)
	{
		watchmaker.chooseWinners(winners);
	}
	
	public void newGeneration()
	{
		watchmaker.Evolve();
	}
}
