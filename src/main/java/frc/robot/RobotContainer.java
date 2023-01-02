// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Autos.AutoIntakeDriveOutake;
import frc.robot.commands.FwdDrivePIDCmd;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.ShootCmd;
import frc.robot.commands.TankDriveCommand;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final IntakeSubsystem intakeSub = new IntakeSubsystem();
  private final ShooterSubsystem shooterSub = new ShooterSubsystem();
  private final DriveSub driveSub = new DriveSub();

  // Intake Command - can intake and release. More consize this way. Maybe add
  // which ones to move.
  private final IntakeCmd intakeForward = new IntakeCmd((intakeSub), true);
  private final IntakeCmd intakeBackward = new IntakeCmd((intakeSub), false);
  private final ShootCmd shootBalls = new ShootCmd(intakeSub, shooterSub, -0.55);
  private final TankDriveCommand tankDrive = new TankDriveCommand(driveSub);
  // private final ArcadeDriveCommand arcadeDrive = new
  // ArcadeDriveCommand(driveSub);
  private final FwdDrivePIDCmd PIDForward = new FwdDrivePIDCmd(driveSub, -32);
  private final AutoIntakeDriveOutake autoDriveFowardIntakeOutake = new AutoIntakeDriveOutake(PIDForward, intakeForward, intakeBackward, new WaitCommand(2));
  private final SequentialCommandGroup fowardIntakeAndOutake = new SequentialCommandGroup(
      new ParallelRaceGroup(new IntakeCmd((intakeSub), true), PIDForward),
      new IntakeCmd(intakeSub, false));

  // private final ExampleCommand m_autoCommand = new
  // ExampleCommand(m_exampleSubsystem);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // CommandScheduler.getInstance().setDefaultCommand(driveSub, arcadeDrive);
    CommandScheduler.getInstance().setDefaultCommand(driveSub, tankDrive);
    OI.button2Aux.whileHeld(intakeBackward);
    OI.button5Aux.whileHeld(intakeForward);
    OI.triggerAux.whenPressed(shootBalls);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoDriveFowardIntakeOutake; // Remember to press control + s to save! You can see the circle on the top
  }
}
