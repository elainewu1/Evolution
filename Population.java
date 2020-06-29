import java.util.*;

public class Population {
	
	int[] fit;
	double mutationR;
	Genome mostFit;
	List<Genome> list=new ArrayList<Genome>();

	Population(Integer numGenomes, Double mutationRate){
		mostFit= new Genome(mutationRate);
		mutationR=mutationRate;
		for (int i=0; i<numGenomes; i++) {
			Genome newG = new Genome(mutationRate);
			list.add(newG);
		}
	}
	
	// Each day, the population would change
	void day() {	
		Collections.sort(list, new Comparator<Genome>(){
			@Override
			public int compare(Genome one, Genome two) {
				return one.fitness().compareTo(two.fitness());
			}
		});
			
		//update the mostFit
		mostFit=list.get(0);
				
		Random rand=new Random();
		int x=rand.nextInt(list.size()/2);
		for (int i=list.size()/2-1; i<list.size();i++) {
			Genome temp1=new Genome(list.get(x));	
			if (rand.nextBoolean()){
				temp1.mutate();
			}else {
				int y=rand.nextInt(list.size()/2+1);
				while (x==y) {
					y=rand.nextInt(list.size()/2+1);
				}
				Genome temp2=new Genome(list.get(y));
				temp1.crossover(temp2);
				temp1.mutate();		
			}
			list.set(i, temp1);
			x=rand.nextInt(list.size()/2+1);	
		}		
	}
}