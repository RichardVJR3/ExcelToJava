public class Monsters {
    //public static final int length = 0;
    String name;
    String monsterPart;
    String level;
    String urlMonster;

    Monsters(String name, String monsterPart, String level, String url) {
        this.name = name;
        this.monsterPart = monsterPart;
        this.level = level;
        this.urlMonster = url;
    }

    void print() {
        System.out.println(name);
        System.out.println(monsterPart);
        System.out.println(level);
        System.out.println(urlMonster);
    }
}
