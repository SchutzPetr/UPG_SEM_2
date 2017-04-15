package def;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Petr Schutz on 18.03.2017
 * <p>
 * Knihovní třída, která se stará o načítání dat ze souborů
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class ReadFile {
    /**
     * Knihovní třída
     */
    private ReadFile() {
    }

    /**
     * Metoda načte do kolekce data ze souboru
     *
     * @param fileName jméno vstupního souboru
     * @return kolekce vstupních dat
     */
    public static List<Integer> readFile(String fileName) {

        List<Integer> list = new ArrayList<>();

        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            try {

                while (true) list.add(in.readInt());

            } catch (EOFException ignored) {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
