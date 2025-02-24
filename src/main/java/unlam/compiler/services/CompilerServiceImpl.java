package unlam.compiler.services;

import unlam.compiler.constants.Constants;
import unlam.compiler.services.interfaces.ICompilerService;
import unlam.compiler.services.interfaces.ILexicalSyntacticService;
import unlam.compiler.services.interfaces.ISymbolTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unlam.compiler.services.interfaces.IntermediateCodeService;

@Service
public class CompilerServiceImpl implements ICompilerService {
    @Autowired
    private ILexicalSyntacticService lexicalSyntacticService;
    @Autowired
    private ISymbolTableService symbolTableService;
    @Autowired
    private IntermediateCodeService intermediateCodeService;

    @Override
    public void run(String fileName) throws Exception {
        lexicalSyntacticService.parser(fileName);
        symbolTableService.generate(Constants.FILENAME_TS, lexicalSyntacticService.getLexer());
        intermediateCodeService.generate(Constants.FILENAME_IC, lexicalSyntacticService.getParser());
        System.out.println(Constants.MSG_COMPILATION_SUCCESSFUL);
    }
}
