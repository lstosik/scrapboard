package net.purevirtual.scrapboard.algs.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Łukasz Stosik <lstosik@gmail.com>
 * @param <Value> 
 */
public class Trie<Value> implements Map<String, Value> {
	Node root = new Node();
	int size;

	public Trie() {
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Value get(Object key) {
		return get((String)key, root, 0);
	}
	
	public Value get(String key, Node<Value> node, int index) {
		if (index == key.length()) {
			return node.value;
		} else {
			Node nextNode = node.getChild(key.charAt(index));
			if (nextNode == null) {
				return null;
			}
			return get(key, nextNode, index+1);
		}
	}

	@Override
	public Value put(String key, Value value) {
		put(key, value, root, 0);
		return null;
	}
	
	public void put(String key, Value value, Node<Value> node, int index) {
		if (index == key.length()) {
			Object oldValue = node.value;
			node.value = value;
			//return  oldValue;
		} else {
			char curChar = key.charAt(index);
			Node nextNode = node.getChild(curChar);
			if (nextNode == null) {
				nextNode = new Node();
				node.setChild(curChar, nextNode);
			}
			put(key, value, nextNode, index+1);
		}
	}

	@Override
	public Value remove(Object key) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void putAll(Map<? extends String, ? extends Value> m) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Set<String> keySet() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Collection<Value> values() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Set<Entry<String, Value>> entrySet() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	private static class Node<Value> {

		Value value = null;
		Object[] children = new Object[256];

		public void setChild(int index, Node<Value> child) {
			children[index] = child;
		}
		
		public Node<Value> getChild(int index) {
			return (Node<Value>) children[index];
		}
		
		public int countChildrenNodes() {
			int sum = 0;
			for(int i=0;i<256;i++) {
				Node<Value> child = getChild(i);
				if(child != null) {
					sum += 1 + child.countChildrenNodes();
				}
			}
			return sum;
		}
	}
	
	public int countNodes() {
		return 1+root.countChildrenNodes();
	}
}
