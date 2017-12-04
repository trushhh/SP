package com;

import com.lexical.Token;
import com.syntax.Rule;
import com.syntax.alphabet.NonTerminal;
import com.syntax.alphabet.SymbolSequence;
import com.syntax.alphabet.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LanguageC extends Language {

    public LanguageC() {
        super();
    }

    public void formRules() {
        NonTerminal STATEMENTS = new NonTerminal("Statements");
        NonTerminal STATEMENT = new NonTerminal("Statement");
        NonTerminal EXP = new NonTerminal("Expression");
        NonTerminal DELIMITER = new NonTerminal("Delimiter");
        NonTerminal IDENTIFIER = new NonTerminal("Identifier");
        NonTerminal OPERATOR = new NonTerminal("Operator");
        NonTerminal CONST = new NonTerminal("Constant");
        NonTerminal BRACKETS = new NonTerminal("Brackets");
        NonTerminal StateOperator = new NonTerminal("StateOperator");
        getNonTerminals().add(STATEMENT);
        getNonTerminals().add(EXP);
        getNonTerminals().add(DELIMITER);
        getNonTerminals().add(IDENTIFIER);
        getNonTerminals().add(OPERATOR);
        getNonTerminals().add(CONST);
        getNonTerminals().add(BRACKETS);

        Terminal squareDelimLeft = new Terminal("[");
        Terminal squareDelimRIght = new Terminal("]");
        getTerminals().add(squareDelimLeft);
        getTerminals().add(squareDelimRIght);

        List<Rule> ruleList = new ArrayList<>();
        Rule eRule1 = new Rule(EXP, new SymbolSequence());
        eRule1.getRight().add(IDENTIFIER);
        Rule eRule2 = new Rule(EXP, new SymbolSequence());
        eRule2.getRight().add(IDENTIFIER);
        eRule2.getRight().add(OPERATOR);
        eRule2.getRight().add(EXP);
        Rule eRule3 = new Rule(EXP, new SymbolSequence());
        eRule3.getRight().add(IDENTIFIER);
        eRule3.getRight().add(squareDelimLeft);
        eRule3.getRight().add(EXP);
        eRule3.getRight().add(squareDelimRIght);
        Rule rule4 = new Rule(EXP, new SymbolSequence());
        rule4.getRight().add(CONST);
        rule4.getRight().add(OPERATOR);
        rule4.getRight().add(EXP);
        Rule rule5 = new Rule(EXP, new SymbolSequence());
        rule5.getRight().add(CONST);
        //Rule rule6 = new Rule(EXP, new SymbolSequence());
        // rule6.getRight().add(EXP);
        // rule6.getRight().add(DELIMITER);
        Rule rule7 = new Rule(STATEMENT, new SymbolSequence());
        rule7.getRight().add(IDENTIFIER);
        rule7.getRight().add(StateOperator);
        rule7.getRight().add(EXP);
        //rule7.getRight().add(new Terminal(";"));

        Rule rule8 = new Rule(STATEMENTS, new SymbolSequence());
        rule8.getRight().add(STATEMENT);
        rule8.getRight().add(new Terminal(";"));
        rule8.getRight().add(STATEMENTS);

        Rule rule9 = new Rule(STATEMENTS, new SymbolSequence());
        rule9.getRight().add(STATEMENT);
        rule9.getRight().add(new Terminal(";"));

        Rule rule10 = new Rule(EXP, new SymbolSequence());
        rule10.getRight().add(OPERATOR);
        rule10.getRight().add(IDENTIFIER);
//        rule10.getRight().add(STATEMENTS);

        Rule ruleBrackets = new Rule(EXP, new SymbolSequence());
        ruleBrackets.getRight().add(CONST);
        ruleBrackets.getRight().add(OPERATOR);
        ruleBrackets.getRight().add(new Terminal("("));
        ruleBrackets.getRight().add(EXP);
        ruleBrackets.getRight().add(new Terminal(")"));

        Rule ruleBrackets2 = new Rule(EXP, new SymbolSequence());
        ruleBrackets2.getRight().add(IDENTIFIER);
        ruleBrackets2.getRight().add(OPERATOR);
        ruleBrackets2.getRight().add(new Terminal("("));
        ruleBrackets2.getRight().add(EXP);
        ruleBrackets2.getRight().add(new Terminal(")"));


        Rule ruleBrackets3 = new Rule(EXP, new SymbolSequence());
        ruleBrackets3.getRight().add(EXP);
        ruleBrackets3.getRight().add(OPERATOR);
        ruleBrackets3.getRight().add(EXP);

        ruleList.add(eRule1);
        ruleList.add(eRule2);
        ruleList.add(eRule3);
        ruleList.add(rule4);
        ruleList.add(rule5);
        ruleList.add(rule7);
        ruleList.add(rule9);
        ruleList.add(rule8);
        ruleList.add(rule10);
        ruleList.add(ruleBrackets);
        ruleList.add(ruleBrackets2);
        ruleList.add(ruleBrackets3);

        //ruleList.add(rule6);
        getRules().addAll(ruleList);
    }

    public void formTokenRules(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.getType().equals("Identifier")) {
                getRules().add(new Rule(new NonTerminal("Identifier"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("Operator")) {
                getRules().add(new Rule(new NonTerminal("Operator"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("StateOperator")) {
                getRules().add(new Rule(new NonTerminal("StateOperator"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("Constant")) {
                getRules().add(new Rule(new NonTerminal("Constant"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
/*            if (token.getType().equals(DELIMITER)) {
                rules.add(new Rule(new NonTerminal(DELIMITER), new Terminal(token)));
                terminals.add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("Brackets")) {
                rules.add(new Rule(new NonTerminal("Brackets"), new Terminal(token)));
                terminals.add(new Terminal(token.getSign()));
            }*/
        }
    }

    public void getLemexeAlphabet(Set<Token> delimiterList, Set<Token> operatorList, Set<Token> keywordList) {
        String DELIMITER = "Delimiter";

        delimiterList.add(new Token(" ", DELIMITER));
        delimiterList.add(new Token(",", DELIMITER));
        delimiterList.add(new Token(";", DELIMITER));
        delimiterList.add(new Token(" ", DELIMITER));
        delimiterList.add(new Token(".", DELIMITER));

        delimiterList.add(new Token("{", "Bracket"));
        delimiterList.add(new Token("}", "Bracket"));
        delimiterList.add(new Token("(", "Bracket"));
        delimiterList.add(new Token(")", "Bracket"));
        delimiterList.add(new Token("[", "Bracket"));
        delimiterList.add(new Token("]", "Bracket"));

        keywordList.add(new Token("int", "Type"));
        keywordList.add(new Token("char", "Type"));
        keywordList.add(new Token("short", "Type"));
        keywordList.add(new Token("int", "Type"));
        keywordList.add(new Token("long", "Type"));
        keywordList.add(new Token("signed", "Type"));
        keywordList.add(new Token("unsigned", "Type"));
        keywordList.add(new Token("float", "Type"));
        keywordList.add(new Token("double", "Type"));
        keywordList.add(new Token("void", "Type"));
        keywordList.add(new Token("boolean", "Type"));

        keywordList.add(new Token("return", "KEYWORD"));
        keywordList.add(new Token("static", "KEYWORD"));
        keywordList.add(new Token("if", "KEYWORD"));
        keywordList.add(new Token("else", "KEYWORD"));
        keywordList.add(new Token("for", "KEYWORD"));
        keywordList.add(new Token("do", "KEYWORD"));
        keywordList.add(new Token("while", "KEYWORD"));

        //binary
        operatorList.add(new Token("+", "Operator"));
        operatorList.add(new Token("-", "Operator"));
        operatorList.add(new Token("*", "Operator"));
        operatorList.add(new Token("/", "Operator"));
        operatorList.add(new Token(">", "Operator"));
        operatorList.add(new Token("<", "Operator"));

        //binary && assingment
        operatorList.add(new Token("=", "Operator"));
        operatorList.add(new Token("==", "StateOperator"));
        operatorList.add(new Token("-=", "StateOperator"));
        operatorList.add(new Token("+=", "StateOperator"));
        operatorList.add(new Token("*=", "StateOperator"));
        operatorList.add(new Token("/=", "StateOperator"));

        //unary
        operatorList.add(new Token("++", "Operator"));
        operatorList.add(new Token("--", "Operator"));
    }

    public boolean checkIfAllowedString(String string) {
        return string.matches("[a-zA-Z0-9=+*/%;&|\\[\\]_(),\\\"'.<> {}-]+$");
    }
}
