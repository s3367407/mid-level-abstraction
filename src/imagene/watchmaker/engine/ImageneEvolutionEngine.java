package imagene.watchmaker.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.uncommons.watchmaker.framework.AbstractEvolutionEngine;
import org.uncommons.watchmaker.framework.EvaluatedCandidate;

import imagene.watchmaker.gp.tree.TreeFactory;

public class ImageneEvolutionEngine<T> extends AbstractEvolutionEngine<T> {
	private final double WinScore = 1d, LossScore = 0d;
	private List<T> _population;
	private List<EvaluatedCandidate<T>> _evaluatedCandidates;
	private TreeFactory _factory;
	private Random _rng;
	private int _populationSize;
	
	
	public ImageneEvolutionEngine(final int populationSize, TreeFactory factory, Random rng) 
	{
		super(null, null, null);
		_populationSize = populationSize;
		_rng = rng;
		
		_population = GenerateInitialPopulation();
	}

	@Override
	protected List<EvaluatedCandidate<T>> nextEvolutionStep(List<EvaluatedCandidate<T>> arg0, int arg1, Random arg2) 
	{
		return null;
	}
	
	public List<T> getPopulation()
	{
		return _population;
	}
	
	public void evolve()
	{
		_population = Evaluate();		
	}
	
	private List<T> Evaluate()
	{
		List<T> newPopulation = new ArrayList<T>();
		
		for(EvaluatedCandidate<T> t : _evaluatedCandidates)
		{
			if(t.getFitness() > 0d)
				newPopulation.add(t.getCandidate());
			else
				newPopulation.add((T) _factory.generateRandomCandidate(_rng));
		}
		
		return newPopulation;
	}
	
	private List<T> GenerateInitialPopulation()
	{
		List<T> population = new ArrayList<T>();
		for(int i = 0; i < _populationSize; i++)
		{
			population.add((T) _factory.generateRandomCandidate(_rng));
		}
		
		return population;
	}
	
	public void survive(List<Integer> winners)
	{			
		for(int i = 0; i < _populationSize; i++)
		{
			if(winners.contains(i))
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(i), WinScore));
			else
				_evaluatedCandidates.add(new EvaluatedCandidate<T>(_population.get(i), LossScore));
		}
	}	
}
