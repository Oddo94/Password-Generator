import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class UserInterface extends JFrame implements ActionListener {
    //"panel" reprezinta panoul principal in care se adauga toate containerele care contin componente
	private JPanel panel,checkBoxPanel,textFieldPanel,buttonPanel,spinnerAndLabelPanel,passwordOutAndLabelPanel;
	private JCheckBox checkBox1,checkBox2,checkBox3,checkBox4;
	private JTextField passwordDisplay;
	private JButton buttonGenerate,buttonCopy;
	private SpinnerNumberModel model;
	private JSpinner spinner;

	PasswordGenerator obj = new PasswordGenerator();
  
	

	public UserInterface() {
        //creare componente(checkbox,butoane,campuri, spinner)
		this.checkBox1 = new JCheckBox("Upper case letters (A-Z)");
		this.checkBox2 = new JCheckBox("Lower case letters (a-z)");
		this.checkBox3 = new JCheckBox("Digits (0-9)");
		this.checkBox4 = new JCheckBox("Special characters (@*&$#)");

		this.buttonGenerate = new JButton("Generate Password!");
		this.buttonCopy = new JButton("Copy");

		this.passwordDisplay = new JTextField();
		passwordDisplay.setEditable(false);
		passwordDisplay.setHorizontalAlignment(JTextField.CENTER);

		this.model = new SpinnerNumberModel(1,1,40,1);
		this.spinner = new JSpinner(model);

		createPanels();
		setPanelLayouts();
		addComponents();
		setCheckBoxBackground();

		addActionListenersToComponents();
		

	}



	public void addComponents( ) {
		//Container pt checkbox si label
		checkBoxPanel.add(createLabel("Select Character Set","left"));
		checkBoxPanel.add(new JLabel(""));// se adauga un label gol pt a mentine alinierea checkbox-urilor
		checkBoxPanel.add(checkBox1);
		checkBoxPanel.add(checkBox2);
		checkBoxPanel.add(checkBox3);
		checkBoxPanel.add(checkBox4);
		checkBoxPanel.setBackground(Color.GREEN);


		//Container pt spinner si label
		spinnerAndLabelPanel.add(createLabel("Select password length","center"), BorderLayout.NORTH);
		spinnerAndLabelPanel.add(spinner, BorderLayout.SOUTH);
		spinnerAndLabelPanel.setBackground(Color.GREEN);//se seteaza culoare verde a containerului care contine spinerul si labelul aferent

		//Container pt campul password output si label
		passwordOutAndLabelPanel.add(passwordDisplay,BorderLayout.SOUTH);
		passwordOutAndLabelPanel.add(createLabel("Random generated password","center"),BorderLayout.NORTH);
		passwordOutAndLabelPanel.setBackground(Color.GREEN);

		//Container pt butoane
		buttonPanel.add(buttonGenerate);
		buttonPanel.add(buttonCopy);
		buttonPanel.setBackground(Color.GREEN);


		//container pt spinner si label
		textFieldPanel.add(spinnerAndLabelPanel, BorderLayout.SOUTH);
		textFieldPanel.add(buttonPanel, BorderLayout.NORTH);
		textFieldPanel.setBackground(Color.GREEN);


		//Adaugare containere in panoul principal
		panel.add(checkBoxPanel, BorderLayout.SOUTH);
		panel.add(textFieldPanel, BorderLayout.CENTER);
		panel.add(passwordOutAndLabelPanel,BorderLayout.NORTH);
	}


	public void setPanelLayouts() {

		//setare layout pt fiecare container respectiv pt panoul principal
		panel.setLayout(new BorderLayout());
		checkBoxPanel.setLayout(new GridLayout(3,2,0,0));
		textFieldPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new GridLayout(1,2,3,0));
		passwordOutAndLabelPanel.setLayout(new BorderLayout());


	}

	//crearea containerelor si a panoului principal
	public void createPanels() {
		this.panel = new JPanel();
		this.checkBoxPanel = new JPanel();
		this.textFieldPanel = new JPanel();
		this.buttonPanel = new JPanel();//container pt butoane
		this.spinnerAndLabelPanel = new JPanel();//container suplimentar pt spinner si label
		this.passwordOutAndLabelPanel = new JPanel();//container  folosit pt campul de generare a parolei si eventual pt label*/

	}

	
	public void setCheckBoxBackground() {
		//setarea culori de fundal pt checkbox
		JCheckBox[] arr = new JCheckBox[] {checkBox1,checkBox2,checkBox3,checkBox4};

		for(JCheckBox item : arr) {
			item.setBackground(Color.GREEN);

		}
	}

	//functie pt creare label si pozitionare 
	public JLabel createLabel(String text, String position) {
		JLabel label = new JLabel(text);

		if(position.equals("center")) {
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}else if(position.equals("left")) {
			label.setHorizontalAlignment(SwingConstants.LEFT);
		}else if(position.equals("right")) {
			label.setHorizontalAlignment(SwingConstants.RIGHT);
		}

		return label;
	}

    //Adaugare ActionListener pt fiecare componenta in parte
	public void addActionListenersToComponents() {
        //BUTOANE
		buttonCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//daca nu s-a generat o parola anterior se afiseaza mesajul
				if(passwordDisplay.getText().equals("")) {
					passwordDisplay.setText("Please generate a password first!");
				}
				//cod pentru copierea in clipboard a continutului parolei generate
				StringSelection stringSelection = new StringSelection(passwordDisplay.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);

			}
		});

		//butonul de generare parolei este dezactivat in mod implicit in lipsa selectarii unei
		//optiuni legate de tipul de caracter generat
		buttonGenerate.setEnabled(false);

		buttonGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordDisplay.setText(obj.makePassword());
			
			}
		});

		//SPINNER
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				obj.setPasswordLength((int)spinner.getValue());

			}
		});

        //CHECKBOXES
		checkBox1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//in cazul in care optiunea nu e selectata variabila pe care aceasta o reprezinta va primi valoarea "false"
				//astfel incat setul respectiv de caractere nu va mai fi utilizat la generarea parolei
				if(!checkBox1.isSelected()) {
					obj.setFlagUpper(false);

					//se verifica de asemenea daca mai exista vreo optiune selectata, 
					//in caz contrar se dezactiveaza butonul de generare a parolei 
					if(!isAnyCheckBoxSelected()) {
						buttonGenerate.setEnabled(false);
					}
					return;
				}
				//daca optiunea a fost selectata se activeaza butonul de generare a parolei
				//iar variabila reprezentand optiunea primeste valoarea "true", astfel incat setul respectiv
				//de caractere va fi utilizat la generarea parolei
				buttonGenerate.setEnabled(true);
				obj.setFlagUpper(true);

			}
		});

		checkBox2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!checkBox2.isSelected()) {
					obj.setFlagLower(false);

					if(!isAnyCheckBoxSelected()) {
						buttonGenerate.setEnabled(false);
					}
					return;
				}
				buttonGenerate.setEnabled(true);
				obj.setFlagLower(true);
			}
		});

		checkBox3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!checkBox3.isSelected()) {
					obj.setFlagDigit(false);

					if(!isAnyCheckBoxSelected()) {
						buttonGenerate.setEnabled(false);
					}
					return;
				}
				buttonGenerate.setEnabled(true);
				obj.setFlagDigit(true);
			}
		});

		checkBox4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!checkBox4.isSelected()) {
					obj.setFlagSpecialChar(false);

					if(!isAnyCheckBoxSelected()) {
						buttonGenerate.setEnabled(false);
					}
					return;
				}
				buttonGenerate.setEnabled(true);
				obj.setFlagSpecialChar(true);
			}
		});
	}

	//functia care returneaza panoul pricipal care contine toate componentele aplicatiei
	public JPanel getMainPanel() {
		return this.panel;
	}

	//functia care verifica daca exista optiuni selectate
	public boolean isAnyCheckBoxSelected() {
		JCheckBox[] checkBoxArray = {checkBox1,checkBox2,checkBox3,checkBox4};

		for(JCheckBox checkBox : checkBoxArray ) {
			if(checkBox.isSelected()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {}


   
}
		
		
		
		
		
		
		
		
		
		
		
		
		




		
   
		
		
			
		
	
	
	
	
	
	

		
		
		
	
		
	
	


