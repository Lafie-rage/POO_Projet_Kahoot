package admin;

/**
 * Une Classe qui permet d'associer une valeur avec une cle.
 * Utilise pour les listes déroulantes
 */
public class ComboKeyValue {

    private int key;
    private String value;

    public ComboKeyValue(int key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}