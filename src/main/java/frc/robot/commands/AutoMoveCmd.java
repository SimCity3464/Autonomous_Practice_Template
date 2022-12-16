// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSub;

public class AutoMoveCmd extends CommandBase {
  /** Creates a new AutoMoveCmd. */
  private final DriveSub driveSubsystem;
  private double finalDistance;
  private double distance;

  public AutoMoveCmd(DriveSub driveSub, double targetDistance) {
    driveSubsystem = driveSub;
    distance = targetDistance;

    addRequirements(driveSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    finalDistance = driveSubsystem.getForward() + distance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.driveTank(0.5, 0.5); //Drive at half speed
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(driveSubsystem.getForward() > finalDistance){ //
      return true;
    }else{
      return false;
    }
  }
}
