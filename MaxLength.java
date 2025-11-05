package GSI;

public class MaxLength extends Exception {

    public MaxLength(String tipoDato, String valor, int tamMax) {
        super("No puedes introducir un " + tipoDato + " de tamaño superior a " + tamMax);
    } 

    // Método estático para validar un número
    public static void verificar(String valor, String tipoDato, int tamMax) throws MaxLength {
        if (valor.length() > tamMax) {
            throw new MaxLength(tipoDato, valor, tamMax);
        }
    }
}
