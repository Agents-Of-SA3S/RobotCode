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
    
    private SpeedController leftSide;   // the motor to directly control with a joystick
    private SpeedController rightSide;
    private SpeedController rightLauncher;
    private SpeedController leftLauncher;
    private SpeedController pickUp;
    private Timer time;
    private RobotDrive driveTrain;
    private Joystick stick;


    private int curObstacle;
    private String[] obstacles={"Moat","Rampart","Rough Terrain","Low Bar","Sally Port","Draw Bridge"};
    private double[] timeToPassObstacle={2.5,5,5,5,5,5};
    private int position;
    
    

    private final double k_updatePeriod = 0.005; // update every 0.005 seconds/5 milliseconds (200Hz)

    //**************************************
    //Setting new instances of Robot Objects
    //**************************************
    public Robot() {
        /*void robotInit()*/
        
        
        
        leftSide = new Victor(0);   
        rightSide = new Victor(1);
        //driveTrain = new RobotDrive(leftSide, rightSide); //speed controllers drive test
        rightLauncher = new VictorSP(4);
        leftLauncher = new VictorSP(3);
        
        pickUp = new Victor(2);
        stick = new Joystick(0);
        
        time = new Timer();


        curObstacle=0;//arrays start from zero guys so moat is 0 rampart is 1 and so on

        position=1;//start from left?
        
        
    }

    private boolean isDoneWithObstacle(double curTime){
        //only until we have the timing down;
        if(curTime>=timeToPassObstacle[curObstacle]){
            return true;
        }
        else{
            return false;
        }
    }

    private String getObstacle(){
        return obstacles[curObstacle];
    }
    
    //********************
    //Autonomous 
    //********************
    //https://wpilib.screenstepslive.com/s/4485/m/13809/l/241857-getting-your-robot-to-drive-with-the-robotdrive-class
    public void autonomous() {

        //http://users.wpi.edu/~bamiller/WPIRoboticsLibrary/d8/d08/class_timer.html
        time.start();

        while (isAutonomous() && isEnabled()) {


            if(isDoneWithObstacle(time.get())){
                positionSelf(position);
            }
            else{
                doDat(getObstacle());
            }
        	

            Timer.delay(k_updatePeriod);

        }

    }

    public void doDat(String doWhat){
        //return;
        //only for testing drivetrain

        if(doWhat.equals("Moat")){
        	forward(0.5);
        }
        else if(doWhat.equals("Rampart")){
            forward(0.67);
        }
        else if(doWhat.equals("Rough Terrain")){
            forward(0.67);
        }
        else if(doWhat.equals("Low Bar")){
        	forward(0.67);
        }
        else if(doWhat.equals("Sally Port")){
            
        }
        else if(doWhat.equals("Draw Bridge")){
            
        }
        else{
            //error?
        }

    }
    public void forward(double amount){
    	leftSide.set(amount);
    	rightSide.set(-amount);
    }
    
    public void turn(String dir,double amount,double forHowLong){
    	if(forHowLong>=time.get()-timeToPassObstacle[curObstacle]){
    		if(dir.equals("right")){
        		leftSide.set(amount);
            	rightSide.set(amount);
        	}
        	else{
        		leftSide.set(-amount);
            	rightSide.set(-amount);
        	}
    	}
    	
    	
    }//first is auto for a bit then turn for however long we should
    
    public void positionSelf(int how){
    	
        if(how == 1){
        	turn("left",0.35,0.8);
        }
        else if(how == 2){
        	turn("left",0.35,1.0);
        }
        else if(how == 3){
        	turn("right",0.35,1.0);
        }
        else if(how == 4){
        	turn("right",0.35,1.2);
        }
        else{
            //error?
        }

    }
            
    
    //*****************
    //Operator Controls 
    //*****************
 
    public void operatorControl() {

        double launcherSpeed = 1;
        double limiter;
        //launcherSpeed may be manipulated therefore cannot be instantiated and declared everytime the loop runs

        //getWatchdog().setEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	
            limiter=1.0;
            //******************
            //Sticks to move Carl
            //******************
            if(stick.getRawButton(9)){
            	limiter=0.75;
            }
            else if(stick.getRawButton(10)){//half speed left press in a third speed right press in
            	limiter=0.5;
            }
            
            
            leftSide.set((stick.getRawAxis(1)) * (limiter));
            
            leftSide.set((stick.getRawAxis(1)) * (-limiter));
            
            rightSide.set(stick.getRawAxis(5) * (-limiter));
            
            rightSide.set(stick.getRawAxis(5) * (limiter));
            
            //*****************
            //if(stick.getRawButton(4) == true)//My launcher code
            //{
            //pickUp.set(-0.4);
            //}

            if(stick.getRawButton(1)){//
                launcherSpeed = 0.8;
            }
            else if(stick.getRawButton(2)){//
                launcherSpeed = 0.85;
                }
            else if(stick.getRawButton(3)){//
                launcherSpeed = 0.95;
        }
            else if(stick.getRawButton(4)){//
                launcherSpeed = 1;
            }
            
            
            //*******************************
            //Launching and buttons to set it
            //*******************************

                
            //*********************
            //Pick up wheels 
            //*********************
            
            pickUp.set(stick.getRawAxis(2) * 0.5);
            //**********************
            //Inverse launcher + Not being pressed
            //**********************
            rightLauncher.set(stick.getRawAxis(3));
            leftLauncher.set(stick.getRawAxis(3));
            
            if(stick.getRawButton(6))
            {
                rightLauncher.set(-0.3);
                leftLauncher.set(-0.3);
            }

            if(stick.getRawAxis(3) <= 0 && !stick.getRawButton(6))
            {
                rightLauncher.set(0);
                leftLauncher.set(0);
            }
            
            
            //**********************
            //Inverse pickup + When pick up not in use
            //**********************

            if(stick.getRawButton(5))
            {
                pickUp.set(-.4);
            }

            Timer.delay(k_updatePeriod);    // wait 5ms to the next update
        }
        
    }
}
