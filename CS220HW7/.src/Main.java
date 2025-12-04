import java.io.FileNotFoundException;

public class Main {
    public static final String PROGRAM_NAME = "Max";
    public static void main(String[] args) {
        try {
            AssemblyFileParser parser = new AssemblyFileParser(PROGRAM_NAME + ".asm");
            System.out.println(parser);
            MachineCodeWriter.writeToBinaryFile(PROGRAM_NAME + ".hack", parser.getParsedAssemblyInstructions());
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }    
    }
}