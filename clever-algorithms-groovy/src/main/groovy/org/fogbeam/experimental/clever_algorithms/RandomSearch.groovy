package org.fogbeam.experimental.clever_algorithms

class RandomSearch 
{

	static main(args) 
	{
		
		// problem configuration
		int problemSize = 2;
		def searchSpace = [ [-5, 5], [-5, 5] ];
		
		int maxIterations = 5000000;
		
		def best = search( searchSpace, maxIterations );
		
		println "Best: cost = ${best['cost']}, vector = ${best['vector']}";
	
		println "done";
			
	}

	def static search( def searchSpace, int maxIterations )
	{
		def best = null;
		
		1.upto( maxIterations )
		{
			def candidate = [:]; 
			candidate['vector'] = randomVector( searchSpace );
			
			// println "Got random vector: ${candidate['vector']}";
			
			candidate['cost'] = objectiveFunction( candidate['vector']);
			
			// println "Got cost as ${candidate['cost']}";
			
			if( best == null || candidate['cost'] < best['cost'])
			{
				best = candidate;
			}
		}
		
		return best;
	}
	
	public def static randomVector( def searchSpace )
	{
		// println "randomVector called!";
		// println "searchSpace = ${searchSpace}"
		def vec = new float[2];
		
		for( int i = 0; i < searchSpace.size(); i++ )
		{	
			vec[i] = searchSpace[i][0] + ( ( searchSpace[i][1] - searchSpace[i][0] ) * Math.random() ); 
		}
		
		return vec;
	}
	
	public def static objectiveFunction( def candidate )
	{
		def sumSq = candidate.collect { it ** 2 }.sum()
		
		return sumSq;
	}
}
