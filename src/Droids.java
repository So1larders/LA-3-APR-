import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Droidec {
    private String Name;
    private String NameClass;
    private int Health;
    private int Damage;
    private String Status;

    public Droidec(String Name, String NameClass, int Health, int Damage) {
        this.Name = Name;
        this.NameClass = NameClass;
        setHealth(Health);
        setDamage(Damage);
        this.Status = "Наступ";
    }

    public String getName() {
        return Name;
    }

    public String getNameClass() {
        return NameClass;
    }

    public int getHealth() {
        return Health;
    }

    public boolean setHealth(int health) {
        if (health < 0) {
            System.out.println("Здоров'я не може бути негативним!");
            return false;
        }
        this.Health = health;
        return true;
    }

    public int getDamage() {
        return Damage;
    }

    public boolean setDamage(int damage) {
        if (damage < 0) {
            System.out.println("Урон не може бути негативним!");
            return false;
        }
        this.Damage = damage;
        return true;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public int calculateDamage() {
        int maxDamage = Damage;
        if (Status.equals("Оборона")) {
            maxDamage /= 2;
        }
        return (int) (Math.random() * maxDamage) + 1;
    }

    public void takeDamage(int damage) {
        this.Health -= damage;
    }
}

public class Droids {

    public static List<Droidec> readDroidsFromFile(String fileName) {
        List<Droidec> droids = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] droidData = line.split(",");
                String name = droidData[0];
                String nameClass = droidData[1];
                int health = Integer.parseInt(droidData[2]);
                int damage = Integer.parseInt(droidData[3]);
                droids.add(new Droidec(name, nameClass, health, damage));
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
        }
        return droids;
    }
}