package com.sambav.expressotesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast


import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Button


class LoginActivity : AppCompatActivity() {
    private var mEmailEditText: EditText? = null
    private var mPasswordEditText: EditText? = null
    private var mEmailSignInButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        mEmailEditText = findViewById(R.id.tv_email);
        mPasswordEditText = findViewById(R.id.tv_password);
         mEmailSignInButton = findViewById(R.id.btn_login);
        mEmailSignInButton!!.setOnClickListener(View.OnClickListener {
            login()
        })
    }
    private fun login() {
        mEmailEditText?.setError(null);
        mPasswordEditText?.setError(null);
        val email = mEmailEditText!!.text.toString()
        val password = mPasswordEditText!!.text.toString()

        var cancel = false
        var focusView: View? = null

        if (TextUtils.isEmpty(password)) {
            mPasswordEditText!!.setError(getString(R.string.error_field_required));
            focusView = mPasswordEditText;
            cancel = true;
        } else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordEditText!!.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordEditText;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailEditText!!.setError(getString(R.string.error_field_required));
            focusView = mEmailEditText;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailEditText!!.setError(getString(R.string.error_invalid_email));
            focusView = mEmailEditText;
            cancel = true;
        }

        if (cancel) {
            focusView?.requestFocus();
        } else {
            if (email.equals("user@email.com") && password.equals("123456")) {
                loginSuccessfully(email);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.error_login_failed), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 4
    }
    private fun loginSuccessfully(email: String) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
        finish()
        Toast.makeText(
            applicationContext,
            getString(R.string.login_successfully),
            Toast.LENGTH_SHORT
        ).show()
    }
}