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
public class Inf_IrA {
    public int Si;
    public int IrA_Sj;
    public String IrA_Simbolo;
    public String ConjuntoItems;

    public Inf_IrA(int Si, int IrA_Sj, String IrA_Simbolo, String ConjuntoItems) {
        this.Si = Si;
        this.IrA_Sj = IrA_Sj;
        this.IrA_Simbolo = IrA_Simbolo;
        this.ConjuntoItems = ConjuntoItems;
    }
    
}
