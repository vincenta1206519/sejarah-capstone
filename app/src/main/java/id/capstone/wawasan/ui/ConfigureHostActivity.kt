package id.capstone.wawasan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import id.capstone.wawasan.R
import id.capstone.wawasan.databinding.ActivityConfigureHostBinding

class ConfigureHostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigureHostBinding
    private val configureHostViewModel: ConfigureHostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigureHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val pass = binding.etPassword.text.toString()
            val host = binding.etHost.text.toString()
            val port = binding.etPort.text.toString()
            val db = binding.etDb.text.toString()

            if (username.isNotEmpty() && pass.isNotEmpty() && host.isNotEmpty() && port.isNotEmpty() && db.isNotEmpty()) {
                configureHostViewModel.getLoginUser(username, pass, host, port, db)
            }
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}