package id.capstone.wawasan.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import id.capstone.wawasan.R
import id.capstone.wawasan.databinding.FragmentChangePasswordBinding
import id.capstone.wawasan.databinding.FragmentProfileBinding

class ChangePasswordFragment : Fragment() {

    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuth = FirebaseAuth.getInstance()

        val user = firebaseAuth.currentUser

        binding.btnChange.setOnClickListener {
            val currentPassword = binding.etPassword.text.toString().trim()
            val newPassword = binding.etNewpassword.text.toString().trim()
            val confirmPassword = binding.etCpassword.text.toString().trim()

            if (newPassword.isEmpty() || confirmPassword.isEmpty()  || currentPassword.isEmpty()) {
                Snackbar.make(view, "Mohon isi semua kolom", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                Snackbar.make(view, "Konfirmasi password tidak cocok", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val credential = EmailAuthProvider.getCredential(user?.email ?: "", currentPassword)
            user?.reauthenticate(credential)
                ?.addOnSuccessListener {
                    user.updatePassword(newPassword)
                        .addOnSuccessListener {
                            Snackbar.make(view, "Password berhasil diubah", Snackbar.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { exception ->
                            Snackbar.make(view, "Gagal mengubah password: ${exception.message}", Snackbar.LENGTH_SHORT).show()
                        }
                }
                ?.addOnFailureListener { exception ->
                    Snackbar.make(view, "Gagal melakukan autentikasi: ${exception.message}", Snackbar.LENGTH_SHORT).show()
                }
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(requireContext(), SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}