/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Dictionary {
	HashMap<String, List<Definition>> dictionary;
		
	public Dictionary() {
		this.dictionary=new HashMap<String, List<Definition>>();	
	}
	
	public void addTerm(Term t) {
		dictionary.put(t.getKey(), t.getDefinitions());
	}
	
	public Term getTerm(String key)throws InvalidKey{
		if(exists(key)){
			return new Term(key, dictionary.get(key));
		}
		else throw new InvalidKey();
	}
	
	public boolean exists(String key) {
		return dictionary.containsKey(key);
	}
	
	public Set<String> getKeys(){
		return dictionary.keySet();
	}
	
	public long getSize() {
		return dictionary.size();
	}
	
}
