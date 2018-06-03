package acg.project.cli.parser;

import java.util.Scanner;

import acg.project.action.ActionSet;
import acg.project.action.command.A_Command;
import acg.project.action.command.creational.create.*;
import acg.architecture.datatype.*;


public class AgentParser extends Parser {

    public AgentParser(ActionSet actionSet) {
        super(actionSet);
    }//end constructor
    
    @Override
    public void parseCommand(String cmd) throws ParseException {
        Scanner cmdScanner = new Scanner(cmd);
        String token = "";
        A_CommandCreationalCreate theCommand = null;
        
        cmdScanner.useDelimiter(" ");
        
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Command given is empty");
        }//end if
        
        token = cmdScanner.next();
        
        if ( !token.equalsIgnoreCase("create") || !cmdScanner.hasNext()) {
            throw new ParseException("Invalid command");
        }//end if
        
        token = cmdScanner.next();
        
        //parse agents 
        if (token.equalsIgnoreCase("carrier")) {
            theCommand = createCommandCarrier(cmdScanner);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateCarrier) theCommand);   
        } else if (token.equalsIgnoreCase("tanker")) {
            theCommand = createCommandTanker(cmdScanner);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateTanker) theCommand);
        } else if (token.equalsIgnoreCase("ols_xmt")) {
            theCommand = createCommandOLS_XMT(cmdScanner);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateOLSTransmitter) theCommand);
        } else if (token.equalsIgnoreCase("tailhook")) {
            theCommand = createCommandTailhook(cmdScanner);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateTailhook) theCommand);
        } else if (token.equalsIgnoreCase("fighter")) {
            theCommand = createCommandFighter(cmdScanner);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateFighter) theCommand);
        } else {
            throw new ParseException("Invalid command");
        }//end else
        
        cmdScanner.close();
    }//end method
    
    private CommandCreationalCreateCarrier createCommandCarrier(Scanner cmdScanner) throws ParseException {
        Identifier idAgentCarrier = null;
        Identifier idTemplateCarrier = null;
        Identifier idAgentCatapult = null;
        Identifier idAgentBarrier = null;
        Identifier idAgentTrap = null;
        Identifier idAgentOLS = null;
        CoordinateWorld coordinates = null;
        AngleNavigational heading = null;
        Speed speed = null;
        String token = "";
        
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Invalid command, create carrier must have a agent id");
        }//end if
        
        idAgentCarrier = new Identifier(cmdScanner.next());
        
        if ( !cmdScanner.next().equalsIgnoreCase("from")) {
            throw new ParseException("Invalid create agent command, expects \"from\"");
        }//end if
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Invalid command, expects carrier tid");
        }//end if
        
        idTemplateCarrier = new Idetifier(cmdScanner.next();
        
        if ( !cmdScanner.next().equalsIgnoreCase("with")) {
            throw new ParseException("Invalid create agent command, expects \"with\"");
        }//end if
        
        if ( !cmdScanner.next().equalsIgnoreCase("catapult")) {
            throw new ParseException("Invalid create agent command, expects \"catapult\"");
        }//end if
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Invalid command, expects catapult's agent id");
        }//end if
        
        idAgentCatapult = new Identifier(cmdScanner.next());
        
        if ( !cmdScanner.next().equalsIgnoreCase("barrier")) {
            throw new ParseException("Invalid create agent command, expects \"barrier\"");
        }//end if
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Invalid command, expects barrier's agent id");
        }//end if
        
        idAgentBarrier = new Identifier(cmdScanner.next());
        
        if ( !cmdScanner.next().equalsIgnoreCase("trap")) {
            throw new ParseException("Invalid create agent command, expects \"trap\"");
        }//end if
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Invalid command, expects trap's agent id");
        }//end if
        
        idAgentTrap = new Identifier(cmdScanner.next());
        token = cmdScanner.next();
        
        if (token.equalsIgnoreCase("ols") &&cmdScanner.hasNext()) {
            idAgentOLS = new Identifier(cmdScanner.next());
        }else {

            if (token.equalsIgnoreCase("optical-landing-system") && cmdScanner.hasNext() && cmdScanner.next().equalsIgnoreCase("transmitter")) {
                idAgentOLS = new Identifier(cmdScanner.next());
            } else {
                throw new ParseException("Invalid create agent command, expects \"ols\" or \"optical-landing-system transmitter\"");
            }//end else
        }//end end else
        
        //account for coordinates, heading 
        
        
        return new CommandCreationalCreateCarrier(idAgentCarrier, idTemplateCarrier, idAgentCatapult, idAgentBarrier, idAgentTrap, idAgentOLS, coordinates, heading, speed);
    }//end method
    
    private CommandCreationalCreateTailhook createCommandTailhook(Scanner cmdScanner) throws ParseException {
        return null;
    }//end method

    private CommandCreationalCreateOLSTransmitter createCommandOLS_XMT(Scanner cmdScanner) throws ParseException {
        return null;
    }//end method

    private CommandCreationalCreateTanker createCommandTanker(Scanner cmdScanner) throws ParseException {
        return null;
    }//end method

    private CommandCreationalCreateFighter createCommandFighter(Scanner cmdScanner) throws ParseException {
        return null;
    }//end method

}//end class
