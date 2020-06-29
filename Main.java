import java.util.*;
import java.lang.*;

public class Main {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		Population start=new Population(100, 0.05);
		int count=0;
		while (start.mostFit.fitness()!=0) {
			start.day();
			System.out.println(start.mostFit.toString());
			count+=1;	
		}
		System.out.println("The number of generation is "+count );
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime)/1000000000;
		System.out.println("Total execution time for evolution: "+totalTime);
	//	testGenome();
		

	}
	
	static void testGenome() {
		for (int i=0; i<10; i++) {
			Genome test= new Genome(1.00);
			System.out.println(test.content);
			test.content.append("hello");
			Genome copytest=new Genome (test);
			System.out.println(copytest.content);
			copytest.content.append("this is first try");
			test.mutate();
			System.out.println(test.content+" mutate");
			test.crossover(copytest);
			System.out.println(test.content+" cross over");
			int testfit=test.fitness();
			System.out.println(test.content.toString() + testfit);
			;
		}
	}
	
/*	static void testPopulation() {
		int i=0;
		while (i<10) {
			Population test=new Population(0,0.05);
			test.day();
			
			
		}
	}*/
}
