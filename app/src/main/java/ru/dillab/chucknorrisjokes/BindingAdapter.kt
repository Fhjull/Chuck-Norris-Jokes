package ru.dillab.chucknorrisjokes

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.dillab.chucknorrisjokes.network.JokesProperty
import ru.dillab.chucknorrisjokes.ui.jokes.JokesApiStatus
import ru.dillab.chucknorrisjokes.ui.jokes.JokesRecycleViewAdapter

// Text of joke that is displayed in every list_item.xml of recycle view
@BindingAdapter("text")
fun bindText(textView: TextView, text: String?) {
    // Replacing HTML format encoding with symbols
    val refactoredText = text
        ?.replace("&quot;", "\"")
        ?.replace("&amp;", "&")
        ?.replace("&lt;", "<")
        ?.replace("&gt;", ">")
    textView.text = refactoredText
}

// List of data with all jokes, that we fetched from API and are displaying in RecycleView
// in jokes_fragment.xml
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<JokesProperty>?) {
    val adapter = recyclerView.adapter as JokesRecycleViewAdapter
    adapter.submitList(data)
}

// Function to display or hide loading status of Jokes in jokes_fragment.xml
@SuppressLint("SetTextI18n")
@BindingAdapter("jokesApiStatus")
fun bindStatus(
    statusTextView: TextView,
    status: JokesApiStatus?
) {
    when (status) {
        JokesApiStatus.LOADING -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "Loading"
        }
        JokesApiStatus.ERROR -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = "Connection error"
        }
        JokesApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
    }
}