package com.example.mywebbrowser

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.email
import org.jetbrains.anko.sendSMS

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.apply{
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

        urlEditText.setOnEditorActionListener{_, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                webView.loadUrl(urlEditText.text.toString())
                true
            }else{
                false
            }
        }
        }

        webView.loadUrl("http://google.com")
        registerForContextMenu(webView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId){
            R.id.action_google, R.id.action_home-> {
                webView.loadUrl("http://google.com")
                return true
            }
            R.id.action_naver -> {
                webView.loadUrl("http://naver.com")
                return true
                }
            R.id.action_daum -> {
                    webView.loadUrl("http://daum.net")
                    return true
                }
            R.id.action_call -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:031-123-4567")
                    if(intent.resolveActivity(packageManager) != null){
                        startActivity(intent)
                    }
                    return true
                }
                R.id.action_send_text ->{
                    sendSMS("031-123-4567")
                    return true
                }
                R.id.action_email ->{
                    email("test@example.com","좋은사이트")
                    return true
                }
            }
        return super.onOptionsItemSelected(item)//8
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_share ->{
                return true
            }
            R.id.action_browser ->{
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    }
