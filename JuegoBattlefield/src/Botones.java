
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Botones extends JButton{
     private int x;
     private int y;
     /* 0: sector libre
        1: hay un buque nuestro ahí
        2: hay un buque suyo ahí
        3: sector vacio atacado
        4: hundimos un hostil
        5: hundimos nuestro propio buque
        6: hundieron uno de nuestros buques
        7: hundieron su propio buque
     */
     Botones(int x,int y){
            super();
            this.x=x;
            this.y=y;
            this.setSize(70,50);
            this.setIcon(new ImageIcon("Imagenes/Mar.jpg"));
            this.setBorder(null);
            this.setOpaque(true);
            this.setVisible(true);
            this.setEnabled(true);
            definirEventos();
        
    }
    private void definirEventos(){
    this.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent me) {
        if(VentanaDeInicio.inicia==false && VentanaDeInicio.Buques[x][y]==0 && VentanaDeInicio.jugar==false)
        {
        
            setIcon(new ImageIcon("Imagenes/MiBuque.png"));
            VentanaDeInicio.MisBuques++;
            VentanaDeInicio.Buques[x][y]=1;
            if(VentanaDeInicio.MisBuques>=5)
            {
            
            VentanaDeInicio.Iniciarbtn.setEnabled(true);
            int hosx, hosy;
            for(int host=0;host<5;host++){
            do{
            hosx=(int)(Math.random()*10);
            
            hosy=(int)(Math.random()*10);
            }while(VentanaDeInicio.Buques[hosx][hosy]!=0);
            VentanaDeInicio.Buques[hosx][hosy]=2;
            VentanaDeInicio.SusBuques++;
            }
            VentanaDeInicio.inicia=true;
            }
        }
        }
        @Override
        public void mousePressed(MouseEvent me) {
       
        }

        @Override
        public void mouseReleased(MouseEvent me) {
       
        }

        @Override
        public void mouseEntered(MouseEvent me) {
      
        }

        @Override
        public void mouseExited(MouseEvent me) {
        
        }
    
    });
    this.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
        if(VentanaDeInicio.jugar==true && VentanaDeInicio.Buques[x][y]<3)
        {
        VentanaDeInicio.detalles.setText("Detalles: "+"\n");
        if(VentanaDeInicio.Buques[x][y]==0)
        {
         VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Le dimos al agua"+"\n");
        changeImage("Imagenes/ondaExpansiva.jpg");
        VentanaDeInicio.Buques[x][y]=3;
        }
        if(VentanaDeInicio.Buques[x][y]==2)
        {
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Hemos hundido un buque hóstil"+"\n");
        VentanaDeInicio.SusBuques--;
        changeImage("Imagenes/suHundido.png");
        VentanaDeInicio.Buques[x][y]=4;
        }
        if(VentanaDeInicio.Buques[x][y]==1)
        {
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Hemos hundido uno de nuestros buques"+"\n");
        VentanaDeInicio.MisBuques--;
        changeImage("Imagenes/MiHundido.png");
        VentanaDeInicio.Buques[x][y]=5;
        }
        if(VentanaDeInicio.SusBuques==0 && VentanaDeInicio.jugar==true)
        {
        VentanaDeInicio.jugar=false;
        VentanaDeInicio.showFinal();
         VentanaDeInicio.detalles.setText("\n"+"\n"+"\n"+" Hemos Vencido");
        }
        if(VentanaDeInicio.MisBuques==0 && VentanaDeInicio.jugar==true)
        {
        VentanaDeInicio.jugar=false;
        VentanaDeInicio.showFinal();
        VentanaDeInicio.detalles.setText("\n"+"\n"+"\n"+" Hemos Perdido");
        }
        if( VentanaDeInicio.jugar==true)
        {
        int rnum2,rnum1;
        do{
        rnum1=(int) (Math.random() * 10);
        
        rnum2=(int) (Math.random() * 10);
       
        }while(VentanaDeInicio.Buques[rnum1][rnum2]>2);
      
        if(VentanaDeInicio.Buques[rnum1][rnum2]==0)
        {
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Le dieron al agua"+"\n");
        VentanaDeInicio.sectores[rnum1][rnum2].changeImage("Imagenes/ondaExpansiva.jpg");
        VentanaDeInicio.Buques[rnum1][rnum2]=3;
        }
        if(VentanaDeInicio.Buques[rnum1][rnum2]==2)
        {
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Hundieron uno de sus buques"+"\n");
        VentanaDeInicio.SusBuques--;
        VentanaDeInicio.sectores[rnum1][rnum2].changeImage("Imagenes/suHundido.png");
        VentanaDeInicio.Buques[rnum1][rnum2]=7;
        }
        if(VentanaDeInicio.Buques[rnum1][rnum2]==1)
        {
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Hundieron uno de nuestros buques"+"\n");
        VentanaDeInicio.MisBuques--;
        VentanaDeInicio.sectores[rnum1][rnum2].changeImage("Imagenes/MiHundido.png");
        VentanaDeInicio.Buques[rnum1][rnum2]=6;
        }
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+"\n"+" Nos quedan "+VentanaDeInicio.MisBuques+" buques"+"\n");
        VentanaDeInicio.detalles.setText(VentanaDeInicio.detalles.getText()+" Quedan  "+VentanaDeInicio.SusBuques+" buques hóstiles"+"\n");
        if(VentanaDeInicio.SusBuques==0 && VentanaDeInicio.jugar==true)
        {
        VentanaDeInicio.jugar=false;
        VentanaDeInicio.showFinal();
         VentanaDeInicio.detalles.setText("\n"+"\n"+"\n"+" Hemos Vencido");
        }
        if(VentanaDeInicio.MisBuques==0 && VentanaDeInicio.jugar==true)
        {
        VentanaDeInicio.jugar=false;
        VentanaDeInicio.showFinal();
        VentanaDeInicio.detalles.setText("\n"+"\n"+"\n"+" Hemos Perdido");
        }
        }
        }
        }
        
    });
    }
    
    private void changeImage(String nombre){
    ImageIcon pic=new ImageIcon(nombre);
    setIcon(pic);
    } 
    } 

