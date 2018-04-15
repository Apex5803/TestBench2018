package org.usfirst.frc5803.RobotTestBench.commands;

import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

public class HoldArmAngle extends Command {

		// Use requires() here to declare subsystem dependencies
		
	

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		System.out.println("Holding Extender position");
		// double angle = RobotMap.Arm1.getClosedLoopTarget(0);
		double Extension = Robot.arm.armmotor.getSelectedSensorPosition(0);
		Robot.arm.Extend(ControlMode.MotionMagic, Extension);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

