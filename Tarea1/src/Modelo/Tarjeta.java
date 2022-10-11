package Modelo;

public class Tarjeta {

    /**
     * Atributos con los que cuenta una tarjeta
     */
    private int identificacion;

    public static double saldo;
    public int numeroCuenta;

    /**
     * Constructor con los atributos de tarjeta
     * @param identificacion
     * @param saldo
     * @param numeroCuenta
     */
    public Tarjeta(int identificacion, double saldo, int numeroCuenta) {
    this.identificacion = identificacion;
    this.saldo = saldo;
    this.numeroCuenta = numeroCuenta;

    }

    /**
     * Getter de los atributos de la tarjeta de los clientes para mostrar valores
     * @return
     */
    public int getIdentificacion() {
        return identificacion;
    }

    public static double getSaldo() {
        return saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }


    /**
     * Setter de los atributos de tarjeta para asignar valores
     * @param identificacion
     */
    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public static void setSaldo(double saldo) {
        Tarjeta.saldo = saldo;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }


    /**
     * toString para mostrar los datos de la tarjeta
     * @return
     */
    @Override
    public String toString() {
        return "Tarjeta{" +
                "identificacion=" + identificacion +
                ", saldo=" + saldo +
                ", numeroCuenta=" + numeroCuenta +
                '}';
    }
}

