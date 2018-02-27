// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc5803.RobotTestBench;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static TalonSRX driveTrainDriveTrainL1;
    public static VictorSPX driveTrainDriveTrainL2;
    public static VictorSPX driveTrainDriveTrainL3;
    public static TalonSRX driveTrainDriveTrainR1;
    public static VictorSPX driveTrainDriveTrainR2;
    public static VictorSPX driveTrainDriveTrainR3;
    public static TalonSRX climber;
    public static DifferentialDrive joystickControl;
    public static Compressor compressor;


    public static void init() {        
        //LEFT DRIVE TRAIN
    	driveTrainDriveTrainL1 = new TalonSRX(PortMap.PORT_6);
         driveTrainDriveTrainL1.setInverted(true);
    	/* For 2018 robot encoders */
        driveTrainDriveTrainL1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        /* For Failstorm */
       // driveTrainDriveTrainL1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        //driveTrainDriveTrainL1.configEncoderCodesPerRev(256);
        driveTrainDriveTrainL1.setSensorPhase(false); //set direction of encoder
        driveTrainDriveTrainL1.setNeutralMode(NeutralMode.Coast);
        
        driveTrainDriveTrainL2 = new VictorSPX(PortMap.PORT_5);
         driveTrainDriveTrainL2.setInverted(true);
        driveTrainDriveTrainL2.follow(driveTrainDriveTrainL1);
        driveTrainDriveTrainL2.setNeutralMode(NeutralMode.Coast);

        driveTrainDriveTrainL3 = new VictorSPX(PortMap.PORT_4);
        driveTrainDriveTrainL3.setInverted(true);
        driveTrainDriveTrainL3.follow(driveTrainDriveTrainL1);
        driveTrainDriveTrainL3.setNeutralMode(NeutralMode.Coast);

        //RIGHT DRIVE TRAIN
        driveTrainDriveTrainR1 = new TalonSRX(PortMap.PORT_3);
        /* For 2018 robot encoders */
        driveTrainDriveTrainR1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        /* For Failstorm */
        //driveTrainDriveTrainR1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        driveTrainDriveTrainR1.setSensorPhase(false); //set direction of sensor
        driveTrainDriveTrainR1.setNeutralMode(NeutralMode.Coast);
        
        driveTrainDriveTrainR2 = new VictorSPX(PortMap.PORT_2);
        driveTrainDriveTrainR2.follow(driveTrainDriveTrainR1);
        driveTrainDriveTrainR2.setNeutralMode(NeutralMode.Coast);

        driveTrainDriveTrainR3 = new VictorSPX(PortMap.PORT_1);
        driveTrainDriveTrainR3.follow(driveTrainDriveTrainR1);
        driveTrainDriveTrainR3.setNeutralMode(NeutralMode.Coast);
        
        climber = new TalonSRX(7);
        climber.setInverted(false);
        climber.setNeutralMode(NeutralMode.Coast);
        
        //joystickControl = new DifferentialDrive(driveTrainDriveTrainL1, driveTrainDriveTrainR1);
        
       // SmartDashboard.putNumber("Left Encoder Position", driveTrainDriveTrainL1.getSelectedSensorPosition(0));
       // SmartDashboard.putNumber("Left Encoder Velocity", driveTrainDriveTrainL1.getSelectedSensorPosition(0));
       // SmartDashboard.putNumber("Right Encoder Position", driveTrainDriveTrainR1.getSelectedSensorPosition(0));
       // SmartDashboard.putNumber("Right Encoder Velocity", driveTrainDriveTrainR1.getSelectedSensorPosition(0));

    }
}
