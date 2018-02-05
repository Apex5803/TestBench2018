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
public class MoveArmToAngle extends Command {
	private double angle;
    StringBuilder _sb = new StringBuilder();

	public MoveArmToAngle(double setAngle) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.arm);
		this.angle = setAngle;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.arm.moveTo(this.angle);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		double movevalue = Robot.arm.vgiver();
		double targetvalue=this.angle*4096/360;
		_sb.append("I'm not finished, value: " + movevalue + "target: " + targetvalue);

		if((targetvalue+10)<movevalue || movevalue<(targetvalue-10)) {
			System.out.println("I'm not finished, value: " + movevalue + "target: " + targetvalue);
			return false;	
		}
		else return true;
	}
	
	@Override
	protected void end() {
		Robot.arm.move(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
 