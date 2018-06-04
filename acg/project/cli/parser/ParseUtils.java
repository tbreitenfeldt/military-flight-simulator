package acg.project.cli.parser;

import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;

import acg.architecture.datatype.*;


public class ParseUtils {

    public static Identifier parseID(String token) throws ParseException {
        
        if (token.length() == 0)
            throw new ParseException();
        
        if (token.matches("^[a-zA-Z0-9_]+$")) // id's are alphanumeric, plus underscore
            return new Identifier(token);
        
        else
            throw new ParseException();
        
    }//end method
    
    public static CoordinateCartesianRelative parseORIGIN(String token) {
        
        String tokenLeft = token.substring(0, token.indexOf(":"));
        String tokenRight = token.substring(token.indexOf(":")+1, token.length());
        
        Double doubleLeft = Double.parseDouble(tokenLeft);
        Double doubleRight = Double.parseDouble(tokenRight);
        
        return new CoordinateCartesianRelative(doubleLeft, doubleRight);
    }//end method
    
    public static AngleNavigational parseAZIMUTH(String token) throws ParseException {
        
        if (token.contains("-") || token.contains("+"))
            throw new ParseException();
        
        Integer angle = Integer.parseInt(token);
        
        if (angle >= 0 && angle < 360)
            return new AngleNavigational(angle);
        
        throw new ParseException();
    }//end method
    
    public static Distance parseDISTANCE(String token) throws ParseException {
        
        if (token.contains("-") || token.contains("+"))
            throw new ParseException();
        
        Double distance = Double.parseDouble(token);
        
        return new Distance(distance);
    }//end method
    
    public static Weight parseWEIGHT(String token) throws ParseException {
        
        if (token.contains("-") || token.contains("+"))
            throw new ParseException();
        
        Integer weight = Integer.parseInt(token);
        
        return new Weight(weight);
    }//end method
    
    public static Speed parseSPEED(String token) throws ParseException {
        
        if (token.contains("-") || token.contains("+"))
            throw new ParseException();
        
        Integer speed = Integer.parseInt(token);
        
        return new Speed(speed);
    }//end method
    
    public static Percent parsePERCENT(String token) throws ParseException {
        
        if (token.contains("-") || token.contains("+"))
            throw new ParseException();
        
        Integer percent = Integer.parseInt(token);
        
        if (percent < 0 || percent > 100)
            throw new ParseException();
        
        return new Percent(percent);
    }//end method
    
    public static CoordinateWorld parseCOORDINATES(String token) throws ParseException {
        Latitude laditude = null;
        Longitude longitude = null;
        int degrees = 0;
        int minutes = 0;
        double seconds = 0.0;
        Scanner coordinateScanner = null;
        
        //Insure that the token is in the format int*int'decimal(with or with out .)"/int*int'decimal"
        if ( !token.matches("\\d+\\*\\d+\'\\d+\\.{0,1}\\d+\"/\\d+\\*\\d+\'\\d+\\.{0,1}\\d+\"")) {
            throw new ParseException("Coordinates have invalid format");
        }//end if
        
        coordinateScanner = new Scanner(token);
        coordinateScanner.useDelimiter("[*\'\"/]");
        
        try {
            degrees = coordinateScanner.nextInt();
            
            if (degrees < 0 || degrees > 90) {
                throw new ParseException("Laditude degrees must be between 0 and 90 inclusive");
            }//end if
            
            minutes = coordinateScanner.nextInt();
            
            if (minutes < 0 || minutes > 59) {
                throw new ParseException("Laditude minutes must be in the range 0 to 59 inclusive");
            }//end if
            
            seconds = coordinateScanner.nextDouble();
            
            if (seconds < 0 || seconds > 59) {
                throw new ParseException("Laditude seconds must be in the range 0 to 59 inclusive");
            }//end if
            
            laditude = new Latitude(degrees, minutes, seconds);
            coordinateScanner.next();  //eat up blank space seperating laditude and longitude 
            
            degrees = coordinateScanner.nextInt();
            
            if (degrees < 0 || degrees > 180) {
                throw new ParseException("Longitude degrees must be between 0 and 180 inclusive");
            }//end if
            
            minutes = coordinateScanner.nextInt();
            
            if (minutes < 0 || minutes > 59) {
                throw new ParseException("Longitude minutes must be in the range 0 to 59 inclusive");
            }//end if
        
            seconds = coordinateScanner.nextDouble();
            
            if (seconds < 0 || seconds > 59) {
                throw new ParseException("Longitude seconds must be in the range 0 to 59 inclusive");
            }//end if
            
            longitude = new Longitude(degrees, minutes, seconds);
        } catch(InputMismatchException ime) {  //catch exceptions for if nextInt or nextDouble are unable to parse correctly 
            throw new ParseException("invalid format for coordinates");
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            throw new ParseException("Invalid coordinates");
        }//end catch
        
        return new CoordinateWorld(laditude, longitude);
    }//end method
    
    public static Altitude parseALTITUDE(String token) throws ParseException {
        double altitude = 0.0;
        
        if (token.isEmpty()) {
            throw new ParseException("Token can not be empty");
        }//end if
        
        try {
            altitude = Double.parseDouble(token);
            
            if (altitude < 0) {
                throw new ParseException("Invalid altitude, it must be greater or equal to 0.0");
            }//end if
            
        } catch(NumberFormatException nfe) {
            throw new ParseException("Invalid altitude");
        }//end catch
        
        return new Altitude(altitude);
    }//end method
    
}//end class