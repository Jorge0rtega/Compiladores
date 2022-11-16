package compiladores;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class converAFNaAFD extends JDialog implements ActionListener{
    JLabel inst, ind;
    JButton aceptar, cancelar;
    JPanel arriba, centro, abajo;
    JComboBox sel1;
    JTextField name;
    JTable tablaafd;
    AFN a = new AFN();
    AFD d = new AFD();
    AFN fs[];
    int index, index2;
    Boolean correcto;
    JScrollPane panel;
    
    public converAFNaAFD(java.awt.Frame parent, boolean model, AFN f[], int n, int n2){
        super(parent,model);
        fs = f;
        index=n;
        index2 = n2;
        ind = new JLabel("Elige nombre del AFD con ID: " + index2);
        inst = new JLabel("\n AFN a convertir:");
        inst.setFont(new Font("Serif", Font.BOLD, 25));
        ind.setFont(new Font("Serif", Font.BOLD, 25));
        name = new JTextField(20);
        sel1 = new JComboBox();
        for(int i=0;i<index;i++){
            sel1.addItem("" + i);
        }
        panel = new JScrollPane();
        tablaafd = new JTable();
        tablaafd.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{
                {null,null,null,null},
                {null,null,null,null},
                {null,null,null,null}
            },
            new String[]{"","","",""}
        ));
        tablaafd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        panel.setViewportView(tablaafd);

        aceptar = new JButton("Convertir y guardar");
        cancelar = new JButton("Salir");
        aceptar.addActionListener(this);
        cancelar.addActionListener(this);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inst)
                        .addGap(18, 18, 18)
                        .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(aceptar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cancelar))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(ind)
                            .addGap(18, 18, 18)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(200, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ind)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inst)
                    .addComponent(sel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptar)
                    .addComponent(cancelar))
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    

        /*arriba=new JPanel();
        abajo=new JPanel();
        centro=new JPanel();
        arriba.add(ind);arriba.add(name);
        centro.add(inst);centro.add(sel1);abajo.add(panel);
        centro.add(aceptar);centro.add(cancelar);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North",arriba);
        getContentPane().add("Center",centro);
        getContentPane().add("South",abajo);
        setSize(800,600);*/
	    //setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e ){
        if(e.getSource()==aceptar){
            int n1 = Integer.parseInt((String)sel1.getSelectedItem());
            a = fs[n1];
            String nombre = name.getText();
            a.ConvAFNaAFD(nombre);
            d.leerAFDdeArchivo(nombre, index2);

            String cad[] = new String[d.TablaAFD.size()];
            for(int i = 0; i < d.TablaAFD.size(); i++){
                cad [i] = d.TablaAFD.get(i).toString();
            }
            String[] lim = cad[0].split(" ");
            Object elem [][] = new Object[d.TablaAFD.size()][lim.length];
            for(int i = 0; i < d.TablaAFD.size(); i++){
                String[] datos = cad[i].split(" ");
                int j=0;
                for(String Item : datos){

                    elem[i][j] = Item;
                    j++;
                }
            }

            CrearTabla(elem);
            
            index2 = index2 + 1;
            JOptionPane.showMessageDialog(this, "Se convirtio el AFN al AFD: " + name.getText());

        }
        if(e.getSource()==cancelar){
            setVisible(false);
        }
    }

    public void CrearTabla(Object elem[][]){
        tablaafd.setDefaultRenderer(Object.class, new Render());

        DefaultTableModel d = new DefaultTableModel(
            elem,
            elem[0]
        ){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tablaafd.setModel(d);

    }

    public AFD getafd(){
        return this.d;
    }

    public int getcont(){
        return this.index2;
    }

}

