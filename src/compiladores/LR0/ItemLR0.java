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
public class ItemLR0 {
    public int NumRegla;
    public int PosPunto;
    public ItemLR0(){
        NumRegla=-1;
        PosPunto=-1;
    }
    public ItemLR0(int NumRegla, int PosPunto){
        this.NumRegla=NumRegla;
        this.PosPunto=PosPunto;
    }
}
