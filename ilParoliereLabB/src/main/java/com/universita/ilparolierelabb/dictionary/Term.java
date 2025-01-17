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

import java.util.ArrayList;
import java.util.List;

public class Term{
	private String key="";
	private List<Definition> definitionSet;
	
	public Term(String key) {
		super();
		this.key = key;
		this.definitionSet=new ArrayList<Definition>();
	}
	
	public Term(String key, List<Definition> definitionSet) {
		super();
		this.key = key;
		this.definitionSet=definitionSet;
	}

	public String getKey() {
		return key;
	}

	public List<Definition> getDefinitions(){
		return definitionSet;
	}
	
	public void addDefinition(Definition d){
		definitionSet.add(d);
	}
	
	public String toString() {
		String ret=""+key+":\n";
		for(Definition d:definitionSet) {
			ret=ret+d+"\n";
		}
		return ret;
	}
}
