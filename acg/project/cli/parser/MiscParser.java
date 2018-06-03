package acg.project.cli.parser;

import java.util.Scanner;

import acg.project.action.ActionSet;
import acg.project.action.command.A_Command;
import acg.project.action.command.miscellanious.*;


public class MiscParser extends Parser {

    public MiscParser(ActionSet actionSet) {
        super(actionSet);
    }
    
    @Override
    public void parseCommand(String cmd) throws ParseException {
    }
	

}
