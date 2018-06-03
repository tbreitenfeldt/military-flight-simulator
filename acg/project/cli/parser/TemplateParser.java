package acg.project.cli.parser;

import java.util.Scanner;

import acg.project.action.ActionSet;
import acg.project.action.command.A_Command;
import acg.project.action.command.creational.define.*;
import acg.architecture.datatype.*;


public class TemplateParser extends Parser {
    
    public TemplateParser(ActionSet actionSet) {
        super(actionSet);
    }
    
    @Override
    public void parseCommand(String cmd) throws ParseException {
        
        Scanner cmdScanner = new Scanner(cmd);
        
        String token = "";
        
        A_Command the_command = null;
        
        if (cmdScanner.hasNext()) {
            
            token = cmdScanner.next();
        
            if (token.equalsIgnoreCase("DEFINE")) {
                
                // DEFINE... commands
                
                if (cmdScanner.hasNext()) {
                   
                    token = cmdScanner.next();
                    
                    if (token.equalsIgnoreCase("TRAP"))
                        the_command = createCommand_DEFINE_TRAP(cmd);
                    
                    else if (token.equalsIgnoreCase("CATAPULT"))
                        the_command = createCommand_DEFINE_CATAPULT(cmd);
                    
                    else if (token.equalsIgnoreCase("OLS_XMT"))
                        the_command = createCommand_DEFINE_OLS_XMT(cmd);
                    
                    else if (token.equalsIgnoreCase("CARRIER"))
                        the_command = createCommand_DEFINE_CARRIER(cmd);
                    
                    else if (token.equalsIgnoreCase("FIGHTER"))
                        the_command = createCommand_DEFINE_FIGHTER(cmd);
                    
                    else if (token.equalsIgnoreCase("TANKER"))
                        the_command = createCommand_DEFINE_TANKER(cmd);
                    
                    else if (token.equalsIgnoreCase("BOOM")) {
                        
                        if (cmdScanner.hasNext()) {
                            
                            token = cmdScanner.next();
                            
                            if (token.equalsIgnoreCase("MALE"))
                                the_command = createCommand_DEFINE_BOOM_MALE(cmd);
                            
                            else if (token.equalsIgnoreCase("FEMALE"))
                                the_command = createCommand_DEFINE_BOOM_FEMALE(cmd);
                        }
                    }
                    
                    else if (token.equalsIgnoreCase("BARRIER"))
                        the_command = createCommand_DEFINE_BARRIER(cmd);
                }
                
                if (the_command != null)
                	this.actionSet.getActionCreationalDefine().submit((A_CommandCreationalDefine) the_command);
                
            }
            
            else if (token.equalsIgnoreCase("SHOW")) {
                the_command = createCommand_SHOW_TEMPLATE(cmd);
                
                if (the_command != null)
                	this.actionSet.getActionCreationalDefine().submit((CommandCreationalShowTemplate) the_command);
            }
        }
        cmdScanner.close();
        

        if (the_command == null)
            throw new ParseException("Invalid command");
    }
	
    public CommandCreationalDefineTrap createCommand_DEFINE_TRAP(String cmd) throws ParseException {
        
        // DEFINE TRAP <tid> ORIGIN <origin> AZIMUTH <azimuth> WIDTH <distance> LIMIT WEIGHT <weight> 
        // SPEED <speed> MISS <percent>
        
        Scanner cmdScanner = new Scanner(cmd);
        String token = "";
        CommandCreationalDefineTrap TRAP;
        
        try {
            
        	token = cmdScanner.next(); // DEFINE
        	token += " " + cmdScanner.next(); // TRAP
        	if(!token.equalsIgnoreCase("DEFINE TRAP"))
        		throw new ParseException();
        	
            token = cmdScanner.next(); // <tid>
            Identifier tid = ParseUtils.parseID(token);
            
            token = cmdScanner.next(); // ORIGIN
            if (!token.equalsIgnoreCase("ORIGIN"))
            	throw new ParseException();
            
            token = cmdScanner.next(); // <origin>
            CoordinateCartesianRelative origin = ParseUtils.parseORIGIN(token);
            
            token = cmdScanner.next(); // AZIMUTH
            if (!token.equalsIgnoreCase("AZIMUTH"))
            	throw new ParseException();
            
            token = cmdScanner.next(); // <azimuth>
            AngleNavigational azimuth = ParseUtils.parseAZIMUTH(token);
            
            token = cmdScanner.next(); // WIDTH
            if (!token.equalsIgnoreCase("WIDTH"))
            	throw new ParseException();
            
            token = cmdScanner.next(); // <width>
            Distance width = ParseUtils.parseDISTANCE(token);
            
            token = cmdScanner.next(); // LIMIT
            token += " " + cmdScanner.next(); // WEIGHT
            if (!token.equalsIgnoreCase("LIMIT WEIGHT"))
            	throw new ParseException();
            
            token = cmdScanner.next(); // <weight>
            Weight weight = ParseUtils.parseWEIGHT(token);
            
            token = cmdScanner.next(); // SPEED
            if (!token.equalsIgnoreCase("SPEED"))
            	throw new ParseException();
            
            token = cmdScanner.next(); // <speed>
            Speed speed = ParseUtils.parseSPEED(token);
            
            token = cmdScanner.next(); // MISS
            if (!token.equalsIgnoreCase("MISS"))
            	throw new ParseException();
            
            token = cmdScanner.next(); // <percent>
            Percent percent = ParseUtils.parsePERCENT(token);
            
            if (cmdScanner.hasNext()) // there should be nothing left in the command text
            	throw new ParseException();
            
            return new CommandCreationalDefineTrap (tid, origin, azimuth, width, weight, speed, percent);
        }
        catch (Exception e){
            throw new ParseException();
        }
    }
	
    public A_Command createCommand_SHOW_TEMPLATE(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_BARRIER(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_BOOM_FEMALE(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_BOOM_MALE(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_TANKER(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_FIGHTER(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_CARRIER(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_OLS_XMT(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	public A_Command createCommand_DEFINE_CATAPULT(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}


}
