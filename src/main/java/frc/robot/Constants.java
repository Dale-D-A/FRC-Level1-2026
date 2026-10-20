package frc.robot;
/** 集中保存端口和教学参数。示例 CAN ID 不代表你们当前实车接线。 */
public final class Constants {
  private Constants() {}
  public static final int DRIVER_PORT = 0; // DS USB Devices 的第 0 槽
  public static final double DEADBAND = 0.08;
  public static final double DRIVE_LIMIT = 0.25; // 第一课只展示低输出
  public static final double LOOP_SECONDS = 0.02;
  // 默认只允许桌面模拟。改为 true 前必须核对实车 ID、方向和限流。
  public static final boolean ALLOW_REAL_HARDWARE = false;
  public static final int LEFT_LEADER = 0, LEFT_FOLLOWER = 2;
  public static final int RIGHT_LEADER = 1, RIGHT_FOLLOWER = 3;
  public static final int INTAKE_ID = 4, SHOOTER_ID = 7;
  public static final double STATOR_LIMIT_AMPS = 30.0; // 教学起始值，需按机构评审
}
