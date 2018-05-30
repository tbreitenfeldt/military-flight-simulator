
package acg.project.cli.parser;

public class CommandParser {
    
    private acd.project.action.ActionSet actionSet;
    private String cmd;
    
    public CommandParser (acg.project.action.ActionSet actionSet, String cmd) {
        
        this.actionSet = actionSet;
        this.cmd = cmd;
    }
    
    public void interpret() {
        
        // remove leading and trailing spaces
        cmd = cmd.trim();
        
        Scanner cmdScanner = new Scanner(cmd);
        String token = "";
        
        if (cmdScanner.hasNext()) {
            
            token = cmdScanner.next();
            
            if (token.equals("DEFINE") {
                
                // DEFINE... commands
                
                if (cmdScanner.hasNext()) {
                   
                    token = cmdScanner.next();
                    
                    if (token.equals("TRAP"))
                        processDEFINE_TRAP();
                    
                    else if (token.equals("CATAPULT"))
                        processDEFINE_CATAPULT();
                    
                    else if (token.equals("OLS_XMT"))
                        processDEFINE_OLS_XMT();
                    
                    else if (token.equals("CARRIER"))
                        processDEFINE_CARRIER();
                    
                    else if (token.equals("FIGHTER"))
                        processDEFINE_FIGHTER();
                    
                    else if (token.equals("TANKER"))
                        processDEFINE_TANKER();
                    
                    else if (token.equals("BOOM")) {
                        
                        if (cmdScanner.hasNext()) {
                            
                            token = cmdScanner.next();
                            
                            if (token.equals("MALE"))
                                processDEFINE_BOOM_MALE();
                            
                            else if (token.equals("FEMALE"))
                                processDEFINE_BOOM_FEMALE();
                            
                            else
                                throw new Exception("Not a valid command");
                        }
                        
                        else
                            throw new Exception ("Not a valid DEFINE BOOM command");
                        
                    }
                    
                    else if (token.equals("BARRIER"))
                        processDEFINE_BARRIER();
                    
                    else
                        throw new Exception("Not a valid DEFINE command");
                }
                
                else
                    throw new Exception("Not a valid command");
                
            }
            
            else if (token.equals("SHOW") {
                
                // SHOW... commands
                
                if (cmdScanner.hasNext()) {
                    
                    token = cmdScanner.next();
                    
                    if (token.equals("TEMPLATE"))
                        processSHOW_TEMPLATE();
                    
                    else
                        throw new Exception("Not a valid command");
                }
                
                else
                    throw new Exception("Not a valid SHOW command");
                
            }
            
            else if (token.equals("CREATE") {
                
                // CREATE... commands
                
                if (cmdScanner.hasNext()) {
                    
                    token = cmdScanner.next();
                    
                    if (token.equals("CARRIER"))
                        processCREATE_CARRIER();
                    
                    else if (token.equals("FIGHTER"))
                        processCREATE_FIGHTER();
                    
                    else if (token.equals("TANKER"))
                        processCREATE_TANKER();
                    
                    else if (token.equals("OLS_XMT"))
                        processCREATE_OLS_XMT();
                    
                    else if (token.equals("TAILHOOK"))
                        processCREATE_TAILHOOK();
                    
                    else
                        throw new Exception("Not a valid CREATE command");
                }
                
                else
                    throw new Exception("Not a valid CREATE command");
                
            }
            
            else if (token.equals("POPULATE") {
                
                // POPULATE... commands
                
                if (cmdScanner.hasNext()) {
                    
                    token = cmdScanner.next();
                    
                    if (token.equals("CARRIER"))
                        processPOPULATE_CARRIER();
                    
                    else if (token.equals("WORLD"))
                        processPOPULATE_WORLD_WITH();
                    
                    else
                        throw new Exception("Not a valid command");
                }
                
                else
                    throw new Exception("Not a valid POPULATE command");
                
            }
            
            else if (token.equals("COMMIT") {
                
                // COMMIT... commands
                processCOMMIT();
            }
            
            else if (token.equals("DO") {
                
                // DO... commands
                
                if (cmdScanner.hasNext()) {
                    
                    // pass over the <aid>
                    cmdScanner.next();
                    
                    if (cmdScanner.hasNext()) {
                        
                        token = cmdScanner.next();
                        
                        if (token.equals("ASK"))
                            processDO_ASK();
                        
                        else if (token.equals("CATAPULT"))
                            processDO_CATAPULT();
                        
                        else if (token.equals("SET")) {
                            
                            if (cmdScanner.hasNext()) {
                                
                                token = cmdScanner.next();
                                
                                if (token.equals("HEADING"))
                                    processDO_SET_HEADING();
                                
                                else
                                    throw new Exception("Not a valid command");
                            }
                            
                            else
                                throw new Exception("Not a valid command");
                        }
                        
                        else if (token.equals("BOOM"))
                            processDO_BOOM();
                        
                        else
                            throw new Exception("Not a valid command");
                            
                    }
                    
                    else
                        throw new Exception("Not a valid DO command");
                }
                
                else
                    throw new Exception("Not a valid DO command");
                
            }
            
            else if (token.equals("@DO") {
                
                // @DO... commands
                
                if (cmdScanner.hasNext()) {
                    
                    // pass over the <aid>
                    cmdScanner.next();
                    
                    if (cmdScanner.hasNext()) {
                        
                        token = cmdScanner.next();
                        
                        if (token.equals("FORCE") {
                            
                            if (cmdScanner.hasNext()) {
                                
                                token = cmdScanner.next();
                                
                                if (token.equals("COORDINATES")) {
                                    
                                    if (cmd.contains("HEADING") && cmd.contains("SPEED"))
                                        processDO_FORCE_ALL();
                                    
                                    else
                                        processDO_FORCE_COORDINATES();
                                }
                                
                                else if (token.equals("HEADING"))
                                    processDO_FORCE_HEADING();
                                
                                else
                                    throw new Exception("Not a valid command");
                            }
                            
                            else
                                throw new Exception("Not a valid command");
                        }
                    
                        else
                            throw new Exception("Not a valid command");
                    }
                    
                    else
                        throw new Exception("Not a valid command");

                }
                
                else
                    throw new Exception("Not a valid @DO command");
                
            }
            
            else if (token.equals("@CLOCK") {
                
                // @CLOCK... commands
                
            }
            
            else if (token.equals("@RUN") {
                
                // @RUN... commands
                
                processRUN();
                
            }
            
            else if (token.equals("@WAIT") {
                
                // @WAIT... commands
                
                processWAIT();
            }
            
            else
                throw new Exception("Not a valid command");
            
        }
        else
            throw new Exception("No command detected");
        
    }
    
}