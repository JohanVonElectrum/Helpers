package net.johanvonelectrum.helpers.math;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    public static String[] GetTerms(String term) {
        if (term.charAt(0) != '(') term = "(" + term + ")";
        System.out.println("Terms: [term] " + term);

        List<String> ps = new ArrayList<String>();
        for (int i = 0; i < term.length(); i++) {
            int p = 0;
            for (int j = 0; j < term.length() - i; j++) {
                if (term.charAt(i + j) == '(') {
                    p++;
                }
                if (term.charAt(i + j) == ')') {
                    p--;
                    if (p == 0) {
                        ps.add(term.substring(i, i + j + 1));
                        break;
                    }
                }
            }
        }
        return ps.toArray(new String[0]);
    }

    public static int CountFunctions(String term) {
        System.out.println("Count: [term] " + term);

        int count = 0;
        int i = 0;

        for (FunctionRegistry func: FunctionManager.instance.ListFunctions()) {
            i = 0;
            int index = term.indexOf(func.cmd, i);
            if (index != -1) {
                count++;
                i = index;
            }
        }

        return count;
    }

    public static double MonoTermCompute(String term) {
        System.out.println("MonoTermCompute: [term] " + term);

        int a = term.indexOf("*");
        int b = term.indexOf("*");
        if (CountFunctions(term) > FunctionManager.instance.GetMaxInputs() && GetTerms(term).length == 1) {
            if (term.indexOf("*") != -1) {
                for (int j = term.indexOf("*") - 1; j >= 0; j--) {
                    if (term.charAt(j) == '+' || term.charAt(j) == '-') {
                        a = j + 1;
                        break;
                    }
                }
                for (int j = term.indexOf("*") + 1; j < term.length(); j++) {
                    if (term.charAt(j) == '+' || term.charAt(j) == '-') {
                        b = j;
                        break;
                    }
                }
                System.out.println("MonoTermCount: [new term]" + term.substring(a, b));
            }
        }

        return Compute(term.substring(a, b));
    }

    public static double Compute(String term) {
        term = term.replace("(", "").replace(")", "");
        System.out.println("Compute: [term] " + term);

        for (FunctionRegistry func: FunctionManager.instance.ListFunctions()) {
            if (term.indexOf(func.cmd) == -1) continue;
            switch (func.inputs) {
                case 1:
                    return Double.parseDouble("" + func.operation.apply(new float[] { Float.parseFloat(term.substring(term.indexOf(func.cmd) + func.cmd.length(), term.length())) }));
                case 2:
                    return Double.parseDouble("" + func.operation.apply(new float[] { Float.parseFloat(term.substring(0, term.indexOf(func.cmd))), Float.parseFloat(term.substring(term.indexOf(func.cmd) + func.cmd.length(), term.length())) }));
            }
        }
        return 0;
    }

    public static double Calculate(String term) {
        if (term.charAt(0) != '(') term = "(" + term + ")";
        System.out.println("Calculate: [term] " + term);

        for (FunctionRegistry func: FunctionManager.instance.ListFunctions()) {
            if (!func.isComposed()) continue;
            int i = 0;
            while (i != -1 && term.charAt(i - 1 < 0 ? 0 : i - 1) != '(') {
                i = term.indexOf(func.cmd);
                term = term.substring(0, i) + "(" + func.cmd + /* el term de la func + */ ")" /* + el resto del term*/;
            }
        }

        for (String str: Interpreter.GetTerms(term)) {
            if (CountFunctions(str) == 1) {
                double value = Compute(str);
                System.out.println(value + " -> " + term.replace(str, value + ""));
                return Calculate(term.replace(str, value + ""));
            }else if (GetTerms(str).length == 1 && CountFunctions(str) > 1) {
                double value = MonoTermCompute(str);
                System.out.println(value + " -> " + term.replace(str, value + ""));
                return Calculate(term.replace(str, value + ""));
            }else if (GetTerms(str).length == 1) return Double.parseDouble(term.replace("(", "").replace(")", ""));
        }
        return 0;
    }
}
