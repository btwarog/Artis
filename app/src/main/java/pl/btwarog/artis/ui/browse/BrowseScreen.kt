package pl.btwarog.artis.ui.browse

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import pl.btwarog.artis.R
import pl.btwarog.artis.ui.ContentActivity

class BrowseScreen: Fragment(R.layout.screen_browse) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.findViewById<View>(R.id.detailActionBtn).setOnClickListener {
			(requireActivity() as ContentActivity).getActivityNavController().navigate(R.id.action_bottomMenu_to_detail)
		}
	}
}