package pl.btwarog.core_ui.presentation.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

interface BaseViewModelFactory<V : ViewModel> {

	fun create(handle: SavedStateHandle): V
}