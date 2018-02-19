package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandB extends CommandGroup{
    public CommandB() {
    
    }
    
    @Override
    protected void execute() {
    	System.out.println("CommandB ran");
    }
    
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}

