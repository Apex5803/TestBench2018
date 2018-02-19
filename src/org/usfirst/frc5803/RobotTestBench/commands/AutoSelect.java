package org.usfirst.frc5803.RobotTestBench.commands;


import org.usfirst.frc5803.RobotTestBench.Robot;

import org.usfirst.frc5803.RobotTestBench.commands.autonomous.CommandB;
import org.usfirst.frc5803.RobotTestBench.commands.autonomous.CommandC;
import org.usfirst.frc5803.RobotTestBench.commands.autonomous.CommandD;
import org.usfirst.frc5803.RobotTestBench.commands.autonomous.CommandE;
import org.usfirst.frc5803.RobotTestBench.commands.autonomous.CommandF;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutoSelect {
	@SuppressWarnings("rawtypes")
	public static SendableChooser autonChooser;
	public static String AutoName = "Center: Backpack";
	public static int AutoNumber = 1;
	public static boolean isRight = true;
	static Command AutonCommand =  new CommandA();
	

    public static void init() {
    	selectAuto();
    	if (SmartDashboard.getBoolean("Autonomous ENABLED", true)){
    			AutonCommand.start();
    	}
    	//System.out.println("Auto Init is working");
    	//Robot.compressor.stop();
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
    	//System.out.println("Auto is working");
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
        
        //#IGiveUpOnLife
        //#AutoIsAnnoying
        //#Steven
        //#WhyCan'tProblemsBeSolvedByStarringAtThem
        //idk
    	//Robot.compressor.setClosedLoopControl(true);
    }
    
    public static void selectAuto(){
    	//System.out.println("is this working yet; "+AutoNumber);
    	//System.out.println("get number; "+(int)Robot.prefs.getNumber("Auto Selector", 4));
    	//System.out.println("get boolean"+Robot.prefs.getBoolean("1A: On Right Side", false));
         //if (AutoNumber != (int) Robot.prefs.getNumber("Auto Selector", 4)) 
    	{
    		System.out.println("please work");
	    	//AutoNumber = (int) Robot.prefs.getNumber("Auto Selector", 2);
	    	System.out.println("Autonumber: "+AutoNumber);
	    	//isRight = Robot.prefs.getBoolean("1A: On Right Side", false);
	    	switch(AutoNumber){
	    		case(1):{
	    			AutoName = "Center: Backpack";
	    			AutonCommand = new CommandF();
	    			break;
	    		}
	    		case(2):{
	    			AutoName = "Side Peg: BackPack";
	    			AutonCommand = new CommandC();
	    			break;
	    		}
	    		case(3):{
	    			AutoName = "Fire Balls Than Side Peg: Backpack";
	    			AutonCommand = new CommandA();
	    			break;
	    		}
	    		case(4):{
	    			AutoName = "Center: Ground Intake";
	    			AutonCommand = new CommandB();
	    			break;
	    		}
	    		case(5):{
	    			AutoName = "Drive Straight";
	    			AutonCommand = new CommandD();
	    			break;
	    		}
	    		case(6):{
	    			AutoName = "Hopper";
	    			AutonCommand = new CommandE();
	    			break;
	    		}
	    		
	    		default:{
	    			AutoName = "Center: Backpack";
	    			AutonCommand = new CommandA();
	    			break;
	    			
	    		}
	    	}
    	}
    //}
    
    /*
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void createChooser(){
    	autonChooser = new SendableChooser();
    	
    	autonChooser.addDefault("Score Gear", new CurrentStopGearAuto());
    	autonChooser.addObject("Drive Distance", new DriveDistance(10,2));
    	autonChooser.addObject("Drive Straight", new CurrentStopGearAuto(false));
    	autonChooser.addObject("Fire 10 then Gear RED", new Fire10BallsAndGear(true));
    	autonChooser.addObject("Fire 10 then Gear BLUE", new Fire10BallsAndGear(false));
    	autonChooser.addObject("Fire 10 ONLY Red", new Fire10Balls(true));
    	autonChooser.addObject("Fire 10 ONLY Blue", new Fire10Balls(false));
    	
    	autonChooser.addObject("Side Gear Right Far", new SideGearAuto(true, false));
    	autonChooser.addObject("Side Gear Right Close", new SideGearAuto(true, true));
    	autonChooser.addObject("Side Gear Left Far", new SideGearAuto(false, false));
    	autonChooser.addObject("Side Gear Left Close", new SideGearAuto(false, true));
    	SmartDashboard.putData("AutonChooser", new SendableChooser());
    	SmartDashboard.putData("AutonChooser", autonChooser);
    }
    */
}}