package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
/** 生命周期总入口。模式在这里切换，具体动作交给 Command。 */
public class Robot extends TimedRobot {
  private RobotContainer container;
  private Command autonomousCommand;
  @Override public void robotInit() {
    // 普通 WPILib 日志，不依赖 AdvantageKit；可用 AdvantageScope 打开 wpilog。
    DataLogManager.start();
    DriverStation.startDataLog(DataLogManager.getLog());
    container = new RobotContainer();
  }
  @Override public void robotPeriodic() {
    // 通常约每 20 ms：更新 Subsystem、检查按钮、执行 Command。
    CommandScheduler.getInstance().run();
  }
  @Override public void autonomousInit() {
    autonomousCommand = container.getAutonomousCommand();
    if (autonomousCommand != null) CommandScheduler.getInstance().schedule(autonomousCommand);
  }
  @Override public void teleopInit() {
    if (autonomousCommand != null) autonomousCommand.cancel();
    container.stopAll();
  }
  @Override public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
    container.stopAll();
  }
  @Override public void testInit() {
    CommandScheduler.getInstance().cancelAll();
    container.stopAll();
  }
}
