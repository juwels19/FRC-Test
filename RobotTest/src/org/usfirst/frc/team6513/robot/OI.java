package org.usfirst.frc.team6513.robot;

import org.usfirst.frc.team6513.robot.commands.ElevatorMoveToBottom;
import org.usfirst.frc.team6513.robot.commands.ElevatorMoveToMid;
import org.usfirst.frc.team6513.robot.commands.ElevatorMoveToTop;
import org.usfirst.frc.team6513.robot.commands.ExtendCommand;
import org.usfirst.frc.team6513.robot.commands.IntakeCommand;
import org.usfirst.frc.team6513.robot.commands.OuttakeCommand;
import org.usfirst.frc.team6513.robot.commands.RetractCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {
	
	public Joystick driverStick = new Joystick(RobotMap.DRIVER_STICK);
	public Joystick operatorStick = new Joystick(RobotMap.OPERATOR_STICK);
	
	JoystickButton intake = new JoystickButton(driverStick, RobotMap.A);
	JoystickButton outtake = new JoystickButton(driverStick, RobotMap.B);
	JoystickButton extend = new JoystickButton(driverStick, RobotMap.X);
	JoystickButton retract = new JoystickButton(driverStick, RobotMap.Y);
	JoystickButton elevatorBottom = new JoystickButton(operatorStick, RobotMap.A);
	JoystickButton elevatorMid = new JoystickButton(operatorStick, RobotMap.X);
	JoystickButton elevatorTop = new JoystickButton(operatorStick, RobotMap.Y);

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	
	public OI() {
		intake.whileHeld(new IntakeCommand());
		outtake.whileHeld(new OuttakeCommand());
		extend.whenPressed(new ExtendCommand());
		retract.whenPressed(new RetractCommand());
		elevatorBottom.whenPressed(new ElevatorMoveToBottom());
		elevatorMid.whenPressed(new ElevatorMoveToMid());
		elevatorTop.whenPressed(new ElevatorMoveToTop());
	
		
	}
	
}
