package org.usfirst.frc5803.RobotTestBench.commands;

import org.usfirst.frc5803.RobotTestBench.Robot;
import org.usfirst.frc5803.RobotTestBench.commands.autonomous.AutonomousCommand;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Dashboard {
	 
		
		public static final boolean ENABLE_DASHBOARD = true;
		
		
		static final double SHORT_DELAY = .025;
	    static final double LONG_DELAY = .5;
	    
	    static double shortOldTime = 0.0;
	    static double longOldTime = 0.0;
	    
	    public static void intializeDashboard() {
	    	if(ENABLE_DASHBOARD){
	    		SmartDashboard.putBoolean("Autonomous ENABLED", true);}}
	    private static void updatePutLong(){
	    	SmartDashboard.putBoolean("Compressor", Robot.compressor.enabled());
	    	//SmartDashboard.putData(Robot.gearIntake);
			SmartDashboard.putString("*Auto Command*", AutoSelect.AutoName); // Displays which auto should run
			SmartDashboard.putBoolean("Auton on Right?", AutoSelect.isRight);
	    }

	    public static void updateDashboard() {
	    	if (ENABLE_DASHBOARD) {
	            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
	                shortOldTime = Timer.getFPGATimestamp();
	                //updatePutShort();
	            }
	            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
	                //Thing that should be updated every LONG_DELAY
	                longOldTime = Timer.getFPGATimestamp();
	                updatePutLong();
	            }
	        }
	    }
	}

