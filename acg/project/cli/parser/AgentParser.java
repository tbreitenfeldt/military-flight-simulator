package acg.project.cli.parser;

import java.util.Scanner;
import java.util.NoSuchElementException;

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
        
        if ( !cmdScanner.hasNext()) {
            throw new ParseException("Command given is empty");
        }//end if
        
        token = cmdScanner.next();  //create
        
        if ( !token.equalsIgnoreCase("create")) {
            throw new ParseException("Invalid command");
        }//end if
        if (!cmdScanner.hasNext()) {
            throw new ParseException("Invalid command");
        }//end if
        
        token = cmdScanner.next();  //agent
        
        //parse agents 
        if (token.equalsIgnoreCase("carrier")) {
            theCommand = createCommandCarrier(cmd);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateCarrier) theCommand);   
        } else if (token.equalsIgnoreCase("tanker")) {
            theCommand = createCommandTanker(cmd);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateTanker) theCommand);
        } else if (token.equalsIgnoreCase("ols_xmt")) {
            theCommand = createCommandOLS_XMT(cmd);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateOLSTransmitter) theCommand);
        } else if (token.equalsIgnoreCase("tailhook")) {
            theCommand = createCommandTailhook(cmd);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateTailhook) theCommand);
        } else if (token.equalsIgnoreCase("fighter")) {
            theCommand = createCommandFighter(cmd);
            this.actionSet.getActionCreationalCreate().submit((CommandCreationalCreateFighter) theCommand);
        } else {
            throw new ParseException("Invalid command");
        }//end else
        
        cmdScanner.close();
    }//end method
    
    private CommandCreationalCreateCarrier createCommandCarrier(String cmd) throws ParseException {
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
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next(); //create 
            token += " " + cmdScanner.next();  //carrier
            
            if (token.equalsIgnoreCase("create carrier")) {
                throw new ParseException("Invalid create carrier command");
            }//end if
            
            token = cmdScanner.next();  //carrier agent ID
            idAgentCarrier = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("from")) {
                throw new ParseException("Invalid create carrier command, expects \"from\"");
            }//end if
            
            token = cmdScanner .next();  //carrier template id
            idTemplateCarrier = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("with")) {
                throw new ParseException("Invalid create carrier command, expects \"with\"");
            }//end if
            
            if ( !cmdScanner.next().equalsIgnoreCase("catapult")) {
                throw new ParseException("Invalid create carrier command, expects \"catapult\"");
            }//end if
            
            token = cmdScanner.next();  //catapult agent id
            idAgentCatapult = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("barrier")) {
                throw new ParseException("Invalid create carrier command, expects \"barrier\"");
            }//end if
            
            token = cmdScanner.next();  //barrier agent id
            idAgentBarrier = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("trap")) {
                throw new ParseException("Invalid create carrier command, expects \"trap\"");
            }//end if
            
            token = cmdScanner.next();  //trap agent id
            idAgentTrap = ParseUtils.parseID(token);
            
            token = cmdScanner.next();  //optical-landing-system or ols 
            if (token.equalsIgnoreCase("ols")) {
                idAgentOLS = ParseUtils.parseID(token);
            }else {
                
                if (token.equalsIgnoreCase("optical-landing-system") && cmdScanner.next().equalsIgnoreCase("transmitter")) {
                    token = cmdScanner.next();  //ols agent id
                    idAgentOLS = ParseUtils.parseID(token);
                } else {
                    throw new ParseException("Invalid create carrier command, expects \"ols\" or \"optical-landing-system transmitter\"");
                }//end else
            }//end end else
            
            if ( !cmdScanner.next().equalsIgnoreCase("at")) {
                throw new ParseException("Invalid create carrier command, expects \"at\"");
            }//end if
            if ( !cmdScanner.next().equalsIgnoreCase("coordinates")) {
                throw new ParseException("Invalid create carrier command, expects \"at\"");
            }//end if
            
            token = cmdScanner.next();  //coordinates in laditude/longitude
            coordinates = ParseUtils.parseCOORDINATES(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("heading")) {
                throw new ParseException("Invalid create carrier command, expects \"heading\"");
            }//end if
            
            token = cmdScanner.next();
            heading = ParseUtils.parseAZIMUTH(token);
            
            if ( !token.equalsIgnoreCase("speed")) {
                throw new ParseException("Invalid create carrier command, expects \"speed\"");
            }//end if 
            
            token = cmdScanner.next();
            speed = ParseUtils.parseSPEED(token);
            
            if (cmdScanner.hasNext()) {
                throw new ParseException("Correct command, but accessive sintax");
            }//end if
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        return new CommandCreationalCreateCarrier(idAgentCarrier, idTemplateCarrier, idAgentCatapult, idAgentBarrier, idAgentTrap, idAgentOLS, coordinates, heading, speed);
    }//end method
    
    private CommandCreationalCreateTailhook createCommandTailhook(String cmd) throws ParseException {
        return null;
    }//end method

    private CommandCreationalCreateOLSTransmitter createCommandOLS_XMT(String cmd) throws ParseException {
        return null;
    }//end method

    private CommandCreationalCreateTanker createCommandTanker(String cmd) throws ParseException {
        return null;
    }//end method

    private CommandCreationalCreateFighter createCommandFighter(String cmd) throws ParseException {
        return null;
    }//end method

}//end class
