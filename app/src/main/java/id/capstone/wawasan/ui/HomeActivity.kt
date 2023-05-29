package id.capstone.wawasan.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import id.capstone.wawasan.R
import id.capstone.wawasan.adapter.DummyAdapter
import id.capstone.wawasan.data.Dummy
import id.capstone.wawasan.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), ProfileFragment.ProfileUpdateListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val list = ArrayList<Dummy>()

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
        }

        binding.icSetting.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.rvDummy.setHasFixedSize(true)
        list.addAll(getListDummy())
        binding.rvDummy.layoutManager = LinearLayoutManager(this)
        val dummyAdapter = DummyAdapter(list)
        binding.rvDummy.adapter = dummyAdapter
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

    private fun getListDummy(): ArrayList<Dummy> {
        val sales = resources.getStringArray(R.array.data_sales)
        val product = resources.getStringArray(R.array.data_product)
        val stock = resources.getStringArray(R.array.data_stock)
        val recStock = resources.getStringArray(R.array.data_recstock)

        val listDummy = ArrayList<Dummy>()
        for (i in sales.indices) {
            val dummy = Dummy(
                sales[i],
                product[i],
                stock[i],
                recStock[i]
            )
            listDummy.add(dummy)
        }
        return listDummy
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