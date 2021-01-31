package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.switchmaterial.SwitchMaterial

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton: Button = findViewById(R.id.roll_button)
        val single: SwitchMaterial = findViewById(R.id.single)
        val singleDice: ImageView = findViewById(R.id.single_dice)
        val dices: LinearLayout = findViewById(R.id.dices)
        val total: TextView = findViewById(R.id.total)

        val dice1: ImageView = findViewById(R.id.dice_image1)
        val dice2: ImageView = findViewById(R.id.dice_image2)

        rollButton.setOnClickListener { rollDices(dice1, dice2, total) }

        rollDices(dice1, dice2, total)

        single.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                total.text = getString(R.string.total, rollDice(singleDice))
                dices.visibility = View.GONE
                singleDice.visibility = View.VISIBLE
                rollButton.setOnClickListener { total.text = getString(R.string.total, rollDice(singleDice))}

            } else {
                rollDices(dice1, dice2, total)
                dices.visibility = View.VISIBLE
                singleDice.visibility = View.GONE
                rollButton.setOnClickListener { rollDices(dice1, dice2, total) }
            }
        }

    }

    private fun rollDices(dice1: ImageView, dice2: ImageView, total: TextView) {
        val d1 = rollDice(dice1)
        val d2 = rollDice(dice2)
        val t = d1 + d2

        total.text = getString(R.string.total, t)

    }

    private fun rollDice(dice: ImageView): Int {
        val randInt = (1..6).random()

        val drawableResource = when (randInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice.setImageResource(drawableResource)
        return randInt
    }
}