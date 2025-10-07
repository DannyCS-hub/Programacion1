import java.util.ArrayList;
import java.util.Scanner;

// ------------------------- CLASES MODELO -------------------------
class Helado {
    private int id;
    private String sabor;
    private ArrayList<String> ingredientes;
    private int tipoCono; // 1=Galleta, 2=Cono, 3=Vaso

    public Helado(int id, String sabor) {
        this.id = id;
        this.sabor = sabor;
        this.ingredientes = new ArrayList<>();
        this.tipoCono = 1;
    }

    public int getId() { return id; }
    public String getSabor() { return sabor; }
    public ArrayList<String> getIngredientes() { return ingredientes; }
    public int getTipoCono() { return tipoCono; }

    public void setSabor(String sabor) { this.sabor = sabor; }
    public void setIngredientes(ArrayList<String> ingredientes) { this.ingredientes = ingredientes; }
    public void setTipoCono(int tipoCono) { this.tipoCono = tipoCono; }

    @Override
    public String toString() {
        String tipoConoStr = switch (tipoCono) {
            case 1 -> "Galleta";
            case 2 -> "Cono";
            case 3 -> "Vaso";
            default -> "Desconocido";
        };
        return "Helado{" +
                "id=" + id +
                ", sabor='" + sabor + '\'' +
                ", ingredientes=" + ingredientes +
                ", tipoCono=" + tipoConoStr +
                '}';
    }
}

class Malteada {
    private int id;
    private String sabor;

    public Malteada(int id, String sabor) {
        this.id = id;
        this.sabor = sabor;
    }

    public int getId() { return id; }
    public String getSabor() { return sabor; }
    public void setSabor(String sabor) { this.sabor = sabor; }

    @Override
    public String toString() {
        return "Malteada{" + "id=" + id + ", sabor='" + sabor + '\'' + '}';
    }
}

class Toppings {
    private int id;
    private String nombre;
    private float precio;
    private ArrayList<String> ingredientes;

    public Toppings(int id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public float getPrecio() { return precio; }
    public ArrayList<String> getIngredientes() { return ingredientes; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(float precio) { this.precio = precio; }
    public void setIngredientes(ArrayList<String> ingredientes) { this.ingredientes = ingredientes; }

    @Override
    public String toString() {
        return "Topping{" + "id=" + id + ", nombre='" + nombre + '\'' +
                ", precio=" + precio + ", ingredientes=" + ingredientes + '}';
    }
}

class Heladeria {
    private int id;
    private String direccion;
    private ArrayList<Helado> listaHeladosDisponibles;
    private ArrayList<Malteada> listaMalteadasDisponibles;
    private ArrayList<Toppings> listaToppings;

    public Heladeria(int id, String direccion) {
        this.id = id;
        this.direccion = direccion;
        this.listaHeladosDisponibles = new ArrayList<>();
        this.listaMalteadasDisponibles = new ArrayList<>();
        this.listaToppings = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getDireccion() { return direccion; }
    public ArrayList<Helado> getListaHeladosDisponibles() { return listaHeladosDisponibles; }
    public ArrayList<Malteada> getListaMalteadasDisponibles() { return listaMalteadasDisponibles; }
    public ArrayList<Toppings> getListaToppings() { return listaToppings; }

    @Override
    public String toString() {
        return "Heladeria{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", helados=" + listaHeladosDisponibles.size() +
                ", malteadas=" + listaMalteadasDisponibles.size() +
                ", toppings=" + listaToppings.size() +
                '}';
    }
}

// ------------------------- CONTROLADOR -------------------------
class ControladorHeladeria {
    private ArrayList<Heladeria> listaHeladerias = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. CRUD Heladerías");
            System.out.println("2. Salir");
            System.out.print("Seleccione: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1 -> crudHeladeria();
                case 2 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 2);
    }

    private void crudHeladeria() {
        int opcion;
        do {
            System.out.println("\n--- CRUD HELADERÍAS ---");
            System.out.println("1. Crear");
            System.out.println("2. Ver");
            System.out.println("3. Gestionar");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("Dirección: ");
                    String dir = input.nextLine();
                    listaHeladerias.add(new Heladeria(id, dir));
                }
                case 2 -> listaHeladerias.forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID de heladería: ");
                    int idH = input.nextInt();
                    listaHeladerias.stream()
                            .filter(h -> h.getId() == idH)
                            .findFirst()
                            .ifPresent(this::gestionarHeladeria);
                }
                case 4 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 4);
    }

    private void gestionarHeladeria(Heladeria h) {
        int opcion;
        do {
            System.out.println("\n--- GESTIONANDO HELADERÍA " + h.getId() + " ---");
            System.out.println("1. CRUD Helados");
            System.out.println("2. CRUD Malteadas");
            System.out.println("3. CRUD Toppings");
            System.out.println("4. Volver");
            System.out.print("Seleccione: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1 -> crudHelados(h);
                case 2 -> crudMalteadas(h);
                case 3 -> crudToppings(h);
                case 4 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 4);
    }

    // ----------------- CRUD HELADOS -----------------
    private void crudHelados(Heladeria h) {
        int opcion;
        do {
            System.out.println("\n--- CRUD HELADOS ---");
            System.out.println("1. Crear");
            System.out.println("2. Ver");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Seleccione: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Sabor: ");
                    String sabor = input.nextLine();

                    ArrayList<String> ingredientes = new ArrayList<>();
                    System.out.print("¿Cuántos ingredientes?: ");
                    int n = input.nextInt();
                    input.nextLine();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Ingrediente " + (i + 1) + ": ");
                        ingredientes.add(input.nextLine());
                    }

                    System.out.print("Tipo de cono (1=Galleta, 2=Cono, 3=Vaso): ");
                    int tipoCono = input.nextInt();

                    Helado nuevo = new Helado(id, sabor);
                    nuevo.setIngredientes(ingredientes);
                    nuevo.setTipoCono(tipoCono);

                    h.getListaHeladosDisponibles().add(nuevo);
                    System.out.println(" Helado agregado.");
                }
                case 2 -> h.getListaHeladosDisponibles().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID a editar: ");
                    int idE = input.nextInt();
                    input.nextLine();
                    for (Helado hel : h.getListaHeladosDisponibles()) {
                        if (hel.getId() == idE) {
                            System.out.print("Nuevo sabor: ");
                            hel.setSabor(input.nextLine());

                            ArrayList<String> nuevosIng = new ArrayList<>();
                            System.out.print("¿Cuántos ingredientes?: ");
                            int m = input.nextInt();
                            input.nextLine();
                            for (int i = 0; i < m; i++) {
                                System.out.print("Ingrediente " + (i + 1) + ": ");
                                nuevosIng.add(input.nextLine());
                            }
                            hel.setIngredientes(nuevosIng);

                            System.out.print("Nuevo tipo de cono (1=Galleta, 2=Cono, 3=Vaso): ");
                            hel.setTipoCono(input.nextInt());

                            System.out.println(" Helado actualizado.");
                        }
                    }
                }
                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int idD = input.nextInt();
                    h.getListaHeladosDisponibles().removeIf(hel -> hel.getId() == idD);
                    System.out.println(" Helado eliminado.");
                }
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    // ----------------- CRUD MALTEADAS -----------------
    private void crudMalteadas(Heladeria h) {
        int opcion;
        do {
            System.out.println("\n--- CRUD MALTEADAS ---");
            System.out.println("1. Crear");
            System.out.println("2. Ver");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Seleccione: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("Sabor: ");
                    String sabor = input.nextLine();
                    h.getListaMalteadasDisponibles().add(new Malteada(id, sabor));
                    System.out.println(" Malteada agregada.");
                }
                case 2 -> h.getListaMalteadasDisponibles().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID a editar: ");
                    int idE = input.nextInt();
                    input.nextLine();
                    for (Malteada m : h.getListaMalteadasDisponibles()) {
                        if (m.getId() == idE) {
                            System.out.print("Nuevo sabor: ");
                            m.setSabor(input.nextLine());
                            System.out.println(" Malteada actualizada.");
                        }
                    }
                }
                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int idD = input.nextInt();
                    h.getListaMalteadasDisponibles().removeIf(m -> m.getId() == idD);
                    System.out.println(" Malteada eliminada.");
                }
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }

    // ----------------- CRUD TOPPINGS -----------------
    private void crudToppings(Heladeria h) {
        int opcion;
        do {
            System.out.println("\n--- CRUD TOPPINGS ---");
            System.out.println("1. Crear");
            System.out.println("2. Ver");
            System.out.println("3. Editar");
            System.out.println("4. Eliminar");
            System.out.println("5. Volver");
            System.out.print("Seleccione: ");
            opcion = input.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = input.nextLine();
                    System.out.print("Precio: ");
                    float precio = input.nextFloat();
                    input.nextLine();

                    ArrayList<String> ingredientes = new ArrayList<>();
                    System.out.print("¿Cuántos ingredientes?: ");
                    int n = input.nextInt();
                    input.nextLine();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Ingrediente " + (i + 1) + ": ");
                        ingredientes.add(input.nextLine());
                    }

                    Toppings t = new Toppings(id, nombre, precio);
                    t.setIngredientes(ingredientes);
                    h.getListaToppings().add(t);
                    System.out.println(" Topping agregado.");
                }
                case 2 -> h.getListaToppings().forEach(System.out::println);
                case 3 -> {
                    System.out.print("ID a editar: ");
                    int idE = input.nextInt();
                    input.nextLine();
                    for (Toppings t : h.getListaToppings()) {
                        if (t.getId() == idE) {
                            System.out.print("Nuevo nombre: ");
                            t.setNombre(input.nextLine());
                            System.out.print("Nuevo precio: ");
                            t.setPrecio(input.nextFloat());
                            input.nextLine();

                            ArrayList<String> nuevosIng = new ArrayList<>();
                            System.out.print("¿Cuántos ingredientes?: ");
                            int m = input.nextInt();
                            input.nextLine();
                            for (int i = 0; i < m; i++) {
                                System.out.print("Ingrediente " + (i + 1) + ": ");
                                nuevosIng.add(input.nextLine());
                            }
                            t.setIngredientes(nuevosIng);

                            System.out.println(" Topping actualizado.");
                        }
                    }
                }
                case 4 -> {
                    System.out.print("ID a eliminar: ");
                    int idD = input.nextInt();
                    h.getListaToppings().removeIf(t -> t.getId() == idD);
                    System.out.println(" Topping eliminado.");
                }
                case 5 -> System.out.println("Volviendo...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 5);
    }
}

// ------------------------- MAIN -------------------------
public class Main {
    public static void main(String[] args) {
        ControladorHeladeria app = new ControladorHeladeria();
        app.menuPrincipal();
    }
}
