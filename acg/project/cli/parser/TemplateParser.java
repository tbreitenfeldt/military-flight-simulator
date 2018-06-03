package acg.project.cli.parser;

import java.util.Scanner;

import acg.project.action.ActionSet;
import acg.project.action.command.A_Command;
import acg.project.action.command.creational.define.*;
import acg.architecture.datatype.*;


public class TemplateParser extends Parser {

    private String cmd;
    
    public TemplateParser(ActionSet actionSet) {
        super(actionSet);
    }
    
    @Override
    public void parseCommand(String cmd) throws ParseException {
        
        this.cmd = cmd;
        
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
                        the_command = createCommand_DEFINE_TRAP();
                    
                    else if (token.equalsIgnoreCase("CATAPULT"))
                        the_command = createCommand_DEFINE_CATAPULT();
                    
                    else if (token.equalsIgnoreCase("OLS_XMT"))
                        the_command = createCommand_DEFINE_OLS_XMT();
                    
                    else if (token.equalsIgnoreCase("CARRIER"))
                        the_command = createCommand_DEFINE_CARRIER();
                    
                    else if (token.equalsIgnoreCase("FIGHTER"))
                        the_command = createCommand_DEFINE_FIGHTER();
                    
                    else if (token.equalsIgnoreCase("TANKER"))
                        the_command = createCommand_DEFINE_TANKER();
                    
                    else if (token.equalsIgnoreCase("BOOM")) {
                        
                        if (cmdScanner.hasNext()) {
                            
                            token = cmdScanner.next();
                            
                            if (token.equalsIgnoreCase("MALE"))
                                the_command = createCommand_DEFINE_BOOM_MALE();
                            
                            else if (token.equalsIgnoreCase("FEMALE"))
                                the_command = createCommand_DEFINE_BOOM_FEMALE();
                        }
                    }
                    
                    else if (token.equalsIgnoreCase("BARRIER"))
                        the_command = createCommand_DEFINE_BARRIER();
                }
                
                if (the_command != null)
                	this.actionSet.getActionCreationalDefine().submit((A_CommandCreationalDefine) the_command);
                
            }
            
            else if (token.equalsIgnoreCase("SHOW")) {
                the_command = createCommand_SHOW_TEMPLATE();
                
                if (the_command != null)
                	this.actionSet.getActionCreationalDefine().submit((CommandCreationalShowTemplate) the_command);
            }
        }
        cmdScanner.close();
        

        if (the_command == null)
            throw new ParseException("Invalid command");
    }
	
    private CommandCreationalDefineTrap createCommand_DEFINE_TRAP() throws ParseException {
        
        // DEFINE TRAP <tid> ORIGIN <origin> AZIMUTH <azimuth> WIDTH <distance> LIMIT WEIGHT <weight> 
        // SPEED <speed> MISS <percent>
        
        Scanner cmdScanner = new Scanner(this.cmd);
        String token = "";
        CommandCreationalDefineTrap TRAP;
        
        try {
            cmdScanner.next(); // DEFINE
            cmdScanner.next(); // TRAP
        }
        catch (Exception e) {
            throw new ParseException();
        }
        
        try {
            
            token = cmdScanner.next(); // <tid>
            Identifier tid = ParseUtils.parseID(token);
            
            cmdScanner.next(); // ORIGIN
            
            token = cmdScanner.next(); // <origin>
            CoordinateCartesianRelative origin = ParseUtils.parseORIGIN(token);
            
            cmdScanner.next(); // AZIMUTH
            
            token = cmdScanner.next(); // <azimuth>
            // AngleNavigational azimuth = ParseUtils.parseAZIMUTH(token);
            
            
            return new CommandCreationalDefineTrap (tid, origin, null, null, null, null, null);
        }
        catch (Exception e){
            throw new ParseException();
        }
    }
	
    private A_Command createCommand_SHOW_TEMPLATE() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_BARRIER() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_BOOM_FEMALE() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_BOOM_MALE() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_TANKER() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_FIGHTER() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_CARRIER() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_OLS_XMT() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_DEFINE_CATAPULT() {
		// TODO Auto-generated method stub
		return null;
	}


}
