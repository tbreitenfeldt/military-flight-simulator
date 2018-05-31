package acg.project.cli.parser;

import java.util.Scanner;

import acg.project.action.command.A_Command;
import acg.project.action.command.creational.create.*;

public class AgentParser extends Parser {

    public AgentParser(acg.project.action.ActionSet actionSet, String cmd) {
        
        this.actionSet = actionSet;
        this.cmd = cmd;
    }
    
    @Override
    public void parseCommand() throws Exception {
        
        Scanner cmdScanner = new Scanner(cmd);
        
        String token = "";
        
        A_Command the_command = null;
        
        if (cmdScanner.hasNext()) {
            
            token = cmdScanner.next();
            
            if (token.equals("CREATE")) {
                
                // CREATE... commands
                
                if (cmdScanner.hasNext()) {
                    
                    token = cmdScanner.next();
                    
                    if (token.equals("CARRIER")) {
                        the_command = createCommand_CREATE_CARRIER();
                        this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateCarrier) the_command);   
                    }
                    
                    else if (token.equals("FIGHTER")) {
                        the_command = createCommand_CREATE_TANKER();
                        this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateTanker) the_command);
                    }
                    
                    else if (token.equals("OLS_XMT")) {
                        the_command = createCommand_CREATE_OLS_XMT();
                        this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateOLSTransmitter) the_command);
                    }
                    
                    else if (token.equals("TAILHOOK")) {
                        the_command = createCommand_CREATE_TAILHOOK();
                        this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateTailhook) the_command);
                    }
                    else
                    	throw new Exception("Invalid command");
                }
                else
                	throw new Exception("Invalid command");
            }
            else
            	throw new Exception("Invalid command");
        }
        else
            throw new Exception("Invalid command");
        
        if (cmdScanner != null)
        	cmdScanner.close();
    }
    
	private CommandCreationalCreateCarrier createCommand_CREATE_CARRIER() {
        
        // process the command text and create the respective command object, return
        // can throw an exception or return null if there is an error when creating the
        // command
        
        return new CommandCreationalCreateCarrier(null, null, null, null, null, null, null, null, null);
    }
	
    private A_Command createCommand_CREATE_TAILHOOK() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_CREATE_OLS_XMT() {
		// TODO Auto-generated method stub
		return null;
	}

	private A_Command createCommand_CREATE_TANKER() {
		// TODO Auto-generated method stub
		return null;
	}
}
