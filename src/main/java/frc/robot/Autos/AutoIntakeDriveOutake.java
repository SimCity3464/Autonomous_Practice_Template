// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FwdDrivePIDCmd;
import frc.robot.commands.IntakeCmd;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoIntakeDriveOutake extends SequentialCommandGroup {
  /** Creates a new AutoIntakeDriveOutake. */
  public AutoIntakeDriveOutake(FwdDrivePIDCmd driveCommand, IntakeCmd intakeCommand, IntakeCmd outakeCommand, WaitCommand delayTwoSeconds) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new ParallelRaceGroup(driveCommand, intakeCommand), delayTwoSeconds, new ParallelRaceGroup(outakeCommand, delayTwoSeconds));
  }
}
