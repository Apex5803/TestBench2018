package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.usfirst.frc5803.RobotTestBench.commands.FollowTrajectory;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc5803.RobotTestBench.utils.*;
import org.usfirst.frc5803.RobotTestBench.models.*;

/**
 * This autonomous routine is used for tuning the FollowTrajectory command with motion profiling.
 * It will drive forward 5 feet. 
 */
public class ScaleRight extends CommandGroup {
	SrxTrajectoryImporter importer = new SrxTrajectoryImporter("/home/lvuser/Autos");
	SrxTrajectory toFollow = null;
    public ScaleRight() {
        	try {
    			this.toFollow = importer.importSrxTrajectory("ScaleRight");
    			//System.out.println("import" + this.toFollow); 
    		} catch (IOException | ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	addSequential(new FollowTrajectory("ScaleRight"));
    }
}
