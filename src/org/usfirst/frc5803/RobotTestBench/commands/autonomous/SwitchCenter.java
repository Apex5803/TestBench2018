package org.usfirst.frc5803.RobotTestBench.commands.autonomous;

import org.usfirst.frc5803.RobotTestBench.models.GameState;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchCenter extends CommandGroup{
public SwitchCenter(GameState gameState) {
	if (gameState.mySwitchSide.toString() == "LEFT") {
		addSequential(new SwitchLeft());
		}
	else if (gameState.mySwitchSide.toString() == "RIGHT") {
		addSequential(new SwitchRight());
	}
	else System.out.println("did not recieve switch side");
}
}
