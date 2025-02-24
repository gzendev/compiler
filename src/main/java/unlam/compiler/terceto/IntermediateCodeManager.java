package unlam.compiler.terceto;

import unlam.compiler.symboltable.DataType;
import java.util.*;

public class IntermediateCodeManager {
    private ArrayList<Terceto> tercetos = new ArrayList<Terceto>();
    private HashMap<String, Integer> punterosTercetos = new HashMap<String, Integer>();
    private Stack<Integer> pilaPunteros = new Stack<Integer>();
    private Stack<String> pilaLexemas = new Stack<String>();
    private Stack<String> pilaComparadores = new Stack<String>();
    private Map<Integer, Stack<Integer> > pilasCondicionesSuficientesIf = new HashMap<Integer, Stack<Integer> >();
    private Map<Integer, Stack<Integer> > pilasCondicionesSuficientesWhile = new HashMap<Integer, Stack<Integer> >();
    private Queue<String> resultsAux = new LinkedList<String>();
    private Queue<String> resultsStringAux = new LinkedList<String>();
    private Stack<Integer> pilaIfs = new Stack<Integer>();
    private Stack<Integer> pilaWhiles = new Stack<Integer>();
    private Integer contIfs = 0;
    private Integer contWhiles = 0;
    private static final HashMap<String, String> comparadorInverso;
    static {
        comparadorInverso = new HashMap<String, String>();
        comparadorInverso.put("BNE", "BEQ");
        comparadorInverso.put("BEQ", "BNE");
        comparadorInverso.put("BLE", "BGT");
        comparadorInverso.put("BGT", "BLE");
        comparadorInverso.put("BLT", "BGE");
        comparadorInverso.put("BGE", "BLT");
    };

    public Integer crearTerceto(String operador, String operando1, String operando2)
    {
        Integer numTerceto = tercetos.size();
        if(esOperacionMatematica(operador))
        {
            Integer numAux = resultsAux.size();
            String strResultNum = numAux.toString();
            String resultAux = "@aux" + strResultNum;
            tercetos.add(new Terceto(operador, operando1, operando2, resultAux));
            resultsAux.add(resultAux);
            return numTerceto;
        }
        tercetos.add(new Terceto(operador, operando1, operando2));
        return numTerceto;
    }

    public Integer crearTerceto(String valor)
    {
        Integer numTerceto = tercetos.size();
        tercetos.add(new Terceto(valor));
        return numTerceto;
    }

    public Integer crearTerceto(String operador, String operando1)
    {
        Integer numTerceto = tercetos.size();
        tercetos.add(new Terceto(operador, operando1));
        return numTerceto;
    }

    public Terceto getTerceto(Integer numTerceto)
    {
        return tercetos.get(numTerceto);
    }

    public void setPunteroTerceto(String puntero, Integer numeroTerceto)
    {
        punterosTercetos.put(puntero, numeroTerceto);
    }

    public Integer getNumeroTercetoFromPuntero(String puntero)
    {
        return punterosTercetos.get(puntero);
    }

    public String getStrNumeroTercetoFromPuntero(String puntero)
    {
        return "#" + punterosTercetos.get(puntero).toString();
    }

    public void apilarPuntero(Integer puntero)
    {
        this.pilaPunteros.add(puntero);
    }

    public Integer desapilarPuntero()
    {
        return this.pilaPunteros.pop();
    }

    public Integer topePilaPunteros()
    {
        return this.pilaPunteros.peek();
    }

    public void apilarLexema(String lexema)
    {
        this.pilaLexemas.add(lexema);
    }

    public String desapilarLexema()
    {
        return this.pilaLexemas.pop();
    }

    public void apilarComparador(String comparador)
    {
        this.pilaComparadores.add(comparador);
    }

    public String desapilarComparador()
    {
        return this.pilaComparadores.pop();
    }

    public void apilarCondSufIf(Integer numIf, Integer puntero)
    {
        this.pilasCondicionesSuficientesIf.computeIfAbsent(numIf, k -> new Stack<Integer>());
        this.pilasCondicionesSuficientesIf.get(numIf).add(puntero);
    }

    public Integer desapilarCondSufIf(Integer numIf)
    {
        return this.pilasCondicionesSuficientesIf.get(numIf).pop();
    }

    public Boolean hayCondicionesSuficientesIf(Integer numIf)
    {
        return !this.pilasCondicionesSuficientesIf.get(numIf).empty();
    }

    public void apilarCondSufWhile(Integer numWhile, Integer puntero)
    {
        this.pilasCondicionesSuficientesWhile.computeIfAbsent(numWhile, k -> new Stack<Integer>());
        this.pilasCondicionesSuficientesWhile.get(numWhile).add(puntero);
    }

    public Integer desapilarCondSufWhile(Integer numWhile)
    {
        return this.pilasCondicionesSuficientesWhile.get(numWhile).pop();
    }

    public Boolean hayCondicionesSuficientesWhile(Integer numWhile)
    {
        return !this.pilasCondicionesSuficientesWhile.get(numWhile).empty();
    }

    public String getComparadorInverso(String comparador)
    {
        return comparadorInverso.get(comparador);
    }

    public Integer getUltimoNumeroTerceto()
    {
        return this.tercetos.size() - 1;
    }

    public void actualizarTerceto(Integer numeroTerceto, CampoTerceto campo, String valor)
    {
        if(campo == CampoTerceto.PRIMER_ELEM)
        {
            this.tercetos.get(numeroTerceto).setOperacion(valor);
            return;
        }
        if(campo == CampoTerceto.SEGUNDO_ELEM)
        {
            this.tercetos.get(numeroTerceto).setOperando1(valor);
            return;
        }
        if(campo == CampoTerceto.TERCER_ELEM)
        {
            this.tercetos.get(numeroTerceto).setOperando2(valor);
            return;
        }
    }

    public void eliminarTerceto(Integer numeroTerceto)
    {
        int numero = numeroTerceto;
        this.tercetos.remove(numero);
    }

    public Integer getCte(Integer numeroTerceto)
    {
        return getTerceto(numeroTerceto).getCte();
    }

    public String getOperandoFromNumeroTerceto(Integer numeroTerceto)
    {
        Terceto terceto = this.tercetos.get(numeroTerceto);
        if(terceto.getType() == TercetoType.SINGLE_VALUE)
        {
            return terceto.getOperando1();
        }
        return terceto.getResultAux();
    }

    public static Boolean esOperacionMatematica(String operador)
    {
        return
                operador.equals("+") || operador.equals("-") || operador.equals("*") || operador.equals("/");
    }

    public static Boolean esOperadorDeSalto(String operador)
    {
        return
                operador.equals("BEQ") || operador.equals("BNE") || operador.equals("BLT") || operador.equals("BLE")
                        || operador.equals("BGT") || operador.equals("BGE");
    }

    public Boolean esOperacionEntreConstantes(String puntero1, String puntero2)
    {
        return getTerceto(this.getNumeroTercetoFromPuntero(puntero1)).esCte() &&
                getTerceto(this.getNumeroTercetoFromPuntero(puntero2)).esCte();
    }

    public void apilarIf()
    {
        this.pilaIfs.add(this.contIfs);
        contIfs++;
    }

    public Integer getNumeroIf()
    {
        return this.pilaIfs.peek();
    }

    public String getStrNumeroIf()
    {
        return getNumeroIf().toString();
    }

    public Integer desapilarIf()
    {
        return this.pilaIfs.pop();
    }

    public void apilarWhile()
    {
        this.pilaWhiles.add(this.contWhiles);
        contWhiles++;
    }

    public Integer getNumeroWhile()
    {
        return this.pilaWhiles.peek();
    }

    public String getStrNumeroWhile()
    {
        return getNumeroWhile().toString();
    }

    public Integer desapilarWhile()
    {
        return this.pilaWhiles.pop();
    }

    public Queue<String> getAuxiliares(DataType type)
    {
        if(type == DataType.CTE_STRING)
        {
            return resultsStringAux;
        }
        return resultsAux;
    }

    public void mostrarTercetos()
    {
        System.out.println("----------------MOSTRANDO TERCETOS---------------------");
        for(int i = 0; i < tercetos.size(); i++)
        {
            System.out.println(i + ") " + tercetos.get(i));
        }
        System.out.println("--------------------------------------------------------");
    }

    public List<Terceto> getTercetosList()
    {
        return this.tercetos;
    }
}
