package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import org.usfirst.frc5803.RobotTestBench.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandC extends CommandGroup{
    public CommandC() {

    }
    
    @Override
    protected void execute() {
    	System.out.println("CommandC ran");
    }
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

	

