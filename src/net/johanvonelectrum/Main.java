package net.johanvonelectrum;

import net.johanvonelectrum.helpers.math.FunctionManager;
import net.johanvonelectrum.helpers.math.Interpreter;

import java.util.function.Function;

public class Main {

    public FunctionManager functionManager;

    public static void main(String[] args) throws Exception {
        (new Main()).program(args);
    }

    public void program(String[] args) throws Exception {
        functionManager = new FunctionManager();

        RegisterFunctions();

        System.out.println(Interpreter.Calculate("(3+5*(3-1)-5)+1"));
    }

    public void RegisterFunctions() throws Exception {
        functionManager.Register("+", "Addition.", 2, new Function() {
            @Override
            public Object apply(Object o) {
                float[] input = (float[])o;
                return input[0] + input[1];
            }
        });
        functionManager.Register("-", "Subtraction.", 2, new Function() {
            @Override
            public Object apply(Object o) {
                float[] input = (float[])o;
                System.out.println("[-] " + input[0] + ":" + input[1]);
                return input[0] - input[1];
            }
        });
        functionManager.Register("*", "Multiplication.", 2, new Function() {
            @Override
            public Object apply(Object o) {
                float[] input = (float[])o;
                return input[0] * input[1];
            }
        });
        functionManager.Register("/", "Division.", 2, new Function() {
            @Override
            public Object apply(Object o) {
                float[] input = (float[])o;
                return input[0] / input[1];
            }
        });
        functionManager.Register("sqrt", "Square root.", 1, new Function() {
            @Override
            public Object apply(Object o) {
                return Math.sqrt(Double.parseDouble(((float[])o)[0] + ""));
            }
        });
    }
}
