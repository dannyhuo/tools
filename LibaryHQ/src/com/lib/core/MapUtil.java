package com.lib.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * @author Danny.Huo
 *
 */
public class MapUtil {
	
	/**
	 * 取两个map的并集
	 * @param union
	 * @param beUnion
	 * @return
	 */
	public static <K, V> Map<K, V> union(Map<K, V> union, Map<K, V> beUnion){
		Map<K, V> retMap = null;
		if(null != beUnion){
			retMap = new HashMap<K, V>(beUnion);
			if(null != union){
				Iterator<K> iterator = union.keySet().iterator();
				while(iterator.hasNext()){
					K key = iterator.next();
					retMap.put(key, union.get(key));
				}
			}
		}
		return retMap;
	}
	
	/**
	 * 取两个map的差集<br>
	 * 在参数一中存在，在参数二中不存在的<br>
	 * @param union
	 * @param beUnion
	 * @return
	 */
	public static <K, V> Map<K, V> subtraction(Map<K, V> union, Map<K, V> beUnion){
		Map<K, V> retMap = null;
		if(null != union && null != beUnion){
			retMap = new HashMap<K, V>();
			Iterator<K> iterator = union.keySet().iterator();
			while(iterator.hasNext()){
				K key = iterator.next();
				if(!beUnion.containsKey(key)){
					retMap.put(key, union.get(key));
				}
			}
		}
		return retMap;
	}
	
	/**
	 * 取两个map的交集
	 * @param union
	 * @param beUnion
	 * @return
	 */
	public static <K, V> Map<K, V> intersection(Map<K, V> union, Map<K, V> beUnion){
		Map<K, V> retMap = null;
		if(null != union && null != beUnion){
			retMap = new HashMap<K, V>();
			Iterator<K> iterator = beUnion.keySet().iterator();
			while(iterator.hasNext()){
				K key = iterator.next();
				if(union.containsKey(key)){
					retMap.put(key, beUnion.get(key));
				}
			}
		}
		return retMap;
	}
	
	/**
	 * 将一个map中的key和value对换
	 * @param map
	 * @return
	 */
	public static <K, V> Map<V, K> exchange(Map<K, V> map){
		Map<V, K> retMap = null;
		if(null != map && map.size() > 0){
			retMap = new HashMap<V, K>();
			Iterator<K> iter = map.keySet().iterator();
			while(iter.hasNext()){
				K key = iter.next();
				retMap.put(map.get(key), key);
			}
		}
		return retMap;
	}
}
