import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//makes 2 passes
public class AssemblyFileParser {
    //INSTANCE VARIABLES
    private List<String> cleanAssemblyCode;
    private Scanner fileReader;

    public AssemblyFileParser(String fileName) throws FileNotFoundException{
        cleanAssemblyCode = new ArrayList<>();
        File assemblyFile = new File(fileName);
        if(!assemblyFile.exists() || assemblyFile.length() == 0){
            throw new FileNotFoundException(fileName + " does not exist or is empty.");
        }

        fileReader = new Scanner(assemblyFile);
        makeFirstPass();

        fileReader.close();
    }

    private void makeFirstPass() {
        // Loop through file
        String rawLine, cleanLine;
        while(fileReader.hasNextLine()) {
            rawLine = fileReader.nextLine();

            cleanLine = clean(rawLine);

            //If clean line not empty, add to list
            if(cleanLine.isEmpty()){
                cleanAssemblyCode.add(cleanLine);
            }
        }
    }

    public String clean(String rawLine) {
        //Start by replacing all whitespaces with a space character
        String cleanLine = rawLine.replaceAll("\\s+", " ");

        //Remove comments
        int commentIndex = cleanLine.indexOf("//");
        if (commentIndex != -1) {
            cleanLine = cleanLine.substring(0, commentIndex).trim();
        }
        return cleanLine;
    }

    @Override
    public String toString() {
        // Loop through each line in cleanAssemblyCode
        StringBuilder output = new StringBuilder();
        for(String line : cleanAssemblyCode){
            output.append(line).append("\n");
        }
        return output.toString();
    }
}
