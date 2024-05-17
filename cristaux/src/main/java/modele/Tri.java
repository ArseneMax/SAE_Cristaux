package modele;

import java.util.ArrayList;
import java.util.Arrays;

public class Tri {
    public static int[] triSelection(int[] liste){
        for (int i = 0; i< liste.length;i++){
            int[] min = {liste[i],i};
            for (int j = i;j< liste.length;j++){
                if ( liste[j]<min[0]){
                    min[0]=liste[j];
                    min[1]=j;
                }
            }
            liste[min[1]]=liste[i];
            liste[i]=min[0];
        }
        return liste;
    }
    public static int[] triBulle(int[] liste) {
        return liste;
    }


}
