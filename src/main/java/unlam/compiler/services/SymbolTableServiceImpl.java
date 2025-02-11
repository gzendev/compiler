package unlam.compiler.services;

import unlam.compiler.Lexer;
import unlam.compiler.constants.Constants;
import unlam.compiler.entities.SymbolTableEntry;
import unlam.compiler.services.interfaces.ISymbolTableService;
import unlam.compiler.utils.StringFormatter;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SymbolTableServiceImpl implements ISymbolTableService {

    public SymbolTableServiceImpl() {}

    @Override
    public void generate(String fileName, Lexer lexer) throws Exception {
        Path path = Paths.get(Constants.OUTPUT_DIRECTORY);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println(Constants.MSG_DIRECTORY_ERROR + Constants.SPACE + e.getMessage());
        }
        try(FileWriter fileWriter = new FileWriter("%s/%s".formatted(Constants.OUTPUT_DIRECTORY, fileName))) {
            for(String s :Constants.TABLE_HEADER) {
                fileWriter.write(StringFormatter.centerText(s));
            }
            fileWriter.write("\n");
            for (SymbolTableEntry s :lexer.symbolList) {
                if(s != null) {
                    fileWriter.write(s.toString().concat("\n"));
                }
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new Exception(Constants.MSG_TS_ERROR);
        }
    }

    @Override
    public void generateTable(Lexer lexer) throws Exception {
        System.out.println(Constants.TABLE_HEADER_CONSOLE);
        for (SymbolTableEntry s : lexer.symbolList) {
            if(s != null)
                System.out.println(s.toString().concat("\n"));
        }
    }
}
