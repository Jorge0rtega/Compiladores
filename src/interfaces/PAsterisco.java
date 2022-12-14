package interfaces;
import compiladores.AFN;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PAsterisco extends JDialog implements ActionListener{
    JLabel inst, ind;
    JButton aceptar, cancelar;
    JPanel arriba, centro, abajo;
    JComboBox sel1;
    AFN a = new AFN();
    AFN fs[];
    int index;
    Boolean correcto;
    
    public PAsterisco(java.awt.Frame parent, boolean model, AFN f[], int n){
        super(parent,model);
        fs = f;
        index=n;
        ind = new JLabel("AFN ID. " + index + ":");
        inst = new JLabel("\n Elige el AFN al que se le aplicara la cerradura asterisco: ");
        inst.setFont(new Font("Serif", Font.BOLD, 25));
        ind.setFont(new Font("Serif", Font.BOLD, 25));

        sel1 = new JComboBox();
        for(int i=0;i<index;i++){
            sel1.addItem("" + i);
        }

        aceptar = new JButton("Aceptar");
        cancelar = new JButton("Cancelar");
        aceptar.addActionListener(this);
        cancelar.addActionListener(this);

        arriba=new JPanel();
        abajo=new JPanel();
        centro=new JPanel();
        arriba.add(ind);
        centro.add(inst);centro.add(sel1);
        abajo.add(aceptar);abajo.add(cancelar);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North",arriba);
        getContentPane().add("Center",centro);
        getContentPane().add("South",abajo);
        setSize(700,500);
	    //setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==aceptar){
            int n1 = Integer.parseInt((String)sel1.getSelectedItem());
            a = fs[n1];
            a = a.cerraduraAsterisco();
            index = index + 1;
            JOptionPane.showMessageDialog(this, "Se aplico Cerradura * al AFN "  + n1);

            setVisible(false);
        }
        if(e.getSource()==cancelar){
            setVisible(false);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }
    }

    public AFN getafn(){
        return this.a;
    }

    public int getcont(){
        return this.index;
    }

}

