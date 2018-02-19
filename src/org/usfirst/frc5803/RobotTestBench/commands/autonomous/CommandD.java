package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CommandD extends Command {
    public CommandD() {
    	
    }
	
    @Override
    protected void execute() {
    	System.out.println("CommandD ran");
    }
    
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}


}
