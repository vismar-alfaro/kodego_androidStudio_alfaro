package ph.kodego.alfaro.vismarjay.pokemonapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import ph.kodego.alfaro.vismarjay.pokemonapi.adapters.PokemonInfoAdapter
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.ActivityMainBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.fragments.PokemonImageFragment
import ph.kodego.alfaro.vismarjay.pokemonapi.fragments.PokemonInfoFragment
import ph.kodego.alfaro.vismarjay.pokemonapi.fragments.PokemonListFragment
import ph.kodego.alfaro.vismarjay.pokemonapi.fragments.PokemonMovesFragment

class MainActivity : AppCompatActivity() {
    private val pokemonListFragment = PokemonListFragment()
    private val pokemonInfoFragment = PokemonInfoFragment()
    private val pokemonImageFragment = PokemonImageFragment()
    private val pokemonMovesFragment = PokemonMovesFragment()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding:ActivityMainBinding
    private lateinit var pokemonInfoAdapter: PokemonInfoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_holder,pokemonListFragment)
            .commit()

        pokemonInfoAdapter = PokemonInfoAdapter(supportFragmentManager)
        pokemonInfoAdapter.add(pokemonInfoFragment,"Pokemon Information")
        pokemonInfoAdapter.add(pokemonMovesFragment,"Pokemon Moves")
        pokemonInfoAdapter.add(pokemonImageFragment, "Pokemon Image")
        binding.pokemonInfoViewpager.adapter = pokemonInfoAdapter
    }
}