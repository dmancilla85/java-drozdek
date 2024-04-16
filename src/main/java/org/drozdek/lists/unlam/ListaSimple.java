package org.drozdek.lists.unlam;

//import static java.lang.Math.random;
//import static java.lang.System.out;

public class ListaSimple implements Lista {

    private NodoLista front, back;
    private int length;
    public ListaSimple() {
        front = back = null;
        length = 0;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Lista l1 = new ListaSimple();
		/*out.println("Esta vacia?: " + l1.estaVacia());

		l1.insertar("A");
		l1.insertar("B");
		l1.insertar("C");

		int i = 0, n = 500;
		while(i++ < n)
			l1.insertar(random() * 100 * i/3);

		l1.insertar("Z");

		i = 1;
		Object valor;
		while(!l1.estaVacia() && i <= n + 4){
			valor = l1.buscar(i++);
			out.println(valor);
		}

		l1.eliminar("Z");
		l1.eliminar("A");
		l1.eliminar("B");
		l1.eliminar("C");
		l1.insertar("THIS IS THE END");
		i = 1;

		out.println("------------");
		out.println("Prueba de eliminar");
		while(i <= n + 1){
			valor = l1.buscar(i++);
			out.println(valor);
		*/

    }

    @Override
    public int buscar(Object dato) {
        // TODO Auto-generated method stub
        return 0;
    }

	
	/*public boolean insertar(Object obj) {
		// TODO Auto-generated method stub
		try {
			NodoLista aux = new NodoLista(obj, lista);
			lista = aux;
		} catch(Exception e){
			out.println(e.getMessage());
			return false;
		}
		return true;
	}*/


	/*public boolean eliminar(Object obj) {
		// TODO Auto-generated method stub
		NodoLista iter =  lista, aux;
		
		if(lista.dato.equals(obj)){
			lista = iter.sig;
			return true;
		}
		
		while(iter.sig != null){
			if(iter.sig.dato.equals(obj)){
				aux = iter.sig;
				iter.sig = aux.sig;
				return true;
			}
			iter = iter.sig;
		}
		
		return false;
	}*/

	/*@Override
	public void vaciar() {
		// TODO Auto-generated method stub
		lista = null;
		
	}*/

	/*@Override
	public boolean estaVacia() {
		// TODO Auto-generated method stub
		return lista == null;
	}*/

	/*@Override
	public Object buscar(long index) {
		// TODO Auto-generated method stub
		long i = 0;
		NodoLista iter = lista;
		
		if(estaVacia())
			return null;
		
		while(i++ < index - 1 && iter.sig != null){
			iter = iter.sig;
		}
		
		if(i == index)
			return iter.dato;
		else
			return null;
	}*/

    @Override
    public Object buscar(int pos) {
        // TODO Auto-generated method stub
        int i = 0;
        boolean existe = false;
        NodoLista act = front;

        while (i < length && !existe && act != null) {
            act = act.sig;

            if (i == pos - 1 && act != null)
                existe = true;
        }

        if (existe)
            return act.dato;
        return null;
    }

    @Override
    public boolean empty() {
        // TODO Auto-generated method stub
        return front == null && back == null;
    }

    @Override
    public boolean erase(int pos) {
        // TODO Auto-generated method stub
        int i = 0;
        boolean finded = false;
        NodoLista act = front, ant = null;

        while (i < length && !finded && act != null) {
            act = act.sig;
            ant = act;

            if (i == pos - 1 && act != null)
                finded = true;
        }

        if (finded) {
            if (ant != null)
                ant.sig = act.sig;
            front = act.sig;
            act = null;

            return true;
        }

        return false;
    }

    @Override
    public boolean insert(int pos, Object dato) {
        // TODO Auto-generated method stub

        int i = 0;
        boolean finded = false;
        NodoLista act = front;

        while (i < length && !finded && act != null) {
            act = act.sig;

            if (i == pos - 1 && act != null)
                finded = true;
        }

        if (act == null) {
            act = new NodoLista(dato);
            return true;
        }

        if (finded) {
            act.sig = new NodoLista(dato, act.sig);
            return true;
        }

        return false;
    }

    @Override
    public Object pop_back() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object pop_front() {
        // TODO Auto-generated method stub

        Object aux = null;

        if (front != null) {
            aux = front.dato;
            NodoLista aux2 = front.sig;
            front = aux2;
        }

        return aux;
    }

    @Override
    public boolean push_back(Object dato) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean push_front(Object dato) {
        // TODO Auto-generated method stub

        if (dato != null) {
            if (front == null)
                front = new NodoLista(dato, back);
            else {
                NodoLista aux = front;
                front = new NodoLista(dato, aux);
            }

            length++;
        }
        return dato != null && front != null;
    }

    @Override
    public boolean remove(Object dato) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean reverse() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void vaciar() {
        // TODO Auto-generated method stub
        front = back = null;
    }

    class NodoLista {
        private final Object dato;
        private NodoLista sig;

        public NodoLista(Object obj) {
            dato = obj;
            sig = null;
        }

        public NodoLista(Object obj, NodoLista nl) {
            dato = obj;
            sig = nl;
        }
    }

}
