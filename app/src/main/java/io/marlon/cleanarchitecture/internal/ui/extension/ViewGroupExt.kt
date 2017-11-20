package io.marlon.cleanarchitecture.internal.ui.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Extensões para a classe ViewGroup que visam facilitar seu uso.
 */

/**
 * Adiciona o método "inflate" à classe ViewGroup para facilitar
 * o inflate de views a partir de adapters.
 */
fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)