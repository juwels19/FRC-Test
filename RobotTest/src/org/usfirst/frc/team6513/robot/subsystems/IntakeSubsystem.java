package org.usfirst.frc.team6513.robot.subsystems;

import org.usfirst.frc.team6513.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {

	DoubleSolenoid leftCarriage = new DoubleSolenoid(RobotMap.SOLENOID_PORT_1, RobotMap.SOLENOID_PORT_2);
	DoubleSolenoid rightCarriage = new DoubleSolenoid(RobotMap.SOLENOID_PORT_3, RobotMap.SOLENOID_PORT_4);
	
	Spark leftIntake = new Spark(RobotMap.INTAKE_MOTOR_1);
	Spark rightIntake = new Spark(RobotMap.INTAKE_MOTOR_2);
	
	// Add sensor for detecting if game piece is in the intake
	
	public IntakeSubsystem() {
		// invert motors if needed
		solenoidsOff();
		intakeOff();
	}
		
	public void intake() {
		leftIntake.set(RobotMap.INTAKE_SPEED);
		rightIntake.set(-RobotMap.INTAKE_SPEED);
	}
	
	public void intakeWithObject() {
		leftIntake.set(RobotMap.HOLD_ELEMENT);
		rightIntake.set(-RobotMap.HOLD_ELEMENT);
	}
	
	public void outtake() {
		leftIntake.set(-RobotMap.INTAKE_SPEED);
		rightIntake.set(RobotMap.INTAKE_SPEED);
	}
	
	public void extend() {
		leftCarriage.set(DoubleSolenoid.Value.kForward);
		rightCarriage.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retract() {
		leftCarriage.set(DoubleSolenoid.Value.kReverse);
		rightCarriage.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void solenoidsOff() {
		leftCarriage.set(DoubleSolenoid.Value.kOff);
		rightCarriage.set(DoubleSolenoid.Value.kOff);
	}
	
	public void intakeOff() {
		leftIntake.set(0.0);
		rightIntake.set(0.0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

