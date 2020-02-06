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

    public boolean Register(String cmd, String desc, int inputs, boolean isFunc, int order, Function func) throws Exception {
        if (FunctionExists(cmd)) throw new Exception("This function already exists.");
        String error = StringError.NotVoid(new String[][] {{"cmd", cmd}, {"desc", desc}});
        if (error != "") throw new Exception(error);
        functions.add(new FunctionRegistry(cmd, desc, inputs, isFunc, order, func));
        return true;
    }

    public FunctionRegistry GetFunctionRegistry(String cmd) {
        System.out.println("GetFunctionRegistry: [term] " + cmd);
        for (FunctionRegistry func: functions) {
            System.out.println(func.getCmd() + " : " + cmd);
            if (func.getCmd() == cmd) {
                System.out.println("Function: " + cmd);
                return func;
            }
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
    
    public FunctionRegistry[] OrderFunctions() {
        List<FunctionRegistry> funcs = new ArrayList<FunctionRegistry>();
        for (FunctionRegistry func: functions) {
            boolean placed = false;
            for (int i = 0; i < funcs.size(); i++) {
                if (func.order < funcs.get(i).order || funcs.size() == 0) {
                    funcs.add(i, func);
                    placed = true;
                    break;
                }
            }
            if (!placed) funcs.add(func);
        }
        return funcs.toArray(new FunctionRegistry[0]);
    }

    public int GetOrder(String function) {
        System.out.println("Order: [term] " + function);
        FunctionRegistry funcReg = FunctionManager.instance.GetFunctionRegistry(function);
        if (funcReg == null) return -1;
        return funcReg.getOrder();
    }
}
