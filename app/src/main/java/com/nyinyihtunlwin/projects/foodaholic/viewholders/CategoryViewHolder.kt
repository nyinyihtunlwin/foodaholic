package com.nyinyihtunlwin.projects.foodaholic.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.projects.foodaholic.delegates.CategoryDelegate
import com.nyinyihtunlwin.projects.foodaholic.mvvm.models.CategoryModel
import com.nyinyihtunlwin.projects.sharedmodule.ui.BaseViewHolder
import kotlinx.android.synthetic.main.view_item_category.view.*

class CategoryViewHolder(
    itemView: View,
    var categoryDelegate: CategoryDelegate
) : BaseViewHolder<CategoryModel>(itemView) {

    override fun setData(data: CategoryModel) {
        mData = data
        if (mData != null) {
            itemView.tv_title.text = mData!!.strCategory
            Glide.with(itemView.iv_category.context)
                .load(mData!!.strCategoryThumb)
                .into(itemView.iv_category)
            itemView.tv_description.text = mData!!.strCategoryDescription
        }
    }

    override fun onClick(v: View?) {
        categoryDelegate.onTapCategory(mData!!)
    }
}