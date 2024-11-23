import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class OBattle {

    public static void startBattle(Droidec droid1, Droidec droid2) {
        Random random = new Random();

        try (FileWriter writer = new FileWriter("InfoOBattle.txt", false)) {
            Locations chosenLocation = Location.getLocationsList().get(random.nextInt(Location.getLocationsList().size()));
            writer.write("Локація обрана: " + chosenLocation.getNameLocation() + "\n");
            System.out.println("Локація для бою: " + chosenLocation.getNameLocation());

            writer.write("Обрані дроїди: " + droid1.getName() + " vs " + droid2.getName() + "\n");
            System.out.println("Бій " + droid1.getName() + " & " + droid2.getName() + " Локація " + chosenLocation.getNameLocation() + "!");

            battleLoop(droid1, droid2, chosenLocation, writer);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public static Droidec chooseDroid(Scanner scanner, List<Droidec> droids, String teamDescription) {
        System.out.println("Оберіть дроїда " + teamDescription + " команди:");
        for (int i = 0; i < droids.size(); i++) {
            System.out.println((i + 1) + ". " + droids.get(i).getName() + " (Здоров'я: " + droids.get(i).getHealth() + ", Урон: " + droids.get(i).getDamage() + ")");
        }
        int choice = scanner.nextInt();
        if (choice < 1 || choice > droids.size()) {
            System.out.println("Невірний вибір, спробуйте ще раз.");
            return chooseDroid(scanner, droids, teamDescription);
        }
        return droids.get(choice - 1);
    }

    public static void battleLoop(Droidec droid1, Droidec droid2, Locations chosenLocation, FileWriter writer) throws IOException {
        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            battleTurn(droid1, droid2, chosenLocation, writer);
            if (droid2.getHealth() <= 0) break;
            battleTurn(droid2, droid1, chosenLocation, writer);
        }
    }

    public static void battleTurn(Droidec attacker, Droidec defender, Locations chosenLocation, FileWriter writer) throws IOException {
        int damage = attacker.calculateDamage();

        if (attacker.getStatus().equals("Оборона") && chosenLocation.getNameBoost().equals("Оборона")) {
            damage += chosenLocation.getCfBoosts();
        } else if (attacker.getStatus().equals("Наступ") && chosenLocation.getNameDebaf().equals("Наступ")) {
            damage += chosenLocation.getCfDebaf();
        }

        defender.takeDamage(damage);
        writer.write(attacker.getName() + " атакує " + defender.getName() + " і завдає урон " + damage + "\n");
        System.out.println(attacker.getName() + " атакує " + defender.getName() + " і завдає урон \u001B[34m" + damage + "\u001B[0m\n");
        writer.write("Залишилось здоров'я у " + defender.getName() + ": " + defender.getHealth() + "\n");
        System.out.println("Залишилось здоров'я у " + defender.getName() + ": \u001B[31m" + defender.getHealth() + "\u001B[0m\n");

        if (defender.getHealth() <= 0) {
            writer.write(defender.getName() + " загинув! Перемога " + attacker.getName() + "\n");
            System.out.println("\u001B[35m" + defender.getName() + "\u001B[0m загинув! Перемога \u001B[33m" + attacker.getName() + "\u001B[0m\n");
        }
    }
}
