package com.lib.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Danny.Huo
 * @version 1.0
 *
 */
public class SetUitl {
	
	/**
	 * Get the union of the two set
	 * @param merge Will merged to this set
	 * @param beMerge The being merged set
	 * @return {Set<E>}
	 * The return will be the parameter merge set
	 */
	public static <E> Set<E> merge(Set<E> merge, Set<E> beMerge){
		Set<E> retSet = new HashSet<E>(merge);
		if(null != beMerge){
			Iterator<E> iter = beMerge.iterator();
			while(iter.hasNext()){
				retSet.add(iter.next());
			}
		}
		return retSet;
	}
	
	/**
	 * 取两个Set的差集<br>
	 * 在参数一中存在，在参数二中不存在的<br>
	 * @param subtraction 减集
	 * @param beSubtraction 被减集
	 * @return {Set<E>}
	 */
	public static <E> Set<E> subtraction(Set<E> subtraction, Set<E> beSubtraction){
		Set<E> retSet = null;
		if(null != subtraction && null != beSubtraction){
			retSet = new HashSet<E>();
			Iterator<E> iter = subtraction.iterator();
			while(iter.hasNext()){
				E e = iter.next();
				if(!beSubtraction.contains(e)){
					retSet.add(e);
				}
			}
		}
		return retSet;
	} 
	
	/**
	 * 取两个Set的交集
	 * @param intersection
	 * @param beIntersection
	 * @return{Set<E>}
	 */
	public static <E> Set<E> intersection(Set<E> intersection, Set<E> beIntersection){
		Set<E> retSet = null;
		if(null != intersection && null != beIntersection){
			retSet = new HashSet<E>();
			Iterator<E> iter = beIntersection.iterator();
			while(iter.hasNext()){
				E e = iter.next();
				if(intersection.contains(e)){
					retSet.add(e);
				}
			}
		}
		return retSet;
	}
	
}
