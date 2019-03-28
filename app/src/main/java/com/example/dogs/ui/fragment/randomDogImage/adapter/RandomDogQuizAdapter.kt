package com.example.dogs.ui.fragment.randomDogImage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dogs.databinding.ItemQuizAnswerBinding
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz

class RandomDogQuizAdapter(private val listener: OnItemClickListener) :
    androidx.recyclerview.widget.RecyclerView.Adapter<RandomDogQuizAdapter.QuizViewHolder>() {

    private val _quizList = ArrayList<RandomImageQuiz>()

    class QuizViewHolder(private val binding: ItemQuizAnswerBinding, private val listener: OnItemClickListener) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        fun bind(randomImageQuiz: RandomImageQuiz) {
            binding.button.text = randomImageQuiz.breed
            binding.button.setOnClickListener { v -> listener.onItemClick(v, randomImageQuiz) }
        }
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) = holder.bind(_quizList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val viewBinding = ItemQuizAnswerBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return QuizViewHolder(viewBinding, listener)
    }

    fun setItems(data: List<RandomImageQuiz>) {
        _quizList.clear()
        _quizList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = _quizList.size

    interface OnItemClickListener {
        fun onItemClick(view: View, randomImageQuiz: RandomImageQuiz)
    }
}