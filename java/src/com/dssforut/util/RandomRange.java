/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dssforut.util;

/**
 * Class used for getting a number between a range
 * @author Sebastian
 */
public class RandomRange {

    /*
     * Return a integer between a range
     */
    public static int generateRandomRange(int min, int max) {
        int result = min + (int)(Math.random() * ((max - min) + 1));
        return result;
    }
}