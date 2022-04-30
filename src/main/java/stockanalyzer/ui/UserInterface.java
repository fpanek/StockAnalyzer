package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import stockanalyzer.ctrl.Controller;


public class UserInterface 
{

	private Controller ctrl = new Controller();


	//why 4 different methods?
	public void getDataFromCtrl1(){
		try {
			ctrl.process("FQT.F");
		}catch (YahooAPIException e){
			System.out.println(e.getMessage());
		}
	}

	public void getDataFromCtrl2(){
		try {
			ctrl.process("EVN.VI");
		}catch (YahooAPIException e){
			System.out.println(e.getMessage());
		}
	}

	public void getDataFromCtrl3(){
		try {
			ctrl.process("AMS.SW");
		}catch (YahooAPIException e){
			System.out.println(e.getMessage());
		}
	}
	public void getDataFromCtrl4(){
		try {
			ctrl.process("AMS.SW,EVN.VI,FQT.F,KTCG.VI");
		}catch (YahooAPIException e){
			System.out.println(e.getMessage());
		}
	}

	
	public void getDataForCustomInput() {
		System.out.println("Please type in one or more shares for data retrieval: ");
		Scanner scanner = new Scanner(System.in);
		String ticker = scanner.next();
		try {
			ctrl.process(ticker);
		}catch (YahooAPIException e){
			System.out.println(e.getMessage());
		}
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice 1 --> FRQ", this::getDataFromCtrl1);
		menu.insert("b", "Choice 2 --> EVN ", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3 --> AMS", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("z", "Choice User Imput: more than one company - minmax/average/amountofData",this::getDataFromCtrl4);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		ctrl.closeConnection();
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
