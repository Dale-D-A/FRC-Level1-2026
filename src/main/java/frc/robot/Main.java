package frc.robot;
import edu.wpi.first.wpilibj.RobotBase;
/** 程序入口：启动 WPILib 管理的 Robot 生命周期，通常不修改。 */
public final class Main {
  private Main() {}
  public static void main(String... args) { RobotBase.startRobot(Robot::new); }
}
