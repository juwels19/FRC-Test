package org.usfirst.frc.team6513.robot;

import org.usfirst.frc.team6513.robot.commands.DriveHalfJoystick;
import org.usfirst.frc.team6513.robot.commands.DriveJoystick;
import org.usfirst.frc.team6513.robot.commands.ElevatorMoveJoystick;
import org.usfirst.frc.team6513.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team6513.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team6513.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team6513.robot.subsystems.LEDSubsystem;
import org.usfirst.frc.team6513.robot.subsystems.PowerSubsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;


public class Robot extends TimedRobot {
	public static OI oi;
	public static DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
	public static ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
	public static PowerSubsystem powerSubsystem = new PowerSubsystem();
	public static LEDSubsystem led = new LEDSubsystem();
	public static NetworkTable limeLight = NetworkTableInstance.getDefault().getTable("limelight");
	
	Compressor compressor = new Compressor();
	DriverStation ds = DriverStation.getInstance();
	
	String autoMode = "";
	
	// limelight variables
	boolean targetAquired = false;
	double limelightThrottle = 0.0;
	double limelightYaw = 0.0;
	double distanceFromTarget = 0.0;

	@Override
	public void robotInit() {
		oi = new OI();
		// start compressor when robot first turns on, can be turned on in teleop if need be
		compressor.start();
	}


	@Override
	public void disabledInit() {
		led.robotDisabled();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if (oi.operatorStick.getRawButton(RobotMap.A)) {
			autoMode = "___";
			// puts the auto mode chosen to the driverstation in the dialog box
			DriverStation.reportWarning("Auto mode chosen: " + autoMode, false);
			// set auto mode here
		} // add more cases depending on # of auto modes
	}

	@Override
	public void autonomousInit() {
		// this is a safety net to ensure an auto mode is always seleceted
		if (autoMode.equals("")) {
			// default auto mode is usually just a drive forward routine
			autoMode = "DEFAULT";
		}
		
		led.autoRunning();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		if (autoMode.equals("____")) {
			// call whatever auto mode corresponds to the string
		} // add more cases depending on # of auto modes
	}

	@Override
	public void teleopInit() {
		// starts the normal driving command once teleop begins
		new DriveJoystick();
		led.teleopStarted();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		new DriveJoystick();
		limelightTracking();
		
		// Checks if the trigger is held to activate slower drive control
		if (oi.driverStick.getRawAxis(RobotMap.LEFT_TRIGGER) > 0.5) {
			new DriveHalfJoystick();
		} else {
			new DriveJoystick();
		}
		
		// Check if operator wants to move the elevator manually
		if (oi.operatorStick.getRawAxis(RobotMap.LEFT_STICK_Y) > RobotMap.DEADBAND) {
			new ElevatorMoveJoystick();
		}
		
		// Driver hold LB to auto drive and auto aim to the vision target
		if (oi.driverStick.getRawButton(RobotMap.LB)) {
			if (targetAquired) {
				driveSubsystem.driveAuto(limelightThrottle, limelightYaw);
			} else {
				new DriveJoystick();
			}
		} else  {
			new DriveJoystick();
		}
		
		// Check for remaining match time to trigger led
		if (ds.getMatchTime() == 40.0) {
			led.endGameWarning();
		}
	}

	@Override
	public void testPeriodic() {
		// not usually used
	}
	
	public void limelightTracking() {
		// check if targetX is +ve when overshot to the right and -ve to the left
		// check if targetY is +ve when overshot to the top and -ve to the bottom
		double targetX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0); // x distance from target
		double targetY = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0); // y distance from target
		double targetV = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0.0); // does limelight have target?
		// double targetA = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0.0); // % of target area
		
		double currentDistance = getDistanceFromTarget(targetY);
		
		// if statement checks if limelight has a valid target
		// no target aquired has a tv value of 0.0
		if (targetV < 1.0) {
			targetAquired = false;
			return;
		}
		
		// only reaches this point if the limelight has a valid target
		targetAquired = true;
		
		// control for yaw (turning bot to set position)
		limelightYaw = targetX * RobotMap.DRIVE_YAW_KP;
		
		// control for throttle (moving bot to set position)
		limelightThrottle = (RobotMap.VISION_DISTANCE_FROM_TARGET - currentDistance) * RobotMap.DRIVE_THROTTLE_KP;
		
		// set speed limits on auto drive to target distance
		if (limelightThrottle > RobotMap.AUTO_DRIVE_SPEED_LIMIT && limelightThrottle > 0.0) {
			limelightThrottle = RobotMap.AUTO_DRIVE_SPEED_LIMIT;
		}
		
		if (limelightThrottle < RobotMap.AUTO_DRIVE_SPEED_LIMIT && limelightThrottle < 0.0) {
			limelightThrottle = -RobotMap.AUTO_DRIVE_SPEED_LIMIT;
		}
		
	}
	
	public double getDistanceFromTarget(double targetVerticalOffset) {
		// uses pythagorean to calculate the current distance from the vision target when camera is at an angle
		return (RobotMap.VISION_TARGET_HEIGHT - RobotMap.VISION_CAMERA_HEIGHT) / Math.tan(RobotMap.VISION_CAMERA_ANGLE + targetVerticalOffset);
	}
}
