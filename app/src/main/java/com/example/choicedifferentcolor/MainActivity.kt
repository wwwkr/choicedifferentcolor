package com.example.choicedifferentcolor

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.choicedifferentcolor.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {


    var TAG = this.javaClass.name

    var colorRes : Int = 0
    val random = Random()

    lateinit var binding : ActivityMainBinding

    var round : Int = 0
    // max_round +1 이 최고 난이도 차이가 클수록 쉽다
    var max_round : Int = 50
    var difficulty : Int = max_round+1

    var rnd_num : Int = 0

    var time = 0

    var isFinish : Boolean = false
    var isSuccese : Boolean = false



    lateinit var timerTask : Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setting()



        binding.btnReset.setOnClickListener {
            setting()
        }



    }

    fun setting( ){

        binding.btnReset.isEnabled = false

        if(isFinish){
            time = 30 * 100
            isFinish = false
            round = 0
            isSuccese = false
        }



        timerTask = timer(period = 10){

            time--

            CoroutineScope(Dispatchers.Main).launch {


                binding.tvTimer.text = (time/100).toString()


            }

            if(time <= 0){
                time = 0


                isFinish = true

                CoroutineScope(Dispatchers.Main).launch {



                    Log.e(TAG , " QQQQQQQQQQQQQQ")
                    timerTask.cancel()
                    cancel()
                    binding.btnReset.isEnabled = true


                }
            }

        }


        setColor(binding.view1, binding.view2, binding.view3,
            binding.view4, binding.view5, binding.view6,
            binding.view7, binding.view8, binding.view9)
    }
    fun createRndColor(a : Int , r  : Int, g  : Int, b : Int) = Color.argb(a, r,g,b)







    fun setColor(vararg views : View){

        if(isSuccese){
            Toast.makeText(this, "다시하기를 클릭해주세요", Toast.LENGTH_SHORT).show()
            return
        }


        if(isFinish){
            Toast.makeText(this, "시간초과 되었습니다", Toast.LENGTH_SHORT).show()
            return
        }



        if(round == max_round){

            isFinish = true
            isSuccese = true
            timerTask.cancel()
            binding.btnReset.isEnabled = true

            mShowDialog("성공", "축하합니다 절대색감입니다!\n다시 하시겠습니까?", "네", "아니요", DialogInterface.OnClickListener { dialogInterface, i ->

                setting()
            })

            return
        }


        val colorArr = arrayListOf<Int>(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255))

        colorRes = createRndColor(colorArr[0], colorArr[1] , colorArr[2], colorArr[3] )

        rnd_num = random.nextInt(9)




        if(colorArr[0]+(difficulty-round) >= 255){
            colorArr[0] = colorArr[0] - (difficulty-round)
        }else{
            colorArr[0] = colorArr[0] + (difficulty-round)
        }
        var diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2], colorArr[3])
//        var rnd_num2 = random.nextInt(2)
//        when(rnd_num2){
//
//            0 -> {
//                if(colorArr[0]+(50-round) >= 255){
//                    colorArr[0] = colorArr[0] - (50-round)
//                }else{
//                    colorArr[0] = colorArr[0] + (50-round)
//                }
//                diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2], colorArr[3])
//            }
//            1 -> {
//                if(colorArr[1]+(50-round) >= 255){
//                    colorArr[1] = colorArr[1] - (50-round)
//                }else{
//                    colorArr[1] = colorArr[1] + (50-round)
//                }
//                diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2])
//            }
//            2 -> {
//                if(colorArr[2]+(50-round) >= 255){
//                    colorArr[2] = colorArr[2] - (50-round)
//                }else{
//                    colorArr[2] = colorArr[2] + (50-round)
//                }
//                diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2])
//            }
//        }






        var index = 0
        for(view in views){

            view.setOnClickListener(null)
            if(rnd_num == index){

                view.setBackgroundColor(diffColorRes)
                view.setOnClickListener {

                    setColor(binding.view1, binding.view2, binding.view3,
                        binding.view4, binding.view5, binding.view6,
                        binding.view7, binding.view8, binding.view9)

                }

            }else{
                view.setBackgroundColor(colorRes)
                view.setOnClickListener {


                    if(time >= 500){
                        time-=500
                    }else{
                        time = 0
                    }

                }

            }

            index++


        }



        time = 31 * 100
        round++
        binding.tvRound.text = round.toString()+"/${max_round} ROUND"




    }


    fun mShowDialog(title:String , msg:String, pBtn:String, nBtn :String , dialog : DialogInterface.OnClickListener){

        var builder = AlertDialog.Builder(this)

        builder.setTitle(title)
        builder.setMessage(msg)

        builder.setPositiveButton(pBtn,dialog)


        if(nBtn != ""){
            builder.setNegativeButton(nBtn, DialogInterface.OnClickListener { dialogInterface, i ->


            })
        }

        builder.show()

    }


}