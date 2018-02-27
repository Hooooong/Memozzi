package com.hooooong.memozzi.util;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Calendar;

/**
 * Created by Android Hong on 2018-02-26.
 */

public class DialogUtil {

    private static Calendar cal = Calendar.getInstance();

    /**
     * Date 를 선택하는 Dialog 호출
     *
     * @param context
     * @param listener
     * @param date
     * @return
     */
    public static DatePickerDialog showDatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener, String date) {
        DatePickerDialog dialog;

        String[] str_date = date.substring(0, 10).split("-");

        if (str_date.length == 1) {
            dialog = new DatePickerDialog(context,
                    listener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH));

        } else {
            dialog = new DatePickerDialog(context,
                    listener,
                    Integer.parseInt(str_date[0]),
                    Integer.parseInt(str_date[1]) - 1,
                    Integer.parseInt(str_date[2]));
        }

        return dialog;
    }

    public static TimePickerDialog showTimePickerDialog(Context context, TimePickerDialog.OnTimeSetListener listener, String date) {

        int hour;
        int minute;
        String[] time = date.substring(14).split(":");

        TimePickerDialog dialog;

        if (date.contains("AM")) {
            // 오전
            hour = Integer.parseInt(time[0]);
            minute = Integer.parseInt(time[1]);
        } else {
            // 오후
            hour = Integer.parseInt(time[0]) + 12;
            minute = Integer.parseInt(time[1]);
        }
        dialog = new TimePickerDialog(context, listener, hour, minute, false);

        return dialog;
    }


    /**
     * Dialog 호출하는 메소드
     *
     * @param title : 타이틀
     * @param msg : 메세지
     * @param  listener :
     */
    public static AlertDialog showBasicDialog(Context context, String title, String msg, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false) //false면 버튼을 단다는 것(다른곳을 눌러도 사라지지 않는다.), true면 반대
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

}
