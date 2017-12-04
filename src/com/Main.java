package com;

import com.Exceptions.SyntaxException;
import com.lexical.Tokenizer;
import com.Exceptions.WrongTokenException;
import com.syntax.Parser;

public class Main {

    public static void main(String[] args) {

//        String code = "b+=a[--n];";
//        System.out.println(code + "\n");
//        code = code.replaceAll(" ", "");
//        LanguageC language = new LanguageC();
//        Tokenizer tokenizer = new Tokenizer(code, language);
//        try {
//            tokenizer.formTokens();
//        } catch (WrongTokenException e) {
//            e.printStackTrace();
//        }
//        System.out.println(tokenizer);

        String code1 = "if (a<c) then begin for i:= 1 to n do begin a:=b; end; end;";
        System.out.println(code1 + "\n");

        //code1 = code1.replaceAll(" ", "");
        LanguagePascal languagePascal = new LanguagePascal();
        languagePascal.viewRules();
        Tokenizer tokenizer1 = new Tokenizer(code1, languagePascal);

        try {
            tokenizer1.formTokens();

            System.out.println(tokenizer1);

            languagePascal.formTokenRules(tokenizer1.getLexemeTableList());

            languagePascal.viewRules();
            Parser parser = new Parser(tokenizer1.getLexemeTableList(), languagePascal);
            parser.formTree();
            parser.viewTree(languagePascal.getRoot());

        } catch (WrongTokenException | SyntaxException e) {
            e.printStackTrace();
        }
    }
}
