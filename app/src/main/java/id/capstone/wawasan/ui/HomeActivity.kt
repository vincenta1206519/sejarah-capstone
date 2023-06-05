package id.capstone.wawasan.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import id.capstone.wawasan.adapter.DataAdapter
import id.capstone.wawasan.databinding.ActivityHomeBinding
import id.capstone.wawasan.retrofit.DataResponse

class HomeActivity : AppCompatActivity(), ProfileFragment.ProfileUpdateListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser

        if (user != null) {
            binding.textName.text = user.displayName

            if (user.photoUrl != null) {
                Picasso.get().load(user.photoUrl).into(binding.ivProfile)
            }
        } else {
            binding.textName.text = "Anonymous"
        }

        binding.icSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rv.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rv.addItemDecoration(itemDecoration)


        getListData()
    }

    override fun onProfileUpdated(name: String?, photoUrl: Uri?) {
        refreshProfile()
    }

    private fun refreshProfile() {
        val user = firebaseAuth.currentUser

        if (user != null) {
            binding.textName.text = user.displayName

            if (user.photoUrl != null) {
                Picasso.get().load(user.photoUrl).into(binding.ivProfile)
            }
        }
    }

    private fun getListData() {
        homeViewModel.data.observe(this) { dataResponse ->
            if (dataResponse != null) {
                val dataItems = dataResponse.data
                val adapter = DataAdapter(dataItems)
                binding.rv.adapter = adapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        refreshProfile()
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finishAffinity()"))
    override fun onBackPressed() {
        finishAffinity() // Keluar dari aplikasi
    }
}