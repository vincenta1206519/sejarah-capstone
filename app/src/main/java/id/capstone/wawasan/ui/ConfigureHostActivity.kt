package id.capstone.wawasan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.capstone.wawasan.R
import id.capstone.wawasan.databinding.ActivityConfigureHostBinding

class ConfigureHostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigureHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigureHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNext.setOnClickListener {
            val host = binding.etHost.text.toString()
            val port = binding.etPort.text.toString()

            if (host.isNotEmpty() && port.isNotEmpty()) {
                val savedHost = host
                val savedPort = port
            }

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}