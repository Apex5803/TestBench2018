package org.usfirst.frc5803.RobotTestBench.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;
import java.io.IOException;
import org.json.simple.parser.ParseException;
//import org.usfirst.frc5803.robot.Robot;
import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.models.*;
import org.usfirst.frc5803.RobotTestBench.utils.*;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import org.usfirst.frc.team319.models.SrxTrajectory;

public class FollowTrajectory extends Command {
	private String trajectoryName = "";
	private int kMinPointsInTalon = 5;

	private boolean isFinished = false;

	private SrxTrajectory trajectoryToFollow = null;
	//TODO check to make sure directory on roborio is the same as this
	private SrxTrajectoryImporter importer = new SrxTrajectoryImporter("/home/lvuser/Autos");

	private MotionProfileStatus rightStatus = new MotionProfileStatus();
	private MotionProfileStatus leftStatus = new MotionProfileStatus();

	/**
	 * this is only either Disable, Enable, or Hold. Since we'd never want one
	 * side to be enabled while the other is disabled, we'll use the same status
	 * for both sides.
	 */
	private SetValueMotionProfile setValue = SetValueMotionProfile.Disable;

	// periodically tells the SRXs to do the thing
	private class PeriodicRunnable implements java.lang.Runnable {
		public void run() {
			Robot.driveTrain.L1.processMotionProfileBuffer();
			Robot.driveTrain.R1.processMotionProfileBuffer();
		}
	}

	// Runs the runnable
	private Notifier SrxNotifier = new Notifier(new PeriodicRunnable());

	// constructor
	public FollowTrajectory(String trajectoryName) {
		requires(Robot.driveTrain);
		this.trajectoryName = trajectoryName;
		System.out.println("Following :" + trajectoryName);
	}

	public FollowTrajectory(SrxTrajectory trajectoryToFollow) {
		requires(Robot.driveTrain);
		this.trajectoryToFollow = trajectoryToFollow;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setUpTalon(Robot.driveTrain.R1);
		setUpTalon(Robot.driveTrain.L1);

		setValue = SetValueMotionProfile.Disable;
		
		Robot.driveTrain.L1.set(ControlMode.MotionProfile, setValue.value);
		Robot.driveTrain.R1.set(ControlMode.MotionProfile, setValue.value);

		SrxNotifier.startPeriodic(.005);
		
		System.out.println("initializing FollowTrajectory");


			try 
			{
				System.out.println("tried to import trajectory");
				this.trajectoryToFollow = importer.importSrxTrajectory(trajectoryName);
			} 
			catch (IOException | ParseException e) {
				System.out.println("Failed to import trajectory.");
				e.printStackTrace();
				isFinished = true;
				return;
			}
		
		
	//	int pidfSlot = Robot.driveTrain.HIGH_GEAR_PROFILE;
		fillTalonBuffer(Robot.driveTrain.R1, this.trajectoryToFollow.rightProfile, 0);
		fillTalonBuffer(Robot.driveTrain.L1, this.trajectoryToFollow.leftProfile, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.driveTrain.R1.getMotionProfileStatus(rightStatus);
		Robot.driveTrain.L1.getMotionProfileStatus(leftStatus);
	//	System.out.println("Bottom buffer count: " + rightStatus.btmBufferCnt);
	//	System.out.println("Top buffer count: " + rightStatus.topBufferCnt);

		
		if (rightStatus.isUnderrun || leftStatus.isUnderrun)
		{
			// if either MP has underrun, stop both
			System.out.println("Motion profile has underrun!");
			setValue = SetValueMotionProfile.Disable;
		}
		else if (rightStatus.btmBufferCnt > kMinPointsInTalon && leftStatus.btmBufferCnt > kMinPointsInTalon)
		{
			// if we have enough points in the talon, go.
			//System.out.println("should be going");
			setValue = SetValueMotionProfile.Enable;
		}	
		else if (rightStatus.activePointValid && rightStatus.isLast && leftStatus.activePointValid
				&& leftStatus.isLast)
		{
			// if both profiles are at their last points, hold the last point
			//System.out.println("im at the last point");
			setValue = SetValueMotionProfile.Hold;
		}
		Robot.driveTrain.L1.set(ControlMode.MotionProfile, setValue.value);
		Robot.driveTrain.R1.set(ControlMode.MotionProfile, setValue.value);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean leftComplete = leftStatus.activePointValid && leftStatus.isLast;
		boolean rightComplete = rightStatus.activePointValid && rightStatus.isLast;
		boolean trajectoryComplete = leftComplete && rightComplete;
		return trajectoryComplete || isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		SrxNotifier.stop();
		resetTalon(Robot.driveTrain.R1, ControlMode.PercentOutput, 0);
		resetTalon(Robot.driveTrain.L1, ControlMode.PercentOutput, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		SrxNotifier.stop();
		resetTalon(Robot.driveTrain.R1, ControlMode.PercentOutput, 0);
		resetTalon(Robot.driveTrain.L1, ControlMode.PercentOutput, 0);
	}	

	// set up the talon for motion profile control
	public void setUpTalon(TalonSRX talon) {
		talon.clearMotionProfileTrajectories();
		talon.changeMotionControlFramePeriod(5);
	}

	// set the 	 to the desired controlMode
	// used at the end of the motion profile
	public void resetTalon(TalonSRX talon, ControlMode controlMode, double setValue) {
		talon.clearMotionProfileTrajectories();
		talon.set(ControlMode.MotionProfile, SetValueMotionProfile.Disable.value);
		talon.set(controlMode, setValue);
	}

	// Send all the profile points to the talon object
	public void fillTalonBuffer(TalonSRX talon, SrxMotionProfile prof, int pidfSlot) {
		System.out.println("filling talon buffer");
		TrajectoryPoint point = new TrajectoryPoint();

		for (int i = 0; i < prof.numPoints; ++i) {
			/* for each point, fill our structure and pass it to API */
			point.position = prof.points[i][0];
			point.velocity = prof.points[i][1];
			point.timeDur = TrajectoryDuration.Trajectory_Duration_10ms;
			point.profileSlotSelect0 = pidfSlot; 
			point.profileSlotSelect1 = pidfSlot;
			point.zeroPos = false;
			if (i == 0)
				point.zeroPos = true; /* set this to true on the first point */
			point.isLastPoint = false;
			if ((i + 1) == prof.numPoints)
				point.isLastPoint = true; /*
										   * set this to true on the last point
										   */
			
			talon.pushMotionProfileTrajectory(point);
		}
	}
}