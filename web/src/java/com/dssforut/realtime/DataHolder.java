/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.realtime;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class DataHolder {

    
    private static final List<String> memory = Collections.synchronizedList(new LinkedList<String>());

    public void saveData(String data) {

        if (memory.size() >= 20) {
            memory.remove(0);
        }
        memory.add(data);
        
        System.out.println("Data on server stored: " + data);
        System.out.println("Memory size: " + memory.size());
    }

    public String[] getMemoryData() {
        String[] dataSet = memory.toArray(new String[0]);
        //Collections.reverse(Arrays.asList(dataSet));
        return dataSet;
    }
}
