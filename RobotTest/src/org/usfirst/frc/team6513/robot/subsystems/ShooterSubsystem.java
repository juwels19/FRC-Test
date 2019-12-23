package org.usfirst.frc.team6513.robot.subsystems;

import org.usfirst.frc.team6513.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;

public class ShooterSubsystem {
	
	Spark shooterMotor1 = new Spark(RobotMap.SHOOTER_MOTOR_1);
	Spark shooterMotor2 = new Spark(RobotMap.SHOOTER_MOTOR_2);
	
	Encoder shooterEncoder = new Encoder(RobotMap.ENCODER_PORT_7, RobotMap.ENCODER_PORT_8);
	
	public ShooterSubsystem() {
		reset();
	}
	
	
	
	public void reset() {
		shooterEncoder.reset();
	}
}
