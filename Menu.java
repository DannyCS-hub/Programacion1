import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;

        System.out.println("****************************");
        System.out.println("*     Sistema de ventas    *");
        System.out.println("*     Heladeria Hielo      *");
        System.out.println("*--------------------------*");
        System.out.println("* * * * * * Menu * * * * * *");
        System.out.println("*    1. Cono Sencillo      *");
        System.out.println("*    2. Cono Doble         *");
        System.out.println("*    3. Cono Triple        *");
        System.out.println("*    4. Cono de 4 Bolas    *");
        System.out.println("*    5. Cono de 5 Bolas    *");
        System.out.println("****************************");

        System.out.println("Seleccione su opcion: ");
        opcion = entrada.nextInt();

        // Uso de IFs anidados
        if (opcion == 1) {
            System.out.println("Usted eligió: Cono Sencillo");
        } else {
            if (opcion == 2) {
                System.out.println("Usted eligió: Cono Doble");
            } else {
                if (opcion == 3) {
                    System.out.println("Usted eligió: Cono Triple");
                } else {
                    if (opcion == 4) {
                        System.out.println("Usted eligió: Cono de 4 Bolas");
                    } else {
                        if (opcion == 5) {
                            System.out.println("Usted eligió: Cono de 5 Bolas");
                        } else {
                            System.out.println("Opción no válida, por favor elija entre 1 y 5.");
                        }
                    }
                }
            }
        }
    }
}
