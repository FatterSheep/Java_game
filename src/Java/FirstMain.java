package Java;
import src.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 class FirstMain {
	public static void main(String[] args) {
	//public void init() {
		JFrame f =new JFrame("Hello");
		f.setSize(800,800);
		f.setLocationRelativeTo(null);

		
		f.setLayout(new BorderLayout());
		
		final Canvas c = new Canvas(13);
		f.add(c,BorderLayout.CENTER);
		
		JPanel ptop = new JPanel();
		ptop.setLayout(new FlowLayout());
		f.add(ptop,BorderLayout.NORTH);
		JButton b=new JButton("Play");

		ptop.add(b);


		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				c.restart();
			}
			
		});

		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(true);

	}
}
