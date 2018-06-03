import acg.project.cli.parser.*;
import acg.project.cli.*;
import acg.project.action.*;
import acg.architecture.datatype.*;

public class ParserTester {
	
	public static void main(String[] args) throws Exception {
		
		TestParseDatatype("cat1", "ID");
		
		//TestParseCommand("DeFINE cat1 OrIGIN 1:1 AZIMUTH 10 WIDTH 5 LIMIT WEIGHT 6 SPEED 7 MISS 80", "commandCreate_DEFINE_TRAP");

	}
	
	public static void TestParseDatatype(String token, String datatype) throws ParseException {
		
		try {
			
			switch (datatype) {
			
			case "ID":
				System.out.println(ParseUtils.parseID(token));
				break;
			case "ORIGIN":
				System.out.println(ParseUtils.parseORIGIN(token));
				break;
			case "AZIMUTH":
				System.out.println(ParseUtils.parseAZIMUTH(token));
				break;
			case "DISTANCE":
				System.out.println(ParseUtils.parseDISTANCE(token));
				break;
			case "WEIGHT":
				System.out.println(ParseUtils.parseWEIGHT(token));
				break;
			case "SPEED":
				System.out.println(ParseUtils.parseSPEED(token));
				break;
			case "PERCENT":
				System.out.println(ParseUtils.parsePERCENT(token));
				break;
			}
		}
		catch (Exception e){
			
			throw new ParseException();
		}
	}
	
	public static void TestParseCommand(String cmd, String methodName) throws ParseException {
		
		try {
			/*
			switch (methodName) {
			
			case "createCommand_DEFINE_TRAP":
				TemplateParser templateParser = new TemplateParser(new acg.project.action.ActionSet(new acg.project.cli.CommandLineInterface()));
				System.out.println(templateParser.createCommand_DEFINE_TRAP(cmd));
			}
			*/
			
			// How Tappan will test the program:
			/*
			ActionSet actionSet = new ActionSet(new CommandLineInterface()); 
			CommandParser parser = new CommandParser(actionSet, cmd);
			
			parser.interpret();
			*/
		}
		catch (Exception e) {
			throw new ParseException();
		}
		
	}
}
