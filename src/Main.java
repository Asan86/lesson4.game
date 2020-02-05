import java.util.Random;

public class Main {

    public static int[] heroesHealth /*Здоровье героев*/= {250, 260, 270, 1000};
    public static int[] heroesDamage /*Урон героев*/= {20/*Воин*/, 15/*Маг*/, 10/*Кинетик*/};
    public static String[] heroesAttackType/*Тин атаки*/ = {"Physical",
            "Magical", "Kinetic", "Medic"};
    public static int bossHealth/*Здоровие Босса*/ = 700;
    public static int bossDamage /*Урон Босса*/ = 50;
    public static int medic = 20;

    public static String bossDefenceType /*Тип защиты Босса*/ = "";

    public static void main(String[] args) {
        printStatistics();
        while (!isFinished()) {
            round();
        }
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished /*Закончено ли?*/() {
        if (bossHealth /*Здоровье Босса*/<= 0) {
            System.out.println("Heroes won!!!");
            return true /*Возвращение истины*/;
        }
        if (heroesHealth[0] /*Здоровье 1 героя*/<= 0 && heroesHealth[1] /*Здоровье 2 героя*/<= 0
                && heroesHealth[2] /*Здоровье 3 героя*/<= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void round() {
        changeBossDefence();
        heroesHit();                                                               /*Атакует герои*/
        bossHit();                                                                 /*Атакует Босс*/
        medicTread();
        printStatistics();                                                         /*Статистика боя*/
    }



    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(8) + 2; // 2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHit /*Босс атакует*/() {
        for (int i = 0; i < heroesHealth.length /*Длина здороивя героев*/; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void medicTread (){
        if ((heroesHealth[0] > 0 || heroesHealth[1] > 0 || heroesHealth[2] > 0 || heroesHealth[3] > 0) &&

                (heroesHealth[0] < 250 || heroesHealth[1] < 250 || heroesHealth[2] < 250)) ;

        Random r = new Random();

        int randomNumber = r.nextInt(3);

        switch (randomNumber) {

            case 0:
                heroesHealth[0] = heroesHealth[0] + medic;

                System.out.println("Medic helped " + heroesAttackType[0]);

                break;

            case 1:

                heroesHealth[1] = heroesHealth[1] + medic;

                System.out.println("Medic helped " + heroesAttackType[1]);

                break;

            case 2:

                heroesHealth[2] = heroesHealth[2] + medic;

                System.out.println("Medic helped " + heroesAttackType[2]);

                break;


        }
    };

    public static void printStatistics () {         /*Статистика текущей жизьни участников боя*/
        System.out.println("_______________");
        System.out.println("Boss health: " + bossHealth + " " + bossDefenceType);  /*Здоровье Босса*/
        System.out.println("Warrior health: " + heroesHealth[0]);                  /*Здоровь воина*/
        System.out.println("Magic health: " + heroesHealth[1]);                    /*Здоровье мага*/
        System.out.println("Kinetic health: " + heroesHealth[2]);                  /*Здоровье Кинетика*/
        System.out.println("Medic" + heroesHealth [3]);
        System.out.println("_______________");
    }
}
