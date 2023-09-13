package com.example.marvelapp.features.about.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.marvelapp.BuildConfig
import com.example.marvelapp.R
import com.example.marvelapp.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAboutBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideSearchIconToolBar()
        openLink()
    }

    private fun hideSearchIconToolBar() {
        val toolbar = activity?.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        toolbar?.inflateMenu(R.menu.main_menu)
        toolbar?.menu?.findItem(R.id.search)?.let { menuItem ->
            menuItem.isVisible = false
        }
    }

    private fun openLink() {
        binding.txtGithub.text = underLineText(binding.txtGithub.text)
        binding.txtLinkedin.text = underLineText(binding.txtLinkedin.text)

        binding.txtGithub.setOnClickListener {
            val url = BuildConfig.GITHUB_LINK

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            startActivity(intent)
        }

        binding.txtLinkedin.setOnClickListener {
            val url = BuildConfig.LINKEDIN_LINK

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            startActivity(intent)
        }
    }

    private fun underLineText(text: CharSequence): SpannableString {
        val underlinedText = SpannableString(text)
        underlinedText.setSpan(
            UnderlineSpan(),
            0,
            underlinedText.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return underlinedText
    }

}