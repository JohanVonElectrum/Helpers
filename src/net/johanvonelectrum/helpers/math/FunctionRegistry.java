package net.johanvonelectrum.helpers.math;

import java.util.function.Function;

public class FunctionRegistry {

    String cmd;
    String desc;
    int inputs;
    Function operation;

    public FunctionRegistry(String cmd, String desc, int inputs, Function operation) {
        this.cmd = cmd.toLowerCase();
        this.desc = desc;
        this.inputs = inputs;
        this.operation = operation;
    }

    public String getCmd() { return cmd; }
    public String getDesc() { return desc; }
    public int getInputs() { return inputs; }
    public Function getOperation() { return operation; }

}
