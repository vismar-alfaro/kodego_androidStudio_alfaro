package ph.kodego.alfaro.vismarjay.pokemonapi.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ph.kodego.alfaro.vismarjay.pokemonapi.R
import ph.kodego.alfaro.vismarjay.pokemonapi.adapters.PokemonAdapter
import ph.kodego.alfaro.vismarjay.pokemonapi.adapters.PokemonMovesAdapter
import ph.kodego.alfaro.vismarjay.pokemonapi.api.PokemonAPIClient
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.FragmentPokemonInfoBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.FragmentPokemonListBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.FragmentPokemonMovesBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.getPokemonID
import ph.kodego.alfaro.vismarjay.pokemonapi.models.Pokemon
import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonInfoResponse
import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonMoveInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonMovesFragment : Fragment() {

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            var message:String? = intent!!.getStringExtra("data")

            Log.i("Pokemon Info", message!!.toString())

            message?.let {
                getData(it.getPokemonID())
            }
        }
    }

    var binding: FragmentPokemonMovesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonMovesBinding.inflate(inflater,container,false)
        var view = binding!!.root

        return view    }

    override fun onDestroyView() {
        requireActivity().unregisterReceiver(receiver)
        super.onDestroyView()
    }

    private fun setupReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("ph.kodego.mdp2.GETDATA")
        requireActivity().registerReceiver(receiver,intentFilter)
    }

    private fun getData(id: Int){
        val call: Call<PokemonInfoResponse> =
            PokemonAPIClient.getPokemonData.getPokemonInfo(id)

        call.enqueue(object : Callback<PokemonInfoResponse>{
            override fun onFailure(call: Call<PokemonInfoResponse>, t:Throwable){
            }

            override fun onResponse(
                call: Call<PokemonInfoResponse>,
                response: Response<PokemonInfoResponse>
            ){

                var response: PokemonInfoResponse = response!!.body()!!
                var pokemonMove = response.moves
                var i:String = ""

                for (pokemove in pokemonMove){
                    i += pokemove.move.name + "\n"}

                binding!!.pokemonMove.text = "POKEMON MOVES:\n $i"


                Log.d("API INFO CALL", response.name)
            }
        })
    }


}