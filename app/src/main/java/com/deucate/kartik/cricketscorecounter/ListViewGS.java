package com.deucate.kartik.cricketscorecounter;

class ListViewGS {

    private String mTeam1;
    private String mTeam2;
    private String mDate;
    private int mOver;

   // public ListViewGS() {    }

    ListViewGS(String team1, String team2, String date, int over) {
        mTeam1 = team1;
        mTeam2 = team2;
        mDate = date;
        mOver = over;
    }

    String getTeam1() {        return mTeam1;    }

 //   public void setTeam1(String team1) {        mTeam1 = team1;    }

    String getTeam2() {
        return mTeam2;
    }

  //  public void setTeam2(String team2) {        mTeam2 = team2;    }

    public String getDate() {
        return mDate;
    }

 //   public void setDate(String date) {        mDate = date;    }

    public int getOver() {
        return mOver;
    }

  //  public void setOver(int over) {        mOver = over;    }
}
