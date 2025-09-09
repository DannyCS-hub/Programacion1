import java.util.Scanner;

public class MenuClienteA {

    // Constantes
    static final int MAX_USUARIOS = 30;
    static final boolean ACTIVO = true;

    // Arrays para almacenar los datos de hasta 30 usuarios
    static String[] nombres = new String[MAX_USUARIOS];
    static float[] salarios = new float[MAX_USUARIOS];
    static char[] sexos = new char[MAX_USUARIOS];
    static String[] cargos = new String[MAX_USUARIOS];
    static int totalUsuarios = 0; // Contador de usuarios registrados

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (ACTIVO) {
            char opcion = mostrarMenuPrincipal();  // Menú principal

            switch (opcion) {
                case '1': // Crear
                    if (totalUsuarios >= MAX_USUARIOS) {
                        System.out.println("¡Límite alcanzado! No se pueden agregar más usuarios.");
                        break;
                    }
                    crearUsuario();
                    break;

                case '2': // Listar todos los usuarios
                    listarUsuarios();
                    break;

                case '3': // Editar
                    editarUsuario();
                    break;

                case '4': // Borrar
                    borrarUsuario();
                    break;

                case '5': // Buscar usuario
                    buscarUsuario();
                    break;

                case '0': // Salir
                    System.out.println("Saliendo del programa...");
                    return;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Menú principal
    public static char mostrarMenuPrincipal() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE CLIENTES (" + totalUsuarios + "/" + MAX_USUARIOS + ") ===");
        System.out.println("1. Crear nuevo usuario");
        System.out.println("2. Listar todos los usuarios");
        System.out.println("3. Editar usuario");
        System.out.println("4. Borrar usuario");
        System.out.println("5. Buscar usuario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        return input.next().charAt(0);
    }

    // Crear un nuevo usuario
    public static void crearUsuario() {
        System.out.println("\n=== CREAR NUEVO USUARIO ===");

        System.out.print("Digite el nombre: ");
        nombres[totalUsuarios] = input.next();
        input.nextLine(); // limpiar buffer

        System.out.print("Digite el salario: ");
        salarios[totalUsuarios] = input.nextFloat();
        input.nextLine();

        // Validar sexo
        char sexo;
        do {
            System.out.print("Digite el sexo (M/F): ");
            sexo = Character.toUpperCase(input.next().charAt(0));
            if (sexo != 'M' && sexo != 'F') {
                System.out.println("Error: Sexo debe ser 'M' o 'F'.");
            }
        } while (sexo != 'M' && sexo != 'F');
        sexos[totalUsuarios] = sexo;
        input.nextLine();

        System.out.print("Digite el cargo: ");
        cargos[totalUsuarios] = input.nextLine();

        totalUsuarios++;
        System.out.println("¡Usuario creado exitosamente!");
    }

    // Listar todos los usuarios (Opción 2)
    public static void listarUsuarios() {
        if (totalUsuarios == 0) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== LISTADO DE USUARIOS (" + totalUsuarios + ") ===");
        for (int i = 0; i < totalUsuarios; i++) {
            System.out.println("\nUsuario #" + (i+1));
            System.out.println("Nombre: " + nombres[i]);
            System.out.println("Salario: " + salarios[i]);
            System.out.println("Sexo: " + (sexos[i] == 'M' ? "Masculino" : "Femenino"));
            System.out.println("Cargo: " + cargos[i]);
            System.out.println("----------------------------");
        }

        // Preguntar si desea volver al menú o editar
        System.out.print("\nPresione 'E' para editar un usuario o cualquier otra tecla para volver al menú: ");
        char opcion = Character.toUpperCase(input.next().charAt(0));
        if (opcion == 'E') {
            editarUsuario();
        }
    }

    // Editar un usuario existente
    public static void editarUsuario() {
        if (totalUsuarios == 0) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }

        // Mostrar lista simplificada de usuarios
        System.out.println("\n=== SELECCIONAR USUARIO A EDITAR ===");
        for (int i = 0; i < totalUsuarios; i++) {
            System.out.println((i+1) + ". " + nombres[i] + " - " + cargos[i]);
        }

        System.out.print("\nSeleccione el número de usuario a editar: ");
        int usuarioId = input.nextInt() - 1;

        if (usuarioId < 0 || usuarioId >= totalUsuarios) {
            System.out.println("Número de usuario inválido.");
            return;
        }

        char opcion;
        do {
            System.out.println("\n=== EDITANDO USUARIO: " + nombres[usuarioId] + " ===");
            System.out.println("1. Nombre: " + nombres[usuarioId]);
            System.out.println("2. Salario: " + salarios[usuarioId]);
            System.out.println("3. Sexo: " + (sexos[usuarioId] == 'M' ? "Masculino" : "Femenino"));
            System.out.println("4. Cargo: " + cargos[usuarioId]);
            System.out.println("5. Finalizar edición");
            System.out.print("Seleccione el campo a editar: ");
            opcion = input.next().charAt(0);

            switch (opcion) {
                case '1':
                    System.out.print("Nuevo nombre: ");
                    nombres[usuarioId] = input.next();
                    input.nextLine();
                    System.out.println("Nombre actualizado correctamente.");
                    break;
                case '2':
                    System.out.print("Nuevo salario: ");
                    salarios[usuarioId] = input.nextFloat();
                    input.nextLine();
                    System.out.println("Salario actualizado correctamente.");
                    break;
                case '3':
                    char nuevoSexo;
                    do {
                        System.out.print("Nuevo sexo (M/F): ");
                        nuevoSexo = Character.toUpperCase(input.next().charAt(0));
                        if (nuevoSexo != 'M' && nuevoSexo != 'F') {
                            System.out.println("Error: Sexo debe ser 'M' o 'F'.");
                        }
                    } while (nuevoSexo != 'M' && nuevoSexo != 'F');
                    sexos[usuarioId] = nuevoSexo;
                    input.nextLine();
                    System.out.println("Sexo actualizado correctamente.");
                    break;
                case '4':
                    System.out.print("Nuevo cargo: ");
                    input.nextLine(); // Limpiar buffer
                    cargos[usuarioId] = input.nextLine();
                    System.out.println("Cargo actualizado correctamente.");
                    break;
                case '5':
                    System.out.println("Edición finalizada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != '5');
    }

    // Borrar un usuario
    public static void borrarUsuario() {
        if (totalUsuarios == 0) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }

        // Mostrar lista simplificada de usuarios
        System.out.println("\n=== SELECCIONAR USUARIO A BORRAR ===");
        for (int i = 0; i < totalUsuarios; i++) {
            System.out.println((i+1) + ". " + nombres[i] + " - " + cargos[i]);
        }

        System.out.print("\nSeleccione el número de usuario a borrar: ");
        int usuarioId = input.nextInt() - 1;

        if (usuarioId < 0 || usuarioId >= totalUsuarios) {
            System.out.println("Número de usuario inválido.");
            return;
        }

        // Confirmar borrado
        System.out.print("¿Está seguro de que desea borrar al usuario: " + nombres[usuarioId] + "? (S/N): ");
        char confirmacion = Character.toUpperCase(input.next().charAt(0));

        if (confirmacion == 'S') {
            // Desplazar todos los usuarios posteriores una posición hacia atrás
            for (int i = usuarioId; i < totalUsuarios - 1; i++) {
                nombres[i] = nombres[i+1];
                salarios[i] = salarios[i+1];
                sexos[i] = sexos[i+1];
                cargos[i] = cargos[i+1];
            }

            // Limpiar el último elemento
            nombres[totalUsuarios-1] = null;
            salarios[totalUsuarios-1] = 0;
            sexos[totalUsuarios-1] = ' ';
            cargos[totalUsuarios-1] = null;

            totalUsuarios--;
            System.out.println("Usuario borrado exitosamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    // Buscar usuario por nombre (Opción 5)
    public static void buscarUsuario() {
        if (totalUsuarios == 0) {
            System.out.println("\nNo hay usuarios registrados.");
            return;
        }

        System.out.println("\n=== BUSCAR USUARIO ===");
        System.out.print("Ingrese el nombre a buscar: ");
        String nombreBuscado = input.next();
        input.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < totalUsuarios; i++) {
            if (nombres[i].equalsIgnoreCase(nombreBuscado)) {
                System.out.println("\n=== USUARIO ENCONTRADO ===");
                System.out.println("Nombre: " + nombres[i]);
                System.out.println("Salario: " + salarios[i]);
                System.out.println("Sexo: " + (sexos[i] == 'M' ? "Masculino" : "Femenino"));
                System.out.println("Cargo: " + cargos[i]);
                encontrado = true;

                // Preguntar si desea editar este usuario
                System.out.print("\nPresione 'E' para editar este usuario o cualquier otra tecla para continuar: ");
                char opcion = Character.toUpperCase(input.next().charAt(0));
                if (opcion == 'E') {
                    editarUsuarioEspecifico(i);
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Usuario no encontrado.");
        }
    }

    // Método auxiliar para editar un usuario específico
    public static void editarUsuarioEspecifico(int usuarioId) {
        char opcion;
        do {
            System.out.println("\n=== EDITANDO USUARIO: " + nombres[usuarioId] + " ===");
            System.out.println("1. Nombre: " + nombres[usuarioId]);
            System.out.println("2. Salario: " + salarios[usuarioId]);
            System.out.println("3. Sexo: " + (sexos[usuarioId] == 'M' ? "Masculino" : "Femenino"));
            System.out.println("4. Cargo: " + cargos[usuarioId]);
            System.out.println("5. Finalizar edición");
            System.out.print("Seleccione el campo a editar: ");
            opcion = input.next().charAt(0);

            switch (opcion) {
                case '1':
                    System.out.print("Nuevo nombre: ");
                    nombres[usuarioId] = input.next();
                    input.nextLine();
                    System.out.println("Nombre actualizado correctamente.");
                    break;
                case '2':
                    System.out.print("Nuevo salario: ");
                    salarios[usuarioId] = input.nextFloat();
                    input.nextLine();
                    System.out.println("Salario actualizado correctamente.");
                    break;
                case '3':
                    char nuevoSexo;
                    do {
                        System.out.print("Nuevo sexo (M/F): ");
                        nuevoSexo = Character.toUpperCase(input.next().charAt(0));
                        if (nuevoSexo != 'M' && nuevoSexo != 'F') {
                            System.out.println("Error: Sexo debe ser 'M' o 'F'.");
                        }
                    } while (nuevoSexo != 'M' && nuevoSexo != 'F');
                    sexos[usuarioId] = nuevoSexo;
                    input.nextLine();
                    System.out.println("Sexo actualizado correctamente.");
                    break;
                case '4':
                    System.out.print("Nuevo cargo: ");
                    input.nextLine(); // Limpiar buffer
                    cargos[usuarioId] = input.nextLine();
                    System.out.println("Cargo actualizado correctamente.");
                    break;
                case '5':
                    System.out.println("Edición finalizada.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != '5');
    }
}


