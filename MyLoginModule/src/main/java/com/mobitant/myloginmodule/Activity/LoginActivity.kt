package com.mobitant.myloginmodule.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.mobitant.myloginmodule.API.INodeJS
import com.mobitant.myloginmodule.API.RetrofitClient
import com.mobitant.myloginmodule.R
import com.rengwuxian.materialedittext.MaterialEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity() {

    lateinit var myAPI:INodeJS
    var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Init API
        val retrofit= RetrofitClient.instance
        myAPI = retrofit.create(INodeJS::class.java)

        login_button.onClick {
            login(edittext_email.text.toString(),edittext_password.text.toString())
        }

        register_button.onClick {
            register(edittext_email.text.toString(),edittext_password.text.toString())
        }

    }

    private fun register(email: String,password: String){
        val enter_name_view = LayoutInflater.from(this@LoginActivity)
            .inflate(R.layout.enter_name_layout,null)


        MaterialStyledDialog.Builder(this@LoginActivity)
            .setTitle("Register")
            .setDescription("One more step!")
            .setIcon(R.drawable.ic_user)
            .setNegativeText("Cancel")
            .onNegative{dialog, _ -> dialog.dismiss() }
            .setPositiveText("Register")
            .onPositive { _, _ ->
                val edit_name = enter_name_view.findViewById<View>(R.id.edittext_name) as MaterialEditText

                compositeDisposable.add(myAPI.registerUser(email,edit_name.text.toString(),password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { message ->
                            toast(message).show()
                    }
                )
            }.show()



    }

    private fun login(email: String, password:String){
        compositeDisposable.add(myAPI.loginUser(email,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { message ->
                if (message.contains("encrypted_password"))
                    toast("Login success").show()
                else
                    toast(message).show()
            }
        )
    }

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }


}
