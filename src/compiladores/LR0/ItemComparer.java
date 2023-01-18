/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiladores.LR0;

/**
 *
 * @author Jorge Ortega
 */
public class ItemComparer {
    public boolean Equals(ItemLR0 x, ItemLR0 y){
        return (x.NumRegla==y.NumRegla)&&(x.PosPunto==y.PosPunto);
    }
    public int getHashCode(ItemLR0 obj){
        String s;
        s="["+obj.NumRegla+","+obj.PosPunto+"]";
        return s.hashCode();
    }
}
