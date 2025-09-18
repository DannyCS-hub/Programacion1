import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Usuario[] empleados = new Usuario[10];
        int cantidad = 0;

        int opcion;
        do {
            System.out.println("\n=== MENU EMPLEADOS ===");
            System.out.println("1. Ingresar empleados");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Ordenar empleados (A-Z)");
            System.out.println("4. Editar empleado");
            System.out.println("5. Borrar empleado");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    cantidad = ingresarEmpleados(empleados, cantidad, sc);
                    break;
                case 2:
                    mostrarEmpleados(empleados, cantidad);
                    break;
                case 3:
                    ordenarEmpleadosPorNombre(empleados, cantidad);
                    System.out.println(" Empleados ordenados alfabéticamente.");
                    break;
                case 4:
                    editarEmpleado(empleados, cantidad, sc);
                    break;
                case 5:
                    cantidad = borrarEmpleado(empleados, cantidad, sc);
                    break;
                case 6:
                    System.out.println(" Saliendo del programa...");
                    break;
                default:
                    System.out.println(" Opción no válida.");
            }
        } while (opcion != 6);

        sc.close();
    }




    public static int ingresarEmpleados(Usuario[] empleados, int cantidad, Scanner sc) {
        while (cantidad < empleados.length) {
            System.out.println("Ingrese el nombre del empleado (o -1 para salir): ");
            String nombre = sc.nextLine();
            if (nombre.equals("-1")) break;

            System.out.println("Ingrese el salario del empleado (o -1 para salir): ");
            double salario = sc.nextDouble();
            sc.nextLine(); // limpiar buffer
            if (salario == -1) break;

            empleados[cantidad] = new Usuario(nombre, salario);
            cantidad++;
        }
        return cantidad;
    }

    // 2. Mostrar empleados
    public static void mostrarEmpleados(Usuario[] empleados, int cantidad) {
        if (cantidad == 0) {
            System.out.println("⚠ No hay empleados registrados.");
            return;
        }
        System.out.println("\n=== Lista de Empleados ===");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + empleados[i].getNombre() +
                    " - Salario: " + empleados[i].getSalario());
        }
    }

    // 3. Ordenar empleados alfabéticamente (burbuja)
    public static void ordenarEmpleadosPorNombre(Usuario[] empleados, int cantidad) {
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = 0; j < cantidad - 1 - i; j++) {
                if (empleados[j].getNombre().compareToIgnoreCase(empleados[j + 1].getNombre()) > 0) {
                    Usuario temp = empleados[j];
                    empleados[j] = empleados[j + 1];
                    empleados[j + 1] = temp;
                }
            }
        }
    }

    // 4. Editar empleado
    public static void editarEmpleado(Usuario[] empleados, int cantidad, Scanner sc) {
        mostrarEmpleados(empleados, cantidad);
        if (cantidad == 0) return;

        System.out.print("Ingrese el número del empleado a editar: ");
        int pos = sc.nextInt() - 1;
        sc.nextLine();

        if (pos < 0 || pos >= cantidad) {
            System.out.println("Empleado no válido.");
            return;
        }

        System.out.print("Nuevo nombre (actual: " + empleados[pos].getNombre() + "): ");
        String nuevoNombre = sc.nextLine();
        if (!nuevoNombre.isEmpty()) {
            empleados[pos].setNombre(nuevoNombre);
        }

        System.out.print("Nuevo salario (actual: " + empleados[pos].getSalario() + "): ");
        double nuevoSalario = sc.nextDouble();
        sc.nextLine();
        empleados[pos].setSalario(nuevoSalario);

        System.out.println("Empleado editado correctamente.");
    }

    // 5. Borrar empleado
    public static int borrarEmpleado(Usuario[] empleados, int cantidad, Scanner sc) {
        mostrarEmpleados(empleados, cantidad);
        if (cantidad == 0) return cantidad;

        System.out.print("Ingrese el número del empleado a borrar: ");
        int pos = sc.nextInt() - 1;
        sc.nextLine();

        if (pos < 0 || pos >= cantidad) {
            System.out.println("Empleado no válido.");
            return cantidad;
        }

        // Recorrer los elementos hacia atrás para "borrar"
        for (int i = pos; i < cantidad - 1; i++) {
            empleados[i] = empleados[i + 1];
        }
        empleados[cantidad - 1] = null; // limpiar último
        cantidad--;

        System.out.println("Empleado borrado correctamente.");
        return cantidad;
    }
}

// === CLASE USUARIO ===
class Usuario {
    private String nombre;
    private double salario;

    public Usuario(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
