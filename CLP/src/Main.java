import ast.*;
import evaluator.ExecuteVM;
import evaluator.SimpLanPluslib;
import gen.antlr.SimpLanPlusParser;
import org.antlr.v4.runtime.*;
import gen.antlr.SimpLanPlusLexer;
import gen.svm.SVMLexer;
import gen.svm.SVMParser;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException{

        String fileName = "src/input.txt";

        FileInputStream is = new FileInputStream(fileName);
        ANTLRInputStream input = new ANTLRInputStream(is);
        SimpLanPlusLexer lexer = new SimpLanPlusLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        //esercizio 1
        String errorPath = "out/errors.txt";
        File f = new File(errorPath);
        if(f.exists()) {
            f.delete();
        }
        f.createNewFile();

        Boolean noSynErr = true;
        for(Token t: tokens.getTokens()) {
            if(t.getType() == SimpLanPlusLexer.ERR) {
                noSynErr = false;
                String errString =  "Line " + t.getLine() + " in position " + t.getCharPositionInLine() + " -- Error: invalid character " + t.getText() + "\n";
                Files.write(Paths.get("out/errors.txt"), errString.getBytes() , StandardOpenOption.APPEND);
            }
        }

        ErrorListener listener = new ErrorListener();

        SimpLanPlusParser parser = new SimpLanPlusParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(listener);

        SimpLanPlusVisitorImpl visitor = new SimpLanPlusVisitorImpl();

        Node ast;
        try {
            ast = visitor.visit(parser.prog());   //generazione AST
        }catch (RuntimeException err){
            System.out.println("Syntax errors: check " + errorPath + " for details");
            Files.write(Paths.get(errorPath), err.getMessage().getBytes() , StandardOpenOption.APPEND);
            return;
        }

        if (noSynErr) {


            SymbolTable ST = new SymbolTable();
            ArrayList<SemanticError> errors = ast.checkSemantics(ST, 0);

            if(errors.size()>0){
                System.out.println("You had: " + errors.size() + " errors:");
                for(SemanticError e : errors)
                    System.out.println("\t" + e);
            }
            else {
                System.out.println("Visualizing AST...");
                System.out.println(ast.toPrint(""));

                ArrayList<ErrorType> typeErrors = new ArrayList<ErrorType>();
                Node type = ast.typeCheck(typeErrors); //type-checking bottom-up
                if ((type instanceof ErrorType) || (typeErrors.size() > 0)) {
                    System.out.println("Type checking is WRONG!");
                }
                else {
                    System.out.println(type.toPrint("Type checking ok! Type of the program is: "));

                    //a void program will always result 0
                    String voidProg = "";
                    if (type instanceof VoidType) {
                        voidProg = "storei A0 0\n";
                    }
                    String code = ast.codeGeneration();
                    code += voidProg + "halt\n" + SimpLanPluslib.getCode();

                    BufferedWriter out = new BufferedWriter(new FileWriter(fileName + ".asm"));
                    out.write(code);
                    out.close();
                    System.out.println("Code generated! Assembling and running generated code.");

                    FileInputStream isASM = new FileInputStream(fileName + ".asm");
                    ANTLRInputStream inputASM = new ANTLRInputStream(isASM);
                    SVMLexer lexerASM = new SVMLexer(inputASM);
                    CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
                    SVMParser parserASM = new SVMParser(tokensASM);

                    SVMVisitorImpl visitorSVM = new SVMVisitorImpl();
                    visitorSVM.visit(parserASM.assembly());

                    System.out.println("Starting Virtual Machine...");
                    ExecuteVM vm = new ExecuteVM(visitorSVM.code);
                    vm.cpu();

                }
            }
        }
        else {
            System.out.println("Syntax errors: check " + errorPath + " for details");
        }
    }
}