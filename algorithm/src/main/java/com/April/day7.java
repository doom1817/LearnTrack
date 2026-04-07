package com.April;

/**
 * @author: doom
 * @date: 2026/04/07/16:26
 * @description:
 *  力扣 2069.模拟行走机器人
 */
public class day7 {

    public static void main(String[] args) {
        Robot robot = new Robot(6,3);// 初始化网格图，机器人在 (0, 0) ，朝东。
        robot.step(2);  // 机器人朝东移动 2 步，到达 (2, 0) ，并朝东。
        robot.step(2);  // 机器人朝东移动 2 步，到达 (4, 0) ，并朝东。
        robot.getPos(); // 返回 [4, 0]
        robot.getDir(); // 返回 "East"
        robot.step(2);  // 朝东移动 1 步到达 (5, 0) ，并朝东。
        // 下一步继续往东移动将出界，所以逆时针转变方向朝北。
        // 然后，往北移动 1 步到达 (5, 1) ，并朝北。
        robot.step(1);  // 朝北移动 1 步到达 (5, 2) ，并朝 北 （不是朝西）。
        robot.step(4);  // 下一步继续往北移动将出界，所以逆时针转变方向朝西。
        // 然后，移动 4 步到 (1, 2) ，并朝西。
        robot.getPos(); // 返回 [1, 2]
        robot.getDir(); // 返回 "West"
    }

}
/**
 * 原创
 * */
//class Robot {
//    int width,height; // 网格大小
//    int x,y; //机器人位置
//    int dir ; // 当前方向索引
//    int perimeter; // 机器人周长
//    int[][] dirs = {
//            {1, 0},   // East
//            {0, 1},   // North
//            {-1, 0},  // West
//            {0, -1}   // South
//    };
//    String[] dirNames = {"East", "North", "West", "South"};
//
//    public Robot(int width,int height) {
//        this.width = width;
//        this.height = height;
//        this.x = 0;
//        this.y = 0;
//        this.dir = 0; // 初始化朝东
//        this.perimeter = 2 * (width + height) - 4; // 机器人周长
//    }
//    public void step(int num) {
//        num %=perimeter;
//        if (num==0){
//            num = perimeter;
//        }
//        while (num>0){
//            int nx = x + dirs[dir][0];
//            int ny = y + dirs[dir][1];
//            if (nx < 0 || nx >= width || ny < 0 || ny >= height){
//                dir = (dir + 1) % 4; // 逆时针转
//            }else {
//                x = nx;
//                y = ny;
//                num--;
//            }
//        }
//    }
//    public int[] getPos() {
//        return new int[]{x, y};
//    }
//
//    public String getDir() {
//        return dirNames[dir];
//    }
//}
/**
 * 最佳解
 **/
class Robot {
    private int w, h, s;

    public Robot(int width, int height) {
        w = width;
        h = height;
        s = 0;
    }

    public void step(int num) {
        // 由于机器人只能走外圈，那么走 (w+h-2)*2 步后会回到起点
        // 把 s 取模调整到 [1, (w+h-2)*2]，这样不需要特判 s == 0 时的方向
        s = (s + num - 1) % ((w + h - 2) * 2) + 1;
    }

    public int[] getPos() {
        Object[] t = getState();
        return new int[]{(int) t[0], (int) t[1]};
    }

    public String getDir() {
        Object[] t = getState();
        return (String) t[2];
    }

    private Object[] getState() {
        if (s < w) { //底边向东 (s: 0 ~ w-1)
            return new Object[]{s, 0, "East"};
        } else if (s < w + h - 1) { //右边向北 (s: w ~ w+h-2)
            return new Object[]{w - 1, s - w + 1, "North"};
        } else if (s < w * 2 + h - 2) { //顶边向西 (s: w+h-1 ~ w+h+w-3)
            return new Object[]{w * 2 + h - s - 3, h - 1, "West"};
        } else { //左边向南 (s: w+h+w-2 ~ (w+h-2)*2)
            return new Object[]{0, (w + h) * 2 - s - 4, "South"};
        }
    }
}

