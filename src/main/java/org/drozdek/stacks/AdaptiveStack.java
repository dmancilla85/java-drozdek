package org.drozdek.stacks;

import java.util.Calendar;
import java.util.Random;

import static java.lang.System.out;

import org.drozdek.stacks.interfaces.StackInterface;

public class AdaptiveStack<T> implements StackInterface<T> {

    public static final char STATIC = 0;
    public static final char DYNAMIC = 1;
    public static final int CRITICAL_VALUE = 2000;
    private char type;
    private StackInterface<T> stack;
    private int pushCount = 0;
    private int popCount = 0;

    public AdaptiveStack() {
        stack = new ArrayStack<>();
        type = STATIC;
    }

    @Override
    public void push(T element) {
        convertIfNeeded();
        stack.push(element);
        pushCount++;
    }

    @Override
    public T pop() {
        convertIfNeeded();
        T obj = stack.pop();
        popCount++;
        return obj;
    }

    @Override
    public T topElement() {
        return stack.topElement();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void clear() {
        stack.clear();
        convertIfNeeded();
    }

    private void switchToDynamic() {
        StackInterface<T> newStack = new LinkedStack<>();
        while (!stack.isEmpty()) {
            newStack.push(stack.pop());
        }
        stack = newStack;
        type = DYNAMIC;
    }

    private void switchToStatic() {
        StackInterface<T> newStack = new ArrayStack<>();
        while (!stack.isEmpty()) {
            newStack.push(stack.pop());
        }
        stack = newStack;
        type = STATIC;
    }

    private void convertIfNeeded() {
        if (type == STATIC && needsDynamicSwitch()) {
            switchToDynamic();
        } else if (type == DYNAMIC && needsStaticSwitch()) {
            switchToStatic();
        }
    }

    private boolean needsDynamicSwitch() {
        return (pushCount - popCount) > CRITICAL_VALUE;
    }

    private boolean needsStaticSwitch() {
        return (pushCount - popCount) < CRITICAL_VALUE / 2;
    }

    public void benchmark() {
        Calendar ini;
        Calendar fin;

        int benchmarkPushCount = 0;
        int benchmarkPopCount = 0;

        int maxIncrement = 5000000;
        int repetitions = 1000;
        ArrayStack<Integer> arrayStack;
        LinkedStack<Integer> linkedStack;

        arrayStack = new ArrayStack<>();
        linkedStack = new LinkedStack<>();
        Random random = new Random();
        for (int j = 0; j < maxIncrement; j += 1000) {
            long pushStaticDelays = 0;
            long pushDynamicDelays = 0;
            long popStaticDelays = 0;
            long popDynamicDelays = 0;

            for (int i = 0; i < repetitions; i++) {
                if (i % 2 == 0) {
                    ini = Calendar.getInstance();
                    arrayStack.push(random.nextInt());
                    fin = Calendar.getInstance();
                    pushStaticDelays += fin.getTimeInMillis() - ini.getTimeInMillis();

                    ini = Calendar.getInstance();
                    linkedStack.push(random.nextInt());
                    fin = Calendar.getInstance();
                    pushDynamicDelays += fin.getTimeInMillis() - ini.getTimeInMillis();

                    benchmarkPushCount++;
                } else {
                    ini = Calendar.getInstance();
                    arrayStack.pop();
                    fin = Calendar.getInstance();
                    popStaticDelays += fin.getTimeInMillis() - ini.getTimeInMillis();

                    ini = Calendar.getInstance();
                    linkedStack.pop();
                    fin = Calendar.getInstance();
                    popDynamicDelays += fin.getTimeInMillis() - ini.getTimeInMillis();

                    benchmarkPopCount++;
                }
            }
            for (int i = benchmarkPushCount - benchmarkPopCount; i < j; i++) {
                arrayStack.push(random.nextInt());
                linkedStack.push(random.nextInt());
                benchmarkPushCount++;
            }

            StringBuilder message = new StringBuilder("PUSH::More Efficient::");
            if (pushDynamicDelays == pushStaticDelays) {
                message.append("EQUAL(").append(pushStaticDelays).append(")");
            } else if (pushDynamicDelays > pushStaticDelays) {
                message.append("STATIC");
            } else {
                message.append("DYNAMIC");
            }
            message.append("                POP::More Efficient::");
            if (popDynamicDelays == popStaticDelays) {
                message.append("EQUAL(").append(popStaticDelays).append(")");
            } else if (popDynamicDelays > popStaticDelays) {
                message.append("STATIC");
            } else {
                message.append("DYNAMIC");
            }

            out.printf("%09d - %S - %d%n", j, message, benchmarkPushCount - benchmarkPopCount);
        }
    }
}
