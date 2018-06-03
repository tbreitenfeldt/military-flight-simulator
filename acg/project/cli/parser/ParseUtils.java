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
    
    public static CoordinateCartesianRelative parseORIGIN(String token) {
    	
    	String tokenLeft = token.substring(0, token.indexOf(":"));
    	String tokenRight = token.substring(token.indexOf(":")+1, token.length());
    	
    	Double doubleLeft = Double.parseDouble(tokenLeft);
    	Double doubleRight = Double.parseDouble(tokenRight);
    	
    	return new CoordinateCartesianRelative(doubleLeft, doubleRight);
    }
    
    public static AngleNavigational parseAZIMUTH(String token) throws ParseException {
    	
    	if (token.contains("-") || token.contains("+"))
    		throw new ParseException();
    	
    	Integer angle = Integer.parseInt(token);
    	
    	if (angle >= 0 && angle < 360)
    		return new AngleNavigational(angle);
    	
    	throw new ParseException();
    }

    public static Distance parseDISTANCE(String token) throws ParseException {
    	
    	if (token.contains("-") || token.contains("+"))
    		throw new ParseException();
    	
    	Double distance = Double.parseDouble(token);
    	
    	return new Distance(distance);
    }
    
    public static Weight parseWEIGHT(String token) throws ParseException {
    	
    	if (token.contains("-") || token.contains("+"))
    		throw new ParseException();
    	
    	Integer weight = Integer.parseInt(token);
    	
    	return new Weight(weight);
    }
    
    public static Speed parseSPEED(String token) throws ParseException {
    	
    	if (token.contains("-") || token.contains("+"))
    		throw new ParseException();
    	
    	Integer speed = Integer.parseInt(token);
    	
    	return new Speed(speed);
    }
    
    public static Percent parsePERCENT(String token) throws ParseException {
    	
    	if (token.contains("-") || token.contains("+"))
    		throw new ParseException();
    	
    	Integer percent = Integer.parseInt(token);
    	
    	if (percent < 0 || percent > 100)
    		throw new ParseException();
    	
    	return new Percent(percent);
    }
    
}