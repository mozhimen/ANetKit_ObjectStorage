package com.mozhimen.netk.objectstorage.cos

import android.content.Context
import com.mozhimen.basick.utilk.bases.BaseUtilK
import com.mozhimen.basick.utilk.java.util.UtilKDate
import com.mozhimen.basick.utilk.java.util.date2longDate
import com.mozhimen.netk.objectstorage.os.commons.IOSProxy
import com.tencent.cos.xml.CosXmlService
import com.tencent.cos.xml.CosXmlServiceConfig
import com.tencent.cos.xml.exception.CosXmlClientException
import com.tencent.cos.xml.model.PresignedUrlRequest
import com.tencent.qcloud.core.http.RequestBodySerializer
import java.text.SimpleDateFormat
import java.util.TimeZone


/**
 * @ClassName COSProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/12/21
 * @Version 1.0
 */
class COSProxy : BaseUtilK(), IOSProxy {
    private var _cosXmlService: CosXmlService? = null
    private val _simpleDateFormat: SimpleDateFormat by lazy {
        UtilKDate.getSdf1("yyyy-MM-dd'T'HH:mm:ssZ").apply { timeZone = TimeZone.getTimeZone("UTC") }
    }

    //////////////////////////////////////////////////////////////////////////

    override fun strTimeExpiration2longTimeExpiration(strDate: String): Long? =
        try {
            _simpleDateFormat.parse(strDate)?.date2longDate()
        } catch (e: Exception) {
            null
        }

    fun getService(): CosXmlService? =
        _cosXmlService

    fun initService(region: String, iFetchCredentialsListener: IFetchCredentialsListener): CosXmlService {
        // 存储桶region可以在COS控制台指定存储桶的概览页查看 https://console.cloud.tencent.com/cos5/bucket/ ，关于地域的详情见 https://cloud.tencent.com/document/product/436/6224
        //val region = "ap-guangzhou"
        val serviceConfig = CosXmlServiceConfig.Builder()
            .setRegion(region)
            .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
            .builder()
        return CosXmlService(_context, serviceConfig, COSServerCredentialProvider(iFetchCredentialsListener)).also { _cosXmlService = it }
    }

    //////////////////////////////////////////////////////////////////////////

//    private fun initService() {
//        // 存储桶region可以在COS控制台指定存储桶的概览页查看 https://console.cloud.tencent.com/cos5/bucket/ ，关于地域的详情见 https://cloud.tencent.com/document/product/436/6224
//        val region = "ap-guangzhou"
//        val serviceConfig = CosXmlServiceConfig.Builder()
//            .setRegion(region)
//            .isHttps(true) // 使用 HTTPS 请求，默认为 HTTP 请求
//            .builder()
//        context = InstrumentationRegistry.getInstrumentation().getTargetContext()
//        cosXmlService = CosXmlService(
//            context, serviceConfig,
//            ServerCredentialProvider()
//        )
//    }
//
//    @Test
//    fun testObjectPresignUrl() {
//        initService()
//
//        // 获取预签名下载链接
//        getPresignDownloadUrl()
//
//        // 获取预签名上传链接
//        getPresignUploadUrl()
//        // .cssg-methods-pragma
//    }
}