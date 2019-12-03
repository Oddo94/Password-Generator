import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args) {
		
		UserInterface obj = new UserInterface();
		JFrame window = new JFrame();
		
		window.setSize(380, 250);//se seteaza marimea ferestrei
		window.setResizable(false);//se elimina posibilitatea de redimensionare a ferestrei
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//determina iesirea din aplicatie la selectarea butonului "x"
		window.setTitle("Password Generator v1.2");
		window.add(obj.getMainPanel());//se adauga in fereastra panoul creat de clasa UserInterface
		window.setVisible(true);//se face vizibila fereastra
			
	}

}
