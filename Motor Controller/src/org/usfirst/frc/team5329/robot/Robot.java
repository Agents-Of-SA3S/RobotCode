package org.usfirst.frc.team5329.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * This sample program shows how to control a motor using a joystick. In the operator
 * control part of the program, the joystick is read and the value is written to the motor.
 *
 * Joystick analog values range from -1 to 1 and speed controller inputs also range from -1
 * to 1 making it easy to work together. The program also delays a short time in the loop
 * to allow other threads to run. This is generally a good idea, especially since the joystick
 * values are only transmitted from the Driver Station once every 20ms.
 */
public class Robot extends SampleRobot {
	
    private SpeedController leftSide;	// the motor to directly control with a joystick
    private SpeedController rightSide;
    private SpeedController rightLauncher;
    private SpeedController leftLauncher;
    private SpeedController pickUp;
    private Timer myTimer= new Timer();
    
    //private RobotDrive driveTrain;

    private Joystick stick;
    
    

	private final double k_updatePeriod = 0.005; // update every 0.005 seconds/5 milliseconds (200Hz)

	//**************************************
	//Setting new instances of Robot Objects
	//**************************************
    public Robot() {
    	
    	//driveTrain = new RobotDrive(leftSide, rightSide); //speed controllers drive
    	
        leftSide = new Victor(0);	
        rightSide = new Victor(1);
        
        rightLauncher = new VictorSP(4);
        leftLauncher = new VictorSP(3);
        
        pickUp = new Victor(2);
        stick = new Joystick(0);
        
        int obstacle;
        int position; 
        
        
    }
    
    //********************
    //Autonomous 
    //********************
    
    //OKAY TO WHOEVER IS GOING TO SWITCH THE OBSTACLE LISTEN UP
    //IF YOU WANT TO DO A CERTAIN OBSTACLE, CHANGE THE WHAT OBSTACLE  IS EQUAL TO 
    //ACCORDING TO THIS:
    
     /*public void autonomous() {
    	 while(isAutonomous() && isEnabled())
    	 {
    	 
    	//****************
        //Driving up to the obstacle (2pts)
    	//****************
    		 
    	for(int i = 0; i < 4; i++)
    		{
    			driveTrain.drive(0.2, 0.0);
    			Timer.delay(2.0);
    		}
    	
    	driveTrain.drive(0.0, 0.0);
    		
    //******************************
    //Switch cases for the obstacles
    //******************************
    		 
    	switch(obstacle)
    	{
    	//Moat 
    		case 1: 
    	//Ramparts	
    		case 2:
    	//Rock Wall	
    		case 3:
    	//Rough Terrain	
    		case 4:
    	//Cheval de Frise	
    		case 5:
    	//Sally Port	
    		case 6:
    	//Draw Bridge	
    		case 7:
    	 	
    		default:
	 
    	}	 
    		 
    		 
    		 
    //******************************************** 
    //Switch cases for positioning after obstacles 
	//********************************************.
	  	switch(position)
	  	{
	  	//Position 1
	  		case 1:
	  	//Position 2	
	  		case 2:
	  	//Position 3	
	  		case 3:
	  	//Position 4	
	  		case 4:
	  	//Stop moving after traversing obstacle	
	  		default:
	  	
	  	}
	  	
    	 }
     }
     
    
    //}*/
    
    //*****************
    //Operator Controls 
    //*****************
 
    public void operatorControl() {
    	//getWatchdog().setEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	
        	double launcherSpeed = 1;
        	
        	//******************
        	//Sticks to move Carl
        	//******************
        	
        	leftSide.set((stick.getY()) * (.7));
        	
        	leftSide.set((stick.getY()) * (-.7));
        	
        	rightSide.set(stick.getRawAxis(3) * (-.7));
        	
        	rightSide.set(stick.getRawAxis(3) * (.7));
        	
        	//*****************
        	//if(stick.getRawButton(4) == true)//My launcher code
        	//{
        	//pickUp.set(-0.4);
        	//}
        	
        	if(stick.getRawButton(1)){//
        		launcherSpeed = 0.8;
        		System.out.print("button "+1+" Fired!");	
        	}
        	else if(stick.getRawButton(2)){//
        		launcherSpeed = 0.85;
        		System.out.print("button "+2+" Fired!");
        		}
        	else if(stick.getRawButton(3)){//
        		launcherSpeed = 0.95;
        	System.out.print("button "+3+" Fired!");
        }
        	else if(stick.getRawButton(4)){//
        		launcherSpeed = 1;
        		System.out.print("button "+4+" Fired!");
        	}
        	
        	//BELLOW WILL NOT RUN UNLESS INTIALIZED 
        	if(stick.getRawButton(8))
        	{
        		rightLauncher.set(launcherSpeed-0.1);
        		leftLauncher.set(launcherSpeed);
        	}
        	
        	
        	//*******************************
        	//Launching and buttons to set it
        	//*******************************
        	
        	/*if(stick.getRawButton(8) == true)
        	{
            	if(stick.getRawButton(1) == true)
            	  {
            	  		launcherSpeed = 0.4;
            	  }
            	if(stick.getRawButton(2) == true)
            	  {
            	  		launcherSpeed = 0.6;
            	  }
            	if(stick.getRawButton(3) == true)
            	  {
            	  		launcherSpeed = 1.0;
            	  }
        		rightLauncher.set(launcherSpeed);
        		leftLauncher.set(launcherSpeed);
        	}*/
        		
            //*********************
        	//Pick up wheels 
        	//*********************
        	
        	if(stick.getRawButton(7) == true)
        	{
            	pickUp.set(.4);
        	}

        	//**********************
        	//Inverse launcher + Not being pressed
        	//**********************
        	
        	if(stick.getRawButton(6) == true)
        	{
        		rightLauncher.set(-1);
        		leftLauncher.set(-1);
        	}
        	if(stick.getRawButton(6)== false && stick.getRawButton(8) == false)
        	{
        		rightLauncher.set(0);
        		leftLauncher.set(0);
        	}
        	
        	
        	//**********************
        	//Inverse pickup + When pick up not in use
        	//**********************

        	if(stick.getRawButton(5) == true)
        	{
        		pickUp.set(-.4);
        	}
        	if(stick.getRawButton(5) == false && stick.getRawButton(7) == false && stick.getRawButton(4) == false)
			{
        		pickUp.set(0);
        	}

            Timer.delay(k_updatePeriod);	// wait 5ms to the next update
        }
        
    }
}
