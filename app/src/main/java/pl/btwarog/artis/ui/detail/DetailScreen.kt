package pl.btwarog.artis.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import pl.btwarog.artis.R
import pl.btwarog.artis.appComponent
import pl.btwarog.artis.databinding.ScreenDetailBinding
import pl.btwarog.artis.ui.utils.loadUrlImage
import pl.btwarog.brainz.domain.model.ArtistDetailInfo
import pl.btwarog.core_ui.presentation.ui.BaseViewModelFragment

class DetailScreen :
	BaseViewModelFragment<ScreenDetailBinding, DetailScreenState, DetailScreenAction, DetailViewModel>(R.layout.screen_detail) {

	override fun inject() {
		appComponent.inject(this)
	}

	override val viewModel: DetailViewModel by viewModels()

	override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
		ScreenDetailBinding.inflate(inflater, container, false)

	override fun initView(savedInstanceState: Bundle?) {
		binding.detailToolbar.setupWithNavController(findNavController())
		binding.detailErrorView.retryButton.setOnClickListener {
			viewModel.loadArtist()
		}
	}

	override fun onScreenStateReceived(screenState: DetailScreenState?) {
		when (screenState) {
			is DetailScreenState.DataLoaded -> {
				binding.detailViewFlipper.displayedChild = 0
				setViewWithData(screenState.artistDetailInfo)
			}
			is DetailScreenState.DataError -> {
				binding.detailViewFlipper.displayedChild = 2
				binding.detailErrorView.errorMessage.text =
					getString(if (screenState.networkError) R.string.common_network_issue else R.string.common_general_issue)
			}
			DetailScreenState.DataLoading -> {
				binding.detailViewFlipper.displayedChild = 1
			}
		}
	}

	private fun setViewWithData(artistDetailInfo: ArtistDetailInfo) {
		with(binding.detailContent) {
			detailImage.loadUrlImage(artistDetailInfo.getArtistImageUrl())
			detailNameValue.text = artistDetailInfo.name
			detailDisambiguationValue.text = artistDetailInfo.disambiguation
			detailRealNameValue.text = artistDetailInfo.realName
			detailProfileValue.text = artistDetailInfo.profile
			detailGenderValue.text = artistDetailInfo.gender
			detailTypeValue.text = artistDetailInfo.type
			detailCountryValue.text = artistDetailInfo.country
			detailRatingValue.text = artistDetailInfo.rating.toString()
		}
	}

	override fun onScreenActionReceived(screenAction: DetailScreenAction) {}
}