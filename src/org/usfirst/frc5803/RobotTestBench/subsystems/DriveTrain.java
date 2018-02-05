package org.usfirst.frc5803.RobotTestBench.subsystems;

import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.models.*;
import org.usfirst.frc5803.RobotTestBench.RobotMap;
import org.usfirst.frc5803.RobotTestBench.commands.Drive;
import org.usfirst.frc5803.RobotTestBench.commands.DriveVelocityPIDTest;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


/**
 *
 */

public class DriveTrain extends Subsystem {
	public TalonSRX L1 = RobotMap.driveTrainDriveTrainL1;
	public TalonSRX R1 = RobotMap.driveTrainDriveTrainR1;
	
	//public WPI_TalonSRX L1 = RobotMap.driveTrainDriveTrainL1;
    //public WPI_TalonSRX R1 = RobotMap.driveTrainDriveTrainR1;
   private final DifferentialDrive joystickControl = RobotMap.joystickControl;
   StringBuilder _sb = new StringBuilder();
   int _loops = 0;
	
   @Override
   public void initDefaultCommand() {
	   //TODO make sure this is set to how you want to drive, or what you want to test
    	setDefaultCommand(new Drive());
	   //setDefaultCommand(new DriveVelocityPIDTest());
	   
    }

    public void configPIDF(double p, double i, double d, double f) {

    	this.L1.config_kP(0, p, 0);
    	this.L1.config_kI(0, i, 0);
    	this.L1.config_kD(0, d, 0);
    	this.L1.config_kF(0, f, 0);

    	this.R1.config_kP(0, p, 0);
    	this.R1.config_kI(0, i, 0);
    	this.R1.config_kD(0, d, 0);
    	this.R1.config_kF(0, f, 0);
    }
    
    public void drive(double left, double right){
    	//this.L1.set(ControlMode.PercentOutput, left);
    	//this.R1.set(ControlMode.PercentOutput, right);
    	//joystickControl.arcadeDrive(move, rotate, true);

    }
    /**** NEW METHOD FOR DRIVING PATHS ****/
    //TODO do we need this? I don't think we actually do
    public void drivePath(double left, double right) {
     	this.L1.set(ControlMode.PercentOutput, left);
    	this.R1.set(ControlMode.PercentOutput, right);    	    	
    }

    public void drive(DriveSignal driveSignal) {

    	//System.out.println(driveSignal.toString());

    	this.drive(driveSignal.getLeft(), driveSignal.getRight());

    }


    public boolean quickTurnController() {
    if (Robot.oi.xbox.getY(Hand.kLeft) < 0.2 && Robot.oi.xbox.getY(Hand.kLeft) > -0.2) {
		return true;	
	} else {
		return false;
		}
    }
    
	public void velocityPIDTest() {	
		//WPI_TalonSRX _talonL = this.L1;
		//WPI_TalonSRX _talonR = this.R1;
		TalonSRX _talonL = this.L1;
		TalonSRX _talonR = this.R1;
		
		double leftYstick = Robot.oi.xbox.getY(Hand.kLeft);
		double rightYstick = Robot.oi.xbox.getY(Hand.kRight);
		double motorOutputL = _talonL.getMotorOutputPercent();
		double motorOutputR = _talonR.getMotorOutputPercent();
	    	
		/* prepare line to print */
		_sb.append("\toutL:");
		_sb.append(motorOutputL);
		_sb.append("\tspdL:");
		_sb.append(_talonL.getSelectedSensorVelocity(0));
		_sb.append("\n");
		
		/* prepare next line to print */
		_sb.append("\toutR:");
		_sb.append(motorOutputR);
		_sb.append("\tspdR:");
		_sb.append(_talonR.getSelectedSensorVelocity(0));
		_sb.append("\n");
	
		if (Robot.oi.xbox.getAButton()) { //Run left drive
			/* Speed mode */
			/* Convert 500 RPM to units / 100ms.
			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			//double targetVelocity_UnitsPer100ms = -1 * 210.0 * 4096 / 600;
			double targetVelocity_UnitsPer100ms = -1 * 210.0 * 1024 / 600; //TODO modified to 1024 for quad encoders on failstorm
			/* 500 RPM in either direction */
			_talonL.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
	
			/* append more signals to print when in speed mode for left side. */
			_sb.append("\terrl:");
			_sb.append(_talonL.getClosedLoopError(0));
			_sb.append("\ttrgl:");
			_sb.append(targetVelocity_UnitsPer100ms);
			//_sb.append("\n");
		} 
		else if (Robot.oi.xbox.getBButton()){ //Run right drive
			/* Speed mode */
			/* Convert 500 RPM to units / 100ms.
			 * 4096 Units/Rev * 500 RPM / 600 100ms/min in either direction:
			 * velocity setpoint is in units/100ms
			 */
			//double targetVelocity_UnitsPer100ms = -1 * 210.0 * 4096 / 600;
			double targetVelocity_UnitsPer100ms = -1 * 210.0 * 1024 / 600; //TODO modified to 1024 for quad encoders on failstorm
			/* 500 RPM in either direction */
			//_talonL.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
			_talonR.set(ControlMode.Velocity, targetVelocity_UnitsPer100ms);
	
			/* append more signals to print when in speed mode for right side. */
			_sb.append("\terrR:");
			_sb.append(_talonR.getClosedLoopError(0));
			_sb.append("\ttrgR:");
			_sb.append(targetVelocity_UnitsPer100ms);
			//_sb.append("\n");
		}
		else {
			/* Percent voltage mode */
			_talonL.set(ControlMode.PercentOutput, leftYstick);
			_talonR.set(ControlMode.PercentOutput, rightYstick);
		}
	
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}
		_sb.setLength(0);
	}
    

	@Override
    public void periodic() {
        // Put code here to be run every loop

    }
	
	public void End() {
		Robot.driveTrain.drive(0,0);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

