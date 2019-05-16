package com.mobitant.mygallerymodule

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var currentPath : String? = null
    val TAKE_PICTURE = 1
    val SELECT_PICTURE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonGallery.onClick {
            dispatchGalleryIntent()
        }

        buttonCamera.onClick {
            dispatchCameraIntent()
        }



    }

    private fun dispatchGalleryIntent() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select image"), SELECT_PICTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == TAKE_PICTURE && resultCode == Activity.RESULT_OK){
            try {
                val file = File(currentPath)
                val uri = Uri.fromFile(file)
                imageView.setImageURI(uri)
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
        if(requestCode == SELECT_PICTURE && resultCode == Activity.RESULT_OK){
            try {
                val uri = data!!.data
                imageView.setImageURI(uri)
            }catch (e: IOException){
                e.printStackTrace()
            }
        }
    }


    fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null){
            var photoFile: File? = null
            try {
                photoFile = createImage()
            }catch (e: IOException){
                e.printStackTrace()
            }
            if (photoFile != null){

                //Manifest의 content provider 설정하기
                //authority는 패키지이름이다.
               var photoUri = FileProvider.getUriForFile(this,
                    "com.mobitant.mygallerymodule.fileprovider",photoFile)
                intent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
                startActivityForResult(intent,TAKE_PICTURE)
            }

        }
    }

    fun createImage(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageName = "JPEG_"+timeStamp+"_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(imageName, ".jpg", storageDir)
        currentPath = image.absolutePath
        return image
    }
}
