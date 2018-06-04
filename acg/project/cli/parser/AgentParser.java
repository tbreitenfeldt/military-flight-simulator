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
            
            if ( !cmdScanner.next().equalsIgnoreCase("ols")) {
                throw new ParseException("Invalid create carrier command, expects \"ols\"");
            }//end end else
            
            token = cmdScanner.next();  //ols agent id
            idAgentOLS = ParseUtils.parseID(token);
            
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
        
        return new CommandCreationalCreateCarrier(idAgentCarrier, idTemplateCarrier, idAgentCatapult, idAgentBarrier, idAgentTrap,
                idAgentOLS, coordinates, heading, speed);
    }//end method
    
    private CommandCreationalCreateTailhook createCommandTailhook(String cmd) throws ParseException {
        Identifier idAgent = null;
        Identifier idTemplate = null;
        String token = "";
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next();
            token += " " + cmdScanner.next();
            
            if ( !token.equalsIgnoreCase("create tailhook")) {
                throw new ParseException("Invalid create tailhook command");
            }//end if
            
            token = cmdScanner.next();  //tailhook agent id
            idAgent = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("from")) {
                throw new ParseException("Invalid create tailhook command, expects \"from\"");
            }//end if
            
            token = cmdScanner.next();  //tailhook agent id
            idTemplate = ParseUtils.parseID(token);
            
            if (cmdScanner.hasNext()) {
                throw new ParseException("Correct create tailhook command, but accessive sintax");
            }//end if
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        return new CommandCreationalCreateTailhook(idAgent, idTemplate);
    }//end method
    
    private CommandCreationalCreateOLSTransmitter createCommandOLS_XMT(String cmd) throws ParseException {
        Identifier idAgent = null;
        Identifier idTemplate = null;
        String token = "";
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next();
            token += " " + cmdScanner.next();
            
            if ( !token.equalsIgnoreCase("create ols_xmt")) {
                throw new ParseException("Invalid create ols_xmt command");
            }//end if
            
            token = cmdScanner.next();  //ols_xmt agent id
            idAgent = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("from")) {
                throw new ParseException("Invalid create ols_xmt command, expects \"from\"");
            }//end if
            
            token = cmdScanner.next();  //ols_xmt templat id
            idTemplate = ParseUtils.parseID(token);
            
            if (cmdScanner.hasNext()) {
                throw new ParseException("Correct create ols_xmt command, but accessive sintax");
            }//end if
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        return new CommandCreationalCreateOLSTransmitter(idAgent, idTemplate);
    }//end method

    private CommandCreationalCreateTanker createCommandTanker(String cmd) throws ParseException {
        Identifier idAgentTanker = null;
        Identifier idTemplateTanker = null;
        Identifier idAgentBoom = null;
        CoordinateWorld coordinates = null;
        Altitude altitude = null;
        AngleNavigational heading = null;
        Speed speed = null;
        String token = null;
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next(); //create 
            token += " " + cmdScanner.next();  //tanker
            
            if (token.equalsIgnoreCase("create tanker")) {
                throw new ParseException("Invalid create tanker command");
            }//end if
            
            token = cmdScanner.next();  //tanker agent ID
            idAgentTanker = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("from")) {
                throw new ParseException("Invalid create tanker command, expects \"from\"");
            }//end if
            
            token = cmdScanner .next();  //tanker template id
            idTemplateTanker = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("with")) {
                throw new ParseException("Invalid create tanker command, expects \"with\"");
            }//end if
            
            if ( !cmdScanner.next().equalsIgnoreCase("boom")) {
                throw new ParseException("Invalid create tanker command, expects \"boom\"");
            }//end if
            
            token = cmdScanner.next();  //boom agent id
            idAgentBoom = ParseUtils.parseID(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("at")) {
                throw new ParseException("Invalid create tanker command, expects \"at\"");
            }//end if
            if ( !cmdScanner.next().equalsIgnoreCase("coordinates")) {
                throw new ParseException("Invalid create tanker command, expects \"at\"");
            }//end if
            
            token = cmdScanner.next();  //coordinates in laditude/longitude
            coordinates = ParseUtils.parseCOORDINATES(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("altitude")) {
                throw new ParseException("Invalid create tanker command, expects \"altitude\"");
            }//end if
            
            token = cmdScanner.next();  //altitude 
            altitude = ParseUtils.parseALTITUDE(token);
            
            if ( !cmdScanner.next().equalsIgnoreCase("heading")) {
                throw new ParseException("Invalid create tanker command, expects \"heading\"");
            }//end if
            
            token = cmdScanner.next();  //course
            heading = ParseUtils.parseAZIMUTH(token);
            
            if ( !token.equalsIgnoreCase("speed")) {
                throw new ParseException("Invalid create tanker command, expects \"speed\"");
            }//end if 
            
            token = cmdScanner.next();  //speed
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
        
        return new CommandCreationalCreateTanker(idAgentTanker, idTemplateTanker, idAgentBoom, coordinates, altitude,
                heading, speed);
    }//end method

    private CommandCreationalCreateFighter createCommandFighter(String cmd) throws ParseException {
        return null;
    }//end method

}//end class
