package acg.project.cli.parser;

import java.util.Scanner;
import java.util.NoSuchElementException;

import acg.project.action.ActionSet;
import acg.project.action.command.miscellaneous.*;
import acg.architecture.datatype.*;


public class MiscParser extends Parser {

    public MiscParser(ActionSet actionSet) {
        super(actionSet);
    }//end constructor 
    
    @Override
    public void parseCommand(String cmd) throws ParseException {
        Scanner cmdScanner = new Scanner(cmd);
        String token = "";
        A_CommandMisc theCommand = null;
        
        if ( !cmdScanner.hasNext()) {
            cmdScanner.close();
                cmdScanner.close();
                throw new ParseException("Command given is empty");
        }//end if
        
        token = cmdScanner.next();
        
        if (token.equalsIgnoreCase("@clock")) {
            
            if ( !cmdScanner.hasNext()) {
                theCommand = miscellaneousCommandShowClock(token);
                this.actionSet.getActionMisc().submit((CommandMiscDoShowClock) theCommand);
            } else if (cmdScanner.hasNextDouble()) {
                theCommand = miscellaneousCommandSetClockRate(token);
                this.actionSet.getActionMisc().submit((CommandMiscDoShowClock) theCommand);
            } else {
                token = cmdScanner.next();
                
                if (token.equalsIgnoreCase("pause") || token.equalsIgnoreCase("resume")) {
                    theCommand = miscellaneousCommandSetClockRunning(token);
                    this.actionSet.getActionMisc().submit((CommandMiscDoSetClockRunning) theCommand);
                } else if (token.equalsIgnoreCase("update")) {
                    theCommand = miscellaneousCommandUpdateClock(token);
                    this.actionSet.getActionMisc().submit((CommandMiscDoClockUpdate) theCommand);
                } else {
                    cmdScanner.close();
                    throw new ParseException("Invalid argument for clock");
                }//end else
            }//end else
            
        } else if (token.equalsIgnoreCase("@run")) {
            theCommand = miscellaneousCommandRun(token);
            this.actionSet.getActionMisc().submit((CommandMiscDoRun) theCommand);
        } else if (token.equalsIgnoreCase("@wait")) {
            theCommand = miscellaneousCommandWait(token);
            this.actionSet.getActionMisc().submit((CommandMiscDoWait) theCommand);
        } else {
            cmdScanner.close();
            throw new ParseException("Invalid command");
        }//end else
        
        cmdScanner.close();
    }//end method
    
    private CommandMiscDoSetClockRate miscellaneousCommandSetClockRate(String cmd) throws ParseException {
        Rate rate = null;
        String token = "";
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next();
            
            if ( !token.equalsIgnoreCase("@clock")) {
                cmdScanner.close();
                throw new ParseException("Invalid command");
            }//end if
            if ( !cmdScanner.hasNextInt()) {
                cmdScanner.close();
                throw new ParseException("Invalid @clock command, expects an integer");
            }//end if
            
            token = cmdScanner.next();
            rate = ParseUtils.parseRATE(token);
            
            if (cmdScanner.hasNext()) {
                cmdScanner.close();
                throw new ParseException("Correct command, but accessive sintax");
            }//end if
            
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            cmdScanner.close();
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        
        return new CommandMiscDoSetClockRate(rate);
    }//end method
    
    private CommandMiscDoShowClock miscellaneousCommandShowClock(String cmd) throws ParseException {
        if ( !cmd.equalsIgnoreCase("@clock")) {
            throw new ParseException("Invalid command");
        }//end if
        
        return new CommandMiscDoShowClock();
    }//end method
    
    private CommandMiscDoSetClockRunning miscellaneousCommandSetClockRunning(String cmd) throws ParseException {
        boolean isRunning = false;
        String token = "";
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next();
            
            if ( !cmdScanner.next().equalsIgnoreCase("@clock")) {
                cmdScanner.close();
                throw new ParseException("Invalid command");
            }//end if
            
            token = cmdScanner.next();
            
            if (token.equalsIgnoreCase("pause")) {
                isRunning = false;
            } else if (token.equalsIgnoreCase("resume")) {
                isRunning = true;
            } else {
                cmdScanner.close();
                throw new ParseException("Invalid @clock command, expects \"pause\" or \"resume\"");
            }//end else 
            
            if (cmdScanner.hasNext()) {
                cmdScanner.close();
                throw new ParseException("Correct command, but accessive sintax");
            }//end if
            
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            cmdScanner.close();
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        
        return new CommandMiscDoSetClockRunning(isRunning);
    }//end method
    
    private CommandMiscDoClockUpdate miscellaneousCommandUpdateClock(String cmd) throws ParseException {
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            if ( !cmdScanner.next().equalsIgnoreCase("@clock")) {
                cmdScanner.close();
                throw new ParseException("Invalid command");
            }//end if
            if ( !cmdScanner.next().equalsIgnoreCase("update")) {
                cmdScanner.close();
                throw new ParseException("Invalid @clock command, expects \"update\"");
            }//end if
            
            if (cmdScanner.hasNext()) {
                cmdScanner.close();
                throw new ParseException("Correct command, but accessive sintax");
            }//end if
            
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            cmdScanner.close();
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        
        return new CommandMiscDoClockUpdate();
    }//end method
    
    private CommandMiscDoRun miscellaneousCommandRun(String cmd) throws ParseException {
        String filename = "";
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            if ( !cmdScanner.next().equalsIgnoreCase("@run")) {
                cmdScanner.close();
                throw new ParseException("Invalid command");
            }//end if
            
            cmdScanner.useDelimiter("@run");
            
            filename = cmdScanner.next();
            
            if (cmdScanner.hasNext()) {
                cmdScanner.close();
                throw new ParseException("Correct command, but accessive sintax");
            }//end if
            
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            cmdScanner.close();
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        
        return new CommandMiscDoRun(filename);
    }//end method
    
    private CommandMiscDoWait miscellaneousCommandWait(String cmd) throws ParseException {
        Rate rate = null;
        String token = "";
        Scanner cmdScanner = new Scanner(cmd);
        
        try {
            token = cmdScanner.next();
            
            if ( !token.equalsIgnoreCase("@wait")) {
                cmdScanner.close();
                throw new ParseException("Invalid command");
            }//end if
            if ( !cmdScanner.hasNextInt()) {
                cmdScanner.close();
                throw new ParseException("Invalid @wait command, expects an integer");
            }//end if
            
            token = cmdScanner.next();
            rate = ParseUtils.parseRATE(token);
            
            if (cmdScanner.hasNext()) {
                cmdScanner.close();
                throw new ParseException("Correct command, but accessive sintax");
            }//end if
            
        } catch(ParseException pe) {
            throw pe;
        } catch(NoSuchElementException nsee) {  //catch exceptions if there are no characters left in the scanner
            cmdScanner.close();
            throw new ParseException("Incomplete command.");
        }//end catch
        
        cmdScanner.close();
        
        return new CommandMiscDoWait(rate);
    }//end method
    
}//end class