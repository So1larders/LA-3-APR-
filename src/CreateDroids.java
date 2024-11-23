import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateDroids {

    public static List<Droidec> createDroidsManually() {
        Scanner scanner = new Scanner(System.in);
        List<Droidec> droids = new ArrayList<>();

        System.out.println("Скільки дроїдів ви хочете створити?");
        int numDroids = scanner.nextInt();

        for (int i = 0; i < numDroids; i++) {
            System.out.println("Введіть ім'я дроїда:");
            scanner.nextLine(); // consume newline
            String name = scanner.nextLine();
            System.out.println("Введіть клас дроїда:");
            String nameClass = scanner.nextLine();
            System.out.println("Введіть здоров'я дроїда:");
            int health = scanner.nextInt();
            System.out.println("Введіть урон дроїда:");
            int damage = scanner.nextInt();

            droids.add(new Droidec(name, nameClass, health, damage));
        }

        return droids;
    }
}
