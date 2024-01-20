package org.drozdek.lists.unlam;

public interface Lista {
	
	boolean push_back(Object dato);
	Object pop_back();
	boolean push_front(Object dato);
	Object pop_front();
	boolean remove(Object dato);
	boolean reverse();
	boolean insert(int pos, Object dato);
	boolean erase(int pos);
	boolean empty();
	int buscar(Object dato);
	Object buscar(int pos);
	void vaciar();

}
