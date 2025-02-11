package unlam.compiler;

import unlam.compiler.constants.Constants;
import unlam.compiler.services.interfaces.ICompilerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private ICompilerService compilerService;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(Constants.MSG_FILENAME_ERROR);
            System.exit(0);
        }
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            compilerService.run(args[0]);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}