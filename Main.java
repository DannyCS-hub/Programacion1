import java.util.ArrayList;
import java.util.Scanner;

// ------------------- CLASE PRINCIPAL -------------------
public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.iniciar();
    }
}

// ------------------- CLASE CONTROLADOR -------------------
class Controlador {
    private ArrayList<Heladeria> listaHeladerias = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        Menus menuPpal = new Menus(40, '*', '1');
        ArrayList<String> options = new ArrayList<>();
        options.add("Agregar heladeria");
        options.add("Ver heladerias");
        options.add("Entrar a heladeria");
        options.add("Editar heladeria");
        options.add("Eliminar heladeria");
        options.add("Terminar");

        boolean continuar = true;
        while (continuar) {
            int opcion = menuPpal.createMenu(options, false);
            switch (opcion) {
                case 1 -> agregarHeladeria();
                case 2 -> verHeladerias();
                case 3 -> entrarHeladeria();
                case 4 -> editarHeladeria();
                case 5 -> eliminarHeladeria();
                case 6 -> {
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                }
            }
        }
    }

    private void agregarHeladeria() {
        System.out.print("Ingrese ID de la heladeria: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese direccion de la heladeria: ");
        String direccion = sc.nextLine();
        Heladeria h = new Heladeria(id, direccion);
        listaHeladerias.add(h);
        System.out.println(" Heladeria agregada con exito.");
    }

    private void verHeladerias() {
        if (listaHeladerias.isEmpty()) {
            System.out.println(" No hay heladerias registradas.");
            return;
        }
        for (Heladeria h : listaHeladerias) {
            System.out.println("ID: " + h.id + " | Direccion: " + h.getDireccion());
        }
    }

    private Heladeria buscarHeladeriaPorId(int id) {
        for (Heladeria h : listaHeladerias) {
            if (h.id == id) return h;
        }
        return null;
    }

    private void entrarHeladeria() {
        System.out.print("Ingrese ID de la heladeria: ");
        int id = sc.nextInt();
        sc.nextLine();
        Heladeria h = buscarHeladeriaPorId(id);
        if (h == null) {
            System.out.println(" Heladeria no encontrada.");
            return;
        }

        Menus menuHeladeria = new Menus(50, '-', '1');
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Agregar helado");
        opciones.add("Ver helados");
        opciones.add("Agregar malteada");
        opciones.add("Ver malteadas");
        opciones.add("Volver al menu principal");

        boolean dentro = true;
        while (dentro) {
            int opcion = menuHeladeria.createMenu(opciones, false);
            switch (opcion) {
                case 1 -> agregarHelado(h);
                case 2 -> verHelados(h);
                case 3 -> agregarMalteada(h);
                case 4 -> verMalteadas(h);
                case 5 -> dentro = false;
            }
        }
    }

    private void editarHeladeria() {
        System.out.print("Ingrese ID de la heladeria a editar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Heladeria h = buscarHeladeriaPorId(id);
        if (h != null) {
            System.out.print("Nueva direccion: ");
            String direccion = sc.nextLine();
            h.setDireccion(direccion);
            System.out.println("️ Direccion actualizada.");
        } else {
            System.out.println(" Heladeria no encontrada.");
        }
    }

    private void eliminarHeladeria() {
        System.out.print("Ingrese ID de la heladeria a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Heladeria h = buscarHeladeriaPorId(id);
        if (h != null) {
            listaHeladerias.remove(h);
            System.out.println("️ Heladeria eliminada.");
        } else {
            System.out.println(" Heladeria no encontrada.");
        }
    }

    // ------------------- METODOS HELADOS -------------------
    private void agregarHelado(Heladeria h) {
        System.out.print("Ingrese ID del helado: ");
        int id = sc.nextInt();
        System.out.print("Ingrese precio base del helado: ");
        float precio = sc.nextFloat();
        System.out.print("Ingrese tipo (1=cono, 2=vaso, 3=sundae): ");
        int tipo = sc.nextInt();
        sc.nextLine();

        ArrayList<String> sabores = new ArrayList<>();
        System.out.print("Ingrese sabor principal: ");
        sabores.add(sc.nextLine());

        Helado nuevo = new Helado(id, precio, sabores, tipo);

        boolean agregarToppings = true;
        while (agregarToppings) {
            System.out.print("¿Desea agregar un topping? (s/n): ");
            String r = sc.nextLine();
            if (r.equalsIgnoreCase("s")) {
                System.out.print("Ingrese topping: ");
                String topping = sc.nextLine();
                nuevo.addTopping(topping);
            } else {
                agregarToppings = false;
            }
        }

        h.getListaHeladosDisponibles().add(nuevo);
        System.out.println(" Helado agregado con éxito.");
    }

    private void verHelados(Heladeria h) {
        if (h.getListaHeladosDisponibles().isEmpty()) {
            System.out.println("️ No hay helados en esta heladeria.");
            return;
        }
        for (Helado helado : h.getListaHeladosDisponibles()) {
            System.out.println("ID: " + helado.id +
                    " | Precio: " + helado.getPrecio() +
                    " | Sabores: " + helado.getSabores() +
                    " | Toppings: " + helado.getToppings() +
                    " | Precio Final: " + helado.calcularPrecio());
        }
    }

    // ------------------- METODOS MALTEADAS -------------------
    private void agregarMalteada(Heladeria h) {
        System.out.print("Ingrese ID de la malteada: ");
        int id = sc.nextInt();
        System.out.print("Ingrese precio base de la malteada: ");
        float precio = sc.nextFloat();
        sc.nextLine();

        ArrayList<String> sabores = new ArrayList<>();
        System.out.print("Ingrese sabor principal: ");
        sabores.add(sc.nextLine());

        Malteada nueva = new Malteada(id, precio, sabores);
        h.getListaMalteadasDisponibles().add(nueva);
        System.out.println(" Malteada agregada con éxito.");
    }

    private void verMalteadas(Heladeria h) {
        if (h.getListaMalteadasDisponibles().isEmpty()) {
            System.out.println("️ No hay malteadas en esta heladeria.");
            return;
        }
        for (Malteada m : h.getListaMalteadasDisponibles()) {
            System.out.println("ID: " + m.getId() +
                    " | Precio: " + m.getPrecio() +
                    " | Sabores: " + m.getSabores());
        }
    }
}

// ------------------- CLASE MENUS -------------------
class Menus {
    private int windowWidth = 30;
    private char borderChar = '*';
    private char firstOption = '1';

    public Menus(int windowWidth, char borderChar, char firstOption) {
        this.windowWidth = windowWidth;
        this.borderChar = borderChar;
        this.firstOption = firstOption;
    }

    public int createMenu(ArrayList<String> options, boolean CENTERED) {
        boolean DRAW_MENU = true;
        char optionId = firstOption;
        int option = 0;
        Scanner input = new Scanner(System.in);

        while (DRAW_MENU) {
            optionId = firstOption;
            System.out.println(borderLine());
            for (String optionText : options) {
                if (CENTERED)
                    System.out.println(padding(optionId + ". " + optionText));
                else
                    System.out.println(optionId + ". " + optionText);
                optionId++;
            }
            System.out.println(borderLine());
            System.out.print("Seleccione una opcion: ");
            option = input.nextInt();
            if ((option >= (firstOption - '0')) && (option <= (options.size()))) {
                DRAW_MENU = false;
            } else {
                System.out.println("Opcion invalida");
            }
        }
        return option;
    }

    public String borderLine() {
        String line = "";
        for (int i = 0; i < windowWidth; i++) {
            line += borderChar;
        }
        return line;
    }

    public String padding(String message) {
        int padding = (windowWidth - message.length()) / 2;
        String paddingString = "";
        for (int i = 0; i < padding; i++) {
            paddingString += " ";
        }
        return paddingString + message;
    }
}

// ------------------- CLASE HELADO -------------------
class Helado {
    public int id;
    public float precio;
    private float precioTopping;
    private ArrayList<String> sabores;
    private ArrayList<String> toppings;
    private int tipo;

    public Helado(int id, float precio, ArrayList<String> sabores, int tipo) {
        this.id = id;
        this.precio = precio;
        this.sabores = sabores;
        this.tipo = tipo;
        this.toppings = new ArrayList<String>();
        this.precioTopping = 500;
    }

    public float calcularPrecio() {
        return this.precio + (this.precioTopping * this.toppings.size());
    }

    public void setPrecio(float precio) { this.precio = precio; }
    public void setPrecioTopping(float precioTopping) { this.precioTopping = precioTopping; }
    public void addTopping(String toppingType) { this.toppings.add(toppingType); }
    public void setTipo(int tipo) { this.tipo = tipo; }

    public float getPrecio() { return this.precio; }
    public ArrayList<String> getSabores() { return this.sabores; }
    public ArrayList<String> getToppings() { return this.toppings; }
    public int getTipo() { return this.tipo; }
    public float getPrecioTopping() { return this.precioTopping; }
}

// ------------------- CLASE PEDIDO -------------------
class Pedido {
    private ArrayList<Helado> listaHelados;
    private ArrayList<Malteada> listaMalteadas;
    public int mesa;
    public float propina;

    public Pedido(int mesa) {
        this.mesa = mesa;
        this.listaHelados = new ArrayList<Helado>();
        this.listaMalteadas = new ArrayList<Malteada>();
    }

    public float calcularCuenta() {
        float total = 0;
        for (Helado helado : this.listaHelados) {
            total += helado.calcularPrecio();
        }
        for (Malteada malteada : this.listaMalteadas) {
            total += malteada.calcularPrecio();
        }
        return total + this.propina;
    }

    public void addHelado(Helado producto) { this.listaHelados.add(producto); }
    public void addMalteada(Malteada producto) { this.listaMalteadas.add(producto); }
}

// ------------------- CLASE HELADERIA -------------------
class Heladeria {
    public int id;
    private String direccion;
    private ArrayList<Helado> listaHeladosDisponibles;
    private ArrayList<Malteada> listaMalteadasDisponibles;
    private ArrayList<Pedido> listaPedidos;

    public Heladeria(int id, String direccion) {
        this.id = id;
        this.direccion = direccion;
        this.listaHeladosDisponibles = new ArrayList<Helado>();
        this.listaMalteadasDisponibles = new ArrayList<Malteada>();
        this.listaPedidos = new ArrayList<Pedido>();
    }

    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setListaHeladosDisponibles(ArrayList<Helado> lista) { this.listaHeladosDisponibles = lista; }
    public void setListaMalteadasDisponibles(ArrayList<Malteada> lista) { this.listaMalteadasDisponibles = lista; }
    public void addListaPedidos(Pedido pedido) { this.listaPedidos.add(pedido); }

    public String getDireccion() { return this.direccion; }
    public ArrayList<Helado> getListaHeladosDisponibles() { return this.listaHeladosDisponibles; }
    public ArrayList<Malteada> getListaMalteadasDisponibles() { return this.listaMalteadasDisponibles; }
    public ArrayList<Pedido> getListaPedidos() { return this.listaPedidos; }
}

// ------------------- CLASE MALTEADA -------------------
class Malteada {
    private int id;
    private float precio;
    private ArrayList<String> sabores;

    public Malteada(int id, float precio, ArrayList<String> sabores) {
        this.id = id;
        this.precio = precio;
        this.sabores = sabores;
    }

    public float calcularPrecio() { return this.precio; }
    public int getId() { return this.id; }
    public float getPrecio() { return this.precio; }
    public ArrayList<String> getSabores() { return this.sabores; }
}
