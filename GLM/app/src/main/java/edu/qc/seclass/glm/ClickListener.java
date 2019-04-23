package edu.qc.seclass.glm;

/**
 * @author      Mark Abramov <markabramov01@gmail.com>
 *
 * @version     1.0
 * @since       1.0
 */

public interface ClickListener {
    void onPositionClicked(int position);
    void onLongClicked(int position);
    void switchActivities(int position);
}