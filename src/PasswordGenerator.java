
public class PasswordGenerator {
	private int passwordLength;
	private boolean flagUpper;
	private boolean flagLower;
	private boolean flagDigit;
	private boolean flagSpecialChar;


	public PasswordGenerator() {
       this.passwordLength = 1;
	}
    //functia de verificare a caracterelor duplicate
	protected boolean isDuplicate(int[] randomArr, int i){

		for(int j = 0; j < i; j++){
			if(randomArr[i] == randomArr[j]){
				return true;
			}
		}
		return false;
	}

	//functia de generare a parolei
	protected String makePassword() {
    /*Parola se creaza prin generarea unui sir de numere aleatorii tinand cont de optiunile selectate de utilizator
    si transformarea acestor numere in caractere pe baza tabelului ASCII.*/
        
         
		StringBuilder password = new StringBuilder();//variabila pentru stocarea parolei
		int[] randomArr = new int[passwordLength];//sirul care stocheaza numerele generate aleatoriu
		char c;
		int i = 0;

		while(i < passwordLength) {

			if(flagUpper && i < passwordLength){
				randomArr[i] = (int)Math.floor(Math.random() * ((90-65) + 1) + 65);

				if(!isDuplicate(randomArr,i)){
					i++;
				}
			}

			if(flagLower && i < passwordLength){
				randomArr[i] = (int)Math.floor(Math.random() * ((122-97) + 1) + 97);

				if(!isDuplicate(randomArr,i)){
					i++;
				}
			}

			if(flagDigit && i < passwordLength){
				randomArr[i] = (int)Math.floor(Math.random() * ((57-48) + 1) + 48);

				if(!isDuplicate(randomArr,i)){
					i++;
				}
			}

			if(flagSpecialChar && i < passwordLength) {
				randomArr[i] = (int)Math.floor(Math.random() * ((47-33) + 1) + 33);

				if(!isDuplicate(randomArr,i)){
					i++;
				}

			}
		}
        
		//Transformarea numerelor din sir in caractere si adaugarea lor intr-un StringBuilder
		for(i = 0; i < passwordLength; i++) {
			c = (char) randomArr[i];
			password.append(c);
		}

		return password.toString();

	} 
	
    //Setteri pt modificarea valorilor variabilelor de instanta
	public void setFlagUpper(boolean value) {
		this.flagUpper = value;
	}

	public void setFlagLower(boolean value) {
		this.flagLower = value;
	}

	public void setFlagDigit(boolean value) {
		this.flagDigit = value;
	}

	public void setFlagSpecialChar(boolean value) {
		this.flagSpecialChar = value;
	}

	public void setPasswordLength(int length) {
		this.passwordLength = length;

	}

	
	public int getPasswordLength() {
		return this.passwordLength;
	}	

}
