package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandF extends Command {
    public CommandF() {
    	
    }
    
    @Override
    protected void execute() {
    	System.out.println("CommandF ran");
    }
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


}
