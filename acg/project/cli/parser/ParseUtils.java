package acg.project.cli.parser;

import java.util.Scanner;
import acg.architecture.datatype.*;

public class ParseUtils {
    
    public static Identifier parseID(String token) throws ParseException {
        
        if (token.length() == 0)
            throw new ParseException();
        
        if (token.matches("^[a-zA-Z0-9_]+$")) // id's are alphanumeric, plus underscore
            return new Identifier(token);
        
        else
            throw new ParseException();
        
    }
    
    public static CoordinateCartesianRelative parseORIGIN(String token) throws ParseException {
        
    	Scanner tokenScanner = new Scanner(token);
    	
    	String tokenLeft = token.substring(0, token.indexOf(":"));
    	String tokenRight = token.substring(token.indexOf(":")+1, token.length());
    	
    	Double doubleLeft = Double.parseDouble(tokenLeft);
    	Double doubleRight = Double.parseDouble(tokenRight);
    	
    	return new CoordinateCartesianRelative(doubleLeft, doubleRight);
    }
    
    
}