package com.stevens.retrorxjava.ux

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stevens.retrorxjava.R
import com.stevens.retrorxjava.databinding.FragmentFirstBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private val compositeDisposable = CompositeDisposable()
    private lateinit var adapter: UserListAdapter
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java).apply {
            compositeDisposable.add(
                usersDataObservable
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { usersData ->
                        adapter = UserListAdapter()
                        adapter.submitList(usersData.users)
                        binding.apply {
                            usersRecyclerView.adapter = adapter
                            usersRecyclerView.layoutManager =
                                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                        }
                    },
            )
            compositeDisposable.add(
                getUsers()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe ({
                        Log.d("@@", "FirstFragment.getUsers()")
                    }, { t ->
                    Log.e("@@", "FirstFragment.getUsers()", t)
                })
            )
        }

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}