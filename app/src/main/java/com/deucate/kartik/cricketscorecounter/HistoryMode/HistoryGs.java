package com.deucate.kartik.cricketscorecounter.HistoryMode;

public class HistoryGs {

    private int mBall;
    private String mMove;

     int getBall() {
        return mBall;
    }

 //   public void setBall(int ball) {        mBall = ball;    }

     String getMove() {
        return mMove;
    }

  //  public void setMove(String move) {        mMove = move;    }

//    public HistoryGs() {    }

    public HistoryGs(int ball, String move) {
        mBall = ball;
        mMove = move;
    }
}
