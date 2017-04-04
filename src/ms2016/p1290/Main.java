package ms2016.p1290;

import java.util.Scanner;

/**
 * Created by changsheng on 2017/3/30.
 */

enum Direction {
    RIGHT,DOWN
}

class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Status {
    private Direction direction;
    private Coordinate coordinate;
    private int gridsChanged;
    public Status(Direction direction, Coordinate coordinate, int gridsChanged) {
        this.direction = direction;
        this.coordinate = coordinate;
        this.gridsChanged = gridsChanged;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getGridsChanged() {
        return gridsChanged;
    }
}

public class Main {
    private static int min = 0;
    private static char[][] map = null;
    private static int N, M;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = sc.nextLine().toCharArray();
        }

        for (min = 0; min <= N + M; min++) {
            Status init = new Status(Direction.RIGHT, new Coordinate(0,0),0);
            bfs(init);
        }

    }

    private static void bfs(Status status) {
        Coordinate currentPosition = status.getCoordinate();
        Direction currentDirection = status.getDirection();
        int gridsChanged = status.getGridsChanged();
        if (currentPosition.x == N - 1 && currentPosition.y == M - 1) {
            System.out.println(min);

            System.exit(0);
        }

//        if (currentPosition.x >= N || currentPosition.y >= M) {
//            return;
//        }

        if (gridsChanged > min) {return;}

        if (currentDirection == Direction.RIGHT) {
            if (currentPosition.y < M - 1) {
                //未到右边界
                if (map[currentPosition.x][currentPosition.y + 1] == '.') {
                    //向右非砖
                    bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x , currentPosition.y + 1), gridsChanged));

                    //右变为砖，并转向下
                    if (currentPosition.x < N - 1) {
                        if (map[currentPosition.x + 1][currentPosition.y] == '.') {//向下非砖
                            bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y), gridsChanged + 1));
                        } else {//下砖变非砖，并向下
                            bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y), gridsChanged + 2));
                        }
                    }
                } else {
                    //向右为砖
                    bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x , currentPosition.y + 1), gridsChanged + 1)); //右砖变为非砖，继续向右
                    if (currentPosition.x < N - 1) {
                        if (map[currentPosition.x + 1][currentPosition.y] == '.') {//向下非砖
                            bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y), gridsChanged));
                        } else {//下砖变非砖，并向下
                            bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y), gridsChanged + 1));
                        }
                    }
                }
            } else {
                if (currentPosition.y == M -1) {
                    //在右边界，一定可向下
                    if (map[currentPosition.x + 1][currentPosition.y] == '.') {
                        bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1 , currentPosition.y), gridsChanged)); //向下非砖
                    } else {
                        //下砖变为非砖，向下
                        bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y ), gridsChanged + 1));

                    }
                }
            }
        } else {

            /**当前为向下
             * 若未到达下边界，则可以继续向下，也可以转为向右
             * 若在下边界，则必然转为向右
             */

            if (currentPosition.x < N - 1) {
                //可继续向下
                if (map[currentPosition.x + 1][currentPosition.y] == '.') {
                    //向下非砖
                    bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y), gridsChanged));

                    //下变为砖，并向右
                    if (currentPosition.y < M - 1) {
                        if (map[currentPosition.x ][currentPosition.y + 1] == '.') {
                            //右非砖
                            bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x , currentPosition.y + 1), gridsChanged + 1));
                        } else {//右砖变非砖，并向右
                            bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x , currentPosition.y + 1), gridsChanged + 2));
                        }
                    }

                } else {
                    //向下为砖
                    bfs(new Status(Direction.DOWN, new Coordinate(currentPosition.x + 1, currentPosition.y ), gridsChanged + 1)); //下砖变为非砖，继续向下
                    if (currentPosition.y < M - 1) {
                        if (map[currentPosition.x][currentPosition.y + 1] == '.') {//向右非砖
                            bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x, currentPosition.y + 1), gridsChanged));
                        } else {//右砖变非砖，并向右
                            bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x, currentPosition.y + 1), gridsChanged + 1));
                        }
                    }
                }

            } else {
                if (currentPosition.x == N - 1) {
                    //在下边界，一定可向右
                    if (map[currentPosition.x][currentPosition.y + 1] == '.') {
                        bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x  , currentPosition.y + 1), gridsChanged)); //向右非砖
                    } else {
                        //右砖变为非砖，向右
                        bfs(new Status(Direction.RIGHT, new Coordinate(currentPosition.x , currentPosition.y + 1 ), gridsChanged + 1));

                    }

                }
            }


        }

    }
}
