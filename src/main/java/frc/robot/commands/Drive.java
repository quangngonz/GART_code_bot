// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivebase;

public class Drive extends CommandBase {
  private Drivebase m_drivebase;
  private double m_speedV1;
  private double m_speedV2;
  private double m_speedV3;
  private double m_speedV4;
  
  /** Creates a new Drive. */
  public Drive(Drivebase drivebase, double angle, double rotation) {
    m_drivebase = drivebase;
    m_speedV1 = Math.sin(angle + Math.PI / 4);
    m_speedV2 = Math.cos(angle + Math.PI / 4);
    m_speedV3 = Math.sin(angle + Math.PI / 4);
    m_speedV4 = Math.cos(angle + Math.PI / 4);
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drivebase);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivebase.drive(m_speedV1, m_speedV2, m_speedV3, m_speedV4);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivebase.drive(0, 0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
