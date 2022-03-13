package talex.zsw.sample.module.main.ui

import android.content.Intent
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.github.florent37.viewanimator.ViewAnimator
import io.reactivex.Observer
import kotlinx.android.synthetic.main.activity_main.*
import talex.zsw.basecore.model.ActionItem
import talex.zsw.basecore.util.BroadcastTool
import talex.zsw.basecore.util.LogTool
import talex.zsw.basecore.util.PhotoTool
import talex.zsw.basecore.util.TimeTool
import talex.zsw.basecore.util.glide.GlideTool
import talex.zsw.basecore.view.dialog.rxdialog.RxDialogChooseImage
import talex.zsw.basecore.view.dialog.rxdialog.RxDialogList
import talex.zsw.basecore.view.other.slidedatetimepicker.SlideDateTimeListener
import talex.zsw.basecore.view.other.slidedatetimepicker.SlideDateTimePicker
import talex.zsw.basecore.view.popupwindow.PopLayout
import talex.zsw.basecore.view.popupwindow.PopListView
import talex.zsw.basecore.view.recyleview.SampleFooter
import talex.zsw.basecore.view.recyleview.SampleHeader
import talex.zsw.sample.R
import talex.zsw.sample.base.BaseMVPActivity
import talex.zsw.sample.module.common.ui.WebActivity
import talex.zsw.sample.module.main.adapter.TestAdapter
import talex.zsw.sample.module.main.contract.MainContract
import talex.zsw.sample.module.main.presenter.MainPresenter
import talex.zsw.sample.test.TestData
import java.util.*

/**
 * 作用：首页
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
class MainActivity : BaseMVPActivity<MainContract.Presenter>(), MainContract.View
{
    private var popListView: PopListView? = null
    private var popLayout: PopLayout? = null

    override fun initArgs(intent: Intent?)
    {
    }

    override fun initView()
    {
        setContentView(R.layout.activity_main)
        mPresenter = MainPresenter(this)
    }

    override fun initData()
    {
        val url = "http://img4.imgtn.bdimg.com/it/u=3399090562,3531159538&fm=26&gp=0.jpg"
        GlideTool.loadImgRoundedCornersLeft(mImageView, url, 20)

        val list: ArrayList<String> = arrayListOf()
        list.add("1111")
        list.add("2222")
        list.add("3333")
        list.add("4444")
        list.add("4444")
        list.add("4444")
        list.add("4444")
        list.add("4444")
        mNiceSpinner.attachDataSource(list)

        BroadcastTool.initRegisterReceiverNetWork(this)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        if (rxChooseImg!=null)
        {
            rxChooseImg?.dismiss()
            rxChooseImg = null
        }
    }

    @OnClick(R.id.mBtn1, R.id.mBtn2, R.id.mBtn3, R.id.mBtn4, R.id.mBtn5, R.id.mBtn6)
    fun onViewClicked(view: View)
    {
        when (view.id)
        {
            R.id.mBtn1 ->
            {
                if (popLayout == null)
                {
                    popLayout = PopLayout(this@MainActivity, "厉害了")
                }
                else
                {
                    popLayout =
                            PopLayout(this@MainActivity,
                                      ViewGroup.LayoutParams.WRAP_CONTENT,
                                      ViewGroup.LayoutParams.WRAP_CONTENT,
                                      R.layout.activity_main)
                }
                popLayout?.show(mBtn1)
                ViewAnimator.animate(mBtn1)
                        .scale(1f,5f,1f)
                        .thenAnimate(mBtn1)
                        .rotation(0f,360f)
                        .duration(3*1000)
                        .start()
            }
            R.id.mBtn2 ->
            {
                initPopupView()
                popListView?.show(mBtn2, 0)
            }
            R.id.mBtn3 ->
            {
                SlideDateTimePicker.Builder(supportFragmentManager)
                        .setListener(object : SlideDateTimeListener()
                                     {
                                         override fun onDateTimeSet(date: Date?)
                                         {
                                         }
                                     })
                        .setInitialDate(Date())
                        .setMinDate(Date())
                        .setMaxDate(Date())
                        .setThemeColor(Color.parseColor("#00FF00"))
                        .setTitleColor(Color.parseColor("#FF0000"))
                        .setShowTime(true)
                        .setShowDay(true)
                        .build()
                        .show()
            }
            R.id.mBtn4 ->
            {
                //                val body = BaseModel()
                //                body.key = "26802ee608152"
                //                body.city = "杭州"
                //                mPresenter.getData(HttpDto(Constant.WEATHER, body).setType(HttpDto.GET))
                //                uploadLog()
                showRxChooseImg()
                start(WebActivity::class.java)
            }
            R.id.mBtn5 ->
            {
                Integer.parseInt("abc")
            }
            R.id.mBtn6 ->
            {
                listDialog
                        ?: let {
                            listDialog = RxDialogList(this@MainActivity)
                            listDialog?.setAdapter(adapter)
                            adapter.setNewInstance(TestData.getGoods(20))
                            adapter.addHeaderView(SampleHeader(this@MainActivity))
                            adapter.addFooterView(SampleFooter(this@MainActivity))
                        }
                listDialog?.show()
            }
        }
    }

    private var listDialog: RxDialogList? = null
    private val adapter = TestAdapter()

    private fun initPopupView()
    {
        popListView =
                PopListView(this@MainActivity,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT)
        popListView?.addAction(ActionItem("标清"))
        popListView?.addAction(ActionItem("高清"))
        popListView?.addAction(ActionItem("超清"))
        popListView?.setItemOnClickListener { _, position ->
            LogTool.d(popListView?.getAction(position).toString())
        }
    }

    override fun bindWeatherStr(json: String)
    {
        mTvInfo.text = TimeTool.getCurTimeString()
        mTvInfo.append("\n$json")
    }

    // --------------- 选择图片 ---------------
    private var rxChooseImg: RxDialogChooseImage? = null

    private fun showRxChooseImg()
    {
        rxChooseImg
                ?: let {
                    rxChooseImg = RxDialogChooseImage(this@MainActivity)
                }
        rxChooseImg?.showWithPermission()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        when (requestCode)
        {
            PhotoTool.GET_IMAGE_FROM_PHONE ->
            { //选择相册之后的处理
                if (resultCode == RESULT_OK)
                {
                    PhotoTool.cropImage(this@MainActivity, data?.data, 1, 1, 250, 250) // 裁剪图片
                }
            }
            PhotoTool.GET_IMAGE_BY_CAMERA  ->
            { //选择相册之后的处理
                if (resultCode == RESULT_OK)
                {
                    PhotoTool.cropImage(this@MainActivity,
                                        PhotoTool.imageUriFromCamera,
                                        1,
                                        1,
                                        250,
                                        250) // 裁剪图片
                }
            }
            PhotoTool.CROP_IMAGE           ->
            { // 裁剪完成以后
                Glide.with(this@MainActivity)
                        .load(PhotoTool.cropImageUri)
                        .into(mImageView)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


}