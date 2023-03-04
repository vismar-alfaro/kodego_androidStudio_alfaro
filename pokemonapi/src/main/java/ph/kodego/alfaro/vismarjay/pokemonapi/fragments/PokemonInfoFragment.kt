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
import ph.kodego.alfaro.vismarjay.pokemonapi.api.PokemonAPIClient
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.FragmentPokemonInfoBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.getPokemonID
import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonInfoFragment : Fragment() {

    private val receiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {

            var message: String? = intent!!.getStringExtra("data")

            Log.i("Pokemon Info", message!!.toString())

            message?.let {
                getData(it.getPokemonID())
            }
        }
    }

    var binding: FragmentPokemonInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonInfoBinding.inflate(inflater,container,false)
        var view = binding!!.root
        // Inflate the layout for this fragment

        return view
    }

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
                var pokemonId = response.id
                var pokemonName = response.name
                var pokemonHeight = response.height
                var pokemonAbility = response.abilities
                var i:String = ""

                for(pokeAbility in pokemonAbility){
                    i += pokeAbility.ability.name + "\n"}


                binding!!.pokemonName.text = "POKEMON Name:\n $pokemonName"
                binding!!.pokemonId.text = "POKEMON ID:\n $pokemonId"
                binding!!.pokemonHeight.text = "POKEMON HEIGHT:\n $pokemonHeight"
                binding!!.pokemonAbilities.text = "POKEMON ABILITIES:\n $i"

                Intent().also{
                    Log.d("Pokemon","${response.sprites.front_default}")
                    it.setAction("ph.kodego.mdp2.LOADIMAGEACTION")
                    it.putExtra("data",response.sprites.front_default)
                    context!!.sendBroadcast(it)
                }

                Log.d("API INFO CALL", response.name)
            }
        })
    }

}