package ru.dillab.chucknorrisjokes.ui.web

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import ru.dillab.chucknorrisjokes.databinding.WebFragmentBinding

class WebFragment : Fragment() {

    private var _binding: WebFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var webView: WebView
    private val url = "http://www.icndb.com/api/"

    private lateinit var progressBar: ProgressBar

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = WebFragmentBinding.inflate(inflater, container, false)

        progressBar = binding.progressBar

        webView = binding.webView
        webView.webViewClient = object : WebViewClient() {

            // Overrides function to show progress bar when page is starting to load
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                progressBar.visibility = View.VISIBLE
                return super.shouldOverrideUrlLoading(view, request)
            }

            // Method when to hide progress bar
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }
        }

        val settings = webView.settings
        settings.javaScriptEnabled = true
        settings.builtInZoomControls = true
        settings.displayZoomControls = false

        // Allows to go to previous page when hardware button back is tapped
        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    val webView = v as WebView
                    when (keyCode) {
                        KeyEvent.KEYCODE_BACK -> if (webView.canGoBack()) {
                            webView.goBack()
                            return true
                        }
                    }
                }
                return false
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            webView.loadUrl(url)
            // The method [shouldOverrideUrlLoading] is not starting when we get to the webView at
            // first time, so to show progress bar for first time declare it here
            progressBar.visibility = View.VISIBLE
        }
    }
}