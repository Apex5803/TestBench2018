package org.usfirst.frc5803.RobotTestBench;


import org.usfirst.frc5803.RobotTestBench.utils.TriggerButton;
import org.usfirst.frc5803.RobotTestBench.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

//#victors are victims 
    public Joystick joystickL;
    public XboxController xbox;

    public OI() {
       
        xbox = new XboxController(0);

        //pickup angle
        Button A=new JoystickButton(xbox,1);
        A.whenPressed(new MoveArmToAngle(6));
        //climb angle
        Button B=new JoystickButton(xbox,2);
        B.whenPressed(new MoveArmToAngle(90));
        //switch angle
        Button X=new JoystickButton(xbox,3);
        X.whenPressed(new MoveArmToAngle(45));
        //shoot on scale angle
        Button Y=new JoystickButton(xbox,4);
        Y.whenPressed(new MoveArmToAngle(75));
        //place on scale angle
        Button R1=new JoystickButton(xbox,6);
        R1.whenPressed(new MoveArmToAngle(110));
        //Manual Override
       // Button LB=new JoystickButton(xbox,5);
       // LB.whenPressed(new moveArm());
      // SmartDashboard Buttons
        //

   
        //manual arm control
        //Button RT=new TriggerButton(xbox,3);
        //RT.whileHeld(new moveArm());
      
        //RT.whileHeld(System.out.println("holdingtrigger"));
        // SmartDashboard Buttons

        SmartDashboard.putData("Drive", new Drive());
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("other auto", new AutonomousCommand());
    }

    public Joystick JoystickL() {
        return joystickL;
    }

    public XboxController xbox() {
    	return xbox;
    }
}

