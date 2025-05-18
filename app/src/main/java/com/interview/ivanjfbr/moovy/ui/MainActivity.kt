package com.interview.ivanjfbr.moovy.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.interview.ivanjfbr.moovy.ui.theme.MoovyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoovyTheme {
                MoovyMainScreen()
            }
        }
    }
}