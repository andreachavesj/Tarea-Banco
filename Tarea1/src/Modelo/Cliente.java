package Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {

    /**
     * atributos con los que cuenta un cliente si es que desea ingresar al banco
     */


    public String nombre;
    public int identificacion;
    public String fechaNacimiento;

    public int edad;

    public String direccion;

    public ArrayList<Tarjeta> tarjetasCliente;

    /**
     * Funcion constructor de los atributos del cliente
     * @param nombre
     * @param identificacion
     * @param fechaNacimiento
     * @param edad
     * @param direccion
     */
    public Cliente(String nombre, int identificacion, String fechaNacimiento, int edad, String direccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.direccion = direccion;
    }

    /**
     * getter de los atributos del cliente
     */

    public String getNombre() {
        return nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public ArrayList<Tarjeta> getTarjetasCliente() {
        return tarjetasCliente;
    }


    /**
     * ToString del clinente para poder utilizarlo en listar clientes y mostrar su informacion correspondiente
     * @return
     */
    @Override
    public String toString() {
        return "{ Nombre::'" + nombre + '\'' +
                ", identificacion: " + identificacion +
                ", fecha nacimiento: " + fechaNacimiento + '\'' +
                ", edad: " + edad +
                ", direccion: " + direccion + '\'' +
                ", tarjetasCliente: " + tarjetasCliente +
                '}';
    }
}
