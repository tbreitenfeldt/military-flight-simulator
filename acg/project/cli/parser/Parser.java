package acg.project.cli.parser;

public abstract class Parser {
    
	public String cmd;
    public acg.project.action.ActionSet actionSet;
    
	public abstract void parseCommand() throws Exception;
}