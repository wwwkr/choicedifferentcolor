package com.example.choicedifferentcolor

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.choicedifferentcolor.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {


    var colorRes : Int = 0
    val random = Random()

    lateinit var binding : ActivityMainBinding

    var round : Int = 1

    var rnd_num : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        setColor(binding.view1, binding.view2, binding.view3,
                binding.view4, binding.view5, binding.view6,
                binding.view7, binding.view8, binding.view9)


    }

    fun createRndColor(r  : Int, g  : Int, b : Int) = Color.rgb( r,g,b)







    fun setColor(vararg views : View){

        val colorArr = arrayListOf<Int>(random.nextInt(255), random.nextInt(255), random.nextInt(255))

        colorRes = createRndColor(colorArr[0], colorArr[1] , colorArr[2] )

        rnd_num = random.nextInt(9)
        var rnd_num2 = random.nextInt(2)

        var diffColorRes = 0


        when(rnd_num2){

            0 -> {
                if(colorArr[0]+(50-round) >= 255){
                    colorArr[0] = colorArr[0] - (50-round)
                }else{
                    colorArr[0] = colorArr[0] + (50-round)
                }
                diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2])
            }
            1 -> {
                if(colorArr[1]+(50-round) >= 255){
                    colorArr[1] = colorArr[1] - (50-round)
                }else{
                    colorArr[1] = colorArr[1] + (50-round)
                }
                diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2])
            }
            2 -> {
                if(colorArr[2]+(50-round) >= 255){
                    colorArr[2] = colorArr[2] - (50-round)
                }else{
                    colorArr[2] = colorArr[2] + (50-round)
                }
                diffColorRes = createRndColor(colorArr[0], colorArr[1], colorArr[2])
            }
        }



            binding.tvRound.text = round.toString()


        var index = 0
        for(view in views){

            Log.e("CHECK",  Integer.toHexString(Color.GREEN).substring(2,8))
            if(rnd_num == index){

                view.setBackgroundColor(diffColorRes)
                view.setOnClickListener {
                    view.setOnClickListener(null)
                    setColor(binding.view1, binding.view2, binding.view3,
                        binding.view4, binding.view5, binding.view6,
                        binding.view7, binding.view8, binding.view9)

                }

            }else{
                view.setBackgroundColor(colorRes)

            }

            index++


        }

        round++

    }
}