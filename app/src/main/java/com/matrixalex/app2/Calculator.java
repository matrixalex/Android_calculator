package com.matrixalex.app2;

import java.math.BigDecimal;

public class Calculator {
    private Double result = null;
    private String operator = "";
    private String current_str = "";
    private boolean is_null = false;
    private boolean has_point = false;
    private int roundScale = 3;
    private String nullStr = "NULL";

    public String addChar(String newChar) {
        if (newChar.equals(".")) {
            if (has_point) {
                return current_str;
            }
            has_point = true;
        }
        current_str += newChar;
        return current_str;
    }

    public boolean clearAll() {
        result = null;
        operator = "";
        current_str = "";
        is_null = false;
        has_point = false;
        return true;
    }

    public String deleteChar() {
        if (current_str.length() == 0) return "";
        current_str = current_str.substring(0, current_str.length() - 1);
        if (!current_str.contains(".")){
            has_point = false;
        }
        return current_str;
    }


    private void calculate(String operator){
        if (is_null) return;
        Double number;
        if (!current_str.equals("")) number = Double.valueOf(current_str);
        else number = Double.valueOf(0);
        this.operator = operator;
        switch(operator){
            case "/": {
                if(number == 0){
                    is_null = true;
                } else {
                    result /= number;
                }
                break;
            }
            case "*": {
                result *= number;
                break;
            }
            case "+": {
                result += number;
                break;
            }
            case "-": {
                result -= number;
                break;
            }
            case "SQRT": {
                if (number < 0) {
                    is_null = true;
                } else {
                    result = Math.sqrt(number);
                }
                break;
            }
            case "1/X": {
                if (number == 0) {
                    is_null = true;
                } else {
                    result = 1 / number;
                }
                break;
            }
        }
    }
    public String handleOperator(String operator) {
        operator = operator.toUpperCase();
        System.out.println("handleOperator: " + operator);
        if (operator.equals("=")){
            if (!this.operator.equals("")) {
                System.out.println("Have another operator: " + this.operator);
                calculate(this.operator);
            } else {
                System.out.println("No another operator");
                if (!current_str.equals("")) {
                    System.out.println("Getting result from current string");
                    result = Double.valueOf(current_str);
                }
            }
            this.operator = "";
            current_str = toResultString();
            return current_str;
        } else if (this.operator.equals("")){
            if (operator.equals("1/X") || operator.equals("SQRT")){
                calculate(operator);
                this.operator = "";
                current_str = toResultString();
                return current_str;
            } else {
                System.out.println("Have no another operator");
                if (!current_str.equals("")) result = Double.valueOf(current_str);
                else result = Double.valueOf(0);
                this.operator = operator;
                current_str = "";
                has_point = false;
                return current_str;
            }
        } else {
            if (operator.equals("1/X") || operator.equals("SQRT")){
                calculate(operator);
                this.operator = "";
                current_str = toResultString();
                return current_str;
            } else {
                System.out.println("Calculating operator " + this.operator);
                calculate(this.operator);
                this.operator = operator;
                current_str = "";
                has_point = false;
                return current_str;
            }
        }
    }

    public boolean is_null() {
        return is_null;
    }

    private double round(){
        System.out.println("Rounding result");
        if (result == null) return 0;
        return new BigDecimal(result).setScale(roundScale, BigDecimal.ROUND_UP).doubleValue();
    }

    private String toResultString(){
        System.out.println("Converting result to string");
        if (is_null) return nullStr;
        double rounded = round();
        String resultStr = String.valueOf(rounded);
        if (rounded == (int)rounded) resultStr = String.valueOf((int)rounded);
        return resultStr;
    }
}
