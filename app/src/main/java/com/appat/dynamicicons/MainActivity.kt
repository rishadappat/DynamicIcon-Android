package com.appat.dynamicicons

import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appat.dynamicicons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val aliasClasses = ArrayList<Class<*>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        aliasClasses.add(RedLauncherAlias::class.java)
        aliasClasses.add(GreenLauncherAlias::class.java)
        aliasClasses.add(PurpleLauncherAlias::class.java)
        aliasClasses.add(OrangeLauncherAlias::class.java)
    }

    override fun onPause() {
        super.onPause()
        changeIconForButtonId()
    }

    private fun changeIconForButtonId(){
        when (binding.iconChangeRadioGroup.checkedRadioButtonId) {
            R.id.redRadioButton -> {
                changeIcon(RedLauncherAlias::class.java)
            }
            R.id.greenRadioButton -> {
                changeIcon(GreenLauncherAlias::class.java)
            }
            R.id.purpleRadioButton -> {
                changeIcon(PurpleLauncherAlias::class.java)
            }
            R.id.orangeRadioButton -> {
                changeIcon(OrangeLauncherAlias::class.java)
            }
        }
    }

    private fun changeIcon(selectedColor: Class<*>)
    {
        for (alias in aliasClasses){
            if(alias == selectedColor)
            {
                packageManager.setComponentEnabledSetting(ComponentName(this@MainActivity, alias),
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP)
            }
            else {
                packageManager.setComponentEnabledSetting(ComponentName(this@MainActivity, alias),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP)
            }
        }
    }
}