package com.siuzannasmolianinova.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siuzannasmolianinova.calculator.databinding.CalculatorFragmentBinding
import timber.log.Timber

class CalculatorFragment : Fragment() {
    private var _binding: CalculatorFragmentBinding? = null
    private val binding: CalculatorFragmentBinding get() = _binding!!
    private var expression: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CalculatorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run{
            one.setOnClickListener { clickOnDigit('1') }
            two.setOnClickListener { clickOnDigit('2') }
            three.setOnClickListener { clickOnDigit('3') }
            four.setOnClickListener { clickOnDigit('4') }
            five.setOnClickListener { clickOnDigit('5') }
            six.setOnClickListener { clickOnDigit('6') }
            seven.setOnClickListener { clickOnDigit('7') }
            eight.setOnClickListener { clickOnDigit('8') }
            nine.setOnClickListener { clickOnDigit('9') }
            zero.setOnClickListener { clickOnDigit('0') }
            dot.setOnClickListener { clickOnDigit('.') }
            plus.setOnClickListener { clickOnOperator("+") }
            minus.setOnClickListener { clickOnOperator("-") }
            multiply.setOnClickListener { clickOnOperator("*") }
            divide.setOnClickListener { clickOnOperator(":") }
            percent.setOnClickListener { clickOnOperator("%") }
            negativeButton.setOnClickListener { clickOnNegative() }
            equals.setOnClickListener { clickOnEquals() }
            clear.setOnClickListener { clickOnClear() }
            delete.setOnClickListener { clickOnDel() }
            offButton.setOnClickListener { off() }
        }
    }

    private fun clickOnDigit(data: Char){
        expression += data
        binding.editField.text = expression
    }

    private fun clickOnOperator(operator: String){
        val operators = arrayOf('+', '-', ':', '*')
        if(expression.isEmpty()) return
        if (expression.length > 2){
            Timber.d(expression[expression.length - 2].toString())
        }
        if (expression.length > 2 && operators.contains(expression[expression.length - 2])) return
        expression += " $operator "
        binding.editField.text = expression
    }

    private fun clickOnNegative(){
        val numbers = arrayOf('1', '2', '3', '4', '5', '6', '7', '8', '9', '0' )
        if (expression.length > 2 && numbers.contains(expression[expression.length - 1])) return
        expression += "-"
        binding.editField.text = expression
    }

    private fun clickOnEquals(){
        val list = expression.map()
        equation(list)
    }

    private fun equation(list: List<String>){
        var current = list[0].toDouble()
        for(i in list.indices) {
            if (list.isNotEmpty()) {
                when (list[i]) {
                    "+" -> {
                        Timber.d("current = $current")
                        Timber.d("plus")
                        val next = list[i+1].toDouble()
                        Timber.d("next = $next")
                        current += next
                        Timber.d (" equals = $current")
                        continue
                    }
                    "-" -> {
                        Timber.d("current = $current")
                        Timber.d("minus")
                        val next = list[i+1].toDouble()
                        Timber.d("next = $next")
                        current -= next
                        Timber.d (" equals = $current")
                        continue
                    }
                    "*" -> {
                        Timber.d("current = $current")
                        Timber.d("multiply")
                        val next = list[i+1].toDouble()
                        Timber.d("next = $next")
                        current *= next
                        Timber.d(" equals = $current")
                        continue
                    }
                    ":" -> {
                        Timber.d("current = $current")
                        Timber.d("divide")
                        val next = list[i+1].toDouble()
                        Timber.d("next = $next")
                        current /= next
                        Timber.d(" equals = $current")
                        continue
                    }
                    "%" -> {
                        Timber.d("current = $current")
                        current /= 100
                        Timber.d(" equals = $current")
                        continue
                    }
                    else -> binding.resultTextField.text = current.toString()
                    }
                }
            }
        binding.resultTextField.text = current.toString()
    }

    private fun clickOnDel(){
        if(expression.isNotEmpty()) {
            expression = expression.dropLast(1)
        }
        binding.editField.text = expression
    }

    private fun clickOnClear(){
        expression = ""
        binding.editField.text = expression
    }

    private fun off(){
        (activity as MainActivity).finish()
    }
}