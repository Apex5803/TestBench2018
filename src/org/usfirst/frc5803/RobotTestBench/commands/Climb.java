package org.usfirst.frc5803.RobotTestBench.commands;

import org.usfirst.frc5803.RobotTestBench.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command{
	protected void initialize() {
		
	}
	
	protected void execute() {
		Robot.climber.climb();
	}
	
	protected boolean isFinished() {
			return false;
			
	}
	
	protected void end() {
		Robot.climber.stop();
	}
	
	protected void interrupted() {
		end();
	}
}

