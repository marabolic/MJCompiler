package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class CompilerImpl implements Compiler{
	
	
	
	public CompilerImpl() {
		
	}

	@Override
	public List<CompilerError> compile(String sourceFilePath, String outputFilePath) {
		Logger log = Logger.getLogger(CompilerImpl.class);
		List<CompilerError> errorList = new ArrayList<CompilerError>();
		
		if (sourceFilePath == null || outputFilePath == null) {
			log.error("Not enough arguments supplied! Usage: MJParser <source-file> <obj-file> ");
			return null;
		}
		File sourceCode = new File(sourceFilePath);
		if (!sourceCode.exists()) {
			log.error("Source file [" + sourceCode.getAbsolutePath() + "] not found!");
			return null;
		}
			
		log.info("Compiling source file: " + sourceCode.getAbsolutePath());
		try (BufferedReader br = new BufferedReader(new FileReader(sourceCode))) {
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
			errorList.addAll(lexer.getLexErrors());
			errorList.addAll(p.getSynErrors());
			
	        Program prog = (Program)(s.value);
	        
	        
	        
			MyStatic.init(); // Universe scope
			log.info(prog.toString(""));
			SemanticAnalyzer semanticCheck = new SemanticAnalyzer();
			prog.traverseBottomUp(semanticCheck);
			
			errorList.addAll(semanticCheck.errorList);
			
	        log.info("Print calls = " + semanticCheck.printCallCount);
	        Tab.dump();
	        
	        
	        

	        if (!p.errorDetected && semanticCheck.passed()) {
	        	File objFile = new File(outputFilePath);
	        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
	        	if (objFile.exists())
	        		objFile.delete();
	        	
	        	// Code generation...
	        	Code.dataSize = semanticCheck.nVars + 5;
	        	CodeGenerator codeGenerator = new CodeGenerator();
	        	prog.traverseBottomUp(codeGenerator);
	        
	        	Code.mainPc = codeGenerator.getMainPc();
	        	Code.write(new FileOutputStream(objFile));
	        	log.info("Parsiranje uspesno zavrseno!");
	        	
	        	
	        }
	        else {
	        	log.error("Parsiranje NIJE uspesno zavrseno!");
	        }
	        
	        
	        if(errorList.isEmpty())
	        	return null;
	        else
	        	return errorList;
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
