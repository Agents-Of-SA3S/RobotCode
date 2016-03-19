package org.usfirst.frc.team5329.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

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
    private SpeedController launcher;
    private SpeedController pickUp;
    
    private Joystick stick;

	private final double k_updatePeriod = 0.005; // update every 0.005 seconds/5 milliseconds (200Hz)

    public Robot() {
         leftSide = new Victor(0);	
        rightSide = new Victor (1);
        launcher = new Victor (3);
        pickUp = new Victor (4);// initialize the motor as a Talon on channel 0
        stick = new Joystick(0);	// initialize the joystick on port 0
    }

    /**
     * Runs the motor from a joystick.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	// Set the motor's output.
        	// This takes a number from -1 (100% speed in reverse) to +1 (100% speed going forward)
        	
        	//figure out how to increment speed as it's being pressed down
        	//figure out how to get z-rotation for the right side of driving the base
        	//figure out how to get d-pad for arms
        	//AUTONUMOUS      	
        	
        	//X - Left Side A - RIght B - inverse left y - inverse right 
        	
        	if(stick.getRawButton(1) == true)
        		leftSide.set(0.5);
        	if(stick.getRawButton(2) == true)
            	rightSide.set(0.5);
        	if(stick.getRawButton(3) == true)
        		leftSide.set(-0.5);
        	if(stick.getRawButton(4) == true)
        		rightSide.set(-0.5);
        	
        	//right trigger for launcher 
        	//left trigger for pick up
        	
        	if(stick.getRawButton(8) == true)
            	launcher.set(0.5);
        	if(stick.getRawButton(7) == true)
            	pickUp.set(0.5);
        	
        	//right bumper for inverse launcher 
        	//left bumper for inverse pick up
        	
        	if(stick.getRawButton(5) == true)
        		launcher.set(-0.5);
        	if(stick.getRawButton(6) == true)
        		pickUp.set(-0.5);
        	
        	
        	
        	if(stick.getRawButton(10) == true)
        			System.exit(0);
        	
        	//SO, basically 
        	//for bumpers, you just need to invert the motors 
        	//
        	
        	//leftSide.set((stick.getY()) * (.75));
        	
        	
        	//motor2.set((stick.getY()) * (.75));
        	
        	//rightSide.set(stick.getRawAxis(3)*(.75));
        	
        	//motor4.set(stick.getRawAxis(3) * (.75));
        	
        	//Set up launcher and reverse launcher code for right triggers
        	//
        	
        	
            Timer.delay(k_updatePeriod);	// wait 5ms to the next update
        }
        
    }
}
