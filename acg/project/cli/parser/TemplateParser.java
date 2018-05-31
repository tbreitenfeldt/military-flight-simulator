package acg.project.cli.parser;

import java.util.Scanner;

import acg.project.action.ActionSet;
import acg.project.action.command.A_Command;
import acg.project.action.command.creational.define.*;


public class TemplateParser implements Parser {

    private ActionSet actionSet;

    public TemplateParser(ActionSet actionSet) {
        this.actionSet = actionSet;
    }
    
    @Override
    public void parseCommand(String cmd) throws ParseException {
        
        Scanner cmdScanner = new Scanner(cmd);
        
        String token = "";
        
        A_Command the_command = null;
        
        if (cmdScanner.hasNext()) {
            
            token = cmdScanner.next();
        
            if (token.equals("DEFINE")) {
                
                // DEFINE... commands
                
                if (cmdScanner.hasNext()) {
                   
                    token = cmdScanner.next();
                    
                    if (token.equals("TRAP"))
                        the_command = createCommand_DEFINE_TRAP();
                    
                    else if (token.equals("CATAPULT"))
                        the_command = createCommand_DEFINE_CATAPULT();
                    
                    else if (token.equals("OLS_XMT"))
                        the_command = createCommand_DEFINE_OLS_XMT();
                    
                    else if (token.equals("CARRIER"))
                        the_command = createCommand_DEFINE_CARRIER();
                    
                    else if (token.equals("FIGHTER"))
                        the_command = createCommand_DEFINE_FIGHTER();
                    
                    else if (token.equals("TANKER"))
                        the_command = createCommand_DEFINE_TANKER();
                    
                    else if (token.equals("BOOM")) {
                        
                        if (cmdScanner.hasNext()) {
                            
                            token = cmdScanner.next();
                            
                            if (token.equals("MALE"))
                                the_command = createCommand_DEFINE_BOOM_MALE();
                            
                            else if (token.equals("FEMALE"))
                                the_command = createCommand_DEFINE_BOOM_FEMALE();
                        }
                    }
                    
                    else if (token.equals("BARRIER"))
                        the_command = createCommand_DEFINE_BARRIER();
                }
                
                if (the_command != null)
                	this.actionSet.getActionCreationalDefine().submit((A_CommandCreationalDefine) the_command);
                
            }
            
            else if (token.equals("SHOW")) {
                the_command = createCommand_SHOW_TEMPLATE();
                
                if (the_command != null)
                	this.actionSet.getActionCreationalDefine().submit((CommandCreationalShowTemplate) the_command);
            }
        }
        cmdScanner.close();
        

        if (the_command == null)
            throw new ParseException("Invalid command");
    }
	
    private CommandCreationalDefineTrap createCommand_DEFINE_TRAP() {
        
        // process the command text and create the command object and return.
        // if the command cannot be created due to invalid syntax, return null
        // or throw an ParseException
        
        return new CommandCreationalDefineTrap(null, null, null, null, null, null, null);
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
