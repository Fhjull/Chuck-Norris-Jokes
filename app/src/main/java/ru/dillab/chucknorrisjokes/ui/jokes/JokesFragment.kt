package ru.dillab.chucknorrisjokes.ui.jokes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.dillab.chucknorrisjokes.databinding.JokesFragmentBinding

class JokesFragment : Fragment() {

    private val viewModel: JokesViewModel by lazy {
        ViewModelProvider(this).get(JokesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = JokesFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = JokesRecycleViewAdapter()

        return binding.root
    }
}


