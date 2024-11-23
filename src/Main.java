import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Droidec> droids = null;

        boolean running = true;
        while (running) {
            System.out.println("\nГоловне меню:");
            System.out.println("1. Створити нових дроїдів або зчитати дроїдів із файлу");
            System.out.println("2. Вибрати тип битви");
            System.out.println("3. Вийти");

            int mainChoice = scanner.nextInt();
            switch (mainChoice) {
                case 1:
                    System.out.println("Виберіть опцію:");
                    System.out.println("1. Створити нових дроїдів");
                    System.out.println("2. Зчитати дроїдів із файлу");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            droids = CreateDroids.createDroidsManually();
                            break;
                        case 2:
                            String fileName = "droids.txt";
                            droids = Droids.readDroidsFromFile(fileName);
                            break;
                        default:
                            System.out.println("Невірна опція!");
                            continue;  // Повертає до головного меню
                    }
                    break;

                case 2:
                    if (droids == null || droids.isEmpty()) {
                        System.out.println("Спочатку потрібно створити або зчитати дроїдів!");
                        continue;
                    }


                    System.out.println("Виберіть тип битви:");
                    System.out.println("1. Один на один");
                    System.out.println("2. Команда проти команди");
                    int battleType = scanner.nextInt();

                    switch (battleType) {
                        case 1:
                            Droidec droid1 = OBattle.chooseDroid(scanner, droids, "першої");
                            Droidec droid2 = OBattle.chooseDroid(scanner, droids, "другій");
                            OBattle.startBattle(droid1, droid2);
                            break;
                        case 2:
                            List<Droidec> team1 = TBattle.chooseTeam(scanner, droids, "першої");
                            List<Droidec> team2 = TBattle.chooseTeam(scanner, droids, "другої");
                            TBattle.startBattle(team1, team2);
                            break;
                        default:
                            System.out.println("Невірний вибір битви!");
                            continue;
                    }
                    break;

                case 3:

                    System.out.println("Вихід...");
                    running = false;
                    break;

                default:
                    System.out.println("Невірна опція, спробуйте знову!");
            }
        }
        scanner.close();
    }
}
