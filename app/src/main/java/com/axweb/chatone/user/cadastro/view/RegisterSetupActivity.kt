package com.axweb.chatone.user.cadastro.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.axweb.chatone.R
import com.axweb.chatone.core.showError
import com.axweb.chatone.core.toBitmap
import com.axweb.chatone.databinding.ActivityCadastrarSetupBinding
import com.axweb.chatone.main.view.MainActivity
import com.axweb.chatone.user.model.User
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat


const val RESULT_INTENT_IMAGE_SELECTED = 468

class RegisterSetupActivity : AppCompatActivity() {


        @SuppressLint("SimpleDateFormat", "WeekBasedYear")
    var formatDate = SimpleDateFormat("dd MMMM YYYY")


    private var selection = ""
    private var selecionarUri: Uri? = null

    private lateinit var binding: ActivityCadastrarSetupBinding

    private val storage by lazy { FirebaseStorage.getInstance() }
    private val firebaseUser by lazy { FirebaseAuth.getInstance().currentUser }
    private val firebaseFirestore by lazy { FirebaseFirestore.getInstance() }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editBirthDate.setOnClickListener {

            val maxDate = Calendar.getInstance().time
            SingleDateAndTimePickerDialog.Builder(this)
                    .maxDateRange(maxDate)
                    .bottomSheet()
                    .curved()
                    .displayMinutes(false)
                    .displayHours(false)
                    .displayDays(false)
                    .displayMonth(true)
                    .displayYears(true)
                    .displayDaysOfMonth(true)
                    .title("Data de Nascimento")
                    .listener {
                        val date: String = formatDate.format(it.time)
                        binding.editBirthDate.setText(date)
                    }.display()
        }

        binding.btnFinish.setOnClickListener {
            showLoading()
            saveUserFirestore()
        }

        binding.ivSelectImage.setOnClickListener {
            selecionarFoto()
        }

            radioButton()


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_INTENT_IMAGE_SELECTED && resultCode == RESULT_OK) {
            selecionarUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selecionarUri)
            binding.ivSelectImage.setImageBitmap(bitmap)
        }
    }


    private fun selecionarFoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, RESULT_INTENT_IMAGE_SELECTED)
    }


    private fun saveUserFirestore() {

        val name = binding.editName.text.toString()
        val lastName = binding.editLastName.text.toString()
        val username = binding.editUsername.text.toString()
        val birthDate = binding.editBirthDate.text.toString()


        firebaseFirestore.collection("users").whereEqualTo("username", username).get().addOnSuccessListener {
            if (it.isEmpty) {
                val user = User()
                user.first_name = name
                user.last_name = lastName
                user.username = username
                user.birthdate = birthDate
                user.gender = selection

                saveImage(firebaseUser?.uid) {
                    user.firebase_id = firebaseUser?.uid
                    user.email = firebaseUser?.email
                    user.photo_path = it
                    user.completeRegister = true
                    firebaseFirestore.collection("users")
                            .document(firebaseUser?.uid ?: "")
                            .set(user)
                            .addOnSuccessListener {

                                hideLoading()
                                redirectMain()
                            }.addOnFailureListener {

                                this.hideLoading()
                                Toast.makeText(this, "Texto de falha", Toast.LENGTH_SHORT).show()
                            }
                }
            } else {
                showError("Já exite um usuario com esse Nickname")
            }
        }
    }


    private fun redirectMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }

    private fun saveImage(firebaseId: String?, onFinish: (String?) -> Unit) {
        if (firebaseId == null) {
            onFinish(null)
            return
        }
        val image: Bitmap? = if (selecionarUri != null) {
            MediaStore.Images.Media.getBitmap(contentResolver, selecionarUri)
        } else {
            toBitmap(R.drawable.ic_contact)
        }

        val baos = ByteArrayOutputStream()
        image?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = storage.reference.child("profilesImages").child(firebaseId).putBytes(data)
        uploadTask.addOnFailureListener {
            onFinish(null)
        }.addOnSuccessListener { taskSnapshot ->
            taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                onFinish(uri?.toString())

            }
        }
    }

    private fun showLoading() = binding.loading.apply {
        visibility = View.VISIBLE
    }

    private fun hideLoading() = binding.loading.apply {
        visibility = View.GONE
    }

    private fun radioButton() {


        binding.editGender.setOnCheckedChangeListener { group, id ->
            when (id) {
                R.id.masc -> {

                    //If you Salected Male
                    selection = "Masculino"
                }
                R.id.fem -> {
                    //If you Salected Female
                    selection = "Feminino"
                }
                R.id.outro -> {
                    //If you Salected Female
                    selection = "Outro"
                }

            }
        }

    }
}









    

