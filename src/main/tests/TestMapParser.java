/*
 * Copyright (C) 2015, eProvement s.r.o. All rights reserved.
 */

package main.tests;

import javax.swing.JFileChooser;
import model.Map;
import model.MapParser;

/**
 * @author Michal Kováčik
 * @since Mar 27, 2016
 */
public class TestMapParser {
    public void testMapParser(){
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(fileChooser);
        
        Map map = MapParser.getInstance().parseFile(fileChooser.getSelectedFile());
        for(char[] line:map.getMapArray()){
            System.out.println(String.copyValueOf(line));
        }
    }
}
