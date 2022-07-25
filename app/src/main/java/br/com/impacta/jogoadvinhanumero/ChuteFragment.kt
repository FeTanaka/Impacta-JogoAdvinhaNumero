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

        // Aqui verificamos se um número randomico já foi gerado
        // identificamos se o número já foi gerado pois o valor do argumento é diferente de -1
        // caso o número não tenha sido gerado, geramos um número inteiro randômico entre 0 e 10.
        val numeroRandomicoGerado = if (args.numeroRandomico == -1) {
            Random.nextInt(0, 10)
        } else {
            args.numeroRandomico
        }
        // Toast para facilitar os testes
//        Toast.makeText(context, "Número a ser acertado: ${numeroRandomicoGerado}", Toast.LENGTH_SHORT).show()


        binding.button.setOnClickListener {
            // Condicional caso o usuário não tenha digitado nada antes de apertar o botão.
            if (binding.editTextNumber.text.toString() == "") {
                binding.editTextNumber.error = "Por favor digite um número."
            } else {
                // pegamos o valor digitado pelo usuário
                val chuteUsuario = binding.editTextNumber.text.toString().toInt()
                // dado o valor digitado verificamos qual ação devemos tomar
                val action = when {
                    chuteUsuario > numeroRandomicoGerado -> ChuteFragmentDirections.actionChuteFragmentToChuteMaiorFragment(numeroRandomicoGerado)
                    chuteUsuario < numeroRandomicoGerado -> ChuteFragmentDirections.actionChuteFragmentToChuteMenorFragment(numeroRandomicoGerado)
                    else -> ChuteFragmentDirections.actionChuteFragmentToParabensFragment()
                }
                // vamos para o fragmento determinado pela ação
                findNavController().navigate(action)
            }
        }
    }

}