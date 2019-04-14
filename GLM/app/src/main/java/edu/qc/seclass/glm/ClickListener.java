package edu.qc.seclass.glm;

public interface ClickListener {

    void onPositionClicked(int position);

    void onLongClicked(int position);

    void switchActivities(int position);
}