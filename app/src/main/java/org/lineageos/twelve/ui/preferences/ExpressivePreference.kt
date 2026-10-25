/*
 * SPDX-FileCopyrightText: The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.twelve.ui.preferences

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.google.android.material.card.MaterialCardView
import org.lineageos.twelve.R

open class ExpressivePreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = androidx.preference.R.attr.preferenceStyle,
    defStyleRes: Int = 0,
) : Preference(context, attrs, defStyleAttr, defStyleRes) {

    var cardPosition: CardPosition = CardPosition.SINGLE
    var badgeContainerColorRes: Int = R.color.badge_indigo_container
    var badgeIconColorRes: Int = R.color.badge_indigo_icon
    var showChevron: Boolean = true

    init {
        layoutResource = R.layout.preference_expressive
        isIconSpaceReserved = true
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        holder.itemView.background = SegmentedCardHelper.getSegmentedCardBackground(context, cardPosition)

        val iconCardView = holder.findViewById(R.id.iconCardView) as? MaterialCardView
        iconCardView?.setCardBackgroundColor(ContextCompat.getColor(context, badgeContainerColorRes))

        val iconImageView = holder.findViewById(android.R.id.icon) as? ImageView
        iconImageView?.imageTintList = ColorStateList.valueOf(
            ContextCompat.getColor(context, badgeIconColorRes)
        )

        val actionChevron = holder.findViewById(R.id.actionChevronImageView) as? ImageView
        actionChevron?.isVisible = showChevron
    }
}
