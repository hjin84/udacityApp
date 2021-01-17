package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.ShoeViewModel
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //binding 객체는 xml 이름을 그래도 쓰되 Pascal로 바꾸면 됨!!!
    private lateinit var binding: FragmentShoeDetailBinding

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val shoeViewmModel: ShoeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val view = binding.root

        binding.apply {
            shoeDetailSaveButton.setOnClickListener{
                val name: String = shoenameEdit.text.toString()
                val sizeString: String = shoesizeEdit.text.toString()

                var size: Double = 0.0
                try{
                    if(sizeString != "") size = sizeString.toDouble()
                }catch(e: Exception){
                    //
                }

                val company: String = shoecompanyEdit.text.toString()
                val description: String = shoedescrEdit.text.toString()
                shoeViewmModel?.onAdd(Shoe(name,size,company,description))

                Navigation.findNavController(view).navigate(R.id.action_shoeDetail_save_to_shoelist)
            }
            shoeDetailCancelButton.setOnClickListener{
                Navigation.findNavController(view).navigate(R.id.action_shoeDetail_cancel_to_shoelist)
            }
        }


//        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_shoe_detail, container, false)
//
//        view.findViewById<Button>(R.id.shoeDetail_save_button).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_shoeDetail_save_to_shoelist)
//        }
//        view.findViewById<Button>(R.id.shoeDetail_cancel_button).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_shoeDetail_cancel_to_shoelist)
//        }
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoeDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoeDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}