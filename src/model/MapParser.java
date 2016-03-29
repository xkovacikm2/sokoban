package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class MapParser {

    private MapParser() {
    }

    public static MapParser getInstance() {
        return MapParserHolder.INSTANCE;
    }

    private static class MapParserHolder {

        private static final MapParser INSTANCE = new MapParser();
    }

    public Map parseFile(File f) {
        Map map = null;
        try {
            map = new Map(Files.readAllLines(f.toPath()));
        } catch (IOException ex) {
            Logger.getLogger(MapParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }
}
