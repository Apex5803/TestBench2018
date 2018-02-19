package org.usfirst.frc5803.RobotTestBench.commands;

import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.utils.DriveHelper;

import edu.wpi.first.wpilibj.command.Command;

public class CommandA extends Command{
    public CommandA() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	System.out.println("CommandA ran");
    }
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
