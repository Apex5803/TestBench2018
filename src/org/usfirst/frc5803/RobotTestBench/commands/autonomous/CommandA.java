package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
public class CommandA extends CommandGroup {
	public CommandA() {
	}
	    
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
