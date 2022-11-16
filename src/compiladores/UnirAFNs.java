package compiladores;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;


public class UnirAFNs extends JDialog implements ActionListener{
    JLabel inst, ind;
    JButton aceptar, cancelar;
    JPanel arriba, centro, abajo;
    JComboBox sel1, sel2;

    JTable tabla;
    int cont, safn[], token[];
    Tabla t;

    AFN a = new AFN();
    AFN fs[];
    int index;
    Boolean correcto;
    
    public UnirAFNs(java.awt.Frame parent, boolean model, AFN f[], int n){
        super(parent,model);
        fs = f;
        index=n;
        ind = new JLabel("AFN ID." + index + ":");
        inst = new JLabel("\n seleccione los AFNs a unir y asigne los Tokens:");
        inst.setFont(new Font("Serif", Font.BOLD, 25));
        ind.setFont(new Font("Serif", Font.BOLD, 25));

        cont=0;
        safn = new int[index];
        token = new int[index];
        tabla = new JTable();
        t=new Tabla();

        tabla.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                tablaMouseClicked(evt);
            }
        });

        aceptar = new JButton("Unir AFNs");
        cancelar = new JButton("Cancelar");
        aceptar.addActionListener(this);
        cancelar.addActionListener(this);

        arriba=new JPanel();
        abajo=new JPanel();
        centro=new JPanel();
        arriba.add(ind);
        centro.add(inst);centro.add(tabla);
        abajo.add(aceptar);abajo.add(cancelar);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North",arriba);
        getContentPane().add("Center",centro);
        getContentPane().add("South",abajo);
        setSize(700,400);
        t.VerTabla(tabla, index);
	    //setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==aceptar){
            String s="";

            for(int i = 0; i < index; i++){
                Object value = tabla.getValueAt(i, 1);
                if(value instanceof JCheckBox){
                    if(((JCheckBox)value).isSelected()){
                        Object val1 = tabla.getValueAt(i, 0);
                        Object val2 = tabla.getValueAt(i, 2);
                        safn[cont] = (int)val1;
                        s=s + safn[cont] + ", ";
                        if(val2 instanceof String)
                            token[cont] = Integer.parseInt((String) val2);
                        else
                            token[cont] = (int) val2;
                        cont = cont + 1;
                    }
                }
            }

            for(int i = 0; i < cont; i++){
                a.UnionEspecialANFs(fs[safn[i]], token[i]);
            }

            index = index + 1;
            JOptionPane.showMessageDialog(this, "Se unieron los AFN's " );

            setVisible(false);
        }
        if(e.getSource()==cancelar){
            setVisible(false);
        }
    }

    private void tablaMouseClicked(java.awt.event.MouseEvent evt){
        int column = tabla.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY()/tabla.getRowHeight();

        if(row < tabla.getRowCount() && row >= 0 && column < tabla.getColumnCount() &&  column>= 0){
            Object value = tabla.getValueAt(row, column);
            if(value instanceof JCheckBox){
                ((JCheckBox) value).doClick();
                JCheckBox cb = (JCheckBox)value;
                tabla.setValueAt(cb, row, column);
            }
        }
    }

    public AFN getafn(){
        return this.a;
    }

    public int getcont(){
        return this.index;
    }

}
