package com.hot.graphTheory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: doom
 * @date: 2026/03/10/22:26
 * @description:
 * 力扣207. 课程表
 */
public class title3 {
    public static void main(String[] args) {
        System.out.println(canFinish( 2, new int[][]{{1,0}}));
        System.out.println(canFinish(2, new int[][]{{1,0},{0,1}}));
    }
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        int[] isDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            graph.get(preCourse).add(course); //先修课程指向需要学习的课程
            isDegree[course]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++){
            if(isDegree[i] == 0){
                queue.offer(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()){
            int course = queue.poll();
            count++;
            for (int nextCourse : graph.get(course)){
                isDegree[nextCourse]--;
                if (isDegree[nextCourse] == 0){
                    queue.offer(nextCourse);
                }
            }
        }
        return count == numCourses;
    }
}
