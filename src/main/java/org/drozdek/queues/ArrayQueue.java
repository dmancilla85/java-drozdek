package org.drozdek.queues;

import java.io.PrintStream;

public class ArrayQueue {
    private int first;
    private int last;
    private final int size;
    private final Object[] storage;

    public ArrayQueue(int n){
        size=n;
        storage=new Object[size];
        first=last=-1;
    }

    public ArrayQueue(){
        this(100);
    }

    public Object dequeue(){
        Object tmp=storage[first];

        if(first==last)
            last=first=-1;
        else if(first==size-1)
            first=0;
        else first++;

        return tmp;
    }

    public void enqueue(Object el){
        if(last==size-1 || last==-1){
            storage[0]=el;
            last=0;

            if(first==-1)
                first=0;
        } else
            storage[++last]=el;
    }

    public boolean isEmpty(){
        return first==-1;
    }

    public boolean isFull(){
        return first==0 && last == size-1 || first==last+1;
    }

    public void printAll(PrintStream out){
        for(int i=0;i<size;i++)
            out.print(storage[i] + " ");
    }

}