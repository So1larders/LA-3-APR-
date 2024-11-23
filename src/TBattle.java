import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class TBattle {

    public static void startBattle(List<Droidec> team1, List<Droidec> team2) {
        Random random = new Random();

        try (FileWriter writer = new FileWriter("InfoTBattle.txt", false)) {
            Locations chosenLocation = Location.getLocationsList().get(random.nextInt(Location.getLocationsList().size()));
            writer.write("Локація обрана: " + chosenLocation.getNameLocation() + "\n");
            System.out.println("Локація для бою: " + chosenLocation.getNameLocation());

            battleLoop(random, writer, chosenLocation, team1, team2);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public static List<Droidec> chooseTeam(Scanner scanner, List<Droidec> droids, String teamDescription) {
        List<Droidec> team = new ArrayList<>();
        System.out.println("Кількість дроїдів " + teamDescription + " команди:");
        int teamSize = scanner.nextInt();

        for (int i = 0; i < teamSize; i++) {
            Droidec droid = OBattle.chooseDroid(scanner, droids, teamDescription);  // Використовуємо метод chooseDroid з OBattle
            team.add(droid);
        }

        return team;
    }

    private static void battleLoop(Random random, FileWriter writer, Locations chosenLocation, List<Droidec> team1, List<Droidec> team2) throws IOException {
        System.out.println("Бій розпочинається! Локація: " + chosenLocation.getNameLocation() + "\n");

        while (!team1.isEmpty() && !team2.isEmpty()) {
            executeRound(writer, chosenLocation, team1, team2, random, true);
            if (team2.isEmpty()) break;
            executeRound(writer, chosenLocation, team2, team1, random, false);
        }

        if (team1.isEmpty()) {
            writer.write("Команда 2 перемогла!\n");
            System.out.println("Команда 2 перемогла!\n");
        } else {
            writer.write("Команда 1 перемогла!\n");
            System.out.println("Команда 1 перемогла!\n");
        }
    }

    private static void executeRound(FileWriter writer, Locations location, List<Droidec> attackingTeam, List<Droidec> defendingTeam, Random random, boolean isFirstTeam) throws IOException {
        for (Droidec attacker : attackingTeam) {
            if (defendingTeam.isEmpty()) break;

            Droidec defender = defendingTeam.get(random.nextInt(defendingTeam.size()));
            int damage = attacker.calculateDamage();

            if (attacker.getStatus().equals("Оборона") && location.getNameBoost().equals("Оборона")) {
                damage += location.getCfBoosts();
            } else if (attacker.getStatus().equals("Наступ") && location.getNameDebaf().equals("Наступ")) {
                damage += location.getCfDebaf();
            }

            defender.takeDamage(damage);
            writer.write(attacker.getName() + " атакує " + defender.getName() + " і завдає урон " + damage + "\n");
            System.out.println(attacker.getName() + " атакує " + defender.getName() + " і завдає урон \u001B[34m" + damage + "\u001B[0m\n");

            if (defender.getHealth() <= 0) {
                writer.write(defender.getName() + " загинув! Перемога " + attacker.getName() + "\n");
                System.out.println("\u001B[35m" + defender.getName() + "\u001B[0m загинув! Перемога \u001B[33m" + attacker.getName() + "\u001B[0m\n");
                defendingTeam.remove(defender);
            }
        }
    }
}
