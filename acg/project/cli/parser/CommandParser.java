
package acg.project.cli.parser;

import java.util.Hashtable;
import java.util.Scanner;

import acg.project.action.ActionSet;


public class CommandParser {
    
    private String cmd;
    private Hashtable<String, Parser> parsers;
    private ActionSet  actionSet;

	public CommandParser(acg.project.action.ActionSet actionSet, String cmd) {
        this.cmd = cmd;
        this.actionSet = actionSet;
        this.parsers = new Hashtable<String, Parser>();
        
		parsers.put("DEFINE", new TemplateParser(actionSet));
		parsers.put("SHOW", new TemplateParser(actionSet));
		parsers.put("CREATE", new AgentParser(actionSet)); 
        parsers.put("POPULATE", new StructuralParser(actionSet)); 
        parsers.put("COMMIT", new StructuralParser(actionSet));
        parsers.put("DO", new BehavioralParser(actionSet));
        parsers.put("@DO", new BehavioralParser(actionSet));
        parsers.put("@CLOCK", new MiscParser(actionSet));
        parsers.put("@RUN", new MiscParser(actionSet));
        parsers.put("@WAIT", new MiscParser(actionSet));
    }

	public void interpret() throws ParseException {
        Scanner cmdScanner = new Scanner(cmd);
        String firstWord = "";
        Parser parser = null;
        
        if (cmdScanner.hasNext()) {
            
            firstWord = cmdScanner.next();
            firstWord = firstWord.toLowerCase();
            cmdScanner.close();
            cmdScanner = null;
            
            try {
                parser = parsers.get(firstWord); // manually catch the hashtable throwing an exception if the command doesnt exist and
                // throw it ourselves because it is an invalid command
            }
            catch (Exception e) {
                throw new ParseException("Invalid command");
            }
            
            if (parser != null)
                parser.parseCommand(this.cmd);
        }
        
        if (cmdScanner != null) {
            cmdScanner.close();
            cmdScanner = null;
        }
	}
}
