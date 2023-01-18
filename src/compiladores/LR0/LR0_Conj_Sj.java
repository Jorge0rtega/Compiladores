/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.LR0;

import java.util.HashSet;

/**
 *
 * @author Jorge Ortega
 */
public class LR0_Conj_Sj {
    public int j;
    public HashSet<ItemLR0>Sj;
    
    public LR0_Conj_Sj(){
        j=-1;
        Sj=new HashSet<ItemLR0>();
        Sj.clear();
    }
}
