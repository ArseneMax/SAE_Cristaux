package modele;

import java.util.ArrayList;

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
        }
    }
}
