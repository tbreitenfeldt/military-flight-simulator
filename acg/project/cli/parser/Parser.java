package acg.project.cli.parser;



public interface Parser {
    
    public abstract void parseCommand(String cmd) throws ParseException;

}