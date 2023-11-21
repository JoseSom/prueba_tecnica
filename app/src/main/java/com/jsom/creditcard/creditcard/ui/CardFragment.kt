package com.jsom.creditcard.creditcard.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jsom.creditcard.R
import com.jsom.creditcard.creditcard.adapter.MovementsAdapter
import com.jsom.creditcard.creditcard.data.model.CreditCard
import com.jsom.creditcard.creditcard.data.model.Movement
import com.jsom.creditcard.creditcard.data.model.cardNumber
import com.jsom.creditcard.creditcard.viewmodel.CardViewModel
import com.jsom.creditcard.databinding.FragmentCardBinding
import java.util.Random

class CardFragment : Fragment() {

    companion object {
        fun newInstance() = CardFragment()
    }

    private lateinit var viewModel: CardViewModel
    private lateinit var binding: FragmentCardBinding
    private lateinit var movementsAdapter: MovementsAdapter

    private lateinit var countDownTimer: CountDownTimer
    private var initialCountDown: Long = 300000
    private var countDownInterval: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CardViewModel::class.java]
    }

    private fun setUpObservers() {
        viewModel.creditCardInfo.observe(viewLifecycleOwner) { response ->
            response?.let { showCardView(it) }
        }

        viewModel.movementsList.observe(viewLifecycleOwner) { response ->
            response?.let { showMovements(it) }
        }
    }

    private fun showMovements(movements: List<Movement>) {
        movementsAdapter = MovementsAdapter(movements)
        binding.rvMovementsList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = movementsAdapter
        }
    }

    private fun showCardView(card: CreditCard) {
        binding.expirationDate.text = card.expirationDate

        val formatName = CreditCard.formatName(card.cardHolder)
        binding.cardHolder.text = formatName

        val cardNumber = CreditCard.splitCardNumber(card.cardNumber)
        if (cardNumber.size == 4){
            binding.firstNumberBlock.text = cardNumber[0]
            binding.secondNumberBlock.text = cardNumber[1]
            binding.thirdNumberBlock.text = cardNumber[2]
            binding.fourthNumberBlock.text = cardNumber[3]
        }

        binding.cvv.setOnClickListener {
            generateCoutnDown()

            val random = Random()
            val cvv = String.format("%03d", random.nextInt(1000))
            binding.cvv.text = cvv

            countDownTimer.start()
        }
    }

    private fun generateCoutnDown() {
        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                val timeLeft = String.format("%02d:%02d", minutes, seconds)
                binding.cvv.text = timeLeft
            }

            override fun onFinish() {
                binding.cvv.text = getString(R.string.generate_cvv)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        setUpObservers()
        viewModel.getCardInfo()
        viewModel.getMovements()
        return binding.root
    }

}