package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import org.usfirst.frc5803.RobotTestBench.commands.FollowTrajectory;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This autonomous routine is used for tuning the FollowTrajectory command with motion profiling.
 * It will drive forward 5 feet. 
 */
public class DriveForwardFiveFeet extends CommandGroup {

    public DriveForwardFiveFeet() {
    	/* Yes this is confusing, this path is set to drive forward five feet */
    	addSequential(new FollowTrajectory("ThreeFeet"));
    }
}
