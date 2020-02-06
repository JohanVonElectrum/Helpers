package net.johanvonelectrum.helpers.math;

import java.util.function.Function;

public class FunctionRegistry {

    String cmd;
    String desc;
    int inputs;
    boolean composed;
    Function operation;

    public FunctionRegistry(String cmd, String desc, int inputs, boolean composed, Function operation) {
        this.cmd = cmd.toLowerCase();
        this.desc = desc;
        this.inputs = inputs;
        this.composed = composed;
        this.operation = operation;
    }

    public String getCmd() { return cmd; }
    public String getDesc() { return desc; }
    public int getInputs() { return inputs; }
    public boolean isComposed() { return composed; }
    public Function getOperation() { return operation; }

}
