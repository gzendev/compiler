package unlam.compiler.services;

import unlam.compiler.constants.Constants;
import unlam.compiler.services.interfaces.ICompilerService;
import unlam.compiler.services.interfaces.ILexicalSyntacticService;
import unlam.compiler.services.interfaces.ISymbolTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompilerServiceImpl implements ICompilerService {
    @Autowired
    private ILexicalSyntacticService lexicalSyntacticService;
    @Autowired
    private ISymbolTableService symbolTableService;

    @Override
    public void run(String fileName) throws Exception {
        symbolTableService.generate(Constants.FILENAME_TS, lexicalSyntacticService.parser(fileName));
        System.out.println(Constants.MSG_COMPILATION_SUCCESSFUL);
    }
}
