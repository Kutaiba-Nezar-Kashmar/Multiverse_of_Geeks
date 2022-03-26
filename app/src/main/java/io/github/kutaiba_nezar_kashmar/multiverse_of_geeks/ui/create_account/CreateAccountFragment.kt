package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.create_account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentCreateProfileBinding

class CreateAccountFragment : Fragment()
{
    private var _binding: FragmentCreateProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val createAccountVM = ViewModelProvider(this).get(CreateAccountViewModel::class.java)
        _binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root;
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}