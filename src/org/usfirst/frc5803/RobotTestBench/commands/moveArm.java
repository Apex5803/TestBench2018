/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5803.RobotTestBench.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5803.RobotTestBench.robotold2;
import org.usfirst.frc5803.RobotTestBench.subsystems.Arm;
import org.usfirst.frc5803.RobotTestBench.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class moveArm extends Command {
	public moveArm() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		/*
	//Check if robot arm is past the highest angle it should physically go, translated to native units
		if(Robot.arm.vgiver() > (109 * 4096/360)) {
			System.out.println("I am too far!");
			//check if trigger is pulled to activate manual control
			if(Robot.oi.xbox.getTriggerAxis(Hand.kLeft)>0.75) {		
				System.out.println("I am trying to manual control!");
				//check if joystick value is trying to drive arm (up) past highest setpoint
				if(Robot.oi.xbox.getY(Hand.kLeft) > 0){
					System.out.println("I am not going the bad direction!");
					//don't let it go the bad direction
					Robot.arm.move(0);
				}
				//going the good direction, keep going
				
				else {
					System.out.println("I am too far, but going the safe direction!");
					Robot.arm.move(Robot.oi.xbox.getY(Hand.kLeft));
				}
			}
			//if trigger not pulled, don't move
			else Robot.arm.move(0);
		}
		//check if arm is below the lowest angle it should physically go, translated to native units
		else if (Robot.arm.vgiver() < (7 * 4096/360)) {
			System.out.println("I am too low!");
			//check if trigger is pulled to activate manual control
			if(Robot.oi.xbox.getTriggerAxis(Hand.kLeft)>0.75) {		
				System.out.println("I am too low and trying to manual control!");
				//check if trying to go the bad direction (down) and don't move if true
				if(Robot.oi.xbox.getY(Hand.kLeft) < 0){
					System.out.println("I am too low and trying to go the bad direction!");
					//don't let it go the bad direction
					Robot.arm.move(0);
				}
				//going the good direction, keep going
				else {
					System.out.println("I am too low, but going the good direction!");
					Robot.arm.move(Robot.oi.xbox.getY(Hand.kLeft));
				}
			}
			//trigger not pulled for manual override, don't go
			else Robot.arm.move(0);
		}
		//should only get here if arm is at a safe angle, run either direction if trigger pulled for manual override
		else if( (Robot.arm.vgiver() > (7 * 4096/360)) && (Robot.arm.vgiver() < (109 * 4096/360)) ) {
			System.out.println("I am within limits!");
			if(Robot.oi.xbox.getTriggerAxis(Hand.kLeft)>0.75) {
				System.out.println("running full manual test");
				Robot.arm.move(0.5 * Robot.oi.xbox.getY(Hand.kLeft)); 	
			}
			else Robot.arm.move(0);
		}
		
		*/
		if(Robot.oi.xbox.getTriggerAxis(Hand.kLeft) > 0.75) {
			//System.out.println("running full manual test");
			if(Robot.oi.xbox.getY(Hand.kLeft) > 0.2 || Robot.oi.xbox.getY(Hand.kLeft) < -0.2) {
				Robot.arm.move(0.2 * Robot.oi.xbox.getY(Hand.kLeft)); 						
			}
			else Robot.arm.move(0);
		}		
		else Robot.arm.move(0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
 