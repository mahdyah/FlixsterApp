package com.example.flixsterone
import com.codepath.asynchttpclient.AsyncHttpClient;
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

private const val API_Key=""
class MoviesFragment : Fragment(), OnListFragmentInteractionListene {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
       val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
          val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_Key


        // Using the client, perform the HTTP request
        client[
                "https://api.themoviedb.org/3/movie/now_playing?",
                params,
                object : JsonHttpResponseHandler()
                { //connect these callbacks to your API call
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON

                    ) {
                        // The wait for a response is over
                      progressBar.hide()

                        val resultsJSON : JSONArray = json.jsonObject.get("results") as JSONArray
                        val moviesRawJSON : String = resultsJSON.toString()

                        val gson = Gson()
                        val arrayMoviesType = object : TypeToken<List<Movie>>() {}.type
                        val models : List<Movie> = gson.fromJson(moviesRawJSON, arrayMoviesType)

                        recyclerView.adapter = MoviesAdapter(models, this@MoviesFragment)



                        // Look for this in Logcat:
                        Log.d("MoviesFragment", "response successful")
                    }
                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                     progressBar.hide()
                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("MoviesFragment", errorResponse)
                        }
                    }
                }]


    }
    override fun onItemClick(movie: Movie) {
        Toast.makeText(context, "test: " + movie.title, Toast.LENGTH_LONG).show()
    }
}
