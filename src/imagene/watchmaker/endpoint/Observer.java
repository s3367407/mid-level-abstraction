package imagene.watchmaker.endpoint;

import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

import imagene.watchmaker.gp.node.Node;

public class Observer implements EvolutionObserver<Node> {
	private PopulationData<? extends Node> CurrentPopulation;
	@Override
	public void populationUpdate(PopulationData<? extends Node> arg0) {
			CurrentPopulation = arg0;
	}

}
