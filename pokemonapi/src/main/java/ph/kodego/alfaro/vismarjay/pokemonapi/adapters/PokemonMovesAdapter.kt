package ph.kodego.alfaro.vismarjay.pokemonapi.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.kodego.alfaro.vismarjay.pokemonapi.databinding.ItemPokemonMovesBinding
import ph.kodego.alfaro.vismarjay.pokemonapi.models.PokemonMoveInfo

class PokemonMovesAdapter(private val context: Context,
                          private var pokemonMoves: ArrayList<PokemonMoveInfo>)
    : RecyclerView.Adapter<PokemonMovesAdapter.ViewHolder>() {

    override fun onCreateViewHolder (parent: ViewGroup, viewType:Int): PokemonMovesAdapter.ViewHolder {
        val itemBinding = ItemPokemonMovesBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return PokemonMovesAdapter.ViewHolder(itemBinding)
    }

    override fun getItemCount() = pokemonMoves.size

    override fun onBindViewHolder(holder: PokemonMovesAdapter.ViewHolder,
                                  position: Int){
        holder.bindItems(pokemonMoves[position])

    }

    fun setList(pokemonMovesList: ArrayList<PokemonMoveInfo>){
        this.pokemonMoves.clear()
        this.pokemonMoves.addAll(pokemonMovesList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ItemPokemonMovesBinding):
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItems(pokemonMoves: PokemonMoveInfo){
            itemBinding.pokemonMoveName.text = pokemonMoves.move.name
        }
    }


}