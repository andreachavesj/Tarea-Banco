package Vista;

import Controlador.Banco;
import Modelo.Cliente;
import Modelo.Tarjeta;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.BitSet;
import java.util.Date;
import java.util.Scanner;

/**
 * Scanner utilizados en el programa separado para utilizar en caracteres y numeros
 */
public class UI {
    private static final Scanner leerLetras = new Scanner(System.in);

    private static final Scanner leerNumeros = new Scanner(System.in);

    /**
     * Permite generar el programa principal llamando al banco
     *
     * @param args
     */
    public static void main(String[] args) {
        Banco banco = new Banco();
        banco.menu();
    }

    /**
     * Funcion para imprimir las opciones del menu que tiene el usuario
     */
    public static void imprimirMenu() {
        System.out.println("A continuación se le desplegará el menú de opciones del banco");
        System.out.println("1.Registrar clientes");
        System.out.println("2.Listar clientes.");
        System.out.println("3.Crear cuenta.");
        System.out.println("4.Realizar depósito");
        System.out.println("5.Realizar retiro.");
        System.out.println("6.Mostrar saldo de cuenta.");
        System.out.println("7.Salir.");
        System.out.println("Porfavor ingrese la opción que desee");
    }

    /**
     * Funcion que toma los datos del cliente y le va solicitando cada dato
     */
    public static void registrarCliente() {
        System.out.println("A continuación podrá crear un cliente");

        System.out.println("Digite el nombre del cliente:");
        String nombre = leerLetras.nextLine();

        System.out.println("Digite la identificación del cliente:");
        int identificacion = leerNumeros.nextInt();

        System.out.println("Digite el año de nacimiento del cliente: ");
        int annoNacimiento = leerNumeros.nextInt();

        System.out.println("Digite el mes de nacimiento del cliente: ");
        int mesNacimiento = leerNumeros.nextInt();

        System.out.println("Digite el día de nacimiento del cliente: ");
        int diaNacimiento = leerNumeros.nextInt();

        LocalDate now = LocalDate.now();
        LocalDate born = LocalDate.of(annoNacimiento,mesNacimiento,diaNacimiento);
        Period period = Period.between(born,now);

        System.out.println("La edad exacta del cliente corresponde a:");
        System.out.println(period.getYears()+" años con "+period.getMonths()+" meses y "+period.getDays()+" días.");

        String nacimiento= diaNacimiento+"/"+mesNacimiento+"/"+annoNacimiento;

        int edad= period.getYears();

        System.out.println("Digite la dirección donde vive el cliente:");
        String direccion = leerLetras.nextLine();

        Cliente nuevoCliente = new Cliente(nombre, identificacion, nacimiento, edad, direccion);
        Banco.registrarCliente(nuevoCliente);
    }

    /**
     * Funcion que permite mostrar a todos los clientes existentes en el banco
     */
    public static void listarClientes() {
        System.out.println("Los clientes existentes en el banco son: ");
        System.out.println(Banco.conjuntoClientes.toString());
    }


    /**
     * Funcion que toma los datos para crear una cuenta al cliente y llama las funciones para darle un numero a la cuenta
     * tambien registra la cuenta y lo agrega al conjunto de cuentas totales y muestra mensaje de cuenta creada y brinda el numero
     * de cuenta
     */
    public static void crearCuenta() {
        System.out.println("Digite la identificación del cliente: ");
        int identificacion = leerNumeros.nextInt();

        System.out.println("Digite el monto en colones del deposito a realizar: ");
        double deposito = leerNumeros.nextDouble();

        int numeroCuenta = Banco.numeroRandomCuenta(1000000, 9999999);

        Tarjeta nuevaCuenta = new Tarjeta(identificacion, deposito, numeroCuenta);
        Banco.registrarCuenta(nuevaCuenta);
        Banco.cuentaCreada();
    }

    /**
     * Funcion que permite al usuario ingresar la cantidad de dinero que desea depositar
     * @return el numero del deposito que se realizara
     */
    public static double realizarDeposito() {
        System.out.println("Digite el monto en colones que desea depositar: ");
        return leerNumeros.nextInt();
    }

    /**
     * Funcion que permite al usuario ingresar la cantidad de dinero que desea retirar
     * @return el monto que desea depositar
     */
    public static double realizarRetiro() {
        System.out.println("Digite el monto en colones que desea retirar: ");
        return leerNumeros.nextInt();
    }

    /**
     * Funcion que muestra el saldo de una tarjeta en especifico de un cliente
     */
    public static void mostrarSaldo() {
        System.out.println("El saldo de la tarjeta es: "+ Banco.mostrarSaldo());
    }

    /**
     * Funcion que permite mostrar mensajes en casos de detectar errores relacionados a la identificacion, como
     * ingresada dos veces, no existente, incorrecta algun dato.
     */
    public static void errorIdentificacion() {
        System.out.println("Existe un error en la identificación, ya ha sido ingresada anteriormente o no existe.");
    }

    /**
     * Funcion que permite mostrar mensajes de error en caso de detectar problemas con el cliente como si no ha sido creado,
     * si algun dato fue ingresado mal.
     */
    public static void errorCliente() {
        System.out.println("Existe un error en el cliente y no ha sido encontrado.");
    }

    /**
     * Funcion que permite reconocer errores en el deposito en caso de no contar con el suficiente minimo de dinero,
     * no encontrar cuenta u otros.
     */
    public static void errorDeposito() {
        System.out.println("Existe un error en el deposito, deposito no puede ser tramitado.");
    }

    /**
     * Funcion que permite brindar el numero de cuenta para el cliente para el que fue creada
     */
    public static void crearNumeroCuenta() {
        System.out.println("Su cuenta ha sido creada con exito: " + Banco.getConjuntoCuentas().get(Banco.getConjuntoCuentas().size() - 1).getNumeroCuenta());
    }

    /**
     * Funcion que permite mostrar que la cuenta no tiene los suficientes fondos o no tiene para realizar alguna otra funcion
     * como lo es el retiro o en caso de mostrar el saldo y que sea 0
     */
    public static void sinFondos() {
        System.out.println("Cantidad no válida por insuficiencia de fondos o no permitido por politica de banco.");
    }

    /**
     * Funcion que permite obtener la identificacion ya que se utilizara en otras funciones como parametro para realizar
     * depositos, retiros o conocer el saldo ya que enlaza con la cuenta
     * @return
     */
    public static int mostrarIdentificacion() {
        System.out.println("Digite el número de identificacion del cliente: ");
        return leerNumeros.nextInt();
    }

    /**Funcion que permite mostrar el numero de cuenta en otras funciones ya que se necesita para otras funciones
     * y así el usuario la ingresa y se utiliza como parametro luego
     * @return
     */
    public static int mostrarNumeroCuenta() {
        System.out.println("Digite el número de la cuenta que desea accesar: ");
        return leerNumeros.nextInt();
    }


}
