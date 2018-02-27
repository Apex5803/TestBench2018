package org.usfirst.frc5803.RobotTestBench.commands;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.command.Command;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.RobotMap;
import org.usfirst.frc5803.RobotTestBench.utils.SrxTrajectoryImporter;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//import org.usfirst.frc5803.RobotTestBench.models.BobTalonSRX;
import org.usfirst.frc5803.RobotTestBench.models.SrxMotionProfile;
import org.usfirst.frc5803.RobotTestBench.models.SrxTrajectory;

public class FollowTrajectory extends Command {

	private TalonSRX rightTalon = RobotMap.driveTrainDriveTrainR1;
	private TalonSRX leftTalon = RobotMap.driveTrainDriveTrainL1;
	
	private String trajectoryName = "";
	private int kMinPointsInTalon = 5;

	private boolean isFinished = false;

	private SrxTrajectory trajectoryToFollow = null;
	private SrxTrajectoryImporter importer = new SrxTrajectoryImporter("/home/lvuser/Autos");

	private MotionProfileStatus rightStatus = new MotionProfileStatus();
	private MotionProfileStatus leftStatus = new MotionProfileStatus();

	private boolean hasPathStarted = false;

	/**
	 * this is only either Disable, Enable, or Hold. Since we'd never want one side
	 * to be enabled while the other is disabled, we'll use the same status for both
	 * sides.
	 */
	private SetValueMotionProfile setValue = SetValueMotionProfile.Disable;

	private class BufferLoader implements java.lang.Runnable {
		private int leftLastPointSent = 0;
		private int rightLastPointSent = 0;

		public BufferLoader() {
			this.leftLastPointSent = 0;
			this.rightLastPointSent = 0;
		}

		public void run() {
			leftTalon.processMotionProfileBuffer();
			rightTalon.processMotionProfileBuffer();
			leftLastPointSent = manageBuffer(leftTalon, trajectoryToFollow.leftProfile, 0, leftLastPointSent);
			rightLastPointSent = manageBuffer(rightTalon, trajectoryToFollow.rightProfile, 0, rightLastPointSent);
		}
		
		private int manageBuffer(TalonSRX talon, SrxMotionProfile prof, int pidfSlot, int lastPointSent) {
			if (lastPointSent >= prof.numPoints) {
				return lastPointSent;
			}
			
			if (!talon.isMotionProfileTopLevelBufferFull() && lastPointSent < prof.numPoints) {
				TrajectoryPoint point = new TrajectoryPoint();
				/* for each point, fill our structure and pass it to API */
				point.position = prof.points[lastPointSent][0];
				point.velocity = prof.points[lastPointSent][1];
				point.timeDur = TrajectoryDuration.Trajectory_Duration_10ms;
				point.profileSlotSelect0 = pidfSlot;
				point.profileSlotSelect1 = pidfSlot;
				point.zeroPos = false;
				if (lastPointSent == 0) {
					point.zeroPos = true; /* set this to true on the first point */

				}
				point.isLastPoint = false;
				if ((lastPointSent + 1) == prof.numPoints) {
					point.isLastPoint = true; /* set this to true on the last point*/
				}
				ErrorCode error = talon.pushMotionProfileTrajectory(point);
				if (error != ErrorCode.OK) {
					System.out.println("WTF BBQ");
				}
				lastPointSent++;
			}			
			return lastPointSent;
		}
	}

	// Runs the runnable
	private Notifier bufferNotifier;

	// constructor
	public FollowTrajectory(String trajectoryName) {
		requires(Robot.driveTrain);
		this.trajectoryName = trajectoryName;
	}

	public FollowTrajectory(SrxTrajectory trajectoryToFollow) {
		requires(Robot.driveTrain);
		this.trajectoryToFollow = trajectoryToFollow;
	}
	
	
	// Called just before this Command runs the first time
	protected void initialize() {
		this.hasPathStarted = false;
		setUpTalon(rightTalon);
		setUpTalon(leftTalon);

		setValue = SetValueMotionProfile.Disable;

		leftTalon.set(ControlMode.MotionProfile, setValue.value);
		rightTalon.set(ControlMode.MotionProfile, setValue.value);

		if (trajectoryToFollow == null) {

			try {
				this.trajectoryToFollow = importer.importSrxTrajectory(trajectoryName);
			} catch (IOException | ParseException e) {
				System.out.println("Failed to import trajectory.");
				e.printStackTrace();
				isFinished = true;
				return;
			}
		}
		bufferNotifier = new Notifier(new BufferLoader());
		bufferNotifier.startPeriodic(.005);
		System.out.println("Trajectory Points: " + this.trajectoryToFollow.leftProfile.numPoints + "," 
		+ this.trajectoryToFollow.rightProfile.numPoints);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		rightTalon.getMotionProfileStatus(rightStatus);
		leftTalon.getMotionProfileStatus(leftStatus);

		if (rightStatus.isUnderrun || leftStatus.isUnderrun) {
			// if either MP has underrun, stop both
			System.out.println("Motion profile has underrun!");
			setValue = SetValueMotionProfile.Disable;
		} else if (rightStatus.btmBufferCnt > kMinPointsInTalon && leftStatus.btmBufferCnt > kMinPointsInTalon) {
			// if we have enough points in the talon, go.
			setValue = SetValueMotionProfile.Enable;
			hasPathStarted = true;
		} else if (rightStatus.activePointValid && rightStatus.isLast && leftStatus.activePointValid
				&& leftStatus.isLast) {
			// if both profiles are at their last points, hold the last point
			setValue = SetValueMotionProfile.Hold;
			//System.out.println("HOLDING LAST POINT!!!!!");
		}

		leftTalon.set(ControlMode.MotionProfile, setValue.value);
		rightTalon.set(ControlMode.MotionProfile, setValue.value);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (!hasPathStarted) {
			return false;
		}
		boolean leftComplete = leftStatus.activePointValid && leftStatus.isLast;
		boolean rightComplete = rightStatus.activePointValid && rightStatus.isLast;
//		System.out.println("Bottom Buffer Counts:  " + leftStatus.btmBufferCnt + "," + rightStatus.btmBufferCnt);
//		System.out.println("Top Buffer Counts:  " + leftStatus.topBufferCnt + "," + rightStatus.topBufferCnt);
//		System.out.println("Complete: " + leftComplete + "," + rightComplete);
		boolean trajectoryComplete = leftComplete && rightComplete;
		return trajectoryComplete || isFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		cleanUp();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		cleanUp();
	}
	
	public void cleanUp() {
//		System.out.println("cleaning Up...");
		resetTalon(rightTalon, ControlMode.PercentOutput, 0);
		resetTalon(leftTalon, ControlMode.PercentOutput, 0);
		bufferNotifier.stop();
	}

	// set up the talon for motion profile control
	public void setUpTalon(TalonSRX talon) {
		talon.clearMotionProfileTrajectories();
		talon.changeMotionControlFramePeriod(5);
	}

	// set the to the desired controlMode
	// used at the end of the motion profile
	public void resetTalon(TalonSRX talon, ControlMode controlMode, double setValue) {
		//System.out.println("Clearing MP Trajectories");
		talon.clearMotionProfileTrajectories();
		talon.set(ControlMode.MotionProfile, SetValueMotionProfile.Disable.value);
		talon.set(controlMode, setValue);
	}

}