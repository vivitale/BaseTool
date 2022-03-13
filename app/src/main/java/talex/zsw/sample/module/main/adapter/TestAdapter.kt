package talex.zsw.sample.module.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import talex.zsw.sample.R
import talex.zsw.sample.test.TestDto

/**
 * 作用: 公告
 * 作者：tale email:vvtale@gmail.com
 */
class TestAdapter : BaseQuickAdapter<TestDto, BaseViewHolder>(R.layout.item_pop_listview)
{
    override fun convert(holder: BaseViewHolder, item: TestDto)
    { //iv_itpop tv_itpop
        holder.setText(R.id.tv_itpop, item.title)
        // GlideTool.loadImg(holder.getView(R.id.iv_itpop), item.image)
    }
}