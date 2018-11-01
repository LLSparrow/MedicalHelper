package com.oldsenior.ella.medicinelist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oldsenior.ella.corelib.util.autoCleared
import com.oldsenior.ella.corelib.view.BaseFragment
import com.oldsenior.ella.corelib.view.viewmodel.observe
import com.oldsenior.ella.corelib.view.viewmodel.withViewModel

class MedicalListMenuFragment : BaseFragment() {

    lateinit var recycler: androidx.recyclerview.widget.RecyclerView.Recycler

    //private var binding by autoCleared<MedicalListMenuFragmentBinding>()
    private var adapter by autoCleared<MedicalListAdapter>()

    var dataBindingComponent: androidx.databinding.DataBindingComponent = ImageDataBindingComponent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        withViewModel<MedicalListViewModel>(viewModelFactory) {
            observe(medList) {
                if (medList.value != null) {
                    adapter.submitList(medList.value)
                } else {
                    adapter.submitList(emptyList())
                }
            }
        }

        adapter = MedicalListAdapter(dataBindingComponent, appExecutors) { medication ->
            //            findNavController().navigate(
//                    RepoFragmentDirections.showUser(contributor.login)

        }

        this.adapter = adapter
       // binding.contributorList.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val dataBinding = DataBindingUtil.inflate<ScriptGroup.Binding>()(
//                layoutInflater,
//                R.layout.fragment_medical_list_menu,
//                container,
//                false)
//
//        binding = dataBinding
//        return dataBinding.root
        return null
    }
}