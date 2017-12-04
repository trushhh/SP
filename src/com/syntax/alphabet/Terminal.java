package com.syntax.alphabet;

import com.lexical.Token;

public class Terminal extends AbstractSymbol {

    public Terminal(String literal) {
        super(literal);
    }

    public Terminal(Token token) {
        this.setLiteral(token.getSign());
    }
}
