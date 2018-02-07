/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5803.RobotTestBench.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

import org.usfirst.frc5803.RobotTestBench.RobotMap;
import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

import org.usfirst.frc5803.RobotTestBench.subsystems.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/**
 * A subsystem to move Failstorm's climber like an arm.
 */
public class Arm extends Subsystem {
	public TalonSRX armmotor = RobotMap.climber;
	
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new moveArm());
	}
	 public void configPIDF(double p, double i, double d, double f) {

	    	this.armmotor.config_kP(0, p, 0);
	    	this.armmotor.config_kI(0, i, 0);
	    	this.armmotor.config_kD(0, d, 0);
	    	this.armmotor.config_kF(0, f, 0);

	    }
	 public double vgiver(){
		 return this.armmotor.getSelectedSensorPosition(0);
	 }
	 public void move(double stickv){
		 //System.out.println("move method"+(vgiver()*360/4096));
		 /*
		 if(this.armmotor.getSelectedSensorPosition(0)*4096/360>115) {
		 		double setPoint= 82*4096/360;
			 	this.armmotor.set(ControlMode.MotionMagic, setPoint);
			 	System.out.println("Reseting to 110");
		 	}
		 	
		 else	*/
		 this.armmotor.set(ControlMode.PercentOutput, stickv);
	 }
	 public void moveTo(double angle){
		 //angle*4096 tics per rev./total degrees per revolution 
		 double setPoint= angle*4096/360;
		 this.armmotor.set(ControlMode.MotionMagic, setPoint);
		 //System.out.println(angle + ": target angle");
		 System.out.println( this.armmotor.getSelectedSensorPosition(0)*306/4096 + ": actual angle");
	 }
	 public void End() {
			Robot.arm.move(0);
		}
	 
}