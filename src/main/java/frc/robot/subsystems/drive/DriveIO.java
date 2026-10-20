package frc.robot.subsystems.drive;
/** IO 是硬件契约：读取状态与设置输出。Subsystem 无需知道电机品牌。 */
public interface DriveIO {
  class Inputs {
    public double leftOutput, rightOutput; // 归一化请求值，不是实测电压
    public double leftMotorRps, rightMotorRps; // 电机转每秒，不是轮速 m/s
  }
  void updateInputs(Inputs inputs);
  void setOutputs(double left, double right);
  default void stop() { setOutputs(0, 0); }
}
