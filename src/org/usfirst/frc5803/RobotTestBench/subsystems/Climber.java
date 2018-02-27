package org.usfirst.frc5803.RobotTestBench.subsystems;

import org.usfirst.frc5803.RobotTestBench.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class Climber {
	public void climb() {
		RobotMap.climber.set(ControlMode.PercentOutput, 1);
	}
	
	public void stop() {
		RobotMap.climber.set(ControlMode.PercentOutput, 0);
	}
}
