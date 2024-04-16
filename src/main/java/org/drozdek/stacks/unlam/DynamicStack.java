package org.drozdek.stacks.unlam;

import static java.lang.System.out;

public class DynamicStack implements SimpleStack {

    private StackNode node;

    public DynamicStack() {
        this.node = null;
    }

    public static void main(String[] args) {
        DynamicStack p1 = new DynamicStack();

        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");
        p1.apilar(5);
        p1.apilar("jlk");
        p1.apilar(25);
        p1.apilar("j5k6");
        p1.apilar(34);
        p1.apilar(3782.45);
        p1.apilar("dedj");

        int i = 1;
        while (!p1.isEmpty())
            out.println(i++ + "� ingresado: " + p1.desapilar());
    }

    @Override
    public boolean apilar(Object obj) {
        try {
            StackNode aux = this.node;
            this.node = new StackNode();
            node.data = obj;
            node.next = aux;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public Object desapilar() {
        if (node != null) {
            Object dato = node.data;
            this.node = this.node.next;
            return dato;
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.node == null;
    }

    @Override
    public void vaciar() {
        this.node = null;

    }


    @Override
    public Object verTope() {
        return this.node.data;
    }

    /**
     * @param args
     */

    public class StackNode {

        private Object data;
        private StackNode next;
    }


}
