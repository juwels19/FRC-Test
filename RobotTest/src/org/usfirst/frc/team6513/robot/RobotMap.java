package org.usfirst.frc.team6513.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Constants
	public static final double DEADBAND = 0.05;
	public static final double TICKS_PER_INCH = 100;
	public static final double ELEVATOR_SPEED = 0.6;
	public static final double INTAKE_SPEED = 0.7;
	public static final double HOLD_ELEMENT = 0.3;
	public static final double ELEVATOR_KP = 0.0;
	public static final double ELEVATOR_KI = 0.0;
	public static final double ELEVATOR_KD = 0.0;
	public static final double ELEVAOTR_KF = 0.0;
	public static final double DRIVE_KP = 0.0;
	public static final double DRIVE_KI = 0.0;
	public static final double DRIVE_KD = 0.0;
	public static final double DRIVE_KF = 0.0;
	public static final double ROTATION_KP = 0.0;
	public static final double DRIVE_THROTTLE_KP = 0.0;
	public static final double DRIVE_YAW_KP = 0.0;
	public static final double AUTO_DRIVE_SPEED_LIMIT = 0.0;
	public static final double AUTO_DRIVE_ANGLE_THRESHOLD = 0.0;
	public static final double VISION_TARGET_HEIGHT = 0.0;
	public static final double VISION_CAMERA_HEIGHT = 0.0;
	public static final double VISION_CAMERA_ANGLE = 0.0;
	public static final double VISION_DISTANCE_FROM_TARGET = 0.0;

	// Drive Motors
	public static final int FRONT_LEFT_DRIVE_PORT = 0; 
	public static final int REAR_LEFT_DRIVE_PORT = 1;
	public static final int FRONT_RIGHT_DRIVE_PORT = 2;
	public static final int REAR_RIGHT_DRIVE_PORT = 3;
	
	// Intake Motors
	public static final int INTAKE_MOTOR_1 = 4;
	public static final int INTAKE_MOTOR_2 = 5;
	// add more if needed
	
	// Elevator Motors
	public static final int ELEVATOR_MOTOR_1 = 6;
	public static final int ELEVATOR_MOTOR_2 = 7;
	// add more if needed
	
	// Led Driver Port
	public static final int BLINKIN_DRIVER = 8;

	// Joysticks
	public static final int DRIVER_STICK = 0;
	public static final int OPERATOR_STICK = 1;
	
	// Joystick Axes
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int LEFT_TRIGGER = 2;
	public static final int RIGHT_TRIGGER = 3;
	public static final int RIGHT_STICK_X = 4;
	public static final int RIGHT_STICK_Y = 5;
	
	// Joystick D-Pad in degrees
	public static final int DPAD_UP = 0;
	public static final int DPAD_RIGHT = 90;
	public static final int DPAD_DOWN = 180;
	public static final int DPAD_LEFT = 270;
	
	
	// Joystick Buttons
	public static final int A = 1;
	public static final int B = 2;
	public static final int X = 3;
	public static final int Y = 4;
	public static final int LB = 5;
	public static final int RB = 6;
	public static final int LOGO_LEFT = 7;
	public static final int LOGO_RIGHT = 8;
	public static final int LEFT_STICK_BUTTON = 9;
	public static final int RIGHT_STICK_BUTTON = 10;
	
	// Solenoid Ports
	public static final int SOLENOID_PORT_1 = 0;
	public static final int SOLENOID_PORT_2 = 1;
	public static final int SOLENOID_PORT_3 = 2;
	public static final int SOLENOID_PORT_4 = 3;
	public static final int SOLENOID_PORT_5 = 4;
	public static final int SOLENOID_PORT_6 = 5;
	public static final int SOLENOID_PORT_7 = 6;
	public static final int SOLENOID_PORT_8 = 7;
	
	// Sensors
	public static final int ENCODER_PORT_1 = 0;
	public static final int ENCODER_PORT_2 = 1;
	public static final int ENCODER_PORT_3 = 2;
	public static final int ENCODER_PORT_4 = 3;
	public static final int ENCODER_PORT_5 = 4;
	public static final int ENCODER_PORT_6 = 5;
	
	// Elevator Preset Heights in Inches
	public static final double TOP = 24.0;
	public static final double MID = 12.0;
	public static final double BOTTOM = 0.0;
	
}
