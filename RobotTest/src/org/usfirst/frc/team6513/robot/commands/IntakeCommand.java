package org.usfirst.frc.team6513.robot.commands;

import org.usfirst.frc.team6513.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCommand extends Command {

    public IntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeSubsystem);
    	requires(Robot.led);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeSubsystem.intakeOff();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeSubsystem.intake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.led.hasGamePiece();
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	// Add code for sensor to detect game piece
    	// once sensor detects game piece, return true
    	Robot.intakeSubsystem.intakeOff();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
