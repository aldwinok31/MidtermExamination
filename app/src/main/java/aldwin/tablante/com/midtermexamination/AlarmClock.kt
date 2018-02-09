package aldwin.tablante.com.midtermexamination

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

/**
 * Created by Bobby on 07/02/2018.
 */
class AlarmClock :AppCompatActivity() {
 lateinit var am:AlarmManager
    lateinit var tp:TimePicker
    lateinit var txt : TextView
    lateinit var con: Context
    lateinit var btnStop: Button
    lateinit var btnStart: Button
    lateinit var updatetxt:TextView
    lateinit var pi:PendingIntent
var hour:Int = 0
    var min:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alarm)
        this.con=thisx
        am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        tp = findViewById(R.id.timePicker) as TimePicker
        btnStart = findViewById(R.id.button2) as Button
        btnStop = findViewById(R.id.button3) as Button
        var calendar: Calendar = Calendar.getInstance()
        var myIntent:Intent = Intent(this,MyAlarm::class.java)
        updatetxt= findViewById(R.id.textView2) as TextView
        btnStart.setOnClickListener(object : View.OnClickListener{
            @SuppressLint("NewApi")
            override fun onClick(p0: View?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


                    calendar.set(Calendar.HOUR_OF_DAY,tp.hour)
                    calendar.set(Calendar.MINUTE,tp.minute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = tp.hour
                    min = tp.minute

                }
                else{




                    calendar.set(Calendar.HOUR_OF_DAY,tp.currentHour)
                    calendar.set(Calendar.MINUTE,tp.currentMinute)
                    calendar.set(Calendar.SECOND,0)
                    calendar.set(Calendar.MILLISECOND,0)
                    hour = tp.currentHour
                    min = tp.currentMinute



                }


                var hr_string: String = hour.toString()
                var min_string: String = min.toString()

                if( hour>12){
                hr_string = (hour - 12).toString()
                }
                if( min<10){
                 min_string = "0$min"
                }

                setAlarm("Alarm set To: $hr_string : $min_string " )
                myIntent.putExtra("extra","on")
                pi =PendingIntent.getBroadcast(this@AlarmClock,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)

                    am.setExact(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,pi)

            }


            private fun setAlarm(s: String) {

                updatetxt.setText(s)
            }


        })

        btnStop.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {

                updatetxt.setText("Alarm Off")
                pi =PendingIntent.getBroadcast(this@AlarmClock,0,myIntent,PendingIntent.FLAG_UPDATE_CURRENT)
                am.cancel(pi)
                myIntent.putExtra("extra","off")
                sendBroadcast(myIntent)
            }
        })

    }
}