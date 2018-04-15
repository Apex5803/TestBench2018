/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5803.RobotTestBench.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;

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
		
		
		//if(Robot.oi.xbox.getTriggerAxis(Hand.kLeft) > 0.75) {
			//System.out.println("running full manual test");
			if(Robot.oi.xbox.getY(Hand.kLeft) > 0.2 || Robot.oi.xbox.getY(Hand.kLeft) < -0.2) {
				Robot.arm.move(0.2 * Robot.oi.xbox.getY(Hand.kLeft)); 						
			}
			if(Robot.arm.vgiver() < 10*360/4096) {
				if(!Robot.limitSwitch.get()) {
					System.out.println("WHYYYYYY2");
					Robot.arm.Retract();
				}
				else if(Robot.limitSwitch.get()) {
					System.out.println("WHYYYYYY1");
					end();
					new HoldArmAngle();
					
				}
			}
	}
			//else Robot.arm.move(0);
				
	
		//else Robot.arm.move(0);
	//}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.arm.End();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
 