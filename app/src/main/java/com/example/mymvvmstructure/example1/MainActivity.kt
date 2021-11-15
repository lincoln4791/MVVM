package com.example.mymvvmstructure.example1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymvvmstructure.example1.Adapters.PostsAdapter
import com.example.mymvvmstructure.example1.ViewModel.PostsViewModel
import com.example.mymvvmstructure.databinding.ActivityMainBinding
import com.example.mymvvmstructure.example1.modelClasses.Status
import com.example.mymvvmstructure.example1.modelClasses.User
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:PostsViewModel
    private lateinit var repository: SearchRepository
    private lateinit var mAdapter:PostsAdapter

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialization()
        setUpRecyclerView()


        val db = DatabaseHelper(this)
        binding.btnInsert.setOnClickListener {
            if (binding.editTextName.text.toString().isNotEmpty() &&
                binding.editTextAge.text.toString().isNotEmpty()
            ) {
                val user = User(binding.editTextName.text.toString(), binding.editTextAge.text.toString())
                db.insertData(user)
                //clearField()
            }
            else {
                Toast.makeText(this, "Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnRead.setOnClickListener {
            val data = db.readData()
            binding.tvResult.text = ""
            for (i in 0 until data.size) {
                binding.tvResult.append(
                    data[i].name + " " + data[i].age + "\n"
                )
            }

            lifecycleScope.launch {
                setUpObservers()
            }

        }

    }

    private fun initialization() {
        viewModel = ViewModelProvider(this).get(PostsViewModel::class.java)
        repository = SearchRepository(this)
        mAdapter = PostsAdapter()
    }


    private suspend fun setUpObservers() {
        viewModel.postsSearch(repository).observe(this@MainActivity, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (it.data?.size!! > 0) {
                            //progress_bar.visibility = View.GONE
                            //binding.recyclerView.visibility = View.VISIBLE
                            //tv_result_failure.visibility = View.GONE

                            mAdapter.differ.submitList(it.data)
                        } else {
                            binding.tvResult.text = "No Result Found"
                           /* progress_bar.visibility = View.GONE
                            rv_search_items.visibility = View.GONE
                            tv_result_failure.visibility = View.VISIBLE
                            tv_result_failure.text = if (language) getString(R.string.no_result_english) else getString(R.string.no_result_bangla)*/
                        }

                    }
                    Status.LOADING -> {
                        /*progress_bar.visibility = View.VISIBLE
                        rv_search_items.visibility = View.GONE
                        tv_result_failure.visibility = View.GONE*/
                    }
                    Status.ERROR -> {
                       /* progress_bar.visibility = View.GONE
                        rv_search_items.visibility = View.GONE
                        tv_result_failure.visibility = View.VISIBLE
                        tv_result_failure.text = it.error*/
                    }
                }
            }
        })
    }


    private fun setUpRecyclerView() {

        mAdapter.setHasStableIds(true)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        mAdapter.setOnItemClickListener {data->
            Toast.makeText(this,"Clicked ${data.name}",Toast.LENGTH_SHORT).show()
        }

    }

}