package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import org.usfirst.frc5803.RobotTestBench.models.GameState;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleCenter extends CommandGroup{
public ScaleCenter(GameState gameState) {
	if (gameState.scaleSide.toString() == "LEFT") {
		addSequential(new ScaleLeft());
		}
	else if (gameState.scaleSide.toString() == "RIGHT") {
		addSequential(new ScaleRight());
	}
	else System.out.println("did not recieve scale side");
}
}