import java.util.Scanner;

// Clase 1: Representa un helado
class Helado {
    String tipo;
    String sabor;
    String acompanamiento;

    public Helado(String tipo, String sabor, String acompanamiento) {
        this.tipo = tipo;
        this.sabor = sabor;
        this.acompanamiento = acompanamiento;
    }

    public String toString() {
        return "Tipo: " + tipo + ", Sabor: " + sabor + ", Acompañamiento: " + acompanamiento;
    }
}

// Clase 2: BackEnd de la heladería (CRUD)
class HeladeriaBackEnd {
    static final int MAX_HELADOS = 20;
    Helado[] inventario = new Helado[MAX_HELADOS];
    int total = 0;

    public void crearHelado(String tipo, String sabor, String acompanamiento) {
        if (total < MAX_HELADOS) {
            inventario[total++] = new Helado(tipo, sabor, acompanamiento);
            System.out.println(" Helado creado exitosamente.");
        } else {
            System.out.println(" Inventario lleno.");
        }
    }

    public void listarHelados() {
        if (total == 0) {
            System.out.println("No hay helados registrados.");
            return;
        }
        for (int i = 0; i < total; i++) {
            System.out.println((i+1) + ". " + inventario[i]);
        }
    }

    public void editarHelado(int index, String tipo, String sabor, String acompanamiento) {
        if (index >= 0 && index < total) {
            inventario[index] = new Helado(tipo, sabor, acompanamiento);
            System.out.println(" Helado editado correctamente.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void borrarHelado(int index) {
        if (index >= 0 && index < total) {
            for (int i = index; i < total - 1; i++) {
                inventario[i] = inventario[i+1];
            }
            inventario[--total] = null;
            System.out.println("🗑️ Helado borrado.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void buscarHelado(String tipo) {
        boolean encontrado = false;
        for (int i = 0; i < total; i++) {
            if (inventario[i].tipo.equalsIgnoreCase(tipo)) {
                System.out.println(" Encontrado: " + inventario[i]);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No se encontró el helado.");
    }
}

// Clase 3: Interfaz de usuario
public class HeladeriaVersion2 {
    static Scanner input = new Scanner(System.in);
    static HeladeriaBackEnd backend = new HeladeriaBackEnd();

    public static void main(String[] args) {
        mostrarMenu();
    }

    // Recursividad en el menú
    public static void mostrarMenu() {
        System.out.println("\n=== MENÚ HELADERÍA ===");
        System.out.println("1. Crear helado");
        System.out.println("2. Listar helados");
        System.out.println("3. Editar helado");
        System.out.println("4. Borrar helado");
        System.out.println("5. Buscar helado");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");

        int opcion = input.nextInt();
        input.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Tipo: ");
                String tipo = input.nextLine();
                System.out.print("Sabor: ");
                String sabor = input.nextLine();
                System.out.print("Acompañamiento: ");
                String acomp = input.nextLine();
                backend.crearHelado(tipo, sabor, acomp);
                break;

            case 2:
                backend.listarHelados();
                break;

            case 3:
                backend.listarHelados();
                System.out.print("Ingrese número de helado a editar: ");
                int idxE = input.nextInt() - 1;
                input.nextLine();
                System.out.print("Nuevo tipo: ");
                String t = input.nextLine();
                System.out.print("Nuevo sabor: ");
                String s = input.nextLine();
                System.out.print("Nuevo acompañamiento: ");
                String a = input.nextLine();
                backend.editarHelado(idxE, t, s, a);
                break;

            case 4:
                backend.listarHelados();
                System.out.print("Ingrese número de helado a borrar: ");
                int idxB = input.nextInt() - 1;
                backend.borrarHelado(idxB);
                break;

            case 5:
                System.out.print("Ingrese tipo de helado a buscar: ");
                String busq = input.nextLine();
                backend.buscarHelado(busq);
                break;

            case 6:
                System.out.println("Saliendo...");
                return; // caso base de recursividad

            default:
                System.out.println("Opción no válida.");
        }

        // Recursividad: vuelve al menú
        mostrarMenu();
    }
}
