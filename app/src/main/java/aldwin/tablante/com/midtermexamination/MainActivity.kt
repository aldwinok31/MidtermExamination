package aldwin.tablante.com.midtermexamination

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ToggleButton
import org.w3c.dom.Text
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
var txt1 : TextView? = null
var bool:Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)
        var l :Long
        var nBut1 = findViewById<Button>(R.id.button) as Button
        var but1 = findViewById<ToggleButton>(R.id.TogBut1) as ToggleButton
        var edt1 = findViewById<EditText>(R.id.edt1) as EditText
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
var x:Long? = range.toLongOrNull()

        mTimer.execute(x)

        bool = false
    }
    else{
bool = true
    }

}


    }

   inner class Timer:AsyncTask<Long,String,Void>() {

       override fun doInBackground(vararg p0: Long?): Void? {
           var limit = p0[0]
           try {

               for (i in limit!! downTo 0) {
                   publishProgress(i.toString())
                   Thread.sleep(1000)
               }

           } catch (ex: InterruptedException) {
               ex.stackTrace
           }

return null
       }

       override fun onProgressUpdate(vararg values: String?) {

               var strings = values[0]

               txt1!!.setText(strings.toString())
               super.onProgressUpdate(*values)

       }


       override fun onPostExecute(result: Void?) {
           super.onPostExecute(result)
       }
   }



}


