package interfaces;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabla {
    
    public void VerTabla(JTable tabla, int n){
        tabla.setDefaultRenderer(Object.class, new Render());
        int num = n;
        JCheckBox btn[] = new JCheckBox[num];
        Object componentes[][] = new Object[num][3];
        for(int i=0;i<num;i++){
            btn[i]=new JCheckBox("");
            componentes[i][0]=i;
            componentes[i][1]=btn[i];
            componentes[i][2]=0;
        }

        DefaultTableModel d =new DefaultTableModel(
            componentes,
            new Object []{"AFNs", "Seleccionar", "Tokens"}
        ){
            public boolean isCellEditable(int row, int column){
                if(column == 2)
                    return true;
                return false;
            }
        };

        tabla.setModel(d);

    }

}
