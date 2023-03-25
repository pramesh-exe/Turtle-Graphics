import java.awt.FlowLayout;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import uk.ac.leedsbeckett.oop.LBUGraphics;

import java.awt.*;

import java.io.*;

import javax.imageio.*;

public class GraphicsSystem extends LBUGraphics
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Graphics g = getGraphicsContext();
	public int fill = 1;
	int sv = 1;
	public static void main(String[] args)
    {
            new GraphicsSystem(); //create instance of class that extends LBUGraphics (could be separate class without main), gets out of static context
            try{
                FileWriter fw = new FileWriter("temp.txt", false);		// clears the temporary text file when running the program
                PrintWriter pw = new PrintWriter(fw, false);
                pw.flush();
                pw.close();
                fw.close();
                }catch(Exception exception){
                    System.out.println("Exception have been caught");
                }
    }

    public GraphicsSystem()
    {
        JFrame MainFrame = new JFrame();             //create a frame to display the turtle panel on
        MainFrame.setLayout(new FlowLayout());       //not strictly necessary
        MainFrame.add(this);                         //"this" is this object that extends turtle graphics so we are adding a turtle graphics panel to the frame
        MainFrame.pack();                            //set the frame to a size we can see
        MainFrame.setVisible(true);                  //now display it
        turnLeft(90);
            
    }

    
    public void processCommand(String command)      //this method must be provided because LBUGraphics will call it when it's JTextField is used
    {
    	displayMessage("");							//clears the message displayed after each input
    	penDown();
    	String a = returnInt(command);
    	String b = returnString(command);
    	boolean c = missing(command);
    	g.setColor(PenColour);
//    				System.out.println(a);
    				System.out.println(fill);
    	try {
    		if (command.equalsIgnoreCase("about")){
    			about();
    			temp(command);
    			sv = 0;
    		}
    		else if (command.equalsIgnoreCase("clear")){
    			clear();
    			temp(command);
    			sv = 0;
        	}
        	else if (command.equalsIgnoreCase("reset")){
     		   reset();
     		   turnLeft(90);
     		   temp(command);
     		   sv = 0;
        	} 
        	else if(command.equalsIgnoreCase("save image")){
//        		try {
        		ImageIO.write(getBufferedImage(), "PNG", new File("MyFile.png"));
        		temp(command);
        		sv = 1;
//        		}catch(Exception e){
//        			e.printStackTrace();
//        		}
        	}
        	else if(command.equalsIgnoreCase("load image")){
        		try {      			
        			if (sv==1) {
        				BufferedImage i = ImageIO.read(new File("MyFile.png"));
        				setBufferedImage(i);
        				temp(command);
        			}
        			else {
        				displayMessage("ERROR: You havent saved the current image. Type the command again to continue");
        				sv = 1;
        			}
        		}catch(Exception e){
        			displayMessage("ERROR: No saved file to load");
        		}
        	}
        	else if (b.equalsIgnoreCase("circle")){
        		if(c==false) {
        			displayMessage("ERROR: Enter a parameter");
        		}
        		else
        		{        			
        			if (!extra(command)) {
        				displayMessage("ERROR: Enter a single parameter");
        			}
        			else {
        				int num = Integer.parseInt(a);
        				if (num>360 || num<0) {
        					displayMessage("ERROR: Enter a valid number between 1 and 360");
        				}
        				else {
        					if(fill==1) {
        						circle(num);
        						temp(command);
        						sv = 0;
        					}
        					else {
        						circle2(num);
        						temp(command);
        						sv = 0;
        					}
        				}
        			}
        		}			
        	}
        	else if(b.equalsIgnoreCase("forward")){
        		if(c==false) {
        			displayMessage("ERROR: Enter a parameter");
        		}
        		else
        		{
        			if (!extra(command)) {
        				displayMessage("ERROR: Enter a single parameter");
        			}
        			else {
        				int num = Integer.parseInt(a);
        				if (num>360 || num<0) {
        					displayMessage("ERROR: Enter a valid number between 1 and 360");
        				} 
        				else {
        					forward(num);
        					temp(command);
        					sv = 0;
        				}
        			}
        		}
        	}
        	else if(b.equalsIgnoreCase("backward")){
        		if(c==false) {
        			displayMessage("ERROR: Enter a parameter");
        		}
        		else
        		{
        			if (!extra(command)) {
        				displayMessage("ERROR: Enter a single parameter");
        			}
        			else {
        				int num = Integer.parseInt(a);
        				if (num>360 || num<0) {
        					displayMessage("ERROR: Enter a valid number between 1 and 360");
        				} 
        				else {
        					forward(-num);
        					temp(command);
        					sv = 0;
        				}
        			}
        		}
        	}
        	else if(b.equalsIgnoreCase("turnleft")){
        		if(c==false) {
        			turnLeft();
        			temp(command);
        			sv = 0;
        		}
        		else
        		{
        			if (!extra(command)) {
        				displayMessage("ERROR: Enter a single parameter");
        			}
        			else {
        				int num = Integer.parseInt(a);
        				if (num>360 || num<0) {
        					displayMessage("ERROR: Enter a valid number between 1 and 360");
        				} 
        				else {
        					turnLeft(num);
        					temp(command);
        					sv = 0;
        				}
        			}
        		}
        	}
        	else if(b.equalsIgnoreCase("turnright")){
        		if(c==false) {
        			turnRight();
        			temp(command);
        			sv = 0;
        		}
        		else
        		{
        			if (!extra(command)) {
        				displayMessage("ERROR: Enter a single parameter");
        			}
        			else {
        				int num = Integer.parseInt(a);
        				if (num>360 || num<0) {
        					displayMessage("ERROR: Enter a valid number between 1 and 360");
        				} 
        				else {
        					turnRight(num);
        					temp(command);
        					sv = 0;
        				}
        			}
        		}
        	}
        	else if (command.equalsIgnoreCase("penup")){
        		penUp();
        		temp(command);
        		sv = 0;
        	}
        	else if (command.equalsIgnoreCase("pendown")){
        		penDown();
        		temp(command);
        		sv = 0;
        	}
        	else if (b.equalsIgnoreCase("pen")){
        		if(checkPen(command)==false) {   
        			displayMessage("ERROR: Enter 3 parameters");
        		}
        		else
        		{
        			if (!checkPenExtra(command)) {
        				displayMessage("ERROR: Enter 3 parameters only");
        			}
        			else {       			
        				int red = Integer.parseInt(a);
        				int green = Integer.parseInt(returnGreen(command));
        				int blue = Integer.parseInt(returnBlue(command));
        				if (red>255 || red<0 || green>255 || green<0 || blue>255 || blue<0) {
        					displayMessage("ERROR: Enter a valid number between 0 and 255");
        				} 
        				else {
          				pen(red, green, blue);
          				temp(command);
          				sv = 0;
        				}
        			}
        		}
        		
        	}
        	else if (b.equalsIgnoreCase("triangle")){
        		int num = Integer.parseInt(a);
        		triangle(num);
        		temp(command);
        		sv = 0;
        	}
        	else if (b.equalsIgnoreCase("rectangle")){
        		if(!checkRect(command)) {   
        			displayMessage("ERROR: Enter 2 parameters");
        		}
        		else
        		{
        			if (!checkRectExtra(command)) {
        				displayMessage("ERROR: Enter 2 parameters only");
        			}
        			else {       			
        				int l = Integer.parseInt(a);
        				int w = Integer.parseInt(returnGreen(command));
        				if (l>360 || l<0 || w>360 || w<0) {
        					displayMessage("ERROR: Enter a valid number between 1 and 360");
        				}
        				else {
        					if(fill==1) {
        						rect(l,w);
        						temp(command);
        						sv = 0;
        					}
        					else {
        						rect2(l,w);
        						temp(command);
        						sv = 0;
        					}
        				}
        			}
        		}
        		
        	}
        	else if (command.equalsIgnoreCase("red")){
        		setPenColour(Color.red);
        		temp(command);
        		sv = 0;
        	}
        	else if (command.equalsIgnoreCase("white")){
        		setPenColour(Color.white);
        		temp(command);
        		sv = 0;
        	}
        	else if (command.equalsIgnoreCase("black")){
        		setPenColour(Color.black);
        		temp(command);
        		sv = 0;
        	}
        	else if (command.equalsIgnoreCase("green")){
        		setPenColour(Color.green);
        		temp(command);
        		sv = 0;
        	}else if (command.equalsIgnoreCase("load")){
        		load();  
        	}else if (command.equalsIgnoreCase("save")){
        		save();        	
    		}else if (command.equalsIgnoreCase("fill")){
    			if(fill==1){
    				togglefill();
    				temp(command);
    				displayMessage("Fill: ON");
    				sv = 0;
    			}
    			else {
    				togglefill();
    				temp(command);
    				displayMessage("Fill: Off");
    				sv = 0;
    			}
    		}
        	else {
        		displayMessage("ERROR:Invalid command");
        	}
    	}catch(Exception e) 
    	{
    		displayMessage("ERROR: Please enter a numeric data!");
    	}
    }


    public boolean checkTriangle(int a, int b, int c)	//Method to validate sides of triangle
    {
        if (a + b <= c || a + c <= b || b + c <= a)
            return false;
        else
            return true;
    }
      
    public String returnInt(String value){			// Method to return 2th word from the input String
    	String[] str= value.split("\\s+");
    	if (str.length>1) {
    		return str[1];
    	}
    	else {
    		return "";
    	}
    }
    
    public String returnGreen(String value){		// Method to return 3th word from the input String
    	String[] str= value.split("\\s+");
    	if (str.length>2) {
    		return str[2];
    	}
    	else {
    		return "";
    	}
    }
    
    public String returnBlue(String value){			// Method to return 4th word from the input String
    	String[] str= value.split("\\s+");
    	if (str.length>3) {
    		return str[3];
    	}
    	else {
    		return "";
    	}
    }
    
    public boolean missing(String value) {			// Method to check for enough parameter
    	String[] str= value.split("\\s+");
    	if (str.length>1)
    		return true;
    	else{
    		return false;
    	}
    }
    
    public boolean extra(String value) {			// Method to check for extra parameter
    	String[] str= value.split("\\s+");
    	if (str.length<3)
    		return true;
    	else{
    		return false;
    	}
    }
    
    public boolean checkRect(String value) {			// Method to check for enough parameter 
    	String[] str= value.split("\\s+");
    	if (str.length>2)
    		return true;
    	else{
    		return false;
    	}
    }
    
    public boolean checkRectExtra(String value) {	// Method to check for extra parameter
    	String[] str= value.split("\\s+");
    	if (str.length<4)
    		return true;
    	else{
    		return false;
    	}
    }
    
    public boolean checkPenExtra(String value) {	// Method to check for extra parameter
    	String[] str= value.split("\\s+");
    	if (str.length<5)
    		return true;
    	else{
    		return false;
    	}
    }
    public boolean checkPen(String value) {			// Method to check for enough parameter 
    	String[] str= value.split("\\s+");
    	if (str.length>3)
    		return true;
    	else{
    		return false;
    	}
    }
    
    public String returnString(String value) {		// Method to return the first word of the input String
    	String[] str= value.split("\\s+");
    	return str[0];
    }
    
    @Override
    public void about() 					// Overridden method for about					
    {
    	super.about();
    	g.drawString("Pramesh Shrestha", 650, 250);
    }

    public int togglefill() {			// Method to toggle fill mode
    	if (fill == 1) {
    		fill = 0;
    		return fill;
    	}
    	else {
    		fill = 1;
    		return fill;
    	}
    }
    
    public void rect(int l , int b) {
    	g.drawRect(getxPos(), getyPos(), l, b);
    }
    
    public void rect2(int l , int b) {
    	g.fillRect(getxPos(), getyPos(), l, b);
    }
    @Override
    public void circle(int radius) {		// Overridden method for circle
    	g.drawOval(getxPos(), getyPos(), radius, radius);  	
    }
    
    public void circle2(int radius) {		// Method for fill circle
    	g.fillOval(getxPos(), getyPos(), radius, radius);  	
    }
    
    public void triangle(int x) {		// Method for equalateral triangle
    	forward(x);
    	turnRight(120);
    	forward(x);
    	turnRight(120);
    	forward(x);
    	turnRight(120);
    }
    
//    public void triangle(int a, int b, int c) {
//    	   
//        int[] x= new int[3];
//        int[] y= new int[3];
//        
//        x[0] = getxPos();
//        y[0] = getyPos();        
//        
//        x[1] = b+getxPos();
//        y[1] = b+getyPos();
//        
//        
//        x[2] = c+getxPos();
//        y[2] = c+getyPos();
//        
//        
//        g.drawPolygon(x, y, 3);
//        
//    }
    public void pen(int r, int g, int b ) {			// Method to change the pen color as desired
    	setPenColour(new Color(r,g,b));
    }
    
    public void temp(String a) {				//Method for writing down the commands in a temporary text file 
    	try{
    		File file = new File("temp.txt");
    		if (!file.exists()) {
    		     file.createNewFile();
    		  }
    		FileWriter fw = new FileWriter(file, true);
    		BufferedWriter bw = new BufferedWriter(fw);
    		bw.write(a + "\n");
    		bw.close();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void save()throws Exception			// Method for saving the commands entered in the temporary file
        {
            FileInputStream in = new FileInputStream("temp.txt");
            FileOutputStream out = new FileOutputStream("history.txt");
      
            try {
      
                int n;
                while ((n = in.read()) != -1) {
                    out.write(n);
                }
            }
            finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
            System.out.println("File Copied");
        }
    
    public void load() {					//Method for loading the saved text file
    	try{
    		BufferedReader br = new BufferedReader(new FileReader("history.txt"));
    		String currentLine = br.readLine();
    			while(currentLine != null) {
    				if(currentLine.equalsIgnoreCase("save") || currentLine.equalsIgnoreCase("load")) {}
    				else {
    					processCommand(currentLine);
    				}
    				currentLine = br.readLine();
    			}
    			br.close();
	
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}
