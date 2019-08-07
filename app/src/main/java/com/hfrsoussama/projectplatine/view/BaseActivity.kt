package com.hfrsoussama.projectplatine.view

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun isTablet(): Boolean

}