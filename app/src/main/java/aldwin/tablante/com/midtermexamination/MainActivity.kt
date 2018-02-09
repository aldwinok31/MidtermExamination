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
        var l :Long
        var nBut1 = findViewById<Button>(R.id.button) as Button
        var but1 : ToggleButton = findViewById<ToggleButton>(R.id.TogBut1) as ToggleButton

        var txtv1 = findViewById<TextView>(R.id.textView) as TextView

        txt1 = txtv1
        var range = edt1.toString()


nBut1.setOnClickListener{
    var intent:Intent = Intent(this,AlarmClock::class.java)
    startActivity(intent)

}




but1.setOnClickListener {

    if(bool) {
        var mTimer: Timer = Timer()
        val edt1: EditText = findViewById<EditText>(R.id.edt1) as EditText
        val edt:EditText = findViewById(R.id.edt) as EditText
        var z : Int = edt.text.toString().toInt()
        var x : Int = edt1.text.toString().toInt()

        mTimer.execute(x,z)
  nTimer = mTimer
        bool = false
    }
    else{
        nTimer!!.cancel(true)
        nTimer = null
        txt1!!.setText("-")
bool = true

    }

}


    }

   inner class Timer:AsyncTask<Int,String,Void>() {


       override fun doInBackground(vararg p0: Int?): Void? {
           var limit = p0[0]
           var limit2 = p0[1]
           var i = limit
           try {
               for (i2 in limit2!! downTo 0) {

                   if(i ==0) {
                       i=60
                   }
                       while (i!! > 0) {
                           publishProgress(i.toString(), i2.toString())
                           Thread.sleep(1000)
                           i = i!!.minus(1)
                       }

               }
               val vibratorService: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
               vibratorService.vibrate(1000)
               nTimer!!.cancel(true)
               txt1!!.setText("00:00")

           } catch (ex: InterruptedException) {
               ex.stackTrace
               saver = limit!!

           }


return null
       }

       override fun onProgressUpdate(vararg values: String?) {

               var strings = values[0]
           var strings2 = values[1]

               txt1!!.setText(strings2.toString()+ " : " +strings.toString())
               super.onProgressUpdate(*values)

       }


       override fun onPostExecute(result: Void?) {
           super.onPostExecute(result)
       }
   }




}


