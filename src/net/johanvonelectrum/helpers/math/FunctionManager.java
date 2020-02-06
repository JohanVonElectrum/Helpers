package net.johanvonelectrum.helpers.math;

import net.johanvonelectrum.helpers.string.StringError;

import java.util.*;
import java.util.function.Function;

public class FunctionManager {

    public static FunctionManager instance;

    List<FunctionRegistry> functions;

    public FunctionManager() {
        instance = this;
        functions = new ArrayList<FunctionRegistry>();
    }

    public boolean FunctionExists(String cmd) {
        for (FunctionRegistry func: functions) {
            if (func.cmd == cmd) return true;
        }
        return false;
    }

    public boolean Register(String cmd, String desc, int inputs, Function func) throws Exception {
        if (FunctionExists(cmd)) throw new Exception("This function already exists.");
        String error = StringError.NotVoid(new String[][] {{"cmd", cmd}, {"desc", desc}});
        if (error != "") throw new Exception(error);
        functions.add(new FunctionRegistry(cmd, desc, inputs, func));
        return true;
    }

    public FunctionRegistry GetFunctionRegistry(String cmd) {
        for (FunctionRegistry func: functions) {
            if (func.cmd == cmd) return func;
        }
        return null;
    }

    public int GetMaxInputs() {
        int maxInputs = 0;
        for (FunctionRegistry func: ListFunctions()) {
            if (maxInputs < func.inputs) maxInputs = func.inputs;
        }
        return  maxInputs;
    }

    public FunctionRegistry[] ListFunctions() {
        return functions.toArray(new FunctionRegistry[0]);
    }
}
