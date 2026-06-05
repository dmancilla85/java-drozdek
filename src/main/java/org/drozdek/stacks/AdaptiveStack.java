package org.drozdek.stacks;

import org.drozdek.stacks.interfaces.StackInterface;

import java.util.Calendar;
import java.util.Random;

import static java.lang.System.out;

public class AdaptiveStack<T> implements StackInterface<T> {

    public static final char STATIC = 0;
    public static final char DYNAMIC = 1;
    public static final int CRITICAL_VALUE = 2000;
    private static final Random RANDOM = new Random();
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
    public String toString() {
        return stack.toString();
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
        int benchmarkPushCount = 0;
        int benchmarkPopCount = 0;

        int maxIncrement = 5000000;
        int repetitions = 1000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        for (int j = 0; j < maxIncrement; j += 1000) {
            long[] delays = runTimedIterations(repetitions, arrayStack, linkedStack);
            benchmarkPushCount += delays[4];
            benchmarkPopCount += delays[5];

            for (int i = benchmarkPushCount - benchmarkPopCount; i < j; i++) {
                arrayStack.push(RANDOM.nextInt());
                linkedStack.push(RANDOM.nextInt());
                benchmarkPushCount++;
            }

            String message = buildEfficiencyMessage(delays);
            out.printf("%09d - %S - %d%n", j, message, benchmarkPushCount - benchmarkPopCount);
        }
    }

    private long[] runTimedIterations(int repetitions, ArrayStack<Integer> arrayStack,
                                      LinkedStack<Integer> linkedStack) {
        long pushStaticDelays = 0;
        long pushDynamicDelays = 0;
        long popStaticDelays = 0;
        long popDynamicDelays = 0;
        int pushCount = 0;
        int popCount = 0;

        for (int i = 0; i < repetitions; i++) {
            if (i % 2 == 0) {
                Calendar ini = Calendar.getInstance();
                arrayStack.push(RANDOM.nextInt());
                Calendar fin = Calendar.getInstance();
                pushStaticDelays += fin.getTimeInMillis() - ini.getTimeInMillis();

                ini = Calendar.getInstance();
                linkedStack.push(RANDOM.nextInt());
                fin = Calendar.getInstance();
                pushDynamicDelays += fin.getTimeInMillis() - ini.getTimeInMillis();
                pushCount++;
            } else {
                Calendar ini = Calendar.getInstance();
                arrayStack.pop();
                Calendar fin = Calendar.getInstance();
                popStaticDelays += fin.getTimeInMillis() - ini.getTimeInMillis();

                ini = Calendar.getInstance();
                linkedStack.pop();
                fin = Calendar.getInstance();
                popDynamicDelays += fin.getTimeInMillis() - ini.getTimeInMillis();
                popCount++;
            }
        }
        return new long[]{pushStaticDelays, pushDynamicDelays, popStaticDelays, popDynamicDelays, pushCount, popCount};
    }

    private String buildEfficiencyMessage(long[] delays) {
        long pushStaticDelays = delays[0];
        long pushDynamicDelays = delays[1];
        long popStaticDelays = delays[2];
        long popDynamicDelays = delays[3];

        StringBuilder message = new StringBuilder("PUSH::More Efficient::");
        appendEfficiency(message, pushDynamicDelays, pushStaticDelays);
        message.append("                POP::More Efficient::");
        appendEfficiency(message, popDynamicDelays, popStaticDelays);
        return message.toString();
    }

    private void appendEfficiency(StringBuilder message, long dynamicDelays, long staticDelays) {
        if (dynamicDelays == staticDelays) {
            message.append("EQUAL(").append(staticDelays).append(")");
        } else if (dynamicDelays > staticDelays) {
            message.append("STATIC");
        } else {
            message.append("DYNAMIC");
        }
    }
}
