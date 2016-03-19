package org.usfirst.frc.team5329.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    RobotDrive myRobot;  // class that handles basic drive operations
    //trigger RT to launch 
    //reverse launcher RB button

    JoystickButton toLaunch; //RT 
    JoystickButton reverseLaunch;//RB
    
    //LT to pick up 
    //LB to reverse pick up
    JoystickButton toPickUp;//LT
    JoystickButton toReversePickUp;//LB
    //sticks control tank
    
    
    
    //D-Pad Arms...
    
    
    Joystick controller; 
    //four talons for base 
    //two victor SPs for white launcher wheels
    //one victor 888 for pick up wheels
    
    
    
    //FULL SPEED AHEAD 
    
    
    //Buttons control the speed of launcher 
    //Fullpower in reverse for all times
    //X is full speed and far 
    //Y is middle 
    //B is upclose
    
    
    double pickUpPower;
    double launcherPower;
    
    
    
    public Robot() {
    	//take motor controllers for 0 and 1 as left (given same value) and 2 and 3 as right (given same value)
        myRobot = new RobotDrive(0, 1 , 2 , 3);
        myRobot.setExpiration(0.1);
        controller = new Joystick(4);
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
        myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	myRobot.tankDrive(controller, 2, controller, 5);
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

}
