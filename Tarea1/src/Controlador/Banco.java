package Controlador;

import Modelo.Cliente;
import Modelo.Tarjeta;
import Vista.UI;
import java.util.ArrayList;
import java.util.Scanner;

/**Atributos que contiene el banco como conjunto de todas cuentas y clientes existentes**/
public class Banco {
    private static final Scanner leerLetras = new Scanner(System.in);

    private static final Scanner leerNumeros = new Scanner(System.in);

    private static ArrayList<Tarjeta> conjuntoCuentas;

    public static ArrayList<Cliente> conjuntoClientes;

    public Banco() {
        conjuntoCuentas = new ArrayList<>();
        conjuntoClientes = new ArrayList<>();
    }
    public static ArrayList<Tarjeta> getConjuntoCuentas() {
        return conjuntoCuentas;
    }

    public static ArrayList<Cliente> getConjuntoClientes() {
        return conjuntoClientes;
    }

    /**Funcion que permite que el usuario vea el menu y seleccione la opcion
     *
     */
    public static void menu(){
        System.out.println("Si desea ingresar al banco los Lavadores digite 1, sino digite 2");
        int continuar = leerNumeros.nextInt();
        while (continuar == 1) {
            UI.imprimirMenu();
            int opcion = leerNumeros.nextInt();
            if (opcion == 1){
                UI.registrarCliente();
            }else if (opcion == 2) {
                UI.listarClientes();
            } else if (opcion == 3) {
                UI.crearCuenta();
            } else if (opcion == 4) {
                int identificacion = UI.mostrarIdentificacion();
                int numeroCuenta = UI.mostrarNumeroCuenta();
                realizarDeposito(identificacion, numeroCuenta);
            } else if (opcion == 5) {
                int idCliente = UI.mostrarIdentificacion();
                int numeroTarjeta = UI.mostrarNumeroCuenta();
                realizarRetiro(idCliente, numeroTarjeta);
            } else if (opcion == 6) {
                UI.mostrarSaldo();
            } else if (opcion == 7) {
                System.exit(0);
            }
        }
    }


    /**
     * Funcion que agrega al cliente al conjunto total de clientes mientras no haya sido ingresado antes
     * @param nuevoCliente
     */
    public static void registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente(nuevoCliente.getIdentificacion())) {
            conjuntoClientes.add(nuevoCliente);
        } else {
            UI.errorIdentificacion();
        }
    }

    /**
     * Función que genera número random para número de cuenta
     * @param maximo
     * @param minimo
     * @return numero aleatorio entre minimo y maximo que cumpla con 7 digitos
     */
    public static int numeroRandomCuenta(int maximo, int minimo) {
            int range = (maximo-minimo) + 1;
            return (int) (Math.random() * range) + minimo;
    }

    /**
     * Función que agrega la tarjeta de un cliente al total de cuentas y se asegura de que el deposito inicial sea igual o mayor a 50000
     * @param nuevaCuenta
     */
    public static void registrarCuenta(Tarjeta nuevaCuenta) {
        if (!nuevoCliente(nuevaCuenta.getIdentificacion()) && Tarjeta.getSaldo() > 49000) {
            conjuntoCuentas.add(nuevaCuenta);
        } else if (nuevoCliente(nuevaCuenta.getIdentificacion())) {
            UI.errorCliente();
        } else if (Tarjeta.getSaldo() < 50000) {
            UI.errorDeposito();
        }
    }

    /**
     * Funcion que permite crear nuevo cliente en caso de que no exista anteriormente
     * @param identificacion
     * @return si el cliente fue creado o si ya existia y por ende no fue creado
     */
    private static boolean nuevoCliente(int identificacion) {
        boolean clienteNuevo = true;
        for (Cliente conjuntoClientes : conjuntoClientes) {
            clienteNuevo = conjuntoClientes.getIdentificacion() != (identificacion);
        }
        return clienteNuevo;
    }

    /**
     *Funcion que permite realizar depositos a la cuenta de un cliente en especifico y así aumentar el saldo de la cuenta
     * @param identificacion
     * @param numeroCuenta
     */
    public static void realizarDeposito(int identificacion, int numeroCuenta) {
        for (Tarjeta conjuntoCuenta : conjuntoCuentas) {
            if (conjuntoCuenta.getIdentificacion() == identificacion && conjuntoCuenta.getNumeroCuenta() == numeroCuenta) {
                double nuevoSaldo = conjuntoCuenta.getSaldo() + verificarDeposito();
                if (nuevoSaldo > 0 && conjuntoCuenta.getNumeroCuenta()==numeroCuenta) {
                    conjuntoCuenta.setSaldo(nuevoSaldo);
                } else if (nuevoSaldo < 0) {
                    UI.errorDeposito();
                }

            } else {
                UI.errorIdentificacion();
            }
        }
    }

    public static double verificarDeposito(){
        double deposito= 0;

        while (deposito <= 0) {
            deposito= UI.realizarDeposito();
        }
        return deposito;
    }

    /**
     * Funcion que permite realizar un retiro siempre que el saldo sea mayor o igual a lo que desea retirarse, si no, no se realizara el retiro
     * @param identificacion
     * @param numeroCuenta
     */
    public static void realizarRetiro(int identificacion, int numeroCuenta) {
        for (Tarjeta conjuntoCuenta : conjuntoCuentas) {
            if (conjuntoCuenta.getIdentificacion() == identificacion && conjuntoCuenta.getNumeroCuenta() == numeroCuenta) {
                double plataCuenta = verificarRetiro();
                double nuevoSaldo = Tarjeta.getSaldo() - plataCuenta;
                if (plataCuenta >= 0 && plataCuenta <= Tarjeta.getSaldo()) {
                    Tarjeta.setSaldo(nuevoSaldo);
                } else if (plataCuenta >= Tarjeta.getSaldo()) {
                    UI.sinFondos();
                } else {
                    UI.sinFondos();
                }
            } else {
                UI.errorIdentificacion();
            }
        }
    }

    public static double verificarRetiro(){
        double retiro= 0;

        while (retiro <= 0) {
            retiro= UI.realizarRetiro();
        }
        return retiro;
    }



    /**
     *Funcion que permite mostrar el numero de tarjeta si es el caso que el cliente tiene y si no, mostrar que no existe
     *
     */
    public static void cuentaCreada() {
        if (!conjuntoCuentas.isEmpty()) {
            UI.crearNumeroCuenta();
        } else UI.sinFondos();
    }

    /**
     * Funcion que permite mostar el saldo de la cuenta de un cliente especifico y avisar si es que no tiene fondos
     * @return el saldo de la cuenta que se solicito
     */
    public static double mostrarSaldo() {
        double saldo = 0.00;
        for (Tarjeta conjuntoCuenta : conjuntoCuentas) {
            if (conjuntoCuenta.getIdentificacion() == UI.mostrarIdentificacion() && conjuntoCuenta.getNumeroCuenta() == UI.mostrarNumeroCuenta()) {
                saldo = Tarjeta.getSaldo();
                if (saldo == 0) {
                    UI.sinFondos();
                }
            }
        }
        return saldo;
    }
}
