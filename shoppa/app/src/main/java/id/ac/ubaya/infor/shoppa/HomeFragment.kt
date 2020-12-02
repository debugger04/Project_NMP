package id.ac.ubaya.infor.shoppa

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var images:ArrayList<ImageProduct> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        images.add(ImageProduct("http://play-lh.googleusercontent.com/IeNJWoKYx1waOhfWF6TiuSiWBLfqLb18lmZYXSgsH1fvb8v1IYiZr5aYWe0Gxu-pVZX3"))
        images.add(ImageProduct("http://ecdn.teacherspayteachers.com/thumbitem/square-clip-art-free-clip-art-in-preview--2647528-1543003299/original-2647528-3.jpg"))
        images.add(ImageProduct("https://www.jing.fm/clipimg/detail/8-84558_square-objects-clipart-3-d-shapes-cube.png"))
        images.add(ImageProduct("https://www.pngitem.com/pimgs/m/84-843413_television-clipart-square-thing-black-and-white-tv.png"))
        images.add(ImageProduct("https://www.clipartkey.com/mpngs/m/11-114922_square-clipart-square-gift-square-gift-box-clipart.png"))
        images.add(ImageProduct("https://www.pngitem.com/pimgs/m/106-1066739_square-clipart-square-object-lego-body-object-mayhem.png"))

        val lm:LinearLayoutManager = LinearLayoutManager(this.context)
        lm.orientation = LinearLayoutManager.HORIZONTAL
        var recyclerView = v.findViewById<RecyclerView>(R.id.sliderView)
        recyclerView?.layoutManager = lm
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = ImageProductAdapter(images, this)

        val gm:GridLayoutManager = GridLayoutManager(this.context,2)
        var recyclerView2 = v.findViewById<RecyclerView>(R.id.scrollView)
        recyclerView2?.layoutManager = gm
        recyclerView2?.setHasFixedSize(true)
        recyclerView2?.adapter = ProductAdapter(images, this)

        Log.d("isi_array", images.toString())

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}