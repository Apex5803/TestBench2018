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
import com.ctre.phoenix.motorcontrol.ControlMode;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static WPI_TalonSRX driveTrainDriveTrainL1;
    public static WPI_VictorSPX driveTrainDriveTrainL2;
    public static WPI_VictorSPX driveTrainDriveTrainL3;
    public static WPI_TalonSRX driveTrainDriveTrainR1;
    public static WPI_VictorSPX driveTrainDriveTrainR2;
    public static WPI_VictorSPX driveTrainDriveTrainR3;
    public static DifferentialDrive joystickControl;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrainDriveTrainL1 = new WPI_TalonSRX(PortMap.PORT_6);
        driveTrainDriveTrainL1.setInverted(true);
        
        driveTrainDriveTrainL2 = new WPI_VictorSPX(PortMap.PORT_5);
      //  driveTrainDriveTrainL2.setInverted(true);
        driveTrainDriveTrainL2.set(ControlMode.Follower, driveTrainDriveTrainL1.getDeviceID());
        
        driveTrainDriveTrainL3 = new WPI_VictorSPX(PortMap.PORT_4);
       // driveTrainDriveTrainL3.setInverted(true);
        driveTrainDriveTrainL3.set(ControlMode.Follower, driveTrainDriveTrainL1.getDeviceID());
        
        driveTrainDriveTrainR1 = new WPI_TalonSRX(PortMap.PORT_3);
        
        
        driveTrainDriveTrainR2 = new WPI_VictorSPX(PortMap.PORT_2);
        driveTrainDriveTrainR2.set(ControlMode.Follower, driveTrainDriveTrainR1.getDeviceID());
        
        driveTrainDriveTrainR3 = new WPI_VictorSPX(PortMap.PORT_1);
        driveTrainDriveTrainR3.set(ControlMode.Follower, driveTrainDriveTrainR1.getDeviceID());
        
        joystickControl = new DifferentialDrive(driveTrainDriveTrainL1, driveTrainDriveTrainR1);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}