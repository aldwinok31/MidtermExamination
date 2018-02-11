package aldwin.tablante.com.midtermexamination

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.widget.*
import org.w3c.dom.Text
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
var txt1 : TextView? = null
var bool:Boolean = true
var nTimer : Timer? = null
    var saver : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

        var nBut1 = findViewById<Button>(R.id.button) as Button
        var but1 : ToggleButton = findViewById<ToggleButton>(R.id.TogBut1) as ToggleButton

        var txtv1 = findViewById<TextView>(R.id.textView) as TextView

        txt1 = txtv1



nBut1.setOnClickListener{
    var intent:Intent = Intent(this,AlarmClock::class.java)
    startActivity(intent)

}




but1.setOnClickListener {

    if(bool) {
        var mTimer: Timer = Timer()
        val edt1: EditText = findViewById<EditText>(R.id.edt1) as EditText
        val edt:EditText = findViewById(R.id.edt) as EditText
        val edt2:EditText = findViewById(R.id.edt2) as EditText
        var y : Int = edt2.text.toString().toInt()
        var z : Int = edt.text.toString().toInt()
        var x : Int = edt1.text.toString().toInt()

        mTimer.execute(x,z,y)
  nTimer = mTimer
        bool = false
    }
    else{
        nTimer!!.cancel(true)
        nTimer = null

bool = true

    }

}


    }
    fun stringformat(vararg string : String){
        var strings = string[0]
        var strings2 = string[1]
        var strings3 = string[2]
        var txt1 = findViewById<TextView>(R.id.textView)
        if(strings.toInt() <10){
            strings = "0$strings"
        }
        if(strings2.toInt() <10){
            strings2 = "0$strings2"
        }
        if(strings3.toInt() <10){
            strings3 = "0$strings3"
        }
        txt1!!.setText(strings + " : "+ strings2 + " : " + strings3)



    }


    inner class Timer:AsyncTask<Int,String,Void>() {


       override fun doInBackground(vararg p0: Int?): Void? {
           var limit = p0[0]
           var limit2 = p0[1]
           var limit3 = p0[2]
           var i = limit

           var i2 = limit2

           var i3 = limit3
           try {
               //for (i2 in limit2!! downTo 0) {

               while(i3!! >=0) {

                   if (i2!! > 60) {
                       while (i2!! > 60) {
                           i2 -= 60
                           i3 = i3!!.plus(1)

                       }
                   }
                   if (i2 == 0) {
                       i2 = 60
                       i3 = i3!!.minus(1)
                   }




                   while (i2!! >= 0) {

                            if (i!! > 60) {

                                 while (i > 60) {
                                  i -= 60
                                    i2 = i2!!.plus(1)
                               }

                              }



                       while (i!! > 0) {
                           publishProgress(i.toString(), i2.toString(),i3.toString())
                           Thread.sleep(1000)
                           i = i!!.minus(1)
                       }





                       if (i == 0) {
                           i = 59
                       }


                       i2 = i2!!.minus(1)





                   }



i3 = i3!!.minus(1)

               }


               //}
               val vibratorService: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
               vibratorService.vibrate(1000)
               nTimer!!.cancel(true)
               txt1!!.setText("00:00:00")

           } catch (ex: InterruptedException) {
               ex.stackTrace
               saver = limit!!

           }


return null
       }

       override fun onProgressUpdate(vararg values: String?) {

               var strings = values[0]
           var strings2 = values[1]
           var strings3 = values[2]


           stringformat(strings3!!.toString(),strings2!!.toString(),strings!!.toString())



               super.onProgressUpdate(*values)

       }


       override fun onPostExecute(result: Void?) {
           super.onPostExecute(result)
       }
   }




}

