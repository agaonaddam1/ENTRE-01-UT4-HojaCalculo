
/**
 *  Un objeto de esta clase representa a una sencilla
 *  hoja de cálculo. La hoja tiene hasta un máximo de 3 filas (no más)
 *  En cada fila la empresa "apunta" los ingresos y gastos en 
 *  una determinada fecha
 * 
 * @author - Ander Gaona  
 *  
 */
public class HojaCalculo
{
    private String nombre;
    private Fila fila1;
    private Fila fila2;
    private Fila fila3;

    /**
     * Constructor  
     * Crea la hoja de cálculo con el nombre indicado 
     * e inicializa las filas al valor null (inicialmente
     * la hoja se crea sin filas)
     */
    public HojaCalculo(String nombre)    {
        this.nombre = nombre;
        this.fila1 = null;
        this.fila2 = null;
        this.fila3 = null;

    }

    /**
     * accesor para el nombre de la hoja
     */
    public String getNombre() {
        return this.nombre;

    }

    /**
     * accesor para la fila1
     */
    public Fila getFila1() {
        return fila1;

    }

    /**
     * accesor para la fila2
     */
    public Fila getFila2() {
        return fila2;

    }

    /**
     * accesor para la fila3
     */
    public Fila getFila3() {
        return fila3;

    }

    /**
     * Devuelve el nº de filas de la hoja
     * (dependerá de cuántas filas estén a null)
     */
    public int getNumeroFilas() {
        int numeroFilas = 0;
        if (fila1 != null){
            numeroFilas++;
        }
        if (fila2 != null){
            numeroFilas++;
        }
        if (fila3 != null){
            numeroFilas++;
        }
        return numeroFilas;

    }

    /**
     * Devuelve true si la hoja está completa
     * (tiene exactamente 3 filas)
     */
    public boolean hojaCompleta() {
        if (getNumeroFilas() != 3){
            return false;
        }
        return true;

    }

    /**
     * Se añade una nueva fila a la hoja
     * Si la hoja está completa se muestra el mensaje "FilaX no se puede añadir en HOJAX"
     * Si no está completa se añade la fila a la hoja teniendo en cuenta
     * si se añade como primera, segunda o tercera fila (no han de quedar huecos)
     */
    public void addFila(Fila fila) {
        if (hojaCompleta() == false){  
            if (fila1 == null){
                fila1 = fila;
            }
            else if (fila2 == null){
                fila2 = fila;
            }
            else {
                fila3 = fila;
            }
        }
        else{
            System.out.println(fila.getId() + " no se puede añadir en " + getNombre());
        }
    }

    /**
     * Dada la información a guardar en una fila el método
     * crea la fila y la añade a la hoja
     * (evita repetir código)
     */
    public void addFila(String id, Fecha fecha, double ingresos, double gastos) {
        Fila fila = new Fila(id, fecha, ingresos, gastos);
        addFila(fila);
    }

    /**
     * Calcula y devuelve el total de ingresos entre
     * todas las filas que incluye la hoja
     */
    public double getTotalIngresos() {
        double total = 0;
        total += fila1.getIngresos();
        if (fila2 != null){
            total += fila2.getIngresos();
        }
        if (fila3 != null){
            total += fila3.getIngresos();
        }
        return total;
    }

    /**
     * Calcula y devuelve el total de gastos
     * entre todas las filas que incluye la hoja
     */
    public double getTotalGastos() {
        double total = 0;
        total += fila1.getGastos();
        if (fila2 != null){
            total += fila2.getGastos();
        }
        if (fila3 != null){
            total += fila3.getGastos();
        }
        return total;

    }

    /**
     * Calcula y devuelve el total del beneficio
     * entre todas las filas que incluye la hoja
     */
    public double getBeneficio() {
        double total = 0;
        total += fila1.getBeneficio();
        if (fila2 != null){
            total += fila2.getBeneficio();   
        }
        if (fila3 != null){
            total += fila3.getBeneficio();
        }
        return total;

    }

    /**
     * Representación textual de la hoja
     * con el formato exacto que indica el enunciado
     */
    public String toString() {
        if (fila2 == null){
            return String.format("%-8s\n %23s %16s %16s %16s\n" + 
                fila1.toString() +
                "\n-------------------------------------------------------------------------------------------\n" +
                "%40.2f€ %15.2f€ % 15.2f€",
                getNombre(),
                "FECHA", "INGRESOS","GASTOS","BENEFICIO",
                getTotalIngresos(), getTotalGastos(),getBeneficio());  
        }
        else if (fila3 == null){
            return String.format("%-8s\n %23s %16s %16s %16s\n" + 
                fila1.toString() + "\n" + 
                fila2.toString() +
                "\n-------------------------------------------------------------------------------------------\n" +
                "%40.2f€ %15.2f€ % 15.2f€",
                getNombre(),
                "FECHA", "INGRESOS","GASTOS","BENEFICIO",
                getTotalIngresos(), getTotalGastos(),getBeneficio());
        }
        else {
            return String.format("%-8s\n %23s %16s %16s %16s\n" + 
                fila1.toString() + "\n" + 
                fila2.toString() + "\n" +
                fila3.toString() + "\n-------------------------------------------------------------------------------------------\n" +
                "%40.2f€ %15.2f€ % 15.2f€",
                getNombre(),
                "FECHA", "INGRESOS","GASTOS","BENEFICIO",
                getTotalIngresos(), getTotalGastos(),getBeneficio()); 
        }
    }

    /**
     * Devuelve un duplicado de la hoja actual.
     * El nombre de la hoja duplicada será "Duplicada HojaX"
     * Al duplicar la hoja se duplicarán también las filas que contenga
     */
    public HojaCalculo duplicarHoja() {
        HojaCalculo hoja = new HojaCalculo("Duplicada " + this.getNombre());
        hoja.addFila(this.fila1);
        if (fila2 != null){
            hoja.addFila(this.fila2);
        }
        if (fila3 != null){
            hoja.addFila(this.fila3);
        }
        return hoja;
    }

}
