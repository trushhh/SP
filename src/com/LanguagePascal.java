package com;

import com.lexical.Token;
import com.syntax.Rule;
import com.syntax.alphabet.NonTerminal;
import com.syntax.alphabet.SymbolSequence;
import com.syntax.alphabet.Terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LanguagePascal extends Language {

    public LanguagePascal() {
        super();
    }

    public void formRules() {
        NonTerminal STATEMENTS = new NonTerminal("Statements");
        NonTerminal STATE = new NonTerminal("State");
        NonTerminal NEXT = new NonTerminal("Next");
        NonTerminal ASSIGN = new NonTerminal("Assign");
        NonTerminal IFOP = new NonTerminal("IfOp");
        NonTerminal FOROP = new NonTerminal("ForOp");
        NonTerminal EXP = new NonTerminal("Exp");
        NonTerminal KEYWORD = new NonTerminal("Keyword");
        NonTerminal BLOCK = new NonTerminal("Block");
        NonTerminal DELIMITER = new NonTerminal("Delimiter");
        NonTerminal IDENTIFIER = new NonTerminal("Identifier");
        NonTerminal OPERATOR = new NonTerminal("Operator");
        NonTerminal CONST = new NonTerminal("Constant");
        NonTerminal BRACKETS = new NonTerminal("Brackets");

        getNonTerminals().add(STATE);
        getNonTerminals().add(NEXT);
        getNonTerminals().add(ASSIGN);
        getNonTerminals().add(IFOP);
        getNonTerminals().add(FOROP);
        getNonTerminals().add(EXP);
        getNonTerminals().add(KEYWORD);
        getNonTerminals().add(BLOCK);
        getNonTerminals().add(DELIMITER);
        getNonTerminals().add(IDENTIFIER);
        getNonTerminals().add(OPERATOR);
        getNonTerminals().add(CONST);
        getNonTerminals().add(BRACKETS);

        List<Rule> ruleList = new ArrayList<>();

        Rule rule1 = new Rule(STATEMENTS, new SymbolSequence());
        rule1.getRight().add(STATE);
        rule1.getRight().add(new Terminal(";"));
        rule1.getRight().add(NEXT);

        Rule rule2 = new Rule(NEXT, new SymbolSequence());
        rule2.getRight().add(STATEMENTS);

        Rule rule2a = new Rule(NEXT, new SymbolSequence());
        rule2a.getRight().add(new Terminal(""));

        Rule rule3 = new Rule(STATE, new SymbolSequence());
        rule3.getRight().add(ASSIGN);

        Rule rule4 = new Rule(STATE, new SymbolSequence());
        rule4.getRight().add(IFOP);

        Rule rule5 = new Rule(STATE, new SymbolSequence());
        rule5.getRight().add(FOROP);

        Rule rule6 = new Rule(ASSIGN, new SymbolSequence());
        rule6.getRight().add(IDENTIFIER);
        rule6.getRight().add(new Terminal(":="));
        rule6.getRight().add(EXP);

        Rule rule7 = new Rule(IFOP, new SymbolSequence());
        rule7.getRight().add(new Terminal("if"));
        rule7.getRight().add(EXP);
        rule7.getRight().add(new Terminal("then"));
        rule7.getRight().add(BLOCK);

        Rule rule8 = new Rule(FOROP, new SymbolSequence());
        rule8.getRight().add(new Terminal("for"));
        rule8.getRight().add(ASSIGN);
        rule8.getRight().add(new Terminal("to"));
        rule8.getRight().add(EXP);
        rule8.getRight().add(new Terminal("do"));
        rule8.getRight().add(BLOCK);

        Rule rule9 = new Rule(BLOCK, new SymbolSequence());
        rule9.getRight().add(new Terminal("begin"));
        rule9.getRight().add(STATEMENTS);
        rule9.getRight().add(new Terminal("end"));

        Rule rule10 = new Rule(EXP, new SymbolSequence());
        rule10.getRight().add(EXP);
        rule10.getRight().add(OPERATOR);
        rule10.getRight().add(EXP);

        Rule rule11 = new Rule(EXP, new SymbolSequence());
        rule11.getRight().add(new Terminal("("));
        rule11.getRight().add(EXP);
        rule11.getRight().add(new Terminal(")"));

        Rule rule12 = new Rule(EXP, new SymbolSequence());
        rule12.getRight().add(IDENTIFIER);

        Rule rule13 = new Rule(EXP, new SymbolSequence());
        rule13.getRight().add(CONST);


        ruleList.add(rule1);
        ruleList.add(rule2);
        ruleList.add(rule2a);
        ruleList.add(rule3);
        ruleList.add(rule4);
        ruleList.add(rule5);
        ruleList.add(rule6);
        ruleList.add(rule7);
        ruleList.add(rule8);
        ruleList.add(rule9);
        ruleList.add(rule10);
        ruleList.add(rule11);
        ruleList.add(rule12);
        ruleList.add(rule13);

        getRules().addAll(ruleList);
    }

    public void formTokenRules(List<Token> tokens) {
        for (Token token : tokens) {
            if (token.getType().equals("Keyword") && !getRules().contains(token)) {
                getRules().add(new Rule(new NonTerminal("Keyword"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("Identifier")) {
                getRules().add(new Rule(new NonTerminal("Identifier"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("Operator") && !token.getSign().equals(":=")) {
                getRules().add(new Rule(new NonTerminal("Operator"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
            if (token.getType().equals("Constant")) {
                getRules().add(new Rule(new NonTerminal("Constant"), new Terminal(token)));
                getTerminals().add(new Terminal(token.getSign()));
            }
        }

    }

    public void getLemexeAlphabet(Set<Token> delimiterList, Set<Token> operatorList, Set<Token> keywordList) {
        String DELIMITER = "Delimiter";

        delimiterList.add(new Token(" ", DELIMITER));
        delimiterList.add(new Token(",", DELIMITER));
        delimiterList.add(new Token(";", DELIMITER));
        delimiterList.add(new Token(".", DELIMITER));

        delimiterList.add(new Token("{", "Bracket"));
        delimiterList.add(new Token("}", "Bracket"));
        delimiterList.add(new Token("(", "Bracket"));
        delimiterList.add(new Token(")", "Bracket"));
        delimiterList.add(new Token("[", "Bracket"));
        delimiterList.add(new Token("]", "Bracket"));

        keywordList.add(new Token("integer", "Type"));
        keywordList.add(new Token("char", "Type"));
        keywordList.add(new Token("real", "Type"));
        keywordList.add(new Token("string", "Type"));
        keywordList.add(new Token("boolean", "Type"));
        keywordList.add(new Token("byte", "Type"));

        keywordList.add(new Token("until", "Keyword"));
        keywordList.add(new Token("then", "Keyword"));
        keywordList.add(new Token("to", "Keyword"));
        keywordList.add(new Token("repeat", "Keyword"));
        keywordList.add(new Token("if", "Keyword"));
        keywordList.add(new Token("else", "Keyword"));
        keywordList.add(new Token("begin", "Keyword"));
        keywordList.add(new Token("end", "Keyword"));
        keywordList.add(new Token("for", "Keyword"));
        keywordList.add(new Token("do", "Keyword"));
        keywordList.add(new Token("downto", "Keyword"));
        keywordList.add(new Token("while", "Keyword"));
        keywordList.add(new Token("or", "Keyword"));
        keywordList.add(new Token("and", "Keyword"));

        //binary
        operatorList.add(new Token("+", "Operator"));
        operatorList.add(new Token("-", "Operator"));
        operatorList.add(new Token("*", "Operator"));
        operatorList.add(new Token("/", "Operator"));
        operatorList.add(new Token(">", "Operator"));
        operatorList.add(new Token("<", "Operator"));
        operatorList.add(new Token("=", "Operator"));
        operatorList.add(new Token(":", "Operator"));
        operatorList.add(new Token(">=", "Operator"));
        operatorList.add(new Token("<=", "Operator"));
        operatorList.add(new Token("<>", "Operator"));
        //assingment
        operatorList.add(new Token(":=", "Operator"));

    }

    public boolean checkIfAllowedString(String string) {
        return string.matches("[a-zA-Z0-9=+*/%:;&|\\[\\]_(),\\\"'.<> {}-]+$");
    }

    public void correctionRules(){




    }
}
