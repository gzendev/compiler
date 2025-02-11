package unlam.compiler.constants;

import java.util.List;

public final class Constants {
    public static final String MSG_COMPILATION_SUCCESSFUL = "Compilacion exitosa!";
    public static final String MSG_PARSINNG_ERROR = "Error de parsing!";
    public static final String MSG_FILENAME_ERROR = "Filename must be provided as argument!";
    public static final String MSG_DIRECTORY_ERROR = "Error al crear el directorio!";
    public static final String MSG_TS_ERROR = "Error en la generacion de la TS!";
    public static final int MAX_LENGTH = 30;
    public static final String SPACE = " ";
    public static final String OUTPUT_DIRECTORY  = "target/output";
    public static final String FILENAME_TS  = "symbol-table.txt";
    private static final String TABLE_HEADER_SEPARATOR = "\t\t\t\t\t\t";
    public static final List<String> TABLE_HEADER = List.of("NOMBRE", "TIPODATO", "VALOR", "LONGITUD");
    public static final String TABLE_HEADER_CONSOLE = "NOMBRE" + TABLE_HEADER_SEPARATOR + "TIPODATO" + TABLE_HEADER_SEPARATOR + "VALOR" + TABLE_HEADER_SEPARATOR + "LONGITUD";

}
