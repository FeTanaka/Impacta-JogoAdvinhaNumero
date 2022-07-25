package br.com.impacta.jogoadvinhanumero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.impacta.jogoadvinhanumero.databinding.FragmentChuteBinding
import kotlin.random.Random


class ChuteFragment : Fragment() {

    private var _binding: FragmentChuteBinding? = null
    private val binding get() = _binding!!
    private val args: ChuteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChuteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numeroRandomicoGerado = if (args.numeroRandomico == -1) {
            Random.nextInt(0, 10)
        } else {
            args.numeroRandomico
        }
        Toast.makeText(context, "Número a ser acertado: ${numeroRandomicoGerado}", Toast.LENGTH_SHORT).show()

        binding.button.setOnClickListener {
            if (binding.editTextNumber.text.toString() == "") {
                binding.editTextNumber.error = "Por favor digite um número."
            } else {
                val chuteUsuario = binding.editTextNumber.text.toString().toInt()
                val action = when {
                    chuteUsuario > numeroRandomicoGerado -> ChuteFragmentDirections.actionChuteFragmentToChuteMaiorFragment(numeroRandomicoGerado)
                    chuteUsuario < numeroRandomicoGerado -> ChuteFragmentDirections.actionChuteFragmentToChuteMenorFragment(numeroRandomicoGerado)
                    else -> ChuteFragmentDirections.actionChuteFragmentToParabensFragment()
                }
                findNavController().navigate(action)
            }
        }
    }

}