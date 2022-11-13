package com.alexis.shop.ui.menu.aboutus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.alexis.shop.R
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import eightbitlab.com.blurview.RenderScriptBlur
import kotlinx.android.synthetic.main.fragment_about_us.*

class AboutUsFragment : Fragment() {
    lateinit var cancel_button: ImageView
    lateinit var contact_info: TextView
    lateinit var contact_mail: TextView
    lateinit var textTitle: TextView
    lateinit var contact_wa: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about_us, container, false)
        contact_info = root.findViewById(R.id.contact_info)
        contact_mail = root.findViewById(R.id.txt_mail)
        contact_wa = root.findViewById(R.id.txt_wa)
        cancel_button = root.findViewById(R.id.btn_cancel)
        this.textTitle = root.findViewById(R.id.textTitle)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Animations.runAnimation(
            requireActivity(),
            Animations.ANIMATION_IN,
            null,
            this.contact_info,
            this.contact_mail,
            this.contact_wa,
            this.textTitle
        )
        blurView()

        cancel_button.setOnClickListener {

            Animations.runAnimation(
                requireActivity(),
                Animations.ANIMATION_OUT,
                null,
                this.contact_info,
                this.contact_mail,
                this.contact_wa,
                this.textTitle
            )

            Looper.myLooper()?.let { it1 ->
                Handler(it1).postDelayed({
                    justOut()
                }, 1000L)
            }
        }

        contact_wa.setOnClickListener {
            //Open WhatsApp
            var formattedNumber: String = "62" +
                    contact_wa.text.toString().substring(1, contact_wa.text.toString().length)
            formattedNumber = formattedNumber.replace(" ","")

            try {
                val whatsappIntent = Intent(Intent.ACTION_SEND)
                whatsappIntent.type = "text/plain"
                whatsappIntent.setPackage("com.whatsapp")
                whatsappIntent.putExtra("jid", "$formattedNumber@s.whatsapp.net")
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share")
                requireActivity().startActivity(whatsappIntent)
            } catch (e: Exception) {
                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
            }
        }

        contact_mail.setOnClickListener {
            //Open Email Apps
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")

                intent.putExtra(Intent.EXTRA_EMAIL, (it as TextView).text.toString())
                intent.putExtra(Intent.EXTRA_SUBJECT, "")
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun blurView() {
        val radius = 15f
        val decorView : View = activity?.window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        blurView.setupWith(rootView, RenderScriptBlur(requireContext()))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }
}