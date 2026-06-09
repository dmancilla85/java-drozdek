package org.drozdek.stacks;

import org.drozdek.commons.LoggerService;
import org.drozdek.stacks.interfaces.StackInterface;

import java.security.SecureRandom;
import java.time.Clock;
import java.time.ZoneId;
import java.util.Random;

public class AdaptiveStack<T> implements StackInterface<T> {

    public static final char STATIC = 0;
    public static final char DYNAMIC = 1;
    public static final int CRITICAL_VALUE = 2000;
    private static final Random RANDOM = new SecureRandom();
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

        int repetitions = 1000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedStack<Integer> linkedStack = new LinkedStack<>();

        int j = 0;

        int maxIncrement = 5000000;
        while (j < maxIncrement) {
            long[] delays = runTimedIterations(repetitions, arrayStack, linkedStack);
            benchmarkPushCount += (int) delays[4];
            benchmarkPopCount += (int) delays[5];

            for (int i = benchmarkPushCount - benchmarkPopCount; i < j; i++) {
                arrayStack.push(RANDOM.nextInt());
                linkedStack.push(RANDOM.nextInt());
                benchmarkPushCount++;
            }

            String message = buildEfficiencyMessage(delays);
            LoggerService.logInfo(String
                    .format("%09d - %S - %d%n", j, message, benchmarkPushCount - benchmarkPopCount));
            j += 1000;
        }
    }

    private long[] runTimedIterations(int repetitions, ArrayStack<Integer> arrayStack,
                                      LinkedStack<Integer> linkedStack) {
        long pushStaticDelays = 0;
        long pushDynamicDelays = 0;
        long popStaticDelays = 0;
        long popDynamicDelays = 0;
        int pushCounter = 0;
        int popCounter = 0;

        for (int i = 0; i < repetitions; i++) {
            Clock ini = Clock.tickMillis(ZoneId.systemDefault());
            if (i % 2 == 0) {
                arrayStack.push(RANDOM.nextInt());
                Clock fin = Clock.tickMillis(ZoneId.systemDefault());
                pushStaticDelays += fin.millis() - ini.millis();

                ini = Clock.tickMillis(ZoneId.systemDefault());
                linkedStack.push(RANDOM.nextInt());
                fin = Clock.tickMillis(ZoneId.systemDefault());
                pushDynamicDelays += fin.millis() - ini.millis();
                pushCounter++;
            } else {
                arrayStack.pop();
                Clock fin = Clock.tickMillis(ZoneId.systemDefault());
                popStaticDelays += fin.millis() - ini.millis();

                ini = Clock.tickMillis(ZoneId.systemDefault());
                linkedStack.pop();
                fin = Clock.tickMillis(ZoneId.systemDefault());
                popDynamicDelays += fin.millis() - ini.millis();
                popCounter++;
            }
        }
        return new long[]{pushStaticDelays, pushDynamicDelays, popStaticDelays, popDynamicDelays, pushCounter, popCounter};
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
