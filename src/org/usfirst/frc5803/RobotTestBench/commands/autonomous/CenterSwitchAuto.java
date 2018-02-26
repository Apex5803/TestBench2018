package org.usfirst.frc5803.RobotTestBench.commands.autonomous;
import org.usfirst.frc5803.RobotTestBench.models.GameState;
import org.usfirst.frc5803.RobotTestBench.models.GameState.Side;
import org.usfirst.frc5803.RobotTestBench.commands.FollowTrajectory;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterSwitchAuto extends CommandGroup {

	    public CenterSwitchAuto(GameState gameState) {
	    	
	    	if(gameState.mySwitchSide == Side.LEFT) {
				addSequential(new CommandC());
			}else {
				addSequential(new CommandD());
			
	    }
	
	    }

}
