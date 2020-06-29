import java.util.*;
import java.lang.*;

public class Genome {
	
	String all="ABCDEFGHIJKLMNOPQRSTUVWXYZ \'";
	String target = "PAULO SERGIO LICCIARDI MESSEDER BARRETO";
	
	double MutationRate;
	StringBuilder content=new StringBuilder(16);
	Genome copy;
	Random rand = new Random();
	
	// Initialize the Genome
	public Genome (double MutationRate){
		this.MutationRate=MutationRate;
		this.content.append("A");
	}

	public Genome (Genome gene){
		this.content.append(gene.content);	
	}
	
	// This is where mutation happens, there are 3 ways of mutations
	// Insert, Delete or Change the val
	void mutate() {
		double Mutation=Math.random();
		int length=this.content.length();
		
		if (Mutation<=0.05) {
			int x=rand.nextInt(28);
			int pos=rand.nextInt(length);
			this.content.insert(pos,all.charAt(x));
			length+=1;
		}
		
		if (length>=2) {
			Mutation=Math.random();
			if (Mutation<=0.05) {
				int pos=rand.nextInt(length);
				this.content.deleteCharAt(pos);
				length-=1;
			}	
		}
		
		for (int i=0; i<length; i++) {
			Mutation=Math.random();
			if (Mutation<=0.05) {
				int x=rand.nextInt(28);
				this.content.setCharAt(i,all.charAt(x));
			}
		}
	}

	// This is where cross over happens
	void crossover(Genome other) {	
		StringBuilder cross=new StringBuilder(16);
		int length1=this.content.length();
		int length2=other.content.length();
		for (int i=0; i<Math.max(length1,length2);i++){
			if (rand.nextBoolean()){
				if (i<length1){
					cross.append(this.content.charAt(i));				
				}else {
					break;
				}
			}else {
				if (i<length2) {
					cross.append(other.content.charAt(i));	
				}else {
					break;
				}
			}
		}
		this.content=cross;	
	}

	// Calculate the fitness rate
	Integer fitness() {
		int n=this.content.length();
		int m=target.length();
		int L=Math.max(n,m);
		int f=Math.abs(m-n);
		
		for (int i=0; i<L; i++){
			if (i==n) {
				f+=L-n;
				break;
			}
			else if (i==m) {
				f+=L-m;
				break;
			}else {
				if (this.content.charAt(i)!=(target.charAt(i))){
					f+=1;
				}
			}
		}
		return f;
	}
	
	public String toString() {
		String whole=this.content.toString()+" "+this.fitness();
		return whole;
	}
}

