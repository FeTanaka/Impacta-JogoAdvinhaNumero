package br.com.impacta.jogoadvinhanumero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.impacta.jogoadvinhanumero.databinding.FragmentParabensBinding

class ParabensFragment : Fragment() {

    private var _binding: FragmentParabensBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParabensBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button4.setOnClickListener {
            // Voltamos para o fragmento Chute para começarmos uma nova rodada do jogo.
            // Como o fragmento chute tem um valor padrão para o argumento, não precisamos passar nada.
            // Poderiamos passar -1, caso quisessemos.
            val action = ParabensFragmentDirections.actionParabensFragmentToChuteFragment()
            findNavController().navigate(action)
        }
    }

}