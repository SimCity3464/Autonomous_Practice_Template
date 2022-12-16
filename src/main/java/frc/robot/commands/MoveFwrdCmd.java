// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;S
import frc.robot.subsystems.DriveSub;

public class MoveFwrdCmd extends CommandBase {
  /** Creates a new AutoMoveCmd. */
  private final DriveSub driveSubsystem;
  private double distance;
  private double targetDistance;

  public MoveFwrdCmd(DriveSub driveSub, double targetDistance) {
    driveSubsystem = driveSub;
    distance = targetDistance; // Target distance in feet
    // tickDistance = targetDistance;  // Convert the feet we want to go to ticks the encoder can read. 
    addRequirements(driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveSubsystem.resetEncoders(); //Reset the encoders
    targetDistance = driveSubsystem.getForwardDistance() + distance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // SmartDashboard.putBooleanArray(, value)
    driveSubsystem.driveTank(-0.5, -0.5); //Drive at half speed 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // say, -2 , -6
    if(driveSubsystem.getForwardDistance() < targetDistance){
      return true;
    }else{
      return false;
    }
  }
}
