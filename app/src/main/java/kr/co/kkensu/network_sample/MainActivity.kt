package kr.co.kkensu.network_sample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fasterxml.jackson.databind.type.TypeFactory
import com.kkensu.www.imagepager.ImagePager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var api: ServiceApiImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api = ServiceApiImpl()

        getSearch()

        /**
         * JacksonFactory 사용방법
         */
        var people = People()
        var a = JacksonFactory.create().toJson(people)
        var b = a
        var people2 = JacksonFactory.create().fromJson(a, People::class.java)
        var peopleList: MutableList<People> = ArrayList()
        var c = JacksonFactory.create().toJson(peopleList)
        var peopleList2 = JacksonFactory.create().fromJson<MutableList<People>>(
            c,
            TypeFactory.defaultInstance()
                .constructCollectionType(MutableList::class.java, People::class.java)
        )

        /**
         * ImagePager 사용방법
         */
        button.setOnClickListener {
            ImagePager.with(this)
                .setImageList(
                    R.drawable.ic_launcher_foreground,
                    "https://i.pinimg.com/236x/62/de/12/62de121611b4b6ad0d373be2ff7e23e5.jpg",
                    "https://siloam1004.com/upfile/product1/pr8/15874393981.jpg"
                )
                .setShowBottomImageViews(true)
                .start()
        }

    }

    private fun getSearch() {
        api.search("").enqueue(object : Callback<GetSearchResponse> {
            override fun onResponse(
                call: Call<GetSearchResponse>,
                response: Response<GetSearchResponse>
            ) {
            }

            override fun onFailure(call: Call<GetSearchResponse>, t: Throwable) {
                Log.e("JHC_DEBUG", t?.message)
            }
        })
    }
}