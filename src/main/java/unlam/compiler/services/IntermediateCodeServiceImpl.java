package unlam.compiler.services;

import org.springframework.stereotype.Service;
import unlam.compiler.Parser;
import unlam.compiler.constants.Constants;
import unlam.compiler.services.interfaces.IntermediateCodeService;
import unlam.compiler.terceto.Terceto;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class IntermediateCodeServiceImpl implements IntermediateCodeService {

    private List<Terceto> tercetosList;

    @Override
    public void generate(String fileName, Parser parser) throws Exception {
        Path path = Paths.get(Constants.OUTPUT_DIRECTORY);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println(Constants.MSG_DIRECTORY_ERROR + Constants.SPACE + e.getMessage());
        }
        try(FileWriter fileWriter = new FileWriter("%s/%s".formatted(Constants.OUTPUT_DIRECTORY, fileName))) {
            if(parser == null || parser.getTercetosList().isEmpty()) {
                fileWriter.write("SIN CODIGO INTERMEDIO");
                return;
            }
            this.tercetosList = parser.getTercetosList();
            fileWriter.write("LISTA DE TERCETOS:" + "\n");
            Integer numTerceto = 0;
            for(Terceto terceto : tercetosList) {
                try
                {
                    fileWriter.write(numTerceto.toString() + ") " + terceto.toString() + "\n");
                    numTerceto++;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            fileWriter.flush();
        } catch (IOException e) {
            throw new Exception(Constants.MSG_TS_ERROR);
        }
    }
}
