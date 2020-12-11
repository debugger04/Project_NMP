package id.ac.ubaya.infor.shoppa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity() {
    val USER_ID = ""
    val fragments:ArrayList<Fragment> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        var sharedFile = "id.ac.ubaya.infor.shoppa"
        var shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        var curr = shared.getInt(USER_ID, 0)
        if (curr != 0) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            fragments.add(Onboard1Fragment())
            fragments.add(Onboard2Fragment())
            fragments.add(Onboard3Fragment())
            viewPager.adapter = OnboardAdapter(this, fragments)
        }
    }
}