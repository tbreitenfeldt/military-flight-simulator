
package acg.project.cli.parser;

import java.util.Hashtable;
import java.util.Scanner;

public class CommandParser {
    
    private String cmd;
    private Hashtable<String, Parser> parsers;
    private acg.project.action.ActionSet actionSet;

	public CommandParser(acg.project.action.ActionSet actionSet, String cmd) {
        
        this.cmd = cmd;
        this.actionSet = actionSet;
    }

	public void interpret() {
        
		parsers = new Hashtable<String, Parser>();
		
		parsers.put("DEFINE", new TemplateParser(this.actionSet, cmd));
		parsers.put("SHOW", new TemplateParser(this.actionSet, cmd));
		parsers.put("CREATE", new AgentParser(this.actionSet, cmd)); 
        parsers.put("POPULATE", new StructuralParser(this.actionSet, cmd)); 
        parsers.put("COMMIT", new StructuralParser(this.actionSet, cmd));
        parsers.put("DO", new BehavioralParser(this.actionSet, cmd));
        parsers.put("@DO", new BehavioralParser(this.actionSet, cmd));
        parsers.put("@CLOCK", new MiscParser(this.actionSet, cmd));
        parsers.put("@RUN", new MiscParser(this.actionSet, cmd));
        parsers.put("@WAIT", new MiscParser(this.actionSet, cmd));

        Scanner cmdScanner = new Scanner(cmd);
        String firstWord = "";
        Parser parser = null;
        
        if (cmdScanner.hasNext()) {
            
            firstWord = cmdScanner.next();
            cmdScanner.close();
            cmdScanner = null;
            
            try {
                parser = parsers.get(firstWord); // manually catch the hashtable throwing an exception if the command doesnt exist and
                // throw it ourselves because it is an invalid command
            }
            catch (Exception e) {
                throw new Exception("Invalid command");
            }
            
            if (parser != null)
                parser.parseCommand();
        }
        
        if (cmdScanner != null) {
            cmdScanner.close();
            cmdScanner = null;
        }
	}
}
