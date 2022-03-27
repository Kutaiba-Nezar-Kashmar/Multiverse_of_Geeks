package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.ui.my_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.kutaiba_nezar_kashmar.newapp.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment()
{
    private var _binding : FragmentMyProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
                             ): View?
    {
        val myProfileVM = ViewModelProvider(this).get(MyProfileViewModel::class.java)
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}