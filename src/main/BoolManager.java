package main;

import java.util.ArrayList;

import exceptions.InvalidTypeException;
import exceptions.stpdException;

public class BoolManager {
    public static boolean solveBool(String bool) throws stpdException {
        ArrayList<String> expression = splitExpression(bool);

        for (String string : expression) {
            System.out.println(string);
        }

        return true;
    }

    private static ArrayList<String> splitExpression(String bool) throws stpdException {
        ArrayList<String> splitExpression = new ArrayList<>();
        String[] list = bool.split(" ");

        String cache = "";

        for (int i = 0; i < list.length; i++) {
            String current = list[i];

            if (isCombiner(current)) {
                splitExpression.add(cache);
                cache = "";

                splitExpression.add(current);
            } else if (Main.varMan.isVariable(current)) {
                if (Main.varMan.getVariableType(current) == "bool") {
                    if (!cache.equals("")) cache += " ";

                    cache += Main.varMan.getVariableAsString(current);
                }
            } else if (current.equals("true") || current.equals("false") || Utils.isNumber(current) || Utils.isString(current) || isComparator(current)) {
                if (!cache.equals("")) cache += " ";

                cache += current;
            } else throw new InvalidTypeException(current);
        }

        splitExpression.add(cache);

        return splitExpression;
    }
    
    private static boolean isComparator(String comparator) {
        return comparator.equals("==") || comparator.equals("!=") || comparator.equals("!") || comparator.equals("<=") || comparator.equals("<<") || comparator.equals(">=") || comparator.equals(">>");
    }

    private static boolean isCombiner(String str) {
        return str.equals("or") || str.equals("and");
    }

    public static boolean isUnsolvedBool(String value) {
        return value.contains("or") || value.contains("and") || value.contains("!") || value.contains("==") || value.contains("!=") || value.contains("<=") || value.contains("<<") || value.contains(">=") || value.contains(">>");
    }
}
