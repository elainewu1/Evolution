import java.util.*;
import java.lang.*;

public class Genome {
	
	String all="ABCDEFGHIJKLMNOPQRSTUVWXYZ \'";
	String target = "PAULO SERGIO LICCIARDI MESSEDER BARRETO";
	
	double MutationRate;
	StringBuilder content=new StringBuilder(16);
	Genome copy;
	Random rand = new Random();
	
	
	public Genome (double MutationRate){
		this.MutationRate=MutationRate;
		this.content.append("A");
	
	}

	public Genome (Genome gene){
		this.content.append(gene.content);	
	}

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
	
	/*Integer fitness() {
		int n=this.content.length();
		int m=target.length();
		int[] D = new int[n+1,m+1];
		for (int i=1; i<n;i++) {
			for (int j=1; j<m; j++) {
				if (this.content.charAt(i-1)==target.charAt(j-1)) {
					D[i][j]=D[i-1][j-1];
					
				}else {
					D[i][j]=min(D[i-1][j]+1,D[i][j-1]+1, D[i-1][j-1]+1);
				}
			}
		}
		return D[i][j]=
		
	}*/

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

