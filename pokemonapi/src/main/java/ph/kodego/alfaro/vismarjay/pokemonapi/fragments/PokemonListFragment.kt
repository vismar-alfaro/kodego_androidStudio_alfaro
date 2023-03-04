package ph.kodego.alfaro.vismarjay.pokemonapi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.alfaro.vismarjay.pokemonapi.R
import ph.kodego.alfaro.vismarjay.pokemonapi.adapters.PokemonAdapter
import ph.kodego.alfaro.vismarjay.pokemonapi.api.PokemonAPIClient
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.FragmentPokemonListBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.models.Pokemon
import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonListFragment : Fragment() {

    var pokemonAdapter: PokemonAdapter? = null
    var pokemonList = ArrayList<Pokemon>()
    var binding:FragmentPokemonListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPokemonListBinding.inflate(inflater,container,false)
        var view = binding!!.root

        pokemonAdapter = PokemonAdapter(requireActivity().applicationContext,pokemonList)

        binding!!.pokemonList.layoutManager = LinearLayoutManager(requireActivity().applicationContext,
        LinearLayoutManager.VERTICAL,false)

        binding!!.pokemonList.adapter = pokemonAdapter

        var num = 1
        var offset = 20

        binding!!.nextButton.setOnClickListener{
            num ++
            offset += 20
            binding!!.offsetNumber.text = num.toString()
            binding!!.backButton.visibility = VISIBLE

        }

        binding!!.backButton.setOnClickListener{
            num--
            offset -= 20
            binding!!.offsetNumber.text = num.toString()
            if(num == 1){
                binding!!.backButton.visibility = INVISIBLE
            }

        }

        getData()


        return view
    }


    private fun getData(){

        val call: Call<PokemonListResponse> =
            PokemonAPIClient.getPokemonData.getList(0,20)

        call.enqueue(object : Callback<PokemonListResponse> {
            override fun onFailure(call: Call<PokemonListResponse>,t:Throwable){
                Log.d("API CALL","Failed API CALL")
            }

            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                var response: PokemonListResponse = response!!.body()!!
                pokemonAdapter!!.setList(response.pokemonList)

                var pokelist = response.pokemonList
                for (pokemon in pokelist){
                    Log.d("API CALL", pokemon.name)
                }

            }
        })

    }

    private fun replaceOldWithNewList(){
        pokemonList.clear()
        pokemonAdapter!!.setList(pokemonList)

    }

}