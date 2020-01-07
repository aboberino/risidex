package com.issou.risidex.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.issou.risidex.R
import com.issou.risidex.classe.Pokemon

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var pokedex: List<Pokemon>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataset()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewModel =
            ViewModelProviders.of(this).get(ListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_list, container, false)

        viewManager = LinearLayoutManager(root.context)
        viewAdapter = ListAdapter(pokedex)

        recyclerView = root.findViewById<RecyclerView>(R.id.list_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        return root
    }

    //Initialise les données du json
    private fun initDataset() {
        val mapper = ObjectMapper().registerKotlinModule()

        //Transforme le json en string
        val json = resources.openRawResource(R.raw.risidex).bufferedReader().use { it.readText() }

        //Map les données du json en une liste d'objet pokemon
        pokedex = mapper.readValue(json)
    }
}